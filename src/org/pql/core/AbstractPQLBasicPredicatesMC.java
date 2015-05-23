package org.pql.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jbpt.algo.graph.TransitiveClosure;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.jbpt.petri.Transition;
import org.jbpt.petri.unfolding.AbstractCompletePrefixUnfolding;
import org.jbpt.petri.unfolding.CompletePrefixUnfoldingSetup;
import org.jbpt.petri.unfolding.Event;
import org.jbpt.petri.unfolding.IEvent;
import org.jbpt.petri.unfolding.ILocalConfiguration;
import org.jbpt.petri.unfolding.IOccurrenceNet;
import org.jbpt.petri.unfolding.order.AdequateOrderType;
import org.jbpt.utils.IOUtils;
import org.pql.logic.IThreeValuedLogic;
import org.pql.logic.ThreeValuedLogicValue;
import org.pql.mc.IModelChecker;

/**
 * @author Artem Polyvyanyy
 */
public class AbstractPQLBasicPredicatesMC<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>>
	implements IPQLBasicPredicatesOnTransitions<F,N,P,T,M>, 
			   IPQLBasicPredicatesOnLabels,
			   IPQLBasicPredicatesOnTasks {
	
	private INetSystem<F,N,P,T,M>	 netSystem	  = null;
	private IModelChecker<F,N,P,T,M> modelChecker = null;
	private IThreeValuedLogic		 logic		  = null;
	
	private P o = null;
	
	private Set<String> labels1 = null;
	private Set<String> labels2 = null;
	
	private T t1 = null;
	private T t2 = null;
	private Set<T> ts1 = null;
	private Set<T> ts2 = null;
	
	private Set<N> toRemove = null;
	
	protected class TransformationInfo {
		T t1 = null;
		T t2 = null;
		Set<T> ts1 = null;
		Set<T> ts2 = null;
	}
	
	protected class PrecedenceTestInfo {
		T t1 = null;
		T t2 = null;
		P p = null;
		P q = null;
		P r = null;
	}

	public AbstractPQLBasicPredicatesMC(IModelChecker<F,N,P,T,M> modelChecker, IThreeValuedLogic logic) {
		this.modelChecker = modelChecker;
		this.logic = logic;
		
		this.labels1 = new HashSet<String>();
		this.labels2 = new HashSet<String>();
		
		this.toRemove = new HashSet<N>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void configure(Object param) {
		if (param == null) return;
		
		if (this.netSystem!=null)
			this.netSystem.removeNodes(this.toRemove);
		
		this.toRemove.clear();
		
		this.netSystem = (INetSystem<F,N,P,T,M>) param;
		this.o = this.netSystem.getSinkPlaces().iterator().next();
		
		this.labels1.clear();
		this.labels2.clear();
		this.t1 = null;
		this.t2 = null;
	}
	
	// CAN OCCUR
		
	@Override
	public ThreeValuedLogicValue canOccur(T t) {
		if (this.netSystem==null) return ThreeValuedLogicValue.UNKNOWN;
		if (!this.netSystem.getTransitions().contains(t)) return ThreeValuedLogicValue.UNKNOWN;

		boolean result = this.modelChecker.canReachMarkingWithAtLeastOneTokenAtEachPlace(this.netSystem, this.netSystem.getPreset(t));

		return result ? ThreeValuedLogicValue.TRUE : ThreeValuedLogicValue.FALSE;
	}
	
	private ThreeValuedLogicValue canOccur(Set<String> labels) {
		if (this.netSystem==null) return ThreeValuedLogicValue.UNKNOWN;		
		T t = prepareNetSystem(labels);
		return this.canOccur(t);
	}
	
	@Override
	public ThreeValuedLogicValue canOccur(PQLTask task) {
		return this.canOccur(task.getSimilarLabels());
	}
	
	@Override
	public ThreeValuedLogicValue canOccur(String label) {
		Set<String> labels = new HashSet<String>();
		labels.add(label);
		return this.canOccur(labels);
	}
	
	// ALWAYS OCCURS
	
	@Override
	public ThreeValuedLogicValue alwaysOccurs(T t) {
		if (this.netSystem==null) return ThreeValuedLogicValue.UNKNOWN;
		if (!this.netSystem.getTransitions().contains(t)) return ThreeValuedLogicValue.UNKNOWN;
		
		P p = this.netSystem.createPlace();
		p.setName("TEMP");
		this.netSystem.addFlow(p,t);
		this.netSystem.putTokens(p,1);
		
		Collection<P> marking = new ArrayList<P>();
		marking.add(p);
		marking.add(this.o);
		
		boolean result = this.modelChecker.isReachable(this.netSystem,marking);
		
		this.netSystem.putTokens(p,0);
		this.netSystem.removePlace(p);
		
		return result ? ThreeValuedLogicValue.FALSE : ThreeValuedLogicValue.TRUE;
	}
	
	private ThreeValuedLogicValue alwaysOccurs(Set<String> labels) {
		if (this.netSystem==null) return ThreeValuedLogicValue.UNKNOWN;		
		T t = prepareNetSystem(labels);
		return this.alwaysOccurs(t);
	}
	
	@Override
	public ThreeValuedLogicValue alwaysOccurs(PQLTask task) {
		return this.alwaysOccurs(task.getSimilarLabels());
	}
	
	@Override
	public ThreeValuedLogicValue alwaysOccurs(String label) {
		Set<String> labels = new HashSet<String>();
		labels.add(label);
		return this.alwaysOccurs(labels);
	}
	
	// CAN CONFLICT
	
	@Override
	public ThreeValuedLogicValue canConflict(T t1, T t2) {
		if (this.netSystem==null) return ThreeValuedLogicValue.UNKNOWN;
		if (!this.netSystem.getTransitions().contains(t1)) return ThreeValuedLogicValue.UNKNOWN;
		if (!this.netSystem.getTransitions().contains(t2)) return ThreeValuedLogicValue.UNKNOWN;
		
		if (t1.equals(t2)) return ThreeValuedLogicValue.FALSE;
				
		P p = this.netSystem.createPlace();
		P q = this.netSystem.createPlace();
		P pp = this.netSystem.createPlace();
		P qq = this.netSystem.createPlace();
		p.setName("p"+Math.abs(p.hashCode()));
		q.setName("p"+Math.abs(q.hashCode()));
		pp.setName("p"+Math.abs(pp.hashCode()));
		qq.setName("p"+Math.abs(qq.hashCode()));
		
		T tt1 = this.netSystem.createTransition();
		T tt2 = this.netSystem.createTransition();
		tt1.setName("t"+Math.abs(tt1.hashCode()));
		tt2.setName("t"+Math.abs(tt2.hashCode()));
		
		for (P x : this.netSystem.getPreset(t1)) this.netSystem.addFlow(x,tt1);
		for (P x : this.netSystem.getPostset(t1)) this.netSystem.addFlow(tt1,x);
		for (P x : this.netSystem.getPreset(t2)) this.netSystem.addFlow(x,tt2);
		for (P x : this.netSystem.getPostset(t2)) this.netSystem.addFlow(tt2,x);
		
		this.netSystem.addFlow(p,tt1);
		this.netSystem.addFlow(tt1,pp);
		this.netSystem.addFlow(pp,t1);
		this.netSystem.addFlow(t1,pp);
		
		this.netSystem.addFlow(q,tt2);
		this.netSystem.addFlow(tt2,qq);
		this.netSystem.addFlow(qq,t2);
		this.netSystem.addFlow(t2,qq);
		
		this.netSystem.putTokens(p,1);
		this.netSystem.putTokens(q,1);
		
		Collection<P> marking = new ArrayList<P>();
		marking.add(pp);
		marking.add(q);
		marking.add(this.o);
		
		boolean result = this.modelChecker.isReachable(this.netSystem,marking);
		
		this.netSystem.putTokens(p,0);
		this.netSystem.putTokens(pp,0);
		this.netSystem.putTokens(q,0);
		this.netSystem.putTokens(qq,0);
		this.netSystem.removePlace(p);
		this.netSystem.removePlace(q);
		this.netSystem.removePlace(pp);
		this.netSystem.removePlace(qq);
		
		this.netSystem.removeTransition(tt1);
		this.netSystem.removeTransition(tt2);
		
		return result ? ThreeValuedLogicValue.TRUE : ThreeValuedLogicValue.FALSE;
	}
	
	private ThreeValuedLogicValue canConflict(Set<String> labelsA, Set<String> labelsB) {
		if (this.netSystem==null) return ThreeValuedLogicValue.UNKNOWN;		
		
		TransformationInfo info = this.prepareNetSystem(labelsA,labelsB);
		
		if (info==null) return ThreeValuedLogicValue.UNKNOWN;
		if (info.t1==null || info.t2==null) return ThreeValuedLogicValue.UNKNOWN; 
		
		// self-relation
		if (info.ts1.equals(info.ts2)) return ThreeValuedLogicValue.FALSE;
		
		return this.canConflict(info.t1,info.t2);
	}
	
	@Override
	public ThreeValuedLogicValue canConflict(PQLTask taskA, PQLTask taskB) {
		return this.canConflict(taskA.getSimilarLabels(),taskB.getSimilarLabels());
	}
	
	@Override
	public ThreeValuedLogicValue canConflict(String labelA, String labelB) {
		Set<String> labelsA = new HashSet<String>();
		labelsA.add(labelA);
		Set<String> labelsB = new HashSet<String>();
		labelsA.add(labelB);
		
		return this.canConflict(labelsA,labelsB);
	}
	
	// CAN COOCCUR

	@Override
	public ThreeValuedLogicValue canCooccur(T t1, T t2) {
		if (this.netSystem==null) return ThreeValuedLogicValue.UNKNOWN;
		if (!this.netSystem.getTransitions().contains(t1)) return ThreeValuedLogicValue.UNKNOWN;
		if (!this.netSystem.getTransitions().contains(t2)) return ThreeValuedLogicValue.UNKNOWN;
		
		if (t1.equals(t2)) {
			if (this.canOccur(t1)==ThreeValuedLogicValue.TRUE)
				return ThreeValuedLogicValue.TRUE;
			else
				return ThreeValuedLogicValue.FALSE;
		}
				
		P p = this.netSystem.createPlace();
		P q = this.netSystem.createPlace();
		P pp = this.netSystem.createPlace();
		P qq = this.netSystem.createPlace();
		p.setName("p"+Math.abs(p.hashCode()));
		q.setName("p"+Math.abs(q.hashCode()));
		pp.setName("p"+Math.abs(pp.hashCode()));
		qq.setName("p"+Math.abs(qq.hashCode()));
		
		T tt1 = this.netSystem.createTransition();
		T tt2 = this.netSystem.createTransition();
		tt1.setName("t"+Math.abs(tt1.hashCode()));
		tt2.setName("t"+Math.abs(tt2.hashCode()));
		
		for (P x : this.netSystem.getPreset(t1)) this.netSystem.addFlow(x,tt1);
		for (P x : this.netSystem.getPostset(t1)) this.netSystem.addFlow(tt1,x);
		for (P x : this.netSystem.getPreset(t2)) this.netSystem.addFlow(x,tt2);
		for (P x : this.netSystem.getPostset(t2)) this.netSystem.addFlow(tt2,x);
		
		this.netSystem.addFlow(p,tt1);
		this.netSystem.addFlow(tt1,pp);
		this.netSystem.addFlow(pp,t1);
		this.netSystem.addFlow(t1,pp);
		
		this.netSystem.addFlow(q,tt2);
		this.netSystem.addFlow(tt2,qq);
		this.netSystem.addFlow(qq,t2);
		this.netSystem.addFlow(t2,qq);
		
		this.netSystem.putTokens(p,1);
		this.netSystem.putTokens(q,1);
		
		Collection<P> marking = new ArrayList<P>();
		marking.add(pp);
		marking.add(qq);
		marking.add(this.o);
		
		/*try {
			IOUtils.invokeDOT(".", "sys.png", this.sys.toDOT());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		boolean result = this.modelChecker.isReachable(this.netSystem,marking);
		
		this.netSystem.putTokens(p,0);
		this.netSystem.putTokens(pp,0);
		this.netSystem.putTokens(q,0);
		this.netSystem.putTokens(qq,0);
		this.netSystem.removePlace(p);
		this.netSystem.removePlace(q);
		this.netSystem.removePlace(pp);
		this.netSystem.removePlace(qq);
		
		this.netSystem.removeTransition(tt1);
		this.netSystem.removeTransition(tt2);
		
		return result ? ThreeValuedLogicValue.TRUE : ThreeValuedLogicValue.FALSE;
	}
	
	private ThreeValuedLogicValue canCooccur(Set<String> labelsA, Set<String> labelsB) {
		if (this.netSystem==null) return ThreeValuedLogicValue.UNKNOWN;		
		
		TransformationInfo info = this.prepareNetSystem(labelsA,labelsB);
		
		if (info==null) return ThreeValuedLogicValue.UNKNOWN;
		if (info.t1==null || info.t2==null) return ThreeValuedLogicValue.UNKNOWN; 
		
		// self-relation
		if (info.ts1.equals(info.ts2)) 
			return this.canOccur(info.t1);
		
		return this.canCooccur(info.t1,info.t2);
	}
	
	@Override
	public ThreeValuedLogicValue canCooccur(PQLTask taskA, PQLTask taskB) {
		return this.canCooccur(taskA.getSimilarLabels(),taskB.getSimilarLabels());
	}
	
	@Override
	public ThreeValuedLogicValue canCooccur(String labelA, String labelB) {
		Set<String> labelsA = new HashSet<String>();
		labelsA.add(labelA);
		Set<String> labelsB = new HashSet<String>();
		labelsA.add(labelB);
		
		return this.canCooccur(labelsA,labelsB);
	}
	
	private T prepareNetSystem(Set<String> labels) {
		if (this.netSystem==null) return null;
		
		// net system was already transformed 
		if (this.labels1.equals(labels) && this.labels2.isEmpty()) {
			return this.t1;
		}
		else {
			this.netSystem.removeNodes(this.toRemove);
			this.toRemove.clear();
			
			this.labels1 = new HashSet<String>(labels);
			this.labels2.clear();
			
			// standardise labels
			Set<String> similarLabels = new HashSet<String>();
			for (String label : labels)
				similarLabels.add(label.trim().toLowerCase());
			
			// get transitions with labels of interest
			Set<T> relevantTransitions = new HashSet<T>();
			for (T t : this.netSystem.getTransitions()) {
				if (similarLabels.contains(t.getLabel().trim().toLowerCase()))
					relevantTransitions.add(t);
			}
			
			this.t1 = this.transformNetSystem(relevantTransitions);
			return this.t1;
			
		}
	}
	
	private TransformationInfo prepareNetSystem(Set<String> labelsA, Set<String> labelsB) {
		if (this.netSystem==null) return null;
		
		TransformationInfo result = new TransformationInfo();
		// net system was already transformed 
		if (this.labels1.equals(labelsA) && this.labels2.equals(labelsB)) {
			result.t1 = this.t1;
			result.t2 = this.t2;
			result.ts1 = this.ts1;
			result.ts2 = this.ts2;
			
			return result;
		}
		else {
			this.netSystem.removeNodes(this.toRemove);
			this.toRemove.clear();
			
			/*PNMLSerializer pnml = new PNMLSerializer();
			try {
				IOUtils.toFile("net.pnml", pnml.serializePetriNet((NetSystem)this.netSystem));
			} catch (SerializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			this.labels1 = new HashSet<String>(labelsA);
			this.labels2 = new HashSet<String>(labelsB);
			
			// standardise labels
			Set<String> similarLabelsA = new HashSet<String>();
			for (String label : labelsA)
				similarLabelsA.add(label.trim().toLowerCase());
			
			Set<String> similarLabelsB = new HashSet<String>();
			for (String label : labelsB)
				similarLabelsB.add(label.trim().toLowerCase());
			
			// get transitions with labels of interest
			Set<T> relevantTransitionsA = new HashSet<T>();
			for (T t : this.netSystem.getTransitions()) {
				if (similarLabelsA.contains(t.getLabel().trim().toLowerCase()))
					relevantTransitionsA.add(t);
			}
			
			Set<T> relevantTransitionsB = new HashSet<T>();
			for (T t : this.netSystem.getTransitions()) {
				if (similarLabelsB.contains(t.getLabel().trim().toLowerCase()))
					relevantTransitionsB.add(t);
			}
			
			result = this.transformNetSystem(relevantTransitionsA,relevantTransitionsB);
			
			this.t1 = result.t1;
			this.t2 = result.t2;
			this.ts1 = relevantTransitionsA;
			this.ts2 = relevantTransitionsB;
			
			result.ts1 = relevantTransitionsA;
			result.ts2 = relevantTransitionsB;
			
			return result;
		}
	}
	
	private TransformationInfo transformNetSystem(Set<T> ts1, Set<T> ts2) {
		if (this.netSystem==null) return null;
		if (ts1==null || ts1.isEmpty() || ts2==null || ts2.isEmpty()) {
			this.netSystem.removeNodes(this.toRemove);
			this.toRemove.clear();
			return null;
		}
		
		this.netSystem.removeNodes(toRemove);
		toRemove.clear();
		
		TransformationInfo result = new TransformationInfo();
		result.t1 = this.unifyTransitions(ts1);
		result.t2 = this.unifyTransitions(ts2);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private T unifyTransitions(Set<T> ts) {
		P p1 = this.netSystem.createPlace();
		p1.setName("p"+Math.abs(p1.hashCode()));
		P p2 = this.netSystem.createPlace();
		p2.setName("p"+Math.abs(p2.hashCode()));
		T t = this.netSystem.createTransition();
		t.setName("t"+Math.abs(t.hashCode()));
		toRemove.add((N)p1);
		toRemove.add((N)p2);
		toRemove.add((N)t);
		this.netSystem.addFlow(p1,t);
		this.netSystem.addFlow(t,p2);
		
		/*try {
			IOUtils.invokeDOT(".", "sys1.png", this.sys.toDOT());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		for (T tt : ts) {			
			T tt1 = this.netSystem.createTransition();
			tt1.setName("t"+Math.abs(tt1.hashCode()));
			T tt2 = this.netSystem.createTransition();
			tt2.setName("t"+Math.abs(tt2.hashCode()));
			toRemove.add((N)tt1);
			toRemove.add((N)tt2);
			
			for (P pp : this.netSystem.getPreset(tt))
				this.netSystem.addFlow(pp, tt1);
			
			/*try {
				IOUtils.invokeDOT(".", "sys1.png", this.sys.toDOT());
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			
			for (P pp : this.netSystem.getPostset(tt))
				this.netSystem.addFlow(tt2, pp);
			
			/*try {
				IOUtils.invokeDOT(".", "sys1.png", this.sys.toDOT());
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			
			P pp = this.netSystem.createPlace();
			pp.setName("p"+Math.abs(pp.hashCode()));
			toRemove.add((N)pp);
			
			this.netSystem.addFlow(tt1, pp);
			this.netSystem.addFlow(tt1, p1);
			this.netSystem.addFlow(pp, tt2);
			this.netSystem.addFlow(p2, tt2);
			
			/*try {
				IOUtils.invokeDOT(".", "sys1.png", this.sys.toDOT());
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
		
		P p = this.netSystem.createPlace();
		p.setName("p"+Math.abs(p.hashCode()));
		toRemove.add((N)p);
		for (T tt : ts)  this.netSystem.addFlow(p,tt);
		
		/*try {
			IOUtils.invokeDOT(".", "sys1.png", this.sys.toDOT());
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		return t;
	}

	private T transformNetSystem(Set<T> ts) {
		if (this.netSystem==null) return null;
		if (ts==null || ts.isEmpty()) {
			this.netSystem.removeNodes(this.toRemove);
			this.toRemove.clear();
			return null;
		}
		if (ts.size()==1) {
			this.netSystem.removeNodes(this.toRemove);
			toRemove.clear();
			T t = ts.iterator().next();
			if (this.netSystem.getTransitions().contains(t))
				return t;
			else return null;
		}
		
		this.netSystem.removeNodes(toRemove);
		toRemove.clear();
		
		return this.unifyTransitions(ts);
	}
	
	// CONFLICT
	
	/**
	 * See Def. 4.2. in Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 */
	@Override
	public ThreeValuedLogicValue conflict(PQLTask taskA, PQLTask taskB) {
		return logic.AND(logic.AND(this.canConflict(taskA, taskB), this.canConflict(taskB, taskA)), logic.NOT(this.canCooccur(taskA,taskB)));
	}
	
	// COOCCUR

	/**
	 * See Def. 4.2. in Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 */
	@Override
	public ThreeValuedLogicValue cooccur(PQLTask taskA, PQLTask taskB) {
		return logic.AND(logic.AND(logic.NOT(this.canConflict(taskA, taskB)),logic.NOT(this.canConflict(taskB, taskA))), this.canCooccur(taskA,taskB));
	}
	
	// TOTAL CAUSAL
	
	@Override
	public ThreeValuedLogicValue totalCausal(T t1, T t2) {
		if (this.netSystem==null) return ThreeValuedLogicValue.UNKNOWN;
		if (!this.netSystem.getTransitions().contains(t1) ||
				!this.netSystem.getTransitions().contains(t2)) return ThreeValuedLogicValue.UNKNOWN;
		
		// inject precedence test
		PrecedenceTestInfo info = this.injectPrecedenceTest(t2,t1);
		
		this.netSystem.putTokens(info.p,1);
		
		Collection<P> marking = new ArrayList<P>();
		marking.add(info.r);
		marking.add(this.o);
		
		boolean result = this.modelChecker.isReachable(this.netSystem, marking);
		
		this.netSystem.putTokens(info.p, 0);
		this.netSystem.removePlace(info.p);
		this.netSystem.removePlace(info.q);
		this.netSystem.removePlace(info.r);
		this.netSystem.removeTransition(info.t1);
		this.netSystem.removeTransition(info.t2);
		
		if (result) return ThreeValuedLogicValue.FALSE;
		else return ThreeValuedLogicValue.TRUE;
	}
	
	private ThreeValuedLogicValue totalCausal(Set<String> labelsA, Set<String> labelsB) {
		if (this.netSystem==null) return ThreeValuedLogicValue.UNKNOWN;
		
		TransformationInfo info = this.prepareNetSystem(labelsA,labelsB);
		
		if (info==null) return ThreeValuedLogicValue.UNKNOWN;
		if (info.t1==null || info.t2==null) return ThreeValuedLogicValue.UNKNOWN;
			
		return this.totalCausal(info.t1,info.t2);
	}
	
	@Override
	public ThreeValuedLogicValue totalCausal(PQLTask taskA, PQLTask taskB) {		
		return this.totalCausal(taskA.getSimilarLabels(),taskB.getSimilarLabels());
	}

	// TOTAL CONCURRENT
	
	@Override
	public ThreeValuedLogicValue totalConcur(PQLTask taskA, PQLTask taskB) {
		return this.totalConcur(taskA.getSimilarLabels(),taskB.getSimilarLabels());
	}
	
	private ThreeValuedLogicValue totalConcur(Set<String> labelsA, Set<String> labelsB) {
		if (this.netSystem==null) return ThreeValuedLogicValue.UNKNOWN;		
		
		TransformationInfo info = this.prepareNetSystem(labelsA,labelsB);
		
		if (info==null) return ThreeValuedLogicValue.UNKNOWN;
		if (info.t1==null || info.t2==null) return ThreeValuedLogicValue.UNKNOWN; 
				
		return this.totalConcur(info.t1,info.t2);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ThreeValuedLogicValue totalConcur(T t1, T t2) {
		/*try {
			IOUtils.invokeDOT(".", "sys.png", this.netSystem.toDOT());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		CompletePrefixUnfoldingSetup setup = new CompletePrefixUnfoldingSetup();
		setup.ADEQUATE_ORDER = AdequateOrderType.ESPARZA_FOR_ARBITRARY_SYSTEMS;
		setup.SAFE_OPTIMIZATION = false;
		setup.MAX_EVENTS = Integer.MAX_VALUE;
		setup.MAX_BOUND = Integer.MAX_VALUE;
		
		AbstractCompletePrefixUnfolding unf = new AbstractCompletePrefixUnfolding(this.netSystem, setup);
		
		IOccurrenceNet occNet = unf.getOccurrenceNet();
		Set<Event> cutoffs = unf.getCutoffEvents();
		
		/*try {
			IOUtils.invokeDOT(".", "occ.png", occNet.toDOT());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		Set<T> ts = new HashSet<T>(occNet.getTransitions());
		for (Event cutoff : cutoffs) {
			IEvent corr = unf.getCorrespondingEvent(cutoff);
			
			T tCutoff = (T) occNet.getTransition(cutoff);
			T tCorr = (T) occNet.getTransition(corr);
			
			ILocalConfiguration lcCutoff = cutoff.getLocalConfiguration();
			ILocalConfiguration lcCorr = corr.getLocalConfiguration();
			
			Set<P> cutCutoff = occNet.getCutInducedByLocalConfiguration(tCutoff);
			Set<P> cutCorr = occNet.getCutInducedByLocalConfiguration(tCorr);
			
			Set<P> used = new HashSet<P>();
			
			for (P c1 : cutCutoff) {
				for (P c2 : cutCorr) {
					if (occNet.getCondition(c1).getPlace().equals(occNet.getCondition(c2).getPlace()) && !used.contains(c2)) {
						used.add(c2);
						
						Transition t = new Transition();
						occNet.addFlow(c1,t);
						occNet.addFlow(t,c2);
					}
				}
			}
		}
		
		/*try {
			IOUtils.invokeDOT(".", "occ2.png", occNet.toDOT());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		TransitiveClosure<F,N> TC = new TransitiveClosure<F,N>(occNet);
		for (T tt1 : ts) {
			for (T tt2 : ts) {
				if (tt1.equals(tt2)) continue;
				
				if (!occNet.getEvent(tt1).getTransition().equals(t1)) continue;
				if (!occNet.getEvent(tt2).getTransition().equals(t2)) continue;
				
				if (TC.hasPath((N)tt1,(N)tt2) || TC.hasPath((N)tt2,(N)tt1)) 
					return ThreeValuedLogicValue.FALSE;
			}
		}
		
		return ThreeValuedLogicValue.TRUE;
	}
	
	/**
	 * Inject precedence test in the net system.
	 * 
	 * See Def. 5.5. in Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 * 
	 * @param t1 A transition
	 * @param t2 A transition
	 * @return An information on freshly injected nodes.
	 */
	private PrecedenceTestInfo injectPrecedenceTest(T t1, T t2) {
		PrecedenceTestInfo info = new PrecedenceTestInfo();
		
		P p = this.netSystem.createPlace();
		p.setName("p"+Math.abs(p.hashCode()));
		P q = this.netSystem.createPlace();
		q.setName("q"+Math.abs(q.hashCode()));
		P r = this.netSystem.createPlace();
		r.setName("r"+Math.abs(r.hashCode()));
		
		T x = this.netSystem.createTransition();
		x.setName("x"+Math.abs(x.hashCode()));
		T y = this.netSystem.createTransition();
		y.setName("y"+Math.abs(y.hashCode()));
		
		info.p = p;
		info.q = q;
		info.r = r;
		info.t1 = x;
		info.t2 = y;
		
		this.netSystem.addFlow(p,x);
		this.netSystem.addFlow(x,q);
		this.netSystem.addFlow(q,y);
		this.netSystem.addFlow(y,r);
		
		for (P u: this.netSystem.getPreset(t1)) this.netSystem.addFlow(u,x);
		for (P u: this.netSystem.getPreset(t2)) this.netSystem.addFlow(u,y);
		for (P u: this.netSystem.getPostset(t1)) this.netSystem.addFlow(x,u);
		for (P u: this.netSystem.getPostset(t2)) this.netSystem.addFlow(y,u);
		
		return info;
	}
	
	
	
	
	/*	protected TransformationInfo performTransformationOfNetSystem(String labelA, String labelB) {
	Set<T> ts1 = getTransitionsWithLabel(labelA);
	Set<T> ts2 = getTransitionsWithLabel(labelB);
	
	if (ts1.isEmpty() || ts2.isEmpty()) return null;
			
	TransformationInfo result = new TransformationInfo();
	
	if (ts1.isEmpty()) result.t1 = null;
	if (ts1.size()==1) result.t1 = ts1.iterator().next();
	else result.t1 = this.transformNetSystem(ts1,labelA,result.toRemove);
	
	if (labelA.equals(labelB)) {
		result.t2 = result.t1;
		return result;
	}

	if (ts2.isEmpty()) result.t2 = null;
	Set<N> toRemoveTmp = new HashSet<>();
	if (ts2.size()==1)  result.t2 = ts2.iterator().next();
	else {
		result.t2 = this.transformNetSystem(ts2,labelB,toRemoveTmp);
		result.toRemove.addAll(toRemoveTmp);
	}
	
	return result;
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Override
	public boolean exclusive(T t1, T t2) {
		// System.out.println("exclusive " + t1 + " " + t2);
		
		if (t1.equals(t2)) {
			if (!this.sys.getTransitions().contains(t1)) return true;
			
			if (this.canOccur(t1)) return false;
			
			return true;
		}
		else {
			if (!this.sys.getTransitions().contains(t1)) return true;
			if (!this.sys.getTransitions().contains(t2)) return true;
			
			P p1 = this.sys.createPlace();
			P p2 = this.sys.createPlace();
			p1.setName("TEMP1");
			p2.setName("TEMP2");
			
			T tt1 = this.sys.createTransition();
			T tt2 = this.sys.createTransition();
			tt1.setName("TEMP1");
			tt2.setName("TEMP2");
			
			this.sys.addFlow(p1,tt1);
			this.sys.addFlow(p2,tt2);
			
			for (P p : this.sys.getPreset(t1)) this.sys.addFlow(p,tt1);
			for (P p : this.sys.getPostset(t1)) this.sys.addFlow(tt1,p);
			
			for (P p : this.sys.getPreset(t2)) this.sys.addFlow(p,tt2);
			for (P p : this.sys.getPostset(t2)) this.sys.addFlow(tt2,p);
			
			this.sys.putTokens(p1,1);
			this.sys.putTokens(p2,1);
			
			boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY TEMP1 = 0 AND TEMP2 = 0");
			
			this.sys.putTokens(p1,0);
			this.sys.putTokens(p2,0);
			this.sys.removePlace(p1);
			this.sys.removePlace(p2);
			this.sys.removeTransition(tt1);
			this.sys.removeTransition(tt2);
			
			return !result;
		}
	}
	
	@Override
	public boolean concurrent(T t1, T t2) {
		// System.out.println("concurrent " + t1 + " " + t2);
		
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (this.causal(t1,t2)) return false;
		if (this.causal(t2,t1)) return false;
		
		if (this.alwaysTogether(t1,t2)) return true; 
		
		return false;
	}


	
	@Override
	public boolean alwaysOccursAndIsEventuallySucceeded(T t1, T t2) {
		// System.out.println("alwaysOccursAndIsEventuallySucceeded " + t1 + " " + t2);
		
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (!this.alwaysOccurs(t1)) return false;
		
		if (t1.equals(t2)) {
			if (this.isSoundWorkflowNet) {			
				P p = this.sys.createPlace();
				p.setName("TEMP");
				this.sys.addFlow(p,t1);
				this.sys.putTokens(p,2);
				
				Collection<P> marking = new ArrayList<P>();
				marking.add(p);
				marking.add(this.o);
				
				boolean result = this.LoLA.isReachable(this.sys,marking);
				
				this.sys.putTokens(p,0);
				this.sys.removePlace(p);
				
				return !result;
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
		else {
			if (this.isSoundWorkflowNet) {
				P p1 = this.sys.createPlace();
				P p2 = this.sys.createPlace();
				P p3 = this.sys.createPlace();
				p1.setName("TEMP1");
				p2.setName("TEMP2");
				p3.setName("TEMP3");
				
				T tt1 = this.sys.createTransition();
				T tt2 = this.sys.createTransition();
				tt1.setName("TEMP1");
				tt2.setName("TEMP2");
				
				for (P p : this.sys.getPreset(t1)) this.sys.addFlow(p,tt1);
				for (P p : this.sys.getPostset(t1)) this.sys.addFlow(tt1,p);
				
				for (P p : this.sys.getPreset(t2)) this.sys.addFlow(p,tt2);
				for (P p : this.sys.getPostset(t2)) this.sys.addFlow(tt2,p);
				
				this.sys.addFlow(p1,t1);
				this.sys.addFlow(t1,p1);
				this.sys.addFlow(tt1,p1);
				this.sys.addFlow(tt1,p3);
				this.sys.addFlow(p3,tt2);
				this.sys.addFlow(p2,t2);
				this.sys.addFlow(t2,p2);
				this.sys.addFlow(p2,tt1);
				
				this.sys.putTokens(p2,1);
				
				boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY TEMP3 = 1 AND  " + this.o.getName() + " = 1");
				
				this.sys.removePlace(p1);
				this.sys.removePlace(p2);
				this.sys.removePlace(p3);
				this.sys.removeTransition(tt1);
				this.sys.removeTransition(tt2);
				
				return !result;
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
	}

	@Override
	public boolean alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(T t1, T t2) {
		// System.out.println("alwaysOccursAndEveryOccurrenceIsEventuallySucceeded " + t1 + " " + t2);
		
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (!this.alwaysOccurs(t1)) return false;
		
		if (t1.equals(t2)) {
			if (this.isSoundWorkflowNet) {
				P p = this.sys.createPlace();
				p.setName("TEMP");
				this.sys.addFlow(p,t1);
				this.sys.putTokens(p,2);
				
				Collection<P> marking = new ArrayList<P>();
				marking.add(p);
				marking.add(this.o);
				
				boolean result = this.LoLA.isReachable(this.sys,marking);
				
				this.sys.putTokens(p,0);
				this.sys.removePlace(p);
				
				return !result;
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
		else {
			if (this.isSoundWorkflowNet) {			
				P pp = this.sys.createPlace();
				pp.setName("TEMP");
				
				this.sys.addFlow(pp,t2);
				this.sys.addFlow(t2,pp);
				
				T tt = this.sys.createTransition();
				tt.setName("TEMP");
				for (P p : this.sys.getPreset(t1)) this.sys.addFlow(p,tt);
				for (P p : this.sys.getPostset(t1)) this.sys.addFlow(tt,p);
				
				this.sys.addFlow(pp,tt);
				this.sys.putTokens(pp,1);
				
				boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY " + this.o.getName() + " > 0 AND TEMP = 0");
				
				this.sys.putTokens(pp,0);
				this.sys.removePlace(pp);
				this.sys.removeTransition(tt);
				
				return !result;
				
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
	}

	@Override
	public boolean alwaysOccursAndIsEventuallyPreceded(T t1, T t2) {
		// System.out.println("alwaysOccursAndIsEventuallyPreceded " + t1 + " " + t2);
		
		return this.alwaysOccursAndIsEventuallySucceeded(t2,t1);
	}

	@Override
	public boolean alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(T t1, T t2) {
		// System.out.println("alwaysOccursAndEveryOccurrenceIsEventuallyPreceded " + t1 + " " + t2);
		
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (!this.alwaysOccurs(t1)) return false;
		
		if (t1.equals(t2)) {
			if (this.isSoundWorkflowNet) {
				P p = this.sys.createPlace();
				p.setName("TEMP");
				this.sys.addFlow(p,t1);
				this.sys.putTokens(p,2);
				
				Collection<P> marking = new ArrayList<P>();
				marking.add(p);
				marking.add(this.o);
				
				boolean result = this.LoLA.isReachable(this.sys,marking);
				
				this.sys.putTokens(p,0);
				this.sys.removePlace(p);
				
				return !result;
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
		else {
			if (this.isSoundWorkflowNet) {
				if (!this.alwaysOccurs(t2)) return false;
				
				P p1 = this.sys.createPlace();
				P p2 = this.sys.createPlace();
				p1.setName("TEMP1");
				p2.setName("TEMP2");
				
				this.sys.addFlow(p2,t2);
				this.sys.addFlow(t2,p2);
				
				T tt = this.sys.createTransition();
				tt.setName("TEMP");
				for (P p : this.sys.getPreset(t1)) this.sys.addFlow(p,tt);
				for (P p : this.sys.getPostset(t1)) this.sys.addFlow(tt,p);
				
				this.sys.addFlow(p1,tt);
				this.sys.addFlow(tt,p2);
				
				this.sys.putTokens(p1,1);
				
				boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY " + this.o.getName() + " > 0 AND TEMP1 = 0");
				
				this.sys.putTokens(p1,0);
				this.sys.putTokens(p2,0);
				this.sys.removePlace(p1);
				this.sys.removePlace(p2);
				this.sys.removeTransition(tt);
				
				return !result;
				
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
	}

	@Override
	public boolean alwaysOccursAndIsImmediatelySucceeded(T t1, T t2) {
		// System.out.println("alwaysOccursAndIsImmediatelySucceeded " + t1 + " " + t2);
		
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (!this.alwaysOccurs(t1)) return false;
		
		if (t1.equals(t2)) {
			if (this.isSoundWorkflowNet) {
				P p1 = this.sys.createPlace();
				P p2 = this.sys.createPlace();
				P p3 = this.sys.createPlace();
				p1.setName("TEMP1");
				p2.setName("TEMP2");
				p3.setName("TEMP3");
				
				Collection<T> toRemove = new ArrayList<>();
				for (T t : this.sys.getTransitions()) {
					if (t.equals(t1)) continue;
					
					T tt = this.sys.createTransition();
					toRemove.add(tt);
					tt.setName(t.getName() + "_TEMP");
					for (P p : this.sys.getPreset(t)) this.sys.addFlow(p,tt);
					for (P p : this.sys.getPostset(t)) this.sys.addFlow(tt,p);
					
					this.sys.addFlow(tt,p3);
					this.sys.addFlow(t,p3);
					this.sys.addFlow(p3,t);
					this.sys.addFlow(p3,t1);
					this.sys.addFlow(p2,tt);
					this.sys.addFlow(tt,p1);
				}
				
				this.sys.addFlow(p1,t1);
				this.sys.addFlow(t1,p2);
				
				this.sys.putTokens(p1,1);
				this.sys.putTokens(p3,1);
				
				boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY " + this.o.getName() + " > 0");
				
				this.sys.putTokens(p1,0);
				this.sys.putTokens(p2,0);
				this.sys.putTokens(p3,0);
				
				this.sys.removePlace(p1);
				this.sys.removePlace(p2);
				this.sys.removePlace(p3);
				for (T t : toRemove) this.sys.removeTransition(t);
				
				return !result;
				
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
		else {
			if (this.isSoundWorkflowNet) {			
				P p1 = this.sys.createPlace();
				P p2 = this.sys.createPlace();
				P p3 = this.sys.createPlace();
				P p4 = this.sys.createPlace();
				p1.setName("TEMP1");
				p2.setName("TEMP2");
				p3.setName("TEMP3");
				p4.setName("TEMP4");
				
				T tt1 = this.sys.createTransition();
				tt1.setName("TEMP1");
				for (P p : this.sys.getPreset(t1)) this.sys.addFlow(p,tt1);
				for (P p : this.sys.getPostset(t1)) this.sys.addFlow(tt1,p);
				
				Collection<T> toRemove = new ArrayList<>();
				for (T t : this.sys.getTransitions()) {
					if (t.equals(t1)) continue;
					if (t.equals(t2)) continue;
					
					T tt = this.sys.createTransition();
					toRemove.add(tt);
					tt.setName(t.getName() + "_TEMP");
					for (P p : this.sys.getPreset(t)) this.sys.addFlow(p,tt);
					for (P p : this.sys.getPostset(t)) this.sys.addFlow(tt,p);
					
					this.sys.addFlow(t,p4);
					this.sys.addFlow(p4,t);
					this.sys.addFlow(tt,p4);
					this.sys.addFlow(p1,tt);
					this.sys.addFlow(tt,p2);
					this.sys.addFlow(p3,tt);
				}
				
				this.sys.addFlow(t1,p1);
				this.sys.addFlow(tt1,p1);
				this.sys.addFlow(p1,tt1);
				this.sys.addFlow(p4,t1);
				this.sys.addFlow(t1,p3);
				this.sys.addFlow(p2,t2);
				this.sys.addFlow(t2,p2);
				this.sys.addFlow(p2,t1);
				
				this.sys.putTokens(p2,1);
				this.sys.putTokens(p4,1);
				
				boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY " + this.o.getName() + " > 0");
				
				this.sys.putTokens(p1,0);
				this.sys.putTokens(p2,0);
				this.sys.putTokens(p3,0);
				this.sys.putTokens(p4,0);
				
				this.sys.removePlace(p1);
				this.sys.removePlace(p2);
				this.sys.removePlace(p3);
				this.sys.removePlace(p4);
				this.sys.removeTransition(tt1);
				for (T t : toRemove)
					this.sys.removeTransition(t);
				
				return !result;
				
				
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
	}

	@Override
	public boolean alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(T t1, T t2) {
		// System.out.println("alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded " + t1 + " " + t2);
		
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (this.isSoundWorkflowNet) {			
			// t1 must always occur
			if (!this.alwaysOccurs(t1)) return false;
			
			// it is possible that not t2 immediately follows t1
			for (T t : this.sys.getTransitions()) {
				if (this.canOccurAndIsImmediatelySucceeded(t1,t) && !t.equals(t2))
					return false;
			}
			
			// it is possible that nothing immediately follows t1 
			if (this.sys.getPostset(t1).size()==1 && this.sys.getPostset(t1).iterator().next().equals(this.o))
				return false;
		
			return true;
			
		} else {
			throw new UnsupportedOperationException("Not implemented yet");
		}
	}

	@Override
	public boolean alwaysOccursAndIsImmediatelyPreceded(T t1, T t2) {
		// System.out.println("alwaysOccursAndIsImmediatelyPreceded " + t1 + " " + t2);
		
		return this.alwaysOccursAndIsImmediatelySucceeded(t2,t1);
	}

	@Override
	public boolean alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(T t1, T t2) {
		// System.out.println("alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded " + t1 + " " + t2);
		
		return this.alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(t2,t1);
	}
	
	@Override
	public boolean canOccurAndIsImmediatelySucceeded(T t1, T t2) {
		// System.out.println("canOccurAndIsImmediatelySucceeded " + t1 + " " + t2);
		
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;

		Collection<P> places = new ArrayList<P>();
		places.addAll(this.sys.getPreset(t1));
		places.addAll(this.sys.getPreset(t2));
		places.removeAll(this.sys.getPostset(t1));
		
		if (places.isEmpty()) return true;
		Collection<P> pSet = new HashSet<P>(places);
		
		String cmd = "EXPATH EVENTUALLY ";
		
		Iterator<P> i = pSet.iterator();
		P p = i.next();
		cmd += p.getName() + " >= " + Collections.frequency(places,p);
		while (i.hasNext()) {
			cmd += " AND ";
			p = i.next();
			cmd += p.getName() + " >= " + Collections.frequency(places,p);
		}
		
		boolean result = this.LoLA.checkCTL(this.sys, cmd);
		
		return result;
	}

	@Override
	public boolean canOccurAndIsImmediatelyPreceded(T t1, T t2) {
		// System.out.println("canOccurAndIsImmediatelyPreceded " + t1 + " " + t2);
		
		return this.canOccurAndIsImmediatelySucceeded(t2,t1);
	}
	
	@Override
	public boolean canOccurAndIsEventuallySucceeded(T t1, T t2) {
		// System.out.println("canOccurAndIsEventuallySucceeded " + t1 + " " + t2);
		
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (t1.equals(t2)) {
			P p1 = this.sys.createPlace();
			p1.setName("TEMP");
			this.sys.addFlow(p1,t1);
			this.sys.putTokens(p1, 2);
			
			boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY TEMP = 0");
			
			this.sys.putTokens(p1, 0);
			this.sys.removePlace(p1);
			
			return result;
		}
		else {
			P p1 = this.sys.createPlace();
			P p2 = this.sys.createPlace();
			p1.setName("TEMP1");
			p2.setName("TEMP2");
			
			T tt1 = this.sys.createTransition();
			T tt2 = this.sys.createTransition();
			tt1.setName("TEMP1");
			tt2.setName("TEMP2");
			
			this.sys.addFlow(p1,tt1);
			this.sys.addFlow(tt1,p2);
			this.sys.addFlow(p2,tt2);
			
			for (P p : this.sys.getPreset(t1)) this.sys.addFlow(p,tt1);
			for (P p : this.sys.getPostset(t1)) this.sys.addFlow(tt1,p);
			
			for (P p : this.sys.getPreset(t2)) this.sys.addFlow(p,tt2);
			for (P p : this.sys.getPostset(t2)) this.sys.addFlow(tt2,p);
			
			this.sys.putTokens(p1, 1);
			
			try {
				IOUtils.invokeDOT(".", "sys.png", this.sys.toDOT());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY TEMP1 = 0 AND TEMP2 = 0");
			
			this.sys.putTokens(p1, 0);
			this.sys.putTokens(p2, 0);
			this.sys.removePlace(p1);
			this.sys.removePlace(p2);
			this.sys.removeTransition(tt1);
			this.sys.removeTransition(tt2);
			
			return result;
		}
	}
	
	@Override
	public boolean canOccurAndIsEventuallyPreceded(T t1, T t2) {
		// System.out.println("canOccurAndIsEventuallyPreceded " + t1 + " " + t2);
		
		return this.canOccurAndIsEventuallySucceeded(t2, t1);
	}
	
	

	@Override
	public boolean canOccurAndEveryOccurrenceIsEventuallySucceeded(T t1, T t2) {
		// System.out.println("canOccurAndEveryOccurrenceIsEventuallySucceeded " + t1 + " " + t2);
		
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (t1.equals(t2)) {
			P p1 = this.sys.createPlace();
			p1.setName("TEMP");
			this.sys.addFlow(p1,t1);
			this.sys.putTokens(p1, 2);
			
			boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY TEMP = 0");
			
			this.sys.putTokens(p1, 0);
			this.sys.removePlace(p1);
			
			return result;
		}
		else {
			if (this.isSoundWorkflowNet) {
				P p1 = this.sys.createPlace();
				P p2 = this.sys.createPlace();
				P p3 = this.sys.createPlace();
				p1.setName("TEMP1");
				p2.setName("TEMP2");
				p3.setName("TEMP3");
				
				T tt1 = this.sys.createTransition();
				T tt2 = this.sys.createTransition();
				tt1.setName("TEMP1");
				tt2.setName("TEMP2");
				
				this.sys.addFlow(p1,tt1);
				this.sys.addFlow(tt1,p2);
				this.sys.addFlow(p2,tt2);
				
				this.sys.addFlow(p3,t1);
				this.sys.addFlow(t1,p3);
				this.sys.addFlow(p3,tt2);
				
				for (P p : this.sys.getPreset(t1)) this.sys.addFlow(p,tt1);
				for (P p : this.sys.getPostset(t1)) this.sys.addFlow(tt1,p);
				
				for (P p : this.sys.getPreset(t2)) this.sys.addFlow(p,tt2);
				for (P p : this.sys.getPostset(t2)) this.sys.addFlow(tt2,p);
				
				this.sys.putTokens(p1,1);
				this.sys.putTokens(p3,1);
				
				boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY TEMP1 = 0 AND TEMP2 = 0 AND TEMP3 = 0 AND " + this.o.getName() + " = 1");
				
				this.sys.putTokens(p1, 0);
				this.sys.putTokens(p2, 0);
				this.sys.putTokens(p3, 0);
				this.sys.removePlace(p1);
				this.sys.removePlace(p2);
				this.sys.removePlace(p3);
				this.sys.removeTransition(tt1);
				this.sys.removeTransition(tt2);
				
				return result;
				
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
	}

	@Override
	public boolean canOccurAndEveryOccurrenceIsEventuallyPreceded(T t1, T t2) {
		// System.out.println("canOccurAndEveryOccurrenceIsEventuallyPreceded " + t1 + " " + t2);
		
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (t1.equals(t2)) {
			P p1 = this.sys.createPlace();
			p1.setName("TEMP");
			this.sys.addFlow(p1,t1);
			this.sys.putTokens(p1,2);
			
			boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY TEMP = 0");
			
			this.sys.putTokens(p1, 0);
			this.sys.removePlace(p1);
			
			return result;
		}
		else {
			if (this.isSoundWorkflowNet) {
				P p1 = this.sys.createPlace();
				P p2 = this.sys.createPlace();
				P p3 = this.sys.createPlace();
				p1.setName("TEMP1");
				p2.setName("TEMP2");
				p3.setName("TEMP3");
				
				T tt1 = this.sys.createTransition();
				T tt2 = this.sys.createTransition();
				tt1.setName("TEMP1");
				tt2.setName("TEMP2");
				
				this.sys.addFlow(p1,tt2);
				this.sys.addFlow(tt2,p2);
				this.sys.addFlow(p2,tt1);
				this.sys.addFlow(tt1,p2);
				this.sys.addFlow(p2,t1);
				this.sys.addFlow(t1,p2);
				this.sys.addFlow(p3,t1);
		
				for (P p : this.sys.getPreset(t1)) this.sys.addFlow(p,tt1);
				for (P p : this.sys.getPostset(t1)) this.sys.addFlow(tt1,p);
				
				for (P p : this.sys.getPreset(t2)) this.sys.addFlow(p,tt2);
				for (P p : this.sys.getPostset(t2)) this.sys.addFlow(tt2,p);
				
				this.sys.putTokens(p1,1);
				this.sys.putTokens(p3,1);
				
				boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY TEMP1 = 0 AND TEMP2 = 1 AND TEMP3 = 0 AND " + this.o.getName() + " = 1");
				
				this.sys.putTokens(p1, 0);
				this.sys.putTokens(p2, 0);
				this.sys.putTokens(p3, 0);
				this.sys.removePlace(p1);
				this.sys.removePlace(p2);
				this.sys.removePlace(p3);
				this.sys.removeTransition(tt1);
				this.sys.removeTransition(tt2);
				
				return result;
				
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
	}

	@Override
	public boolean canOccurAndEveryOccurrenceIsImmediatelySucceeded(T t1, T t2) {
		// System.out.println("canOccurAndEveryOccurrenceIsImmediatelySucceeded " + t1 + " " + t2);
		
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (t1.equals(t2)) {			
			return false;
		}
		else {
			if (this.isSoundWorkflowNet) {
				P p1 = this.sys.createPlace();
				P p2 = this.sys.createPlace();
				P p3 = this.sys.createPlace();
				p1.setName("TEMP1");
				p2.setName("TEMP2");
				p3.setName("TEMP3");
				
				T tt1 = this.sys.createTransition();
				T tt2 = this.sys.createTransition();
				tt1.setName("TEMP1");
				tt2.setName("TEMP2");
		
				for (P p : this.sys.getPreset(t1)) this.sys.addFlow(p,tt1);
				for (P p : this.sys.getPostset(t1)) this.sys.addFlow(tt1,p);
				
				for (P p : this.sys.getPreset(t2)) this.sys.addFlow(p,tt2);
				for (P p : this.sys.getPostset(t2)) this.sys.addFlow(tt2,p);
				
				this.sys.addFlow(p1,tt1);
				this.sys.addFlow(tt1,p2);
				this.sys.addFlow(t1,p2);
				this.sys.addFlow(p2,t2);
				this.sys.addFlow(t2,p3);
				this.sys.addFlow(p3,t1);
				this.sys.addFlow(p3,tt1);
				
				for (T t : this.sys.getTransitions()) {
					if (t.equals(t1) || t.equals(tt1) || t.equals(t2)) continue;
					this.sys.addFlow(t,p3);
					this.sys.addFlow(p3,t);
				}
				
				
				this.sys.putTokens(p1,1);
				this.sys.putTokens(p3,1);
				
				boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY TEMP1 = 0 AND TEMP3 = 1 AND " + this.o.getName() + " = 1");
				
				this.sys.putTokens(p1, 0);
				this.sys.putTokens(p2, 0);
				this.sys.putTokens(p3, 0);
				this.sys.removePlace(p1);
				this.sys.removePlace(p2);
				this.sys.removePlace(p3);
				this.sys.removeTransition(tt1);
				this.sys.removeTransition(tt2);
				
				return result;
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
	}

	@Override
	public boolean canOccurAndEveryOccurrenceIsImmediatelyPreceded(T t1, T t2) {
		// System.out.println("canOccurAndEveryOccurrenceIsImmediatelyPreceded " + t1 + " " + t2);
		
		return this.canOccurAndEveryOccurrenceIsImmediatelySucceeded(t2, t1);
	}
	
	
	
	private String lolaAtLeastOnePlaceWithoutTokens(Collection<P> places) {
		String result = "";
		if (places==null || places.isEmpty()) return result;
		
		Iterator<P> i = places.iterator();
		P p = i.next();
		result += p.getName() + " = 0";
		while (i.hasNext()) {
			result += " OR ";
			p = i.next();
			result += p.getName() + " = 0";
		}
		
		return result;
	}
	
	@Override
	public boolean causal(T t1, T t2) {
		// System.out.println("areCausal " + t1 + " " + t2);
		
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		P p = this.sys.createPlace();
		p.setName("TEMP");
		this.sys.addFlow(p,t1);
		
		boolean result = this.canOccur(t2);

		this.sys.removePlace(p);
		
		return !result;
	}
	
	@Override
	public boolean causal(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystemDetailed(t1,t2);
		
		if (info.t1==null && info.t2==null) return true;
		
		if (info.t1!=null && info.t2==null) { 
			this.sys.removeNodes(info.toRemove); return true; 
		}
		
		if (info.t1==null && info.t2!=null)
			if (this.canOccur(info.t2)) { this.sys.removeNodes(info.toRemove); return false; } 
			else { this.sys.removeNodes(info.toRemove); return true; }
		
		boolean result = causal(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}
	
	protected boolean alwaysTogether(T t1, T t2) {
		// System.out.println("alwaysTogether " + t1 + " " + t2);
		
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (this.isSoundWorkflowNet) {
			P p1 = this.sys.createPlace();
			P pp1 = this.sys.createPlace();
			P ppp1 = this.sys.createPlace();
			P p2 = this.sys.createPlace();
			P pp2 = this.sys.createPlace();
			P ppp2 = this.sys.createPlace();
			p1.setName("TEMP1");
			pp1.setName("TEMP11");
			ppp1.setName("TEMP111");
			p2.setName("TEMP2");
			pp2.setName("TEMP22");
			ppp2.setName("TEMP222");
			
			T tt1 = this.sys.createTransition();
			T tt2 = this.sys.createTransition();
			tt1.setName("TEMP1");
			tt2.setName("TEMP2");
			
			for (P p : this.sys.getPreset(t1)) this.sys.addFlow(p,tt1);
			for (P p : this.sys.getPostset(t1)) this.sys.addFlow(tt1,p);
			
			for (P p : this.sys.getPreset(t2)) this.sys.addFlow(p,tt2);
			for (P p : this.sys.getPostset(t2)) this.sys.addFlow(tt2,p);
			
			this.sys.addFlow(p1,t1);
			this.sys.addFlow(t1,p1);
			this.sys.addFlow(p2,t2);
			this.sys.addFlow(t2,p2);
			
			this.sys.addFlow(pp1,tt1);
			this.sys.addFlow(pp2,tt2);
			
			this.sys.putTokens(pp1,1);
			this.sys.putTokens(pp2,1);
			
			this.sys.addFlow(tt1,p1);
			this.sys.addFlow(tt2,p2);
			
			this.sys.addFlow(tt1,ppp1);
			this.sys.addFlow(tt2,ppp2);
			
			boolean res1 = this.LoLA.checkCTL(this.sys, String.format("EXPATH EVENTUALLY " + this.o.getName() + " = 1 AND TEMP111 = 0 AND TEMP222 > 0"));
			boolean res2 = this.LoLA.checkCTL(this.sys, String.format("EXPATH EVENTUALLY " + this.o.getName() + " = 1 AND TEMP222 = 0 AND TEMP111 > 0"));
			
			this.sys.removePlace(p1);
			this.sys.removePlace(pp1);
			this.sys.removePlace(ppp1);
			
			this.sys.removePlace(p2);
			this.sys.removePlace(pp2);
			this.sys.removePlace(ppp2);
			
			this.sys.removeTransition(tt1);
			this.sys.removeTransition(tt2);
			
			return !(res1 || res2);
			
		} else {
			throw new UnsupportedOperationException("Not implemented yet");
		}
	}
	
	
	
	

	@Override
	public boolean exclusive(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return true;
		
		boolean result = exclusive(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean concurrent(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystemDetailed(t1,t2);
		
		if (info.t1==null && info.t2==null) return false;
		
		if (info.t1!=null && info.t2==null)
			if (this.canOccur(info.t1)) { this.sys.removeNodes(info.toRemove); return false; } else { this.sys.removeNodes(info.toRemove); return true; }
		
		if (info.t1==null && info.t2!=null)
			if (this.canOccur(info.t2)) { this.sys.removeNodes(info.toRemove); return false; } else { this.sys.removeNodes(info.toRemove); return true; }
		
		boolean result = concurrent(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}
	
	

	@Override
	public boolean alwaysOccursAndIsEventuallySucceeded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = alwaysOccursAndIsEventuallySucceeded(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = alwaysOccursAndEveryOccurrenceIsEventuallySucceeded(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean alwaysOccursAndIsEventuallyPreceded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = alwaysOccursAndIsEventuallyPreceded(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = alwaysOccursAndEveryOccurrenceIsEventuallyPreceded(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean alwaysOccursAndIsImmediatelySucceeded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = alwaysOccursAndIsImmediatelySucceededSkipSilent(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean alwaysOccursAndEveryOccurrenceIsImmediatelySucceeded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = alwaysOccursAndEveryOccurrenceIsImmediatelySucceededSkipSilent(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean alwaysOccursAndIsImmediatelyPreceded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = alwaysOccursAndIsImmediatelyPrecededSkipSilent(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean alwaysOccursAndEveryOccurrenceIsImmediatelyPreceded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = alwaysOccursAndEveryOccurrenceIsImmediatelyPrecededSkipSilent(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean canOccurAndIsEventuallySucceeded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = canOccurAndIsEventuallySucceeded(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean canOccurAndEveryOccurrenceIsEventuallySucceeded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = canOccurAndEveryOccurrenceIsEventuallySucceeded(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean canOccurAndIsEventuallyPreceded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = canOccurAndIsEventuallyPreceded(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean canOccurAndEveryOccurrenceIsEventuallyPreceded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = canOccurAndEveryOccurrenceIsEventuallyPreceded(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean canOccurAndIsImmediatelySucceeded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = canOccurAndIsImmediatelySucceededSkipSilent(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean canOccurAndEveryOccurrenceIsImmediatelySucceeded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = canOccurAndEveryOccurrenceIsImmediatelySucceededSkipSilent(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean canOccurAndIsImmediatelyPreceded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = canOccurAndIsImmediatelyPrecededSkipSilent(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean canOccurAndEveryOccurrenceIsImmediatelyPreceded(String t1, String t2) {
		TransformationInfo info = performTransformationOfNetSystem (t1,t2);
		
		if (info==null) return false;
		
		boolean result = canOccurAndEveryOccurrenceIsImmediatelyPrecededSkipSilent(info.t1,info.t2);
				
		this.sys.removeNodes(info.toRemove);
			
		return result;
	}

	@Override
	public boolean alwaysOccursAndIsImmediatelySucceededSkipSilent(T t1, T t2) {
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (!this.alwaysOccurs(t1)) return false;
		
		if (t1.equals(t2)) {
			if (this.isSoundWorkflowNet) {
				P p1 = this.sys.createPlace();
				P p2 = this.sys.createPlace();
				P p3 = this.sys.createPlace();
				p1.setName("TEMP1");
				p2.setName("TEMP2");
				p3.setName("TEMP3");
				
				Collection<T> toRemove = new ArrayList<>();
				for (T t : this.sys.getObservableTransitions()) {
					if (t.equals(t1)) continue;
					
					T tt = this.sys.createTransition();
					toRemove.add(tt);
					tt.setName(t.getName() + "_TEMP");
					for (P p : this.sys.getPreset(t)) this.sys.addFlow(p,tt);
					for (P p : this.sys.getPostset(t)) this.sys.addFlow(tt,p);
					
					this.sys.addFlow(tt,p3);
					this.sys.addFlow(t,p3);
					this.sys.addFlow(p3,t);
					this.sys.addFlow(p3,t1);
					this.sys.addFlow(p2,tt);
					this.sys.addFlow(tt,p1);
				}
				
				this.sys.addFlow(p1,t1);
				this.sys.addFlow(t1,p2);
				
				this.sys.putTokens(p1,1);
				this.sys.putTokens(p3,1);
				
				boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY " + this.o.getName() + " > 0");
				
				this.sys.putTokens(p1,0);
				this.sys.putTokens(p2,0);
				this.sys.putTokens(p3,0);
				
				this.sys.removePlace(p1);
				this.sys.removePlace(p2);
				this.sys.removePlace(p3);
				for (T t : toRemove) this.sys.removeTransition(t);
				
				return !result;
				
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
		else {
			if (this.isSoundWorkflowNet) {			
				P p1 = this.sys.createPlace();
				P p2 = this.sys.createPlace();
				P p3 = this.sys.createPlace();
				P p4 = this.sys.createPlace();
				p1.setName("TEMP1");
				p2.setName("TEMP2");
				p3.setName("TEMP3");
				p4.setName("TEMP4");
				
				T tt1 = this.sys.createTransition();
				tt1.setName("TEMP1");
				for (P p : this.sys.getPreset(t1)) this.sys.addFlow(p,tt1);
				for (P p : this.sys.getPostset(t1)) this.sys.addFlow(tt1,p);
				
				Collection<T> toRemove = new ArrayList<>();
				for (T t : this.sys.getObservableTransitions()) {
					if (t.equals(t1) || t.equals(t2)) continue;
					
					T tt = this.sys.createTransition();
					toRemove.add(tt);
					tt.setName(t.getName() + "_TEMP");
					for (P p : this.sys.getPreset(t)) this.sys.addFlow(p,tt);
					for (P p : this.sys.getPostset(t)) this.sys.addFlow(tt,p);
					
					this.sys.addFlow(t,p4);
					this.sys.addFlow(p4,t);
					this.sys.addFlow(tt,p4);
					this.sys.addFlow(p1,tt);
					this.sys.addFlow(tt,p2);
					this.sys.addFlow(p3,tt);
				}
				
				this.sys.addFlow(t1,p1);
				this.sys.addFlow(tt1,p1);
				this.sys.addFlow(p1,tt1);
				this.sys.addFlow(p4,t1);
				this.sys.addFlow(t1,p3);
				this.sys.addFlow(p2,t2);
				this.sys.addFlow(t2,p2);
				this.sys.addFlow(p2,t1);
				
				this.sys.putTokens(p2,1);
				this.sys.putTokens(p4,1);
				
				boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY " + this.o.getName() + " > 0");
				
				this.sys.putTokens(p1,0);
				this.sys.putTokens(p2,0);
				this.sys.putTokens(p3,0);
				this.sys.putTokens(p4,0);
				
				this.sys.removePlace(p1);
				this.sys.removePlace(p2);
				this.sys.removePlace(p3);
				this.sys.removePlace(p4);
				this.sys.removeTransition(tt1);
				for (T t : toRemove)
					this.sys.removeTransition(t);
				
				return !result;
				
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
	}

	@Override
	public boolean alwaysOccursAndEveryOccurrenceIsImmediatelySucceededSkipSilent(T t1, T t2) {
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (this.isSoundWorkflowNet) {
			if (t1.equals(t2)) {
				return false;
			}
			else {
				if (!this.alwaysOccurs(t1))
					return false;
				
				P p1 = this.sys.createPlace();
				P p2 = this.sys.createPlace();
				p1.setName("TEMP1");
				p2.setName("TEMP2");
				
				T tt2 = this.sys.createTransition();				
				T ttt = this.sys.createTransition();
				tt2.setName("TEMP2");
				ttt.setName("TEMP*");
				
				for (P p : this.sys.getPreset(t2)) this.sys.addFlow(p,tt2);
				for (P p : this.sys.getPostset(t2)) this.sys.addFlow(tt2,p);
				
				this.sys.addFlow(t1,p1);
				this.sys.addFlow(p1,t2);
				this.sys.addFlow(t2,p2);
				this.sys.addFlow(p2,t1);
				this.sys.addFlow(p2,t2);
				this.sys.addFlow(t2,p2);
				
				for (T t : this.sys.getObservableTransitions()) {
					if (t.equals(t1) || t.equals(tt2) || t.equals(t2)) continue;
					
					this.sys.addFlow(t,p2);
					this.sys.addFlow(p2,t);
				}
				
				this.sys.putTokens(p2,1);
				
				this.sys.addFlow(this.o, ttt);
				this.sys.addFlow(ttt, this.i);
				
				boolean result = true;
				result &= this.LoLA.isBounded(this.sys);
				if (result) result &= this.LoLA.isLive(this.sys);
			
				this.sys.putTokens(p1, 0);
				this.sys.putTokens(p2, 0);
				this.sys.removePlace(p1);
				this.sys.removePlace(p2);
				this.sys.removeTransition(tt2);
				this.sys.removeTransition(ttt);
				
				return result;
			}
		} else {
			throw new UnsupportedOperationException("Not implemented yet");
		}
	}

	@Override
	public boolean alwaysOccursAndIsImmediatelyPrecededSkipSilent(T t1, T t2) {
		return this.alwaysOccursAndIsImmediatelySucceededSkipSilent(t2,t1);
	}

	@Override
	public boolean alwaysOccursAndEveryOccurrenceIsImmediatelyPrecededSkipSilent(T t1, T t2) {
		return this.alwaysOccursAndEveryOccurrenceIsImmediatelySucceededSkipSilent(t2,t1);
	}

	@Override
	public boolean canOccurAndIsImmediatelySucceededSkipSilent(T t1, T t2) {
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (t1.equals(t2)) {
			P p = this.sys.createPlace();
			p.setName("TEMP");
			
			T tt1 = this.sys.createTransition();
			tt1.setName("TEMP1");
			
			for (P pp : this.sys.getPreset(t1)) this.sys.addFlow(pp,tt1);
			for (P pp : this.sys.getPostset(t1)) this.sys.addFlow(tt1,pp);
			
			this.sys.addFlow(p,t1);
			this.sys.addFlow(t1,p);
			this.sys.addFlow(p,tt1);
			
			for (T t : this.sys.getObservableTransitions()) {
				if (t.equals(t1) || t.equals(tt1)) continue;
				
				this.sys.addFlow(t,p);
				this.sys.addFlow(p,t);
			}
			
			this.sys.putTokens(p,1);
			
			boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY TEMP = 0 AND " + this.lolaAtLeastOneTokenAtEachPlace(this.sys.getPreset(t1)));
			
			this.sys.putTokens(p,0);
			
			this.sys.removePlace(p);
			this.sys.removeTransition(tt1);
			
			return result;
		}
		else {
			P p1 = this.sys.createPlace();
			P p2 = this.sys.createPlace();
			P p3 = this.sys.createPlace();
			p1.setName("TEMP1");
			p2.setName("TEMP2");
			p3.setName("TEMP3");
			
			T tt1 = this.sys.createTransition();
			T tt2 = this.sys.createTransition();
			tt1.setName("TEMP1");
			tt2.setName("TEMP2");
			
			for (P pp : this.sys.getPreset(t1)) this.sys.addFlow(pp,tt1);
			for (P pp : this.sys.getPostset(t1)) this.sys.addFlow(tt1,pp);
			
			for (P pp : this.sys.getPreset(t2)) this.sys.addFlow(pp,tt2);
			for (P pp : this.sys.getPostset(t2)) this.sys.addFlow(tt2,pp);
			
			this.sys.addFlow(p1,tt1);
			this.sys.addFlow(tt1,p2);
			this.sys.addFlow(p2,t2);
			this.sys.addFlow(p3,t1);
			this.sys.addFlow(t1,p3);
			this.sys.addFlow(p3,tt1);
			this.sys.addFlow(t2,p3);
			this.sys.addFlow(p3,tt2);
			this.sys.addFlow(tt2,p3);
			
			for (T t : this.sys.getObservableTransitions()) {
				if (t.equals(t1) || t.equals(tt1) || t.equals(t2) || t.equals(tt2)) continue;
				
				this.sys.addFlow(t,p3);
				this.sys.addFlow(p3,t);
			}
			
			this.sys.putTokens(p1,1);
			this.sys.putTokens(p3,1);
			
			boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY TEMP1 = 0 AND TEMP2 = 0");
			
			this.sys.putTokens(p1,0);
			this.sys.putTokens(p2,0);
			this.sys.putTokens(p3,0);
			
			this.sys.removePlace(p1);
			this.sys.removePlace(p2);
			this.sys.removePlace(p3);
			this.sys.removeTransition(tt1);
			this.sys.removeTransition(tt2);
			
			return result;
		}
	}

	@Override
	public boolean canOccurAndEveryOccurrenceIsImmediatelySucceededSkipSilent(T t1, T t2) {
		if (!this.sys.getTransitions().contains(t1)) return false;
		if (!this.sys.getTransitions().contains(t2)) return false;
		
		if (t1.equals(t2)) {
			return false;
		}
		else {
			if (this.isSoundWorkflowNet) {
				P p1 = this.sys.createPlace();
				P p2 = this.sys.createPlace();
				P p3 = this.sys.createPlace();
				p1.setName("TEMP1");
				p2.setName("TEMP2");
				p3.setName("TEMP3");
				
				T tt1 = this.sys.createTransition();
				T tt2 = this.sys.createTransition();
				tt1.setName("TEMP1");
				tt2.setName("TEMP2");
		
				for (P p : this.sys.getPreset(t1)) this.sys.addFlow(p,tt1);
				for (P p : this.sys.getPostset(t1)) this.sys.addFlow(tt1,p);
				
				for (P p : this.sys.getPreset(t2)) this.sys.addFlow(p,tt2);
				for (P p : this.sys.getPostset(t2)) this.sys.addFlow(tt2,p);
				
				this.sys.addFlow(p1,tt1);
				this.sys.addFlow(tt1,p2);
				this.sys.addFlow(t1,p2);
				this.sys.addFlow(p2,t2);
				this.sys.addFlow(t2,p3);
				this.sys.addFlow(p3,t1);
				this.sys.addFlow(p3,tt1);
				this.sys.addFlow(p3,tt2);
				this.sys.addFlow(tt2,p3);
				
				for (T t : this.sys.getObservableTransitions()) {
					if (t.equals(t1) || t.equals(tt1) || t.equals(t2) || t.equals(tt2)) continue;
					
					this.sys.addFlow(t,p3);
					this.sys.addFlow(p3,t);
				}
				
				this.sys.putTokens(p1,1);
				this.sys.putTokens(p3,1);
				
				boolean result = this.LoLA.checkCTL(this.sys, "EXPATH EVENTUALLY TEMP1 = 0 AND TEMP3 = 1 AND " + this.o.getName() + " = 1");
				
				this.sys.putTokens(p1, 0);
				this.sys.putTokens(p2, 0);
				this.sys.putTokens(p3, 0);
				this.sys.removePlace(p1);
				this.sys.removePlace(p2);
				this.sys.removePlace(p3);
				this.sys.removeTransition(tt1);
				this.sys.removeTransition(tt2);
				
				return result;
			} else {
				throw new UnsupportedOperationException("Not implemented yet");
			}
		}
	}

	@Override
	public boolean canOccurAndIsImmediatelyPrecededSkipSilent(T t1, T t2) {
		return this.canOccurAndIsImmediatelySucceededSkipSilent(t2,t1);
	}

	@Override
	public boolean canOccurAndEveryOccurrenceIsImmediatelyPrecededSkipSilent(T t1, T t2) {
		return this.canOccurAndEveryOccurrenceIsImmediatelySucceededSkipSilent(t2,t1);
	}*/
}
