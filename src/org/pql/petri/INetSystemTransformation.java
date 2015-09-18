package org.pql.petri;

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
public interface INetSystemTransformation<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> {

	public void apply();
	
	public void revert();
	
	public NetSystemTransformationType getType();
	
	public boolean isApplied();
	
	public void substitute(INetSystemTransformation<F,N,P,T,M> tr);
	
	public INetSystem<F,N,P,T,M> getNetSystem();
	
	public Set<P> getFreshPlaces();
	
	public Set<T> getFreshTransitions();
}