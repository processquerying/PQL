package org.pql.core;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;

/**
 * Interface to PQL basic predicates (on {@link INetSystem} transitions).
 * 
 * @author Artem Polyvyanyy
 */
public interface IPQLBasicPredicatesOnTransitions<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
					extends IPQLBasicPredicates {
	
	/**
	 * Check if {@link INetSystem} has an execution in which a given transition occurs.
	 * 
	 * @category core
	 * @param t {@link ITransition}
	 * 
	 * @return {@code TRUE} if {@link INetSystem} has an execution in which {@code t} occurs; {@code FALSE} otherwise.
	 */
	public boolean canOccur(T t);
	
	/**
	 * Check if a given transition occurs in every execution of {@link INetSystem}.
	 * 
	 * @category core
	 * @param t {@link ITransition}
	 * 
	 * @return {@code TRUE} if {@code t} occurs in every execution of {@link INetSystem}; {@code FALSE} otherwise.
	 */
	public boolean alwaysOccurs(T t);
	
	/**
	 * Check if a given transition {@code t1} can conflict with a given transition {@code t2} in {@link INetSystem},
	 * i.e., there is an execution in {@link INetSystem} in which {@code t1} occurs and {@code t2} does not.
	 * 
	 * For more information see Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 * 
	 * @category 4C spectrum
	 * @param t1 {@link ITransition}
	 * @param t2 {@link ITransition}
	 * 
	 * @return {@code TRUE} if {@code t1} can conflict with {@code t2} in {@link INetSystem}; {@code FALSE} otherwise.
	 */
	public boolean canConflict(T t1, T t2);
	
	/**
	 * Check if given transitions {@code t1} and {@code t2} can cooccur in {@link INetSystem},
	 * i.e., there is an execution in {@link INetSystem} in which both {@code t1} and {@code t2} occur.
	 * 
	 * For more information see Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 * 
	 * @category 4C spectrum
	 * @param t1 {@link ITransition}
	 * @param t2 {@link ITransition}
	 * 
	 * @return {@code TRUE} if {@code t1} and {@code t2} can cooccur in {@link INetSystem}; {@code FALSE} otherwise.
	 */
	public boolean canCooccur(T t1, T t2);
	
	/**
	 * Check if given transitions {@code t1} and {@code t2} are in conflict in {@link INetSystem},
	 * i.e., there is an execution in {@link INetSystem} in which {@code t1} occurs, 
	 * there is an execution in {@link INetSystem} in which {@code t2} occurs, and 
	 * there is no execution in {@link INetSystem} in which both {@code t1} and {@code t2} occur.
	 * 
	 * For more information see Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 * 
	 * @category 4C spectrum
	 * @param t1 {@link ITransition}
	 * @param t2 {@link ITransition}
	 * 
	 * @return {@code TRUE} if {@code t1} and {@code t2} are in conflict in {@link INetSystem}; {@code FALSE} otherwise.
	 */
	public boolean conflict(T t1, T t2);
	
	/**
	 * Check if given transitions {@code t1} and {@code t2} cooccur in {@link INetSystem},
	 * i.e., there is an execution in {@link INetSystem} in which {@code t1} occurs, 
	 * there is an execution in {@link INetSystem} in which {@code t2} occurs,   
	 * in every execution of {@link INetSystem} in which {@code t1} occurs it holds that {@code t2} occurs as well, and
	 * in every execution of {@link INetSystem} in which {@code t2} occurs it holds that {@code t1} occurs as well.
	 * 
	 * For more information see Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 * 
	 * @category 4C spectrum
	 * @param t1 {@link ITransition}
	 * @param t2 {@link ITransition}
	 * 
	 * @return {@code TRUE} if {@code t1} and {@code t2} cooccur in {@link INetSystem}; {@code FALSE} otherwise.
	 */
	public boolean cooccur(T t1, T t2);
	
	/**
	 * Check if a given transition {@code t1} is total causal with a given transition {@code t2} in {@link INetSystem},
	 * i.e., in every concurrent execution of {@link INetSystem} it holds that {@code t1} is causal with {@code t2}.
	 * 
	 * For more information see Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 * 
	 * @category 4C spectrum
	 * @param t1 {@link ITransition}
	 * @param t2 {@link ITransition}
	 * 
	 * @return {@code TRUE} if {@code t1} is total causal with {@code t2} in {@link INetSystem}; {@code FALSE} otherwise.
	 */
	public boolean totalCausal(T t1, T t2);
	
	/**
	 * Check if given transitions {@code t1} and {@code t2} are total concurrent in {@link INetSystem},
	 * i.e., in every concurrent execution of {@link INetSystem} it holds that {@code t1} and {@code t2} are concurrent.
	 * 
	 * For more information see Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 * 
	 * @category 4C spectrum
	 * @param t1 {@link ITransition}
	 * @param t2 {@link ITransition}
	 * 
	 * @return {@code TRUE} if {@code t1} and {@code t2} are concurrent in {@link INetSystem}; {@code FALSE} otherwise.
	 */
	public boolean totalConcur(T t1, T t2);
}
