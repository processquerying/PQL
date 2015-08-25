package org.pql.mc;

import org.jbpt.petri.Flow;
import org.jbpt.petri.Marking;
import org.jbpt.petri.Node;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;

/**
 * @author Artem Polyvyanyy
 */
public class LoLA2ModelChecker extends AbstractLoLA2ModelChecker<Flow,Node,Place,Transition,Marking> {

	public LoLA2ModelChecker(String path) {
		super(path);
	}

}
