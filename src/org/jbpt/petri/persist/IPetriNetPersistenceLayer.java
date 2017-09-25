package org.jbpt.petri.persist;

import java.io.File;
import java.sql.SQLException;
import java.util.Set;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.pql.core.PQLTrace;


/**
 * An interface to a storage of Petri nets.
 * 
 * @author Artem Polyvyanyy
 */
public interface IPetriNetPersistenceLayer<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> {
	
	/**
	 * Store a Petri net.
	 * 
	 * @param pnmlFilePath Path string to a PNML file that contains Petri net to be stored.
	 * @param externalID External ID to associate with the stored Petri net.
	 * @return Internal ID of the stored Petri net, or 0 if the Petri net was not stored.
	 * @throws SQLException
	 */
	public int storeNetSystem(String pnmlFilePath, String externalID, String target) throws SQLException;
	
	/**
	 * Store a Petri net.
	 * 
	 * @param pnmlFile File object with PNML content.
	 * @param externalID External ID to associate with the stored Petri net.
	 * @return Internal ID of the stored Petri net, or 0 if the Petri net was not stored.
	 * @throws SQLException
	 */
	public int storeNetSystem(File pnmlFile, String externalID, String target) throws SQLException;
	
	/**
	 * Store a Petri net.
	 * 
	 * @param pnmlByteContent Array of bytes with PNML content.
	 * @param externalID External ID to associate with the stored Petri net.
	 * @return Internal ID of the stored Petri net, or 0 if the Petri net was not stored.
	 * @throws SQLException
	 */
	public int storeNetSystem(byte[] pnmlByteContent, String externalID, String target) throws SQLException;
	
	/**
	 * Store a Petri net.
	 * 
	 * @param sys {@link INetSystem}} to be stored. 
	 * @param externalID External ID to associate with the stored Petri net.
	 * @return Internal ID of the stored Petri net, or 0 if the Petri net was not stored.
	 * @throws SQLException
	 */
	public int storeNetSystem(INetSystem<F,N,P,T,M> sys, String externalID, String target) throws SQLException;
	
	/**
	 * Get internal ID of a Petri net.
	 * 
	 * @param externalID External ID of a Petri net.
	 * @return Internal Petri net ID associated with the given external ID, or 0 if the given {@code externalID} is not associated with any stored Petri net.
	 * @throws SQLException
	 */
	public int getInternalID(String externalID) throws SQLException;
	
	/**
	 * Get external ID of a Petri net.
	 * 
	 * @param internalID Internal ID of a Petri net.
	 * @return External Petri net ID associated with the given internal ID, or {@code null} if the given {@code internalID} is not associated with any stored Petri net.
	 * @throws SQLException
	 */
	public String getExternalID(int internalID) throws SQLException;
	
	/**
	 * Delete a Petri net.
	 *  
	 * @param internalID Internal ID of a Petri net.
	 * @return Internal ID of the deleted Petri net, or 0 if no Petri net was deleted.
	 * @throws SQLException
	 */
	public int deleteNetSystem(int internalID) throws SQLException;
	
	/**
	 * Delete stored Petri net.
	 *  
	 * @param externalID External ID of a Petri net.
	 * @return Internal ID of the deleted Petri net, or 0 if no Petri net was deleted.
	 * @throws SQLException
	 */
	public int deleteNetSystem(String externalID) throws SQLException;
	
	/**
	 * Get all internal IDs of stored Petri nets.
	 * 
	 * @return The set of all internal IDs of stored Petri nets. 
	 * @throws SQLException
	 */
	public Set<Integer> getAllInternalIDs() throws SQLException;
	
	/**
	 * Get Petri net stored under an internal ID.
	 * 
	 * @param internalID Internal ID of a Petri net.
	 * @return {@link INetSystem} stored under the given {@code internalID}; {@code null} if {@code internalID} is not associated with any Petri net.
	 * @throws SQLException
	 */
	public INetSystem<F,N,P,T,M> restoreNetSystem(int internalID) throws SQLException;

	/**
	 * Get PNML content stored under an internal ID.
	 * 
	 * @param internalID Internal ID of a Petri net.
	 * @return PNML content stored under the given {@code internalID}, or {@code null} if {@code internalID} is not associated with any stored Petri net.
	 * @throws SQLException
	 */
	public String restorePNMLContent(int internalID) throws SQLException;

	/**
	 * Reset Petri net storage (WARNING: Removes all stored Petri nets and indexes).
	 * @throws SQLException
	 */
	public void reset() throws SQLException;

	public boolean netHasAllTraceLabels(PQLTrace trace, Set<String> netLabels); //A.P.

	/* -----------------------
	 * LOCATIONS CAPSTONE EDIT
	 * -----------------------
	*/
	
	public int moveNetSystem(String id_name, String targetFolder) throws SQLException;

	public int moveFolderNetSystem(String movingFolder, String targetFolder) throws SQLException;
	
	public int createFolderNetSystem(String folderName, String targetFolder) throws SQLException;
	
	public int deleteFolderNetSystem(String folderName) throws SQLException;
	
}