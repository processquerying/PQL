package org.pql.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jbpt.algo.graph.TransitiveClosure;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.jbpt.petri.Transition;
import org.jbpt.petri.structure.PetriNetStructuralChecks;
import org.jbpt.petri.unfolding.AbstractCompletePrefixUnfolding;
import org.jbpt.petri.unfolding.CompletePrefixUnfoldingSetup;
import org.jbpt.petri.unfolding.Event;
import org.jbpt.petri.unfolding.IEvent;
import org.jbpt.petri.unfolding.IOccurrenceNet;
import org.jbpt.petri.unfolding.order.AdequateOrderType;
import org.json.JSONArray;
import org.pql.mc.IModelChecker;
import org.pql.petri.AbstractControlPlaceTransformation;
import org.pql.petri.AbstractGuardTransitionTransformation;
import org.pql.petri.AbstractLabelUnificationTransformation;
import org.pql.petri.AbstractNetSystemTransformationManager;
import org.pql.petri.AbstractPrecedenceTestTransformation;
import org.pql.petri.IControlPlaceTransformation;
import org.pql.petri.IGuardTransitionTransformation;
import org.pql.petri.ILabelUnificationTransformation;
import org.pql.petri.IPrecedenceTestTransformation;
import org.pql.petri.TransformationLog;

/**
 * An implementation of {@link IPQLBasicPredicatesOnTransitions} and {@link IPQLBasicPredicatesOnTasks} interfaces using model checking techniques.
 * 
 * @author Artem Polyvyanyy
 */
