package org.pql.alignment;

import org.deckfour.xes.model.XLog;
import org.jbpt.petri.NetSystem;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;

/**
 * A.P.
 */
public abstract class AbstractReplayer {
	
	
	public AbstractReplayer() {}
	
	public abstract PQLAlignment getAlignment(PetrinetGraph net,  XLog log); //net system and PQLtrace instead?
	
	public abstract Double getAlignmentWithStars(PetrinetGraph net,  XLog log,  NetSystem ns); //net system and PQLtrace instead?

}
