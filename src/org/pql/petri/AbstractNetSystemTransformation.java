package org.pql.petri;

import java.util.HashSet;
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
public abstract class AbstractNetSystemTransformation<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
				implements INetSystemTransformation<F,N,P,T,M> {

	protected INetSystem<F,N,P,T,M> sys = null;
	
	protected Set<P> freshPs = null;
	protected Set<T> freshTs = null;
	
	private boolean isApplied = false;
	
	public AbstractNetSystemTransformation(INetSystem<F,N,P,T,M> sys) {
		this.freshPs = new HashSet<P>();
		this.freshTs = new HashSet<T>();
		
		this.sys = sys;
	}

	@Override
	public void revert() {
		for (P p : this.freshPs) sys.putTokens(p,0);
		
		sys.removePlaces(this.freshPs);
		sys.removeTransitions(this.freshTs);
		
		this.freshPs.clear();
		this.freshTs.clear();
		
		this.isApplied = false;
	}
	
	@Override
	public void apply() {
		if (this.isApplied) return;
		
		this.applyTransormation();
		
		this.isApplied = true;
	}
	
	protected abstract void applyTransormation(); 

	@Override
	public NetSystemTransformationType getType() {
		return NetSystemTransformationType.NIL;
	}
	
	@Override
	public boolean isApplied() {
		return this.isApplied;
	}
	
	@Override
	public boolean equals(Object that) {
		if (that instanceof INetSystemTransformation<?,?,?,?,?>) {
			if (this.hashCode()==that.hashCode()) return true;
			
			return false;
		}
		
		return false;
	}
	
	@Override
	public void substitute(INetSystemTransformation<F,N,P,T,M> tr) {
		this.isApplied = tr.isApplied();
		this.sys = tr.getNetSystem();
		this.freshPs = tr.getFreshPlaces();
		this.freshTs = tr.getFreshTransitions();
	}
	
	@Override
	public INetSystem<F,N,P,T,M> getNetSystem() {
		return this.sys;
	}

	@Override
	public Set<P> getFreshPlaces() {
		return this.freshPs;
	}

	@Override
	public Set<T> getFreshTransitions() {
		return this.freshTs;
	}
}
