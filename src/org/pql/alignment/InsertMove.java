package org.pql.alignment;

import java.util.Set;
import java.util.Vector;
import org.jbpt.petri.IPlace;

/**
 * A.P.
 */

public class InsertMove{
	
	private Vector<IPlace> marking = new Vector<IPlace>();
	private Vector<String> labels = new Vector<String>();
	private Integer alignmentMove = null;
	
	public InsertMove() {}
	
	public InsertMove( Set<IPlace> p, Vector<String> l, int alMove) 
	{
		this.marking.addAll(p);
		this.labels.addAll(l);
		this.alignmentMove = alMove;
	}
	
	public Vector<IPlace> getMarking(){
		return this.marking;
	};
	
	public Vector<String> getLabels(){
		return this.labels;
	};
	
	public Integer getAlignmentMove(){
		return this.alignmentMove;
	};

	
}
