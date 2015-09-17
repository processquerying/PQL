package org.pql.alignment;

import org.deckfour.xes.model.XLog;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;

/**
 * A.P.
 */
public abstract class AbstractReplayer {
	
	
	public AbstractReplayer() {}
	
	public abstract PQLAlignment getAlignment(PetrinetGraph net,  XLog log); //net system and PQLtrace instead?
	
}
