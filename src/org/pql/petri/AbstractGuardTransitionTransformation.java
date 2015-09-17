package org.pql.petri;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;

/** 
 * @author Artem Polyvyanyy
 * 
 * See Def. 5.1. in Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
 */
public class AbstractGuardTransitionTransformation<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
				extends AbstractNetSystemTransformation<F,N,P,T,M> 
				implements IGuardTransitionTransformation<F,N,P,T,M> {

	private T t = null;
	private ILabelUnificationTransformation<F,N,P,T,M> lut = null;
	
	private P controlPlace = null;
	private P guardPlace = null;
		
	public AbstractGuardTransitionTransformation(INetSystem<F,N,P,T,M> sys, T t) {
		super(sys);
		
		this.t = t;
	}
	
	public AbstractGuardTransitionTransformation(INetSystem<F,N,P,T,M> sys, ILabelUnificationTransformation<F,N,P,T,M> lut) {
		super(sys);
		
		this.lut = lut;
	}

	@Override
	public NetSystemTransformationType getType() {
		return NetSystemTransformationType.GUARD_TRANSITION;
	}

	@Override
	protected void applyTransormation() {
		if (this.t==null) this.t = this.lut.getUnifiedTransition();
		
		this.controlPlace	 = this.sys.createPlace();
		this.guardPlace		 = this.sys.createPlace();
		T tt = this.sys.createTransition(); // guard transition
		
		this.controlPlace.setName("CONTROLPLACE"+Math.abs(this.controlPlace.hashCode()));		
		this.guardPlace.setName("GUARDPLACE"+Math.abs(this.guardPlace.hashCode()));
		tt.setName("GUARDTRANSITION"+Math.abs(tt.hashCode()));
		
		for (P x : this.sys.getPreset(this.t)) this.sys.addFlow(x,tt);
		for (P x : this.sys.getPostset(this.t)) this.sys.addFlow(tt,x);
		
		this.sys.addFlow(this.guardPlace,tt);
		this.sys.addFlow(tt,this.controlPlace);
		this.sys.addFlow(this.controlPlace,this.t);
		this.sys.addFlow(this.t,this.controlPlace);

		this.sys.putTokens(this.guardPlace,1);
		
		this.freshPs.add(this.controlPlace);
		this.freshPs.add(this.guardPlace);
		this.freshTs.add(tt);
	}
	
	@Override
	public int hashCode() {
		if (this.lut!=null)
			return this.getType().getTypeCode() * this.lut.hashCode();
		else
			return this.getType().getTypeCode() * this.t.hashCode(); 
	}

	@Override
	public String toString() {
		return String.format("%s[%s]", this.getType(), this.t);
	}

	@Override
	public P getControlPlace() {
		if (this.isApplied()) return this.controlPlace;
		
		return null;
	}

	@Override
	public P getGuardPlace() {
		if (this.isApplied()) return this.guardPlace;
		
		return null;
	}
	
	@Override
	public void substitute(INetSystemTransformation<F,N,P,T,M> tr) {
		super.substitute(tr);
		
		if (tr instanceof IGuardTransitionTransformation<?,?,?,?,?>) {
			IGuardTransitionTransformation<F,N,P,T,M> gtt = (IGuardTransitionTransformation<F,N,P,T,M>) tr;
			this.controlPlace = gtt.getControlPlace();
			this.guardPlace = gtt.getGuardPlace();
			this.t =  gtt.getGuardedTransition();
			this.lut = gtt.getGuardedTransformation();
					
		}
	}

	@Override
	public T getGuardedTransition() {
		return this.t;
	}

	@Override
	public ILabelUnificationTransformation<F,N,P,T,M> getGuardedTransformation() {
		return this.lut;
	}
		
}
