package org.pql.petri;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;

/** 
 * @author Artem Polyvyanyy
 */
public class AbstractLabelUnificationTransformation<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
				extends AbstractNetSystemTransformation<F,N,P,T,M> 
				implements ILabelUnificationTransformation<F,N,P,T,M> {

	private Set<String> labels = null;
	
	private T unifiedTransition = null;
	
	private Map<T,Set<T>> UT2T = null; //A.P.


		
	public AbstractLabelUnificationTransformation(INetSystem<F,N,P,T,M> sys, Set<String> labels) {
		super(sys);
		this.UT2T = new HashMap<T,Set<T>>(); //A.P.
		this.labels = labels;
	}
	
	@Override
	public NetSystemTransformationType getType() {
		return NetSystemTransformationType.LABEL_UNIFICATION;
	}

	@Override
	protected void applyTransormation() {
		// get transitions with labels of interest
		Set<String> ls = new HashSet<String>();
		for (String s : this.labels) ls.add(s.trim().toLowerCase());
		
		Set<T> ts = new HashSet<T>();
		for (T t : this.sys.getTransitions()) {
			if (ls.contains(t.getLabel().trim().toLowerCase()))
				ts.add(t);
		}
		
		// unify
		if (ts.size()<1) {
			this.unifiedTransition = null;
			return;
		}
		else if (ts.size()==1) {
			this.unifiedTransition = ts.iterator().next();
			return;
		}
		else { // complex case
			P p1 = this.sys.createPlace();
			p1.setName("p"+Math.abs(p1.hashCode()));
			P p2 = this.sys.createPlace();
			p2.setName("p"+Math.abs(p2.hashCode()));
			this.unifiedTransition = this.sys.createTransition();
			this.unifiedTransition.setName("UNIFIEDTRANSITION"+Math.abs(this.unifiedTransition.hashCode()));
			this.freshPs.add(p1);
			this.freshPs.add(p2);
			this.freshTs.add(this.unifiedTransition);
			this.sys.addFlow(p1,this.unifiedTransition);
			this.sys.addFlow(this.unifiedTransition,p2);
			
			/*try {
				IOUtils.invokeDOT(".", "sys1.png", this.sys.toDOT());
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			
			for (T tt : ts) {			
				T tt1 = this.sys.createTransition();
				tt1.setName("t"+Math.abs(tt1.hashCode()));
				T tt2 = this.sys.createTransition();
				tt2.setName("t"+Math.abs(tt2.hashCode()));
				this.freshTs.add(tt1);
				this.freshTs.add(tt2);
				
				//A.P.
				if(UT2T.containsKey(this.unifiedTransition))
				{
					UT2T.get(this.unifiedTransition).add(tt);
				}
				else
				{
					UT2T.put(this.unifiedTransition, new HashSet<T>());
					UT2T.get(this.unifiedTransition).add(tt);
				}
				
				for (P pp : this.sys.getPreset(tt))
					this.sys.addFlow(pp, tt1);
				
				/*try {
					IOUtils.invokeDOT(".", "sys1.png", this.sys.toDOT());
				} catch (IOException e) {
					e.printStackTrace();
				}*/
				
				for (P pp : this.sys.getPostset(tt))
					this.sys.addFlow(tt2, pp);
				
				/*try {
					IOUtils.invokeDOT(".", "sys1.png", this.sys.toDOT());
				} catch (IOException e) {
					e.printStackTrace();
				}*/
				
				P pp = this.sys.createPlace();
				pp.setName("p"+Math.abs(pp.hashCode()));
				this.freshPs.add(pp);
				
				this.sys.addFlow(tt1, pp);
				this.sys.addFlow(tt1, p1);
				this.sys.addFlow(pp, tt2);
				this.sys.addFlow(p2, tt2);
				
				/*try {
					IOUtils.invokeDOT(".", "sys1.png", this.sys.toDOT());
				} catch (IOException e) {
					e.printStackTrace();
				}*/
			}
			
			P p = this.sys.createPlace();
			p.setName("p"+Math.abs(p.hashCode()));
			this.freshPs.add(p);
			for (T tt : ts)  this.sys.addFlow(p,tt);
			
			/*try {
				IOUtils.invokeDOT(".", "sys1.png", this.sys.toDOT());
			} catch (IOException e) {
				e.printStackTrace();
			}*/		
		}
			
		
		//this.freshPs.add(this.controlPlace);*/
	}
	
	@Override
	public int hashCode() {
		int result = this.getType().getTypeCode();
		for (String s : this.labels) result *= s.hashCode();
		
		return result; 
	}

	@Override
	public String toString() {
		return String.format("%s[%s]", this.getType(), this.labels);
	}

	@Override
	public T getUnifiedTransition() {
		if (this.isApplied()) return this.unifiedTransition;
		
		return null;
	}
	
	@Override
	public void substitute(INetSystemTransformation<F,N,P,T,M> tr) {
		super.substitute(tr);
		
		if (tr instanceof ILabelUnificationTransformation<?,?,?,?,?>) {
			ILabelUnificationTransformation<F,N,P,T,M> lut = (ILabelUnificationTransformation<F,N,P,T,M>) tr;
			this.labels = lut.getLabels();
			this.unifiedTransition = lut.getUnifiedTransition();
		}
	}

	@Override
	public Set<String> getLabels() {
		return this.labels;
	}
	
	//A.P.
	@Override
	public Map<T,Set<T>> getUT2T() {
		if (this.isApplied()) return this.UT2T;
			
		return null;
	}


}
