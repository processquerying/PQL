package org.pql.petri;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;

/**
 * @author Artem Polyvyanyy
 */
public interface IControlPlaceTransformation<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
					extends INetSystemTransformation<F,N,P,T,M> {
	
	public P getControlPlace();

}
