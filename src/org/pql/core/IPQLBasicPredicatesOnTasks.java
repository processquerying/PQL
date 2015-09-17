package org.pql.core;

import org.pql.logic.ThreeValuedLogicValue;

/**
 * @author Artem Polyvyanyy
 */
public interface IPQLBasicPredicatesOnTasks {
	
	public void configure(Object param);
	
	public ThreeValuedLogicValue canOccur(PQLTask task);
	
	public ThreeValuedLogicValue alwaysOccurs(PQLTask task);
	
	public ThreeValuedLogicValue executes(PQLTrace trace); //A.P.
	
	public ThreeValuedLogicValue canConflict(PQLTask taskA, PQLTask taskB);
	
	public ThreeValuedLogicValue canCooccur(PQLTask taskA, PQLTask taskB);
	
	public ThreeValuedLogicValue conflict(PQLTask taskA, PQLTask taskB);
	
	public ThreeValuedLogicValue cooccur(PQLTask taskA, PQLTask taskB);
	
	public ThreeValuedLogicValue totalCausal(PQLTask taskA, PQLTask taskB);
	
	public ThreeValuedLogicValue totalConcur(PQLTask taskA, PQLTask taskB);
}
