package org.pql.petri;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;

/** 
 * @author Artem Polyvyanyy
 */
public class AbstractControlPlaceTransformation<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
				extends AbstractNetSystemTransformation<F,N,P,T,M> 
				implements IControlPlaceTransformation<F,N,P,T,M> {

	private P controlPlace = null;
	
	private T t = null;
	private ILabelUnificationTransformation<F,N,P,T,M> lut =  null;
		
	public AbstractControlPlaceTransformation(INetSystem<F,N,P,T,M> sys, T t) {
		super(sys);
		
		this.t = t;
	}

	public AbstractControlPlaceTransformation(INetSystem<F,N,P,T,M> sys, ILabelUnificationTransformation<F,N,P,T,M> lut) {
		super(sys);
		
		this.lut = lut;
	}

	@Override
	public P getControlPlace() {
		if (this.isApplied()) return this.controlPlace;
		
		return null;
	}
	
	@Override
	public NetSystemTransformationType getType() {
		return NetSystemTransformationType.CONTROL_PLACE;
	}

	@Override
	protected void applyTransormation() {
		if (this.t==null) this.t = this.lut.getUnifiedTransition();
		
		this.controlPlace = this.sys.createPlace();
		this.controlPlace.setName("CONTROLPLACE"+Math.abs(this.controlPlace.hashCode()));		
		this.sys.addFlow(this.controlPlace, this.t);
		this.sys.putTokens(this.controlPlace, 1);
		
		this.freshPs.add(this.controlPlace);
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
	public void substitute(INetSystemTransformation<F,N,P,T,M> tr) {
		super.substitute(tr);
		
		if (tr instanceof IControlPlaceTransformation<?,?,?,?,?>) {
			IControlPlaceTransformation<F,N,P,T,M> cpt = (IControlPlaceTransformation<F,N,P,T,M>) tr;
			this.controlPlace = cpt.getControlPlace();
		}
	}
}
