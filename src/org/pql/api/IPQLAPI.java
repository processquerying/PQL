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
import org.pql.query.PQLQueryResult;

/**
 * A PQL interface.
 * 
 * @author Artem Polyvyanyy
 */
public interface IPQLAPI<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> {
	
	/**
	 * Check if a model can be indexed.
	 *  
	 * @param internalID An internal ID of a model.
	 * @return {@code true} if the model with the given internal ID can be indexed; {@code false} otherwise.
	 * @throws SQLException
	 */
	public boolean checkModel(int internalID) throws SQLException;
	
	/**
	 * Store a model.
	 * 
	 * @param pnmlFile File object with PNML content.
	 * @param externalID External ID to associate with the stored model.
	 * @return A unique internal ID of the stored model, or {@code 0} if the model was not stored.
	 * @throws SQLException
	 */
	public int storeModel(File pnmlFile, String externalID) throws SQLException;
	
	/**
	 * Store a model.
	 * 
	 * @param pnmlByteContent Array of bytes with PNML content.
	 * @param externalID External ID to associate with the stored model.
	 * @return A unique internal ID of the stored model, or {@code 0} if the model was not stored.
	 * @throws SQLException
	 */
	public int storeModel(byte[] pnmlByteContent, String externalID) throws SQLException;
	
	/**
	 * Store a model.
	 * 
	 * @param sys {@link INetSystem} object. 
	 * @param externalID External ID to associate with the stored model.
	 * @return A unique internal ID of the stored model, or {@code 0} if the model was not stored.
	 * @throws SQLException
	 */
	public int storeModel(INetSystem<F,N,P,T,M> sys, String externalID) throws SQLException;
	
	/**
	 * Get Petri net stored under an internal ID.
	 * 
	 * @param internalID Internal ID of a stored Petri net.
	 * @return {@link INetSystem} stored under the given {@code internalID}; {@code null} if {@code internalID} is not associated with any Petri net.
	 * @throws SQLException
	 */
	public INetSystem<F,N,P,T,M> restoreModel(int internalID) throws SQLException;
	
	/**
	 * Reset PQL index. 
	 * 
	 * Warning: This call removes all stored Petri nets and indexes.
	 * 
	 * @throws SQLException
	 */
	public void reset() throws SQLException;
	
	/**
	 * Construct PQL index of a model.
	 *  
	 * @param internalID Internal ID of a stored model. 
	 * @return {@code true} on successful construction of the PQL index for the model with the given internal ID; {@code false} otherwise. 
	 * @throws SQLException
	 */
	public boolean index(int internalID) throws SQLException;
	
	/**
	 * Delete PQL index of a model.
	 * 
	 * @param internalID Internal ID of a stored model.
	 * @return {@code true} on successful deletion of the PQL index for the model with the given internal ID; {@code false} otherwise.
	 * @throws SQLException
	 */
	public boolean deleteIndex(int internalID) throws SQLException;
	
	/**
	 * Delete a model and its index.
	 * 
	 * @param internalID Internal ID of a stored Petri net.
	 * @return {@code true} on successful deletion of the model with the given internal ID and its PQL index; {@code false} otherwise.
	 * @throws SQLException
	 */
	public boolean deleteModel(int internalID) throws SQLException;

	/**
	 * Build parse tree of a PQL query. 
	 * 
	 * @param pqlPath A path to a file that stores a PQL query.
	 * @throws Exception
	 */
	public void parsePQLQuery(String pqlPath) throws Exception;
	
	/**
	 * Call PQL index cleanup routine (removes incomplete indexes).
	 *
	 * @throws SQLException
	 */
	public void cleanupIndex() throws SQLException;
	
	/**
	 * Get internal ID of a model.
	 * 
	 * @param externalID External ID of a model.
	 * @return Internal ID associated with the given external ID of the model, or {@code 0} if the given external ID is not associated with any stored model.
	 * @throws SQLException
	 */
	public int getInternalID(String externalID) throws SQLException;
	
	/**
	 * Get external ID of a model.
	 * 
	 * @param externalID Internal ID of a model.
	 * @return External ID associated with the given internal ID of the model, or {@code 0} if the given internal ID is not associated with any stored model.
	 * @throws SQLException
	 */
	public String getExternalID(int internalID) throws SQLException;
	
	/**
	 * Execute a PQL query.
	 * 
	 * @param pqlQuery A PQL query string.
	 * @return {@link PQLQueryResult} that contains information about the query and its result.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public PQLQueryResult query(String pqlQuery) throws ClassNotFoundException, SQLException;
	
	/**
	 * Execute a PQL query on models with certain external IDs.
	 * 
	 * @param pqlQuery A PQL query string.
	 * @param externalIDs A set of external IDs.
	 * @return {@link PQLQueryResult} that contains information about the query and its result.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public PQLQueryResult query(String pqlQuery, Set<String> externalIDs) throws ClassNotFoundException, SQLException;

	/**
	 * Get {@link IndexStatus} of a model.
	 * 
	 * @param internalID Internal ID of a model.
	 * @return {@link IndexStatus} of the model with the given internal ID.
	 * @throws SQLException
	 */
	public IndexStatus getIndexStatus(int internalID) throws SQLException;

	int getIndexTime(int internalID) throws SQLException; //A.P.

	int getIndexStartTime(int internalID) throws SQLException; //A.P.

	int getIndexEndTime(int internalID) throws SQLException; //A.P.

	
}