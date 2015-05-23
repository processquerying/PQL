package org.jbpt.petri.persist;

import java.sql.SQLException;
import java.util.Set;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;

/**
 * @author Artem Polyvyanyy
 */
public interface IPetriNetPersistenceLayer<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F, N, P, T>> {

	public int storeNetSystem(INetSystem<F,N,P,T,M> sys, String identifier) throws SQLException;
	
	public INetSystem<F,N,P,T,M> restoreNetSystem(int ID);
	
	public int deleteNetSystem(String identifier) throws SQLException;

	public int identifier2NetID(String identifier) throws SQLException;

	public int UUID2NetID(String uuid) throws SQLException;
	
	public Set<String> getAllIdentifiers() throws SQLException;

	public void reset() throws SQLException;
}