package org.pql.api;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.pql.logic.ThreeValuedLogicValue;
import org.pql.query.IPQLQuery;

/**
 * Artem Polyvyanyy
 */
public interface IPQLAPI<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> {
	
	/**
	 * Check if a given net system can be indexed.
	 *  
	 * @param sys A net system.
	 * @return <tt>true</tt> if the given net system can be indexed; <tt>false</tt> otherwise.
	 */
	public boolean checkNetSystem(INetSystem<F,N,P,T,M> sys);
	
	/**
	 * Index a given net system.
	 * 
	 * @param sys A net system to be indexed.
	 * @param identifier A unique identifier to associate with the index of the given net system.
	 * @param similarities Label similarity thresholds to index.
	 * @return <tt>0</tt> if the net system was not indexed; otherwise unique database id of the indexed net system. 
	 * @throws SQLException
	 */
	public int indexNetSystem(INetSystem<F,N,P,T,M> sys, String identifier, Set<Double> similarities) throws SQLException;
	
	/**
	 * Delete a net system and its index.
	 * 
	 * @param identifier A unique identifier that is associate with the index of the net system to be removed.
	 * @return <tt>0</tt> if the net system was not removed; otherwise unique database id of the removed net system. 
	 * @throws SQLException
	 */
	public int deleteNetSystem(String identifier) throws SQLException;
	
	/**
	 * Visualise a PQL query. 
	 * 
	 * @param pqlQuery A PQL query to visualise.
	 */
	public void visualiseQuery(String pqlQuery);
	
	/**
	 * Check a PQL query.
	 * 
	 * @param pqlQuery A PQL query.
	 * @param identifier A unique identifier of a net system upon which the the query must be checked.
	 * @return Result of the given query, or <tt>null</tt> if parse errors occurred.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ThreeValuedLogicValue checkQuery(String pqlQuery, String identifier) throws ClassNotFoundException, SQLException;
	
	/**
	 * Get last query object.
	 * 
	 * @return An object representing the last query due to the call of {@link IPQLAPI.checkQuery} method. 
	 */
	public IPQLQuery getLastQuery();
	
	/**
	 * Get number of parse errors in the last query.
	 * 
	 * @return Number of parse errors in the last query.
	 */
	public int getLastNumberOfParseErrors();
	
	/**
	 * Get parse error messages of the last query.
	 * 
	 * @return Parse error messages of the last query.  
	 */
	public List<String> getLastParseErrorMessages();
	
	/**
	 * Prepare a PQL query.
	 * 
	 * @param pqlQuery A PQL query.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void prepareQuery(String pqlQuery) throws ClassNotFoundException, SQLException;
	
	/**
	 * Check the last prepared PQL query on net system that have identifiers in a given set.
	 * 
	 * @param identifiers A set of net system identifiers.
	 * 
	 * @return A set of identifiers which refer to net systems that match the last prepared query.
	 */
	public Set<String> checkLastQuery(Set<String> identifiers) throws SQLException;
	
	/**
	 * Check the last prepared PQL query on all indexed net systems.
	 * 
	 * @return A set of identifiers which refer to net systems that match the last prepared query.
	 * @throws SQLException
	 */
	public Set<String> checkLastQuery() throws SQLException;
	
	/**
	 * Construct a net system from the given byte content (must store PNML file).
	 * 
	 * @param pnmlContent A byte array that stores PNML file.
	 * 
	 * @return A net system object.
	 */
	public INetSystem<F,N,P,T,M> bytes2NetSystem(byte[] pnmlContent);
	
	/**
	 * Reset the index. 
	 */
	public void reset()  throws SQLException;

}
