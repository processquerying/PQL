package org.pql.petri;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;

/**
 * @author Artem Polyvyanyy
 * 
 * See Def. 5.1. in Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
 */
public interface IGuardTransitionTransformation<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
					extends IControlPlaceTransformation<F,N,P,T,M> {
	
	public P getGuardPlace();
	
	public T getGuardedTransition();
	
	public ILabelUnificationTransformation<F,N,P,T,M> getGuardedTransformation();

}
