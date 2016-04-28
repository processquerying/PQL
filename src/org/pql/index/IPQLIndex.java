package org.pql.index;

import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.pql.bot.PQLBot;

/**
 * Interface to the PQL index.
 * 
 * @author Artem Polyvyanyy
 */
public interface IPQLIndex<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> {
	
	/**
	 * Construct index of a Petri net with a given internal ID.
	 * 
	 * @param internalID Internal ID of a stored Petri net. 
	 * @param type Type of index to construct.
	 * @return {@code true} if the Petri net with the given {@code internalID} was indexed successfully; {@code false} otherwise.
	 * @throws SQLException
	 */
	public boolean index(int internalID, IndexType type) throws SQLException;
	
	/**
	 * Construct index of a Petri net with a given internal ID.
	 * 
	 * @param internalID Internal ID of a stored Petri net. 
	 * @param type Type of index to construct.
	 * @return {@code true} if the Petri net with the given {@code internalID} was indexed successfully; {@code false} otherwise.
	 * @throws SQLException
	 */
	public boolean constructIndex(int internalID, IndexType type) throws SQLException;
	
	//A.P.
	public boolean constructIndex(int internalID, IndexType type, Set<Process> p, AtomicBoolean run) throws SQLException;

	
	/**
	 * Get index type.
	 * 
	 * @param internalID Internal ID of a model.
	 * @return {@link IndexType} of the index associated with the model with the given internal ID, or 
	 * {@code null} if the model is not indexed or its index type cannot be identified.
	 * @throws SQLException
	 */
	public IndexType getIndexType(int internalID) throws SQLException;
	
	/**
	 * Get index status.
	 * 
	 * @param internalID Internal ID of a model.
	 * @return {@link IndexStatus} of the model with the given internal ID, or 
	 * {@code null} if the index status of the model cannot be identified.
	 * @throws SQLException
	 */
	public IndexStatus getIndexStatus(int internalID) throws SQLException;
	
	/**
	 * Delete index of a model with a given internal ID.
	 *  
	 * @param internalID Internal ID of a model.
	 * @return {@code true} if index of the model with the given internal ID existed and was deleted as the result of this call; 
	 * {@code false} if there was no index to delete for the model with the given internal ID.
	 * @throws SQLException
	 */
	public boolean deleteIndex(int internalID) throws SQLException;
	public boolean deleteIndexedRelations(int internalID) throws SQLException;//A.P.

	
	/**
	 * Call PQL index cleanup routine (removes incomplete indexes).
	 *
	 * @throws SQLException
	 */
	public void cleanupIndex() throws SQLException;
	
	/**
	 * Get next indexing job.
	 * 
	 * @return An internal ID of a model that is not indexed.
	 * @throws SQLException
	 */
	public int getNextIndexingJob() throws SQLException;
	
	/**
	 * Request indexing a model with a given internal ID.
	 * 
	 * @param internalID Internal ID of a model.
	 * @param botName Name of a PQL bot (see {@link PQLBot}) that requests indexing.
	 * @throws SQLException
	 */
	public void requestIndexing(int internalID, String botName) throws SQLException;
	
	/**
	 * Start indexing a model with a given internal ID.
	 * This method must be preceded by {@link IPQLIndex}.{@code requestIndexing} call. 
	 * 
	 * @param internalID Internal ID of a model.
	 * @param botName Name of a PQL bot (see {@link PQLBot}) that starts indexing.
	 * @return {@code true} if the PQL bot with the given name has successfully started indexing the
	 * model with the given internal ID; {@code false} otherwise.
	 * @throws SQLException
	 */
	public boolean startIndexing(int internalID, String botName) throws SQLException;
	
	/**
	 * Finish indexing a model with a given internal ID.
	 * 
	 * @param internalID Internal ID of a model.
	 * @param botName Name of a PQL bot (see {@link PQLBot}) that finishes indexing.
	 * @throws SQLException
	 */
	public void finishIndexing(int internalID, String botName) throws SQLException;
	
	/**
	 * Check if a model with a given internal ID can be indexed.
	 * If the model cannot be indexed, this fact is recorded in the PQL database.
	 *  
	 * @param internalID Internal ID of a model.
	 * @return {@code true} if the model with the given ID can be indexed; {@code false} otherwise.
	 * @throws SQLException
	 */
	public boolean checkNetSystem(int internalID) throws SQLException;
	
	public boolean checkNetSystem(int internalID, Set<Process> p) throws SQLException; //A.P.
	
	public void cannnotIndex(int internalID) throws SQLException; //A.P.
}