package org.pql.core;

import org.pql.logic.ThreeValuedLogicValue;

/**
 * @author Artem Polyvyanyy
 */
public interface IPQLBasicPredicatesOnLabels {
	
	/**
	 * Check if there is an execution in which a transition with a given label occurs.
	 * @category core
	 * @param label Label of a transition.
	 * @return <tt>TRUE</tt> if there is an execution in which a transition with a given label occurs, 
	 * <tt>FALSE</tt> if there is no execution in which a transition with a given label occurs, and 
	 * <tt>NULL</tt> if there is no transition with a given label.
	 */
	public ThreeValuedLogicValue canOccur(String label);
	
	/**
	 * Check if a transition with a given label occurs in all executions.
	 * @category core
	 * @param label Label of a transition.
	 * @return <tt>TRUE</tt> if a transition with a given label occurs in all executions,
	 * <tt>FALSE</tt> if a transition with a given label does not occur in all executions, and 
	 * <tt>NULL</tt> if there is no transition with a given label.
	 */
	public ThreeValuedLogicValue alwaysOccurs(String label);
	
	/**
	 * Given labels A and B, check if there is an execution in which a transition with label A occurs and a transition with label B does not occur.
	 * @category 4C
	 * @param labelA Label A.
	 * @param labelB Label B.
	 * @return <tt>TRUE</tt> if there is an execution in which a transition with label A occurs and a transition with label B does not occur,
	 * <tt>FALSE</tt> if there is no execution in which a transition with label A occurs and a transition with label B does not occur, and
	 * <tt>NULL</tt> if there is no transition with label A or B.
	 */
	public ThreeValuedLogicValue canConflict(String labelA, String labelB);
	
	/**
	 * Given labels A and B, check if there is an execution in which transitions with labels A and B occur.
	 * @category 4C
	 * @param labelA Label A.
	 * @param labelB Label B.
	 * @return <tt>TRUE</tt> if there is an execution in which transitions with labels A and B occur,
	 * <tt>FALSE</tt> if there is no execution in which transitions with labels A and B occur, and
	 * <tt>NULL</tt> if there is no transition with label A or B.
	 */
	public ThreeValuedLogicValue canCooccur(String labelA, String labelB);
}
