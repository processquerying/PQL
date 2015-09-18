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
 * See Def. 5.5. in Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
 */
public class AbstractPrecedenceTestTransformation<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
				extends AbstractNetSystemTransformation<F,N,P,T,M> 
				implements IPrecedenceTestTransformation<F,N,P,T,M> {

	private T t1 = null;
	private T t2 = null;
	private ILabelUnificationTransformation<F,N,P,T,M> lut1 = null;
	private ILabelUnificationTransformation<F,N,P,T,M> lut2 = null;
	
	private P controlPlace = null;
		
	public AbstractPrecedenceTestTransformation(INetSystem<F,N,P,T,M> sys, T t1, T t2) {
		super(sys);
		
		this.t1 = t1;
		this.t2 = t2;
	}
	
	public AbstractPrecedenceTestTransformation(INetSystem<F, N, P, T, M> sys, ILabelUnificationTransformation<F,N,P,T,M> lut1, ILabelUnificationTransformation<F,N,P,T,M> lut2) {
		super(sys);
		
		this.lut1 = lut1;
		this.lut2 = lut2;
	}

	@Override
	public NetSystemTransformationType getType() {
		return NetSystemTransformationType.PRECEDENCE_TEST;
	}

	@Override
	protected void applyTransormation() {
		if (this.t1==null) this.t1 = this.lut1.getUnifiedTransition();
		if (this.t2==null) this.t2 = this.lut2.getUnifiedTransition();
		
		P p = this.sys.createPlace();
		p.setName("CONTROLPLACE"+Math.abs(p.hashCode()));
		P q = this.sys.createPlace();
		q.setName("CONTROLPLACE"+Math.abs(q.hashCode()));
		
		this.controlPlace = this.sys.createPlace();
		this.controlPlace.setName("CONTROLPLACE"+Math.abs(this.controlPlace.hashCode()));
		
		T x = this.sys.createTransition();
		x.setName("GUARDTRANSITION"+Math.abs(x.hashCode()));
		T y = this.sys.createTransition();
		y.setName("GUARDTRANSITION"+Math.abs(y.hashCode()));
		
		this.sys.addFlow(p,x);
		this.sys.addFlow(x,q);
		this.sys.addFlow(q,y);
		this.sys.addFlow(y,this.controlPlace);
		
		for (P u: this.sys.getPreset(this.t1)) this.sys.addFlow(u,x);
		for (P u: this.sys.getPreset(this.t2)) this.sys.addFlow(u,y);
		for (P u: this.sys.getPostset(this.t1)) this.sys.addFlow(x,u);
		for (P u: this.sys.getPostset(this.t2)) this.sys.addFlow(y,u);
		
		
		this.sys.putTokens(p,1);
		
		this.freshPs.add(this.controlPlace);
		this.freshPs.add(p);
		this.freshPs.add(q);
		this.freshTs.add(x);
		this.freshTs.add(y);
	}
	
	@Override
	public int hashCode() {
		if (this.lut1!=null && this.lut2!=null)
			return this.getType().getTypeCode() * (7 * this.lut1.hashCode() + 11 * this.lut2.hashCode());
		else
			return this.getType().getTypeCode() * (7 * this.t1.hashCode() + 11 * this.t2.hashCode());
	}

	@Override
	public String toString() {
		return String.format("%s[%s,%s]", this.getType(), this.t1, this.t2);
	}

	@Override
	public P getControlPlace() {
		if (this.isApplied()) return this.controlPlace;
		
		return null;
	}
	
	@Override
	public void substitute(INetSystemTransformation<F,N,P,T,M> tr) {
		super.substitute(tr);
		
		if (tr instanceof IPrecedenceTestTransformation<?,?,?,?,?>) {
			IPrecedenceTestTransformation<F,N,P,T,M> ptt = (IPrecedenceTestTransformation<F,N,P,T,M>) tr;
			this.controlPlace = ptt.getControlPlace();
			
			this.t1 = ptt.getPrecedingTransition();
			this.t2 =  ptt.getSucceedingTransition();
			this.lut1 = ptt.getPrecedingTransformation();
			this.lut2 = ptt.getSucceedingTransformation();
		}
	}

	@Override
	public T getPrecedingTransition() {
		return this.t1;
	}

	@Override
	public T getSucceedingTransition() {
		return this.t2;
	}

	@Override
	public ILabelUnificationTransformation<F,N,P,T,M> getPrecedingTransformation() {
		return this.lut1;
	}

	@Override
	public ILabelUnificationTransformation<F,N,P,T,M> getSucceedingTransformation() {
		return this.lut2;
	}

}
