package org.pql.core;

import org.jbpt.petri.INetSystem;
import org.pql.logic.ThreeValuedLogicValue;

/**
 * Interface to PQL basic predicates (on {@link PQLTask}s).
 * 
 * @author Artem Polyvyanyy
 */
public interface IPQLBasicPredicatesOnTasks extends IPQLBasicPredicates {
	
	/**
	 * Check if {@link INetSystem} has an execution in which a given {@link PQLTask} occurs.
	 * 
	 * @category core
	 * @param t {@link PQLTask}
	 * 
	 * @return {@code UNKNOWN} if {@code t} is not a task of {@link INetSystem}; otherwise
	 * {@code TRUE} if {@link INetSystem} has an execution in which {@code t} occurs, or 
	 * {@code FALSE} if {@link INetSystem} has no execution in which {@code t} occurs.
	 */
	public ThreeValuedLogicValue canOccur(PQLTask t);
	
	/**
	 * Check if a given {@link PQLTask} occurs in every execution of {@link INetSystem}.
	 * 
	 * @category core
	 * @param t {@link PQLTask}
	 * 
	 * @return {@code UNKNOWN} if {@code t} is not a task of {@link INetSystem}; otherwise
	 * {@code TRUE} if {@code t} occurs in every execution of {@link INetSystem}, or 
	 * {@code FALSE} if {@code t} does not occur in some execution of {@link INetSystem}.
	 */
	public ThreeValuedLogicValue alwaysOccurs(PQLTask t);
	
	/**
	 * Check if a given {@link PQLTask} {@code t1} can conflict with a given {@link PQLTask} {@code t2} in {@link INetSystem},
	 * i.e., there is an execution in {@link INetSystem} in which {@code t1} occurs and {@code t2} does not.
	 * 
	 * For more information see Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 * 
	 * @category 4C spectrum
	 * @param t1 {@link PQLTask}
	 * @param t2 {@link PQLTask}
	 * 
	 * @return {@code UNKNOWN} if either {@code t1} or {@code t2} is not a {@link PQLTask} of {@link INetSystem}; otherwise
	 * {@code TRUE} if {@code t1} can conflict with {@code t2} in {@link INetSystem}, or 
	 * {@code FALSE} if {@code t1} cannot conflict with {@code t2} in {@link INetSystem}.
	 */
	public ThreeValuedLogicValue canConflict(PQLTask t1, PQLTask t2);
	
	/**
	 * Check if given {@link PQLTask}s {@code t1} and {@code t2} can cooccur in {@link INetSystem},
	 * i.e., there is an execution in {@link INetSystem} in which both {@code t1} and {@code t2} occur.
	 * 
	 * For more information see Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 * 
	 * @category 4C spectrum
	 * @param t1 {@link PQLTask}
	 * @param t2 {@link PQLTask}
	 * 
	 * @return {@code UNKNOWN} if either {@code t1} or {@code t2} is not a {@link PQLTask} of {@link INetSystem}; otherwise
	 * {@code TRUE} if {@code t1} and {@code t2} can cooccur in {@link INetSystem}, or 
	 * {@code FALSE} if {@code t1} and {@code t2} do not cooccur in {@link INetSystem}.
	 */
	public ThreeValuedLogicValue canCooccur(PQLTask t1, PQLTask t2);
	
	/**
	 * Check if given {@link PQLTask}s {@code t1} and {@code t2} are in conflict in {@link INetSystem},
	 * i.e., there is an execution in {@link INetSystem} in which {@code t1} occurs, 
	 * there is an execution in {@link INetSystem} in which {@code t2} occurs, and 
	 * there is no execution in {@link INetSystem} in which both {@code t1} and {@code t2} occur.
	 * 
	 * For more information see Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 * 
	 * @category 4C spectrum
	 * @param t1 {@link PQLTask}
	 * @param t2 {@link PQLTask}
	 * 
	 * @return {@code UNKNOWN} if either {@code t1} or {@code t2} is not a {@link PQLTask} of {@link INetSystem}; otherwise
	 * {@code TRUE} if {@code t1} and {@code t2} are in conflict in {@link INetSystem}, or 
	 * {@code FALSE} if {@code t1} and {@code t2} are not in conflict in {@link INetSystem}.
	 */
	public ThreeValuedLogicValue conflict(PQLTask t1, PQLTask t2);
	
	/**
	 * Check if given {@link PQLTask}s {@code t1} and {@code t2} cooccur in {@link INetSystem},
	 * i.e., there is an execution in {@link INetSystem} in which {@code t1} occurs, 
	 * there is an execution in {@link INetSystem} in which {@code t2} occurs,   
	 * in every execution of {@link INetSystem} in which {@code t1} occurs it holds that {@code t2} occurs as well, and
	 * in every execution of {@link INetSystem} in which {@code t2} occurs it holds that {@code t1} occurs as well.
	 * 
	 * For more information see Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 * 
	 * @category 4C spectrum
	 * @param t1 {@link PQLTask}
	 * @param t2 {@link PQLTask}
	 * 
	 * @return {@code UNKNOWN} if either {@code t1} or {@code t2} is not a {@link PQLTask} of {@link INetSystem}; otherwise
	 * {@code TRUE} if {@code t1} and {@code t2} cooccur in {@link INetSystem}, or 
	 * {@code FALSE} if {@code t1} and {@code t2} do not cooccur in {@link INetSystem}.
	 */
	public ThreeValuedLogicValue cooccur(PQLTask t1, PQLTask t2);
	
	/**
	 * Check if a given {@link PQLTask} {@code t1} is total causal with a given {@link PQLTask} {@code t2} in {@link INetSystem},
	 * i.e., in every concurrent execution of {@link INetSystem} it holds that {@code t1} is causal with {@code t2}.
	 * 
	 * For more information see Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 * 
	 * @category 4C spectrum
	 * @param t1 {@link PQLTask}
	 * @param t2 {@link PQLTask}
	 * 
	 * @return {@code UNKNOWN} if either {@code t1} or {@code t2} is not a {@link PQLTask} of {@link INetSystem}; otherwise
	 * {@code TRUE} if {@code t1} is total causal with {@code t2} in {@link INetSystem}, or 
	 * {@code FALSE} if {@code t1} is not total causal with {@code t2} in {@link INetSystem}.
	 */
	public ThreeValuedLogicValue totalCausal(PQLTask t1, PQLTask t2);
	
	/**
	 * Check if given {@link PQLTask} {@code t1} and {@code t2} are total concurrent in {@link INetSystem},
	 * i.e., in every concurrent execution of {@link INetSystem} it holds that {@code t1} and {@code t2} are concurrent.
	 * 
	 * For more information see Artem Polyvyanyy, Matthias Weidlich, Raffaele Conforti, Marcello La Rosa, Arthur H. M. ter Hofstede: The 4C Spectrum of Fundamental Behavioral Relations for Concurrent Systems. Petri Nets 2014:210-232
	 * 
	 * @category 4C spectrum
	 * @param t1 {@link PQLTask}
	 * @param t2 {@link PQLTask}
	 * 
	 * @return {@code UNKNOWN} if either {@code t1} or {@code t2} is not a {@link PQLTask} of {@link INetSystem}; otherwise
	 * {@code TRUE} if {@code t1} and {@code t2} are concurrent in {@link INetSystem}, or 
	 * {@code FALSE} if {@code t1} and {@code t2} are not concurrent in {@link INetSystem}.
	 */
	public ThreeValuedLogicValue totalConcur(PQLTask t1, PQLTask t2);
	
	public ThreeValuedLogicValue executes(PQLTrace trace); //A.P.
}