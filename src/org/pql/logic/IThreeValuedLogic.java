package org.pql.logic;

/**
 * An interface to the three-valued logic.
 * 
 * For more information refer to:
 * http://en.wikipedia.org/wiki/Three-valued_logic
 *
 * @author Artem Polyvyanyy
 */
public interface IThreeValuedLogic {
	
	/**
	 * Conjunction operation.
	 * 
	 * @param v1 A three-valued logic value.
	 * @param v2 A three-valued logic value.
	 * @return A three-valued logic value result of the conjunction operation on {@code v1} and {@code v2}, as per concrete logic.
	 */
	public ThreeValuedLogicValue AND(ThreeValuedLogicValue v1, ThreeValuedLogicValue v2);
	
	/**
	 * Disjunction operation.
	 * 
	 * @param v1 A three-valued logic value.
	 * @param v2 A three-valued logic value.
	 * @return A three-valued logic value result of the disjunction operation on {@code v1} and {@code v2}, as per concrete logic.
	 */
	public ThreeValuedLogicValue OR(ThreeValuedLogicValue v1, ThreeValuedLogicValue v2);
	
	/**
	 * Negation operation.
	 * 
	 * @param v A three-valued logic value.
	 * @return A three-valued logic value result of the negation operation on {@code v}, as per concrete logic.
	 */
	public ThreeValuedLogicValue NOT(ThreeValuedLogicValue v);
}
