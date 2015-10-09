package org.pql.core;

import org.jbpt.petri.Flow;
import org.jbpt.petri.Marking;
import org.jbpt.petri.Node;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;
import org.pql.mc.IModelChecker;

/**
 * Artem Polyvyanyy
 */
public class PQLBasicPredicatesMC extends
		AbstractPQLBasicPredicatesMC<Flow,Node,Place,Transition,Marking> {

	public PQLBasicPredicatesMC(
			IModelChecker<Flow,Node,Place,Transition,Marking> modelChecker) {
		super(modelChecker);
	}

}
