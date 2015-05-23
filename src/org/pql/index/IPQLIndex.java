package org.pql.index;

import java.sql.SQLException;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;

public interface IPQLIndex<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> {
		
	public int indexBehavioralRelations(INetSystem<F,N,P,T,M> sys, int netID) throws SQLException;
	
}