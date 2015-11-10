package org.pql.alignment;

import java.util.HashSet;
import java.util.Set;
import org.jbpt.petri.IPlace;

/**
 * A.P.
 */
public class SkipMove {
	
	private Set<IPlace> sourcePlaces = new HashSet<IPlace>();
	private Set<IPlace> sinkPlaces = new HashSet<IPlace>();
	
	public SkipMove() {}
	
	public SkipMove( Set<IPlace> sourceP, Set<IPlace> sinkP) 
	{
		this.sourcePlaces.addAll(sourceP);
		this.sinkPlaces.addAll(sinkP);
	}
	
	public Set<IPlace> getSourcePlaces(){
		return this.sourcePlaces;
	};
	
	public Set<IPlace> getSinkPlaces(){
		return this.sinkPlaces;
	};
	
	
}