@SuppressWarnings("unchecked")
public class AbstractPQLBasicPredicatesMC<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>>
	implements IPQLBasicPredicatesOnTransitions<F,N,P,T,M>, 
			   IPQLBasicPredicatesOnTasks {
	
	private INetSystem<F,N,P,T,M>	 clonedNetSystem	= null;
	
	private Map<N,N>				 n2n				= null;
	
	private IModelChecker<F,N,P,T,M> modelChecker		= null;
	
	private AbstractNetSystemTransformationManager<F,N,P,T,M> TM = null;
	
	private P sinkPlace = null;

	public AbstractPQLBasicPredicatesMC(IModelChecker<F,N,P,T,M> modelChecker) {
		this.modelChecker = modelChecker;
		this.n2n = new HashMap<N,N>();
	}
	
	@Override
	public void configure(Object obj) throws PQLException {
		if (obj == null) throw new PQLException("Configuration object is NULL.");
		
		if (!(obj instanceof INetSystem)) throw new PQLException("Configuration object is not an INetSystem.");
		
		INetSystem<F,N,P,T,M> originalNetSystem = (INetSystem<F,N,P,T,M>) obj;
		
		PetriNetStructuralChecks<F,N,P,T> check = new PetriNetStructuralChecks<F,N,P,T>();
		boolean wf = check.isWorkflowNet(originalNetSystem);
		if (!wf) throw new PQLException("Configuration object is not a workflow net.");
		
		this.n2n.clear();
		this.clonedNetSystem = originalNetSystem.clone(this.n2n);
		this.sinkPlace = this.clonedNetSystem.getSinkPlaces().iterator().next();
		this.TM = new AbstractNetSystemTransformationManager<F,N,P,T,M>(this.clonedNetSystem);
	}
		
	@Override
	public boolean canOccur(T t) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		T tt = (T)this.n2n.get(t);
		if (!this.TM.getNetSystem().getTransitions().contains(tt)) return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		
		// transform net system
		this.TM.transform(log);

		// perform check
		return this.modelChecker.canReachMarkingWithAtLeastOneTokenAtEachPlace(this.TM.getNetSystem(),this.TM.getNetSystem().getPreset(tt));
	}
	
	@Override
	public boolean canOccur(PQLTask task) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		ILabelUnificationTransformation<F,N,P,T,M> lut = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),task.getSimilarLabels());
		log.add(lut);
		
		// transform net system
		this.TM.transform(log);
		
		if (lut.getUnifiedTransition()==null) return false;
				
		// perform check
		return this.modelChecker.canReachMarkingWithAtLeastOneTokenAtEachPlace(this.TM.getNetSystem(),this.TM.getNetSystem().getPreset(lut.getUnifiedTransition()));
	}
	
	//A.P.
	@Override
	public boolean canOccur(PQLTask task, Set<Process> p) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		ILabelUnificationTransformation<F,N,P,T,M> lut = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),task.getSimilarLabels());
		log.add(lut);
		
		// transform net system
		this.TM.transform(log);
		
		if (lut.getUnifiedTransition()==null) return false;
				
		// perform check
		return this.modelChecker.canReachMarkingWithAtLeastOneTokenAtEachPlace(this.TM.getNetSystem(),this.TM.getNetSystem().getPreset(lut.getUnifiedTransition()), p);
	}

	
	@Override
	public boolean alwaysOccurs(T t) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		T tt = (T)this.n2n.get(t);
		if (!this.TM.getNetSystem().getTransitions().contains(tt)) return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		IControlPlaceTransformation<F,N,P,T,M> cpt = new AbstractControlPlaceTransformation<F,N,P,T,M>(this.TM.getNetSystem(),tt);
		log.add(cpt);
		
		// transform net system
		this.TM.transform(log);
		
		// perform check
		Collection<P> M = new ArrayList<P>();
		M.add(cpt.getControlPlace());
		M.add(this.sinkPlace);
		return !this.modelChecker.isReachable(this.TM.getNetSystem(),M);
	}
	
	@Override
	public boolean alwaysOccurs(PQLTask t) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		ILabelUnificationTransformation<F,N,P,T,M> lut = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t.getSimilarLabels());
		IControlPlaceTransformation<F,N,P,T,M> cpt = new AbstractControlPlaceTransformation<F,N,P,T,M>(this.TM.getNetSystem(),lut);
		
		log.add(lut);
		log.add(cpt);
		
		// transform net system
		this.TM.transform(log);
				
		// perform check
		Collection<P> M = new ArrayList<P>();
		M.add(cpt.getControlPlace());
		M.add(this.sinkPlace);
		return !this.modelChecker.isReachable(this.TM.getNetSystem(),M);
	}
	
	@Override
	public boolean alwaysOccurs(PQLTask t, Set<Process> p) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		ILabelUnificationTransformation<F,N,P,T,M> lut = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t.getSimilarLabels());
		IControlPlaceTransformation<F,N,P,T,M> cpt = new AbstractControlPlaceTransformation<F,N,P,T,M>(this.TM.getNetSystem(),lut);
		
		log.add(lut);
		log.add(cpt);
		
		// transform net system
		this.TM.transform(log);
				
		// perform check
		Collection<P> M = new ArrayList<P>();
		M.add(cpt.getControlPlace());
		M.add(this.sinkPlace);
		return !this.modelChecker.isReachable(this.TM.getNetSystem(),M,p);
	}
	
	@Override
	public boolean canConflict(T t1, T t2) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		T tt1 = (T)this.n2n.get(t1);
		T tt2 = (T)this.n2n.get(t2);
		if (!this.TM.getNetSystem().getTransitions().contains(tt1)) return false;
		if (!this.TM.getNetSystem().getTransitions().contains(tt2)) return false;
		
		// handle special case
		if (t1.equals(t2))
			return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		IGuardTransitionTransformation<F,N,P,T,M> gtt1 = new AbstractGuardTransitionTransformation<F,N,P,T,M>(this.TM.getNetSystem(),tt1);
		IGuardTransitionTransformation<F,N,P,T,M> gtt2 = new AbstractGuardTransitionTransformation<F,N,P,T,M>(this.TM.getNetSystem(),tt2);
		
		if (gtt1.equals(gtt2)) {
			log.add(gtt1);
			log.add(gtt2);
		}
		else
			log.add(gtt1,gtt2);
		
		// transform net system
		this.TM.transform(log);
		
		// perform check
		Collection<P> M = new ArrayList<P>();
		M.add(gtt1.getControlPlace());
		M.add(gtt2.getGuardPlace());
		M.add(this.sinkPlace);
		return this.modelChecker.isReachable(this.TM.getNetSystem(),M);
	}
	
	@Override
	public boolean canConflict(PQLTask t1, PQLTask t2) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		
		// handle special case
		if (t1.getSimilarLabels().equals(t2.getSimilarLabels()))
			return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		ILabelUnificationTransformation<F,N,P,T,M> lut1 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t1.getSimilarLabels());
		ILabelUnificationTransformation<F,N,P,T,M> lut2 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t2.getSimilarLabels());
		IGuardTransitionTransformation<F,N,P,T,M> gtt1 = new AbstractGuardTransitionTransformation<F,N,P,T,M>(this.TM.getNetSystem(),lut1);
		IGuardTransitionTransformation<F,N,P,T,M> gtt2 = new AbstractGuardTransitionTransformation<F,N,P,T,M>(this.TM.getNetSystem(),lut2);
		
		if (lut1.equals(lut2)) {
			log.add(lut1);
			log.add(lut2);
		}
		else
			log.add(lut1,lut2);
		
		if (gtt1.equals(gtt2)) {
			log.add(gtt1);
			log.add(gtt2);
		}
		else
			log.add(gtt1,gtt2);
		
		// transform net system
		this.TM.transform(log);
		
		// perform check
		Collection<P> M = new ArrayList<P>();
		M.add(gtt1.getControlPlace());
		M.add(gtt2.getGuardPlace());
		M.add(this.sinkPlace);
		return this.modelChecker.isReachable(this.TM.getNetSystem(),M);
	}
	
	//A.P.
	@Override
	public boolean canConflict(PQLTask t1, PQLTask t2, Set<Process> p) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		
		// handle special case
		if (t1.getSimilarLabels().equals(t2.getSimilarLabels()))
			return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		ILabelUnificationTransformation<F,N,P,T,M> lut1 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t1.getSimilarLabels());
		ILabelUnificationTransformation<F,N,P,T,M> lut2 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t2.getSimilarLabels());
		IGuardTransitionTransformation<F,N,P,T,M> gtt1 = new AbstractGuardTransitionTransformation<F,N,P,T,M>(this.TM.getNetSystem(),lut1);
		IGuardTransitionTransformation<F,N,P,T,M> gtt2 = new AbstractGuardTransitionTransformation<F,N,P,T,M>(this.TM.getNetSystem(),lut2);
		
		if (lut1.equals(lut2)) {
			log.add(lut1);
			log.add(lut2);
		}
		else
			log.add(lut1,lut2);
		
		if (gtt1.equals(gtt2)) {
			log.add(gtt1);
			log.add(gtt2);
		}
		else
			log.add(gtt1,gtt2);
		
		// transform net system
		this.TM.transform(log);
		
		// perform check
		Collection<P> M = new ArrayList<P>();
		M.add(gtt1.getControlPlace());
		M.add(gtt2.getGuardPlace());
		M.add(this.sinkPlace);
		return this.modelChecker.isReachable(this.TM.getNetSystem(),M,p);
	}
	
	@Override
	public boolean canCooccur(T t1, T t2) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		T tt1 = (T)this.n2n.get(t1);
		T tt2 = (T)this.n2n.get(t2);
		if (!this.TM.getNetSystem().getTransitions().contains(tt1)) return false;
		if (!this.TM.getNetSystem().getTransitions().contains(tt2)) return false;
		
		// handle special case
		if (t1.equals(t2))
			return this.canOccur(t1);
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		IGuardTransitionTransformation<F,N,P,T,M> gtt1 = new AbstractGuardTransitionTransformation<F,N,P,T,M>(this.TM.getNetSystem(),tt1);
		IGuardTransitionTransformation<F,N,P,T,M> gtt2= new AbstractGuardTransitionTransformation<F,N,P,T,M>(this.TM.getNetSystem(),tt2);
		
		if (gtt1.equals(gtt2)) {
			log.add(gtt1);
			log.add(gtt2);
		}
		else
			log.add(gtt1,gtt2);
		
		
		// transform net system
		this.TM.transform(log);
		
		// perform check
		Collection<P> M = new ArrayList<P>();
		M.add(gtt1.getControlPlace());
		M.add(gtt2.getControlPlace());
		M.add(this.sinkPlace);
		return this.modelChecker.isReachable(this.TM.getNetSystem(),M);
	}
	
	@Override
	public boolean canCooccur(PQLTask t1, PQLTask t2) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		
		// handle special case
		if (t1.getSimilarLabels().equals(t2.getSimilarLabels()))
			return this.canOccur(t1);
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		ILabelUnificationTransformation<F,N,P,T,M> lut1 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t1.getSimilarLabels());
		ILabelUnificationTransformation<F,N,P,T,M> lut2 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t2.getSimilarLabels());
		IGuardTransitionTransformation<F,N,P,T,M> gtt1 = new AbstractGuardTransitionTransformation<F,N,P,T,M>(this.TM.getNetSystem(),lut1);
		IGuardTransitionTransformation<F,N,P,T,M> gtt2 = new AbstractGuardTransitionTransformation<F,N,P,T,M>(this.TM.getNetSystem(),lut2);
		
		if (lut1.equals(lut2)) {
			log.add(lut1);
			log.add(lut2);
		}
		else
			log.add(lut1,lut2);
		
		if (gtt1.equals(gtt2)) {
			log.add(gtt1);
			log.add(gtt2);
		}
		else
			log.add(gtt1,gtt2);
		
		// transform net system
		this.TM.transform(log);
		
		// perform check
		Collection<P> M = new ArrayList<P>();
		M.add(gtt1.getControlPlace());
		M.add(gtt2.getControlPlace());
		M.add(this.sinkPlace);
		return this.modelChecker.isReachable(this.TM.getNetSystem(),M);
	}
	
	//A.P.
	@Override
	public boolean canCooccur(PQLTask t1, PQLTask t2, Set<Process> p) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		
		// handle special case
		if (t1.getSimilarLabels().equals(t2.getSimilarLabels()))
			return this.canOccur(t1,p);
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		ILabelUnificationTransformation<F,N,P,T,M> lut1 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t1.getSimilarLabels());
		ILabelUnificationTransformation<F,N,P,T,M> lut2 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t2.getSimilarLabels());
		IGuardTransitionTransformation<F,N,P,T,M> gtt1 = new AbstractGuardTransitionTransformation<F,N,P,T,M>(this.TM.getNetSystem(),lut1);
		IGuardTransitionTransformation<F,N,P,T,M> gtt2 = new AbstractGuardTransitionTransformation<F,N,P,T,M>(this.TM.getNetSystem(),lut2);
		
		if (lut1.equals(lut2)) {
			log.add(lut1);
			log.add(lut2);
		}
		else
			log.add(lut1,lut2);
		
		if (gtt1.equals(gtt2)) {
			log.add(gtt1);
			log.add(gtt2);
		}
		else
			log.add(gtt1,gtt2);
		
		// transform net system
		this.TM.transform(log);
		
		// perform check
		Collection<P> M = new ArrayList<P>();
		M.add(gtt1.getControlPlace());
		M.add(gtt2.getControlPlace());
		M.add(this.sinkPlace);
		return this.modelChecker.isReachable(this.TM.getNetSystem(),M,p);
	}
	
	@Override
	public boolean conflict(T t1, T t2) {
		return this.canConflict(t1,t2) && this.canConflict(t2,t1) && !this.canCooccur(t1,t2);
	}
	
	@Override
	public boolean conflict(PQLTask t1, PQLTask t2) {
		return this.canConflict(t1,t2) && this.canConflict(t2,t1) && !this.canCooccur(t1,t2);	
	}

	@Override
	public boolean cooccur(T t1, T t2) {
		return !this.canConflict(t1,t2) && !this.canConflict(t2,t1) && this.canCooccur(t1,t2);
	}
	
	@Override
	public boolean cooccur(PQLTask t1, PQLTask t2) {
		return !this.canConflict(t1,t2) && !this.canConflict(t2,t1) && this.canCooccur(t1,t2);
	}
	
	@Override
	public boolean totalCausal(T t1, T t2) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		T tt1 = (T)this.n2n.get(t1);
		T tt2 = (T)this.n2n.get(t2);
		if (!this.TM.getNetSystem().getTransitions().contains(tt1)) return false;
		if (!this.TM.getNetSystem().getTransitions().contains(tt2)) return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		IPrecedenceTestTransformation<F,N,P,T,M> ptt = new AbstractPrecedenceTestTransformation<F,N,P,T,M>(this.TM.getNetSystem(),tt2,tt1);
		log.add(ptt);
		
		// transform net system
		this.TM.transform(log);
				
		// perform check
		Collection<P> M = new ArrayList<P>();
		M.add(ptt.getControlPlace());
		M.add(this.sinkPlace);
		return !this.modelChecker.isReachable(this.TM.getNetSystem(),M);
	}
	
	@Override
	public boolean totalCausal(PQLTask t1, PQLTask t2) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		ILabelUnificationTransformation<F,N,P,T,M> lut1 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t1.getSimilarLabels());
		ILabelUnificationTransformation<F,N,P,T,M> lut2 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t2.getSimilarLabels());
		IPrecedenceTestTransformation<F,N,P,T,M> ptt = new AbstractPrecedenceTestTransformation<F,N,P,T,M>(this.TM.getNetSystem(),lut2,lut1);
		
		if (lut1.equals(lut2)) {
			log.add(lut1);
			log.add(lut2);
		}
		else
			log.add(lut1,lut2);
		
		
		log.add(ptt);
		
		// transform net system
		this.TM.transform(log);
				
		// perform check
		Collection<P> M = new ArrayList<P>();
		M.add(ptt.getControlPlace());
		M.add(this.sinkPlace);
		return !this.modelChecker.isReachable(this.TM.getNetSystem(),M);
	}
	
	//A.P.
	@Override
	public boolean totalCausal(PQLTask t1, PQLTask t2, Set<Process> p) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		ILabelUnificationTransformation<F,N,P,T,M> lut1 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t1.getSimilarLabels());
		ILabelUnificationTransformation<F,N,P,T,M> lut2 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t2.getSimilarLabels());
		IPrecedenceTestTransformation<F,N,P,T,M> ptt = new AbstractPrecedenceTestTransformation<F,N,P,T,M>(this.TM.getNetSystem(),lut2,lut1);
		
		if (lut1.equals(lut2)) {
			log.add(lut1);
			log.add(lut2);
		}
		else
			log.add(lut1,lut2);
		
		
		log.add(ptt);
		
		// transform net system
		this.TM.transform(log);
				
		// perform check
		Collection<P> M = new ArrayList<P>();
		M.add(ptt.getControlPlace());
		M.add(this.sinkPlace);
		return !this.modelChecker.isReachable(this.TM.getNetSystem(),M,p);
	}
	
	@Override
	public boolean totalConcur(T t1, T t2) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		T tt1 = (T)this.n2n.get(t1);
		T tt2 = (T)this.n2n.get(t2);
		
		if (!this.TM.getNetSystem().getTransitions().contains(tt1)) return false;
		if (!this.TM.getNetSystem().getTransitions().contains(tt2)) return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
				
		// transform net system
		this.TM.transform(log);
		
		// perform check
		return this.checkTotalConcur(tt1,tt2);
	}	
	
	@Override
	public boolean totalConcur(PQLTask t1, PQLTask t2) {
		// perform initial checks
		if (this.TM.getNetSystem()==null) return false;
		
		// construct transformation
		TransformationLog<F,N,P,T,M> log = new TransformationLog<F,N,P,T,M>();
		ILabelUnificationTransformation<F,N,P,T,M> lut1 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t1.getSimilarLabels());
		ILabelUnificationTransformation<F,N,P,T,M> lut2 = new AbstractLabelUnificationTransformation<F,N,P,T,M>(this.TM.getNetSystem(),t2.getSimilarLabels());
		
		if (lut1.equals(lut2)) {
			log.add(lut1);
			log.add(lut2);
		}
		else
			log.add(lut1,lut2);
		
		// transform net system
		this.TM.transform(log);
		
		// perform check
		return this.checkTotalConcur(lut1.getUnifiedTransition(),lut2.getUnifiedTransition());
	}
	
	@SuppressWarnings({ "rawtypes" })
	private boolean checkTotalConcur(T t1, T t2) {
		CompletePrefixUnfoldingSetup setup = new CompletePrefixUnfoldingSetup();
		setup.ADEQUATE_ORDER = AdequateOrderType.ESPARZA_FOR_ARBITRARY_SYSTEMS;
		setup.SAFE_OPTIMIZATION = false;
		setup.MAX_EVENTS = Integer.MAX_VALUE;
		setup.MAX_BOUND = Integer.MAX_VALUE;
		
		AbstractCompletePrefixUnfolding unf = new AbstractCompletePrefixUnfolding(this.TM.getNetSystem(), setup);
		
		IOccurrenceNet occNet = unf.getOccurrenceNet();
		Set<Event> cutoffs = unf.getCutoffEvents();
		
		Set<T> ts = new HashSet<T>(occNet.getTransitions());
		for (Event cutoff : cutoffs) {
			IEvent corr = unf.getCorrespondingEvent(cutoff);
			
			T tCutoff = (T) occNet.getTransition(cutoff);
			T tCorr = (T) occNet.getTransition(corr);
			
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
		
		TransitiveClosure<F,N> TC = new TransitiveClosure<F,N>(occNet);
		for (T tt1 : ts) {
			for (T tt2 : ts) {
				if (tt1.equals(tt2)) continue;
				
				if (!occNet.getEvent(tt1).getTransition().equals(t1)) continue;
				if (!occNet.getEvent(tt2).getTransition().equals(t2)) continue;
				
				if (TC.hasPath((N)tt1,(N)tt2) || TC.hasPath((N)tt2,(N)tt1)) 
					return false;
			}
		}
		
		return true;
	}
	//A.P.
	@Override
	public boolean executes(PQLTrace trace) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//A.P.
	@Override
	public boolean repairNet(PQLTrace trace) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//A.P.
	@Override
	public String getRepairedID() {
		// TODO Auto-generated method stub
		return null;
	}
    
	//A.P.
	@Override
	public boolean checkUnaryPredicateMacroV2(String op, String q, JSONArray ids) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//A.P.
	@Override
	public boolean checkBinaryPredicateMacro(String op, String q,
			JSONArray ids1, JSONArray ids2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//A.P.
	@Override
	public boolean checkCooccurMacro(String q, JSONArray ids1, JSONArray ids2) {
		// TODO Auto-generated method stub
		return false;
	}
	//A.P.
	@Override
	public boolean checkConflictMacro(String q, JSONArray ids1, JSONArray ids2) {
		// TODO Auto-generated method stub
		return false;
	}
	//A.P.
	@Override
	public boolean checkCanConflictMacro(String q, JSONArray ids1,
			JSONArray ids2) {
		// TODO Auto-generated method stub
		return false;
	}
	//A.P.
	@Override
	public boolean checkTotalCausalMacro(String q, JSONArray ids1,
			JSONArray ids2) {
		// TODO Auto-generated method stub
		return false;
	}
	//A.P.
	@Override
	public boolean checkTotalConcurMacro(String q, JSONArray ids1,
			JSONArray ids2) {
		// TODO Auto-generated method stub
		return false;
	}

	
}