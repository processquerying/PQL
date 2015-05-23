package org.pql.core;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.pql.logic.ThreeValuedLogicValue;

/**
 * @author Artem Polyvyanyy
 */
public interface IPQLBasicPredicatesOnTransitions<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> {
	
	/**
	 * Check if there is an execution in which a given transition occurs.
	 * @category core
	 * @param t A transition.
	 * @return <tt>TRUE</tt> if there is an execution in which a given transition occurs, 
	 * <tt>FALSE</tt> if there is no execution in which a given transition occurs, and
	 * <tt>NULL</tt> if there is no transition.
	 */
	public ThreeValuedLogicValue canOccur(T t);
	
	/**
	 * Check if a given transition occurs in all executions.
	 * @category core
	 * @param t A transition.
	 * @return <tt>TRUE</tt> if a given transition occurs in all executions,
	 * <tt>FALSE</tt> if a given transition does not occur in all executions, and
	 * <tt>NULL</tt> if there is no transition.
	 */
	public ThreeValuedLogicValue alwaysOccurs(T t);
	
	/**
	 * Given transitions t1 and t2, check if there is an execution in which t1 occurs and t2 does not occur.
	 * @category 4C
	 * @param t1 Transition t1.
	 * @param t2 Transition t2.
	 * @return <tt>TRUE</tt> if there is an execution in which t1 occurs and t2 does not occur,
	 * <tt>FALSE</tt> if there is no execution in which t1 occurs and t2 does not occur, and
	 * <tt>NULL</tt> if there is no transition t1 or t2.
	 */
	public ThreeValuedLogicValue canConflict(T t1, T t2);
	
	/**
	 * Check if there is an execution in which both given transitions occur.
	 * @category 4C
	 * @param t1 A transition.
	 * @param t2 A transition.
	 * @return <tt>TRUE</tt> if there is an execution in which both given transitions occur,
	 * <tt>FALSE</tt> if there is no execution in which both given transitions occur, and
	 * <tt>NULL</tt> if there is no transition t1 or t2.
	 */
	public ThreeValuedLogicValue canCooccur(T t1, T t2);
	
	/**
	 * Check if there is a total causal relation between two transitions of a net system.
	 * @category 4C
	 * @param t1 A transition.
	 * @param t2 A transition.
	 * @return <tt>TRUE</tt> if t1 is total casual with t2,
	 * <tt>FALSE</tt> if t1 is NOT total casual with t2, and
	 * <tt>NULL</tt> if either t1 or t2 is not a transition of the net system.
	 */
	public ThreeValuedLogicValue totalCausal(T t1, T t2);
	
	public ThreeValuedLogicValue totalConcur(T t1, T t2);
}
