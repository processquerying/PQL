package org.pql.petri;

public enum NetSystemTransformationType {
	NIL(0),
	CONTROL_PLACE(1),
	GUARD_TRANSITION(2),
	PRECEDENCE_TEST(3),
	LABEL_UNIFICATION(4),
	WILDCARD_CHARACTERS(5);
	
	
	private final int type;
	
	NetSystemTransformationType(int type) {
        this.type = type;
    }
	
	public int getTypeCode() {
		return this.type;
	}
	
	@Override
	public String toString() {
		switch (this.getTypeCode()) {
			case 1: return "ControlPlace";
			case 2: return "GuardTransition";
			case 3: return "PrecedenceTest";
			case 4: return "LabelUnification";
			case 5: return "WildcardCharacters";
			default: return "NIL";
		}
	}
}
