package org.pql.logic;

/**
 * Implementation of three-valued Kleene logic
 * 
 * @author Artem Polyvyanyy
 */
public class KleeneLogic implements IThreeValuedLogic {

	@Override
	public ThreeValuedLogicValue AND(ThreeValuedLogicValue v1, ThreeValuedLogicValue v2) {
		if (v1==ThreeValuedLogicValue.TRUE) {
			if (v2==ThreeValuedLogicValue.TRUE)
				return ThreeValuedLogicValue.TRUE;
			else if (v2==ThreeValuedLogicValue.FALSE)
				return ThreeValuedLogicValue.FALSE;
			else
				return ThreeValuedLogicValue.UNKNOWN;
		}
		else if (v1==ThreeValuedLogicValue.FALSE) {
			return ThreeValuedLogicValue.FALSE;
		}
		else {
			if (v2==ThreeValuedLogicValue.FALSE)
				return ThreeValuedLogicValue.FALSE;
			else 
				return ThreeValuedLogicValue.UNKNOWN;
		}
	}

	@Override
	public ThreeValuedLogicValue OR(ThreeValuedLogicValue v1, ThreeValuedLogicValue v2) {
		if (v1==ThreeValuedLogicValue.TRUE) {
			return ThreeValuedLogicValue.TRUE; 
		}
		else if (v1==ThreeValuedLogicValue.FALSE) {
			if (v2==ThreeValuedLogicValue.TRUE)
				return ThreeValuedLogicValue.TRUE;
			else if (v2==ThreeValuedLogicValue.FALSE)
				return ThreeValuedLogicValue.FALSE;
			else
				return ThreeValuedLogicValue.UNKNOWN;
		}
		else {
			if (v2==ThreeValuedLogicValue.TRUE)
				return ThreeValuedLogicValue.TRUE;
			else 
				return ThreeValuedLogicValue.UNKNOWN;
		}
	}

	@Override
	public ThreeValuedLogicValue NOT(ThreeValuedLogicValue v) {
		if (v==ThreeValuedLogicValue.TRUE)
			return ThreeValuedLogicValue.FALSE; 
		else if (v==ThreeValuedLogicValue.FALSE)
			return ThreeValuedLogicValue.TRUE;
		else
			return ThreeValuedLogicValue.UNKNOWN;
	}
}
