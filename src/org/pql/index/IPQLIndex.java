package org.pql.index;

import java.sql.SQLException;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;

/**
 * A PQL Index interface.
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
	 * Get index type.
	 * 
	 * @param internalID Internal ID of a stored Petri net.
	 * @return {@link IndexType} of the index associated with the Petri net stored under {@code internalID}; {@code null} if the Petri net with the given {@code internalID} is not indexed or index type is unknown.
	 * @throws SQLException
	 */
	public IndexType getIndexType(int internalID) throws SQLException;
	
	/**
	 * Get index status.
	 * 
	 * @param internalID Internal ID of a stored Petri net.
	 * @return {@link IndexStatus} for the Petri net stored under {@code internalID}; {@code null} if the index status of the Petri net with the given {@code internalID} is unknown.
	 * @throws SQLException
	 */
	public IndexStatus getIndexStatus(int internalID) throws SQLException;
	
	/**
	 * Delete index of a Petri net.
	 *  
	 * @param internalID Internal ID of a Petri net.
	 * @return Internal ID of the Petri net for which the index was deleted; 0, if no index was deleted.
	 * @throws SQLException
	 */
	public int deleteIndex(int internalID) throws SQLException;
	
	/**
	 * Call PQL index cleanup routine (removes incomplete indexes).
	 *
	 * @throws SQLException
	 */
	public void cleanupIndex() throws SQLException;
}