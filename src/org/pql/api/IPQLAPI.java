package org.pql.api;

import java.io.File;
import java.sql.SQLException;
import java.util.Set;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.pql.index.IndexStatus;

/**
 * A PQL interface.
 * 
 * @author Artem Polyvyanyy
 */
public interface IPQLAPI<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> {
	
	/**
	 * Check if a given net system can be indexed.
	 *  
	 * @param sys A net system.
	 * @return <tt>true</tt> if the given net system can be indexed; <tt>false</tt> otherwise.
	 * @throws SQLException
	 */
	public boolean checkNetSystem(int internalID) throws SQLException;
	
	/**
	 * Store a Petri net.
	 * 
	 * @param pnmlFile File object with PNML content.
	 * @param externalID External identifier to associate with the stored Petri net.
	 * @return <tt>0</tt> if the Petri net was not stored; otherwise a unique internal identifier of the stored Petri net.
	 * @throws SQLException
	 */
	public int storeNetSystem(File pnmlFile, String externalID) throws SQLException;
	
	/**
	 * Store a Petri net.
	 * 
	 * @param pnmlByteContent Array of bytes with PNML content.
	 * @param externalID External identifier to associate with the stored Petri net.
	 * @return <tt>0</tt> if the Petri net was not stored; otherwise a unique internal identifier of the stored Petri net.
	 * @throws SQLException
	 */
	public int storeNetSystem(byte[] pnmlByteContent, String externalID) throws SQLException;
	
	/**
	 * Store a Petri net.
	 * 
	 * @param sys {@link INetSystem}} to be stored. 
	 * @param externalID External identifier to associate with the stored Petri net.
	 * @return <tt>0</tt> if the Petri net was not stored; otherwise a unique internal identifier of the stored Petri net.
	 * @throws SQLException
	 */
	public int storeNetSystem(INetSystem<F,N,P,T,M> sys, String externalID) throws SQLException;
	
	/**
	 * Get Petri net stored under an internal ID.
	 * 
	 * @param internalID Internal identifier of a stored Petri net.
	 * @return {@link INetSystem} stored under the given {@code internalID}; {@code null} if {@code internalID} is not associated with any Petri net.
	 * @throws SQLException
	 */
	public INetSystem<F,N,P,T,M> restoreNetSystem(int internalID) throws SQLException;
	
	/**
	 * Reset PQL index. Warning: This call removes all stored Petri nets and indexes. 
	 */
	public void reset() throws SQLException;
	
	/**
	 * Construct PQL index of a Petri net.
	 *  
	 * @param internalID Internal identifier of a stored Petri net. 
	 * @return {@code true} if PQL index was successfully constructed; {@code false} otherwise. 
	 * @throws SQLException
	 */
	public boolean index(int internalID) throws SQLException;
	
	/**
	 * Delete PQL index of a Petri net.
	 * 
	 * @param internalID Internal identifier of a stored Petri net.
	 * @return {@code true} if PQL index was successfully deleted; {@code false} otherwise.
	 * @throws SQLException
	 */
	public boolean deleteIndex(int internalID) throws SQLException;
	
	/**
	 * Delete Petri net and its index.
	 * 
	 * @param internalID Internal identifier of a stored Petri net.
	 * @return {@code true} if Petri net and its index were successfully deleted; {@code false} otherwise.
	 * @throws SQLException
	 */
	public boolean deleteNetSystem(int internalID) throws SQLException;

	/**
	 * Build parse tree of a PQL query. 
	 * 
	 * @param pqlPath A path to a file that stores a PQL query.
	 */
	public void parsePQLQuery(String pqlPath) throws Exception;
	
	/**
	 * Call PQL index cleanup routine (removes incomplete indexes).
	 *
	 * @throws SQLException
	 */
	public void cleanupIndex() throws SQLException;
	
	/**
	 * Get internal identifier of a Petri net.
	 * 
	 * @param externalID External identifier of a Petri net.
	 * @return Internal Petri net identifier associated with the given external identifier; 0, if the given {@code externalID} is not associated with any Petri net.
	 * @throws SQLException
	 */
	public int getInternalID(String externalID) throws SQLException;
	
	/**
	 * Get external identifier of a Petri net.
	 * 
	 * @param internalID Internal identifier of a Petri net.
	 * @return External Petri net identifier associated with the given internal identifier; {@code null} if the given {@code internalID} is not associated with any Petri net.
	 * @throws SQLException
	 */
	public String getExternalID(int internalID) throws SQLException;
	
	/**
	 * Execute PQL query.
	 * 
	 * @param pqlQuery A PQL query string.
	 * @return {@link PQLQueryResult} that contains information about the query and its results.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public PQLQueryResult query(String pqlQuery) throws ClassNotFoundException, SQLException;
	
	/**
	 * Execute PQL query on Petri nets with specified external identifiers.
	 * 
	 * @param pqlQuery A PQL query string.
	 * @param externalIDs A set of external identifiers.
	 * @return {@link PQLQueryResult} that contains information about the query and its results.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public PQLQueryResult query(String pqlQuery, Set<String> externalIDs) throws ClassNotFoundException, SQLException;

	/**
	 * Get {@link IndexStatus} of a Petri net.
	 * @param internalID Internal identifier of a Petri net.
	 * @return {@link IndexStatus} of the Petri net with internal identifier internalID.
	 * @throws SQLException
	 */
	public IndexStatus getIndexStatus(int internalID) throws SQLException;
}
