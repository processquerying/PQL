package org.pql.mc;

import org.jbpt.petri.Flow;
import org.jbpt.petri.Marking;
import org.jbpt.petri.Node;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;

/**
 * @author Artem Polyvyanyy
 */
public class LoLAModelChecker extends AbstractLoLA2ModelChecker<Flow,Node,Place,Transition,Marking> {

	public LoLAModelChecker(String path) {
		super(path);
	}

}
