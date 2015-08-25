package org.pql.bot;

import java.sql.SQLException;

/**
 * An interface to the PQL bot persistence layer 
 * 
 * @author Artem Polyvyanyy
 */
public interface IPQLBotPersistenceLayer {
	
	/**
	 * Send alive signal. 
	 * 
	 * @param botName Name of a PQL bot.
	 * @throws SQLException
	 */
	public void alive(String botName) throws SQLException;
	
	/**
	 * Check if bot with a given name is active.
	 * 
	 * @param botName Name of a PQL bot.
	 * @return <code>true</code> if a PQL bot with <code>botName</code> is active; <code>false</code> otherwise. 
	 * @throws SQLException
	 */
	public boolean isAlive(String botName) throws SQLException;
	
	/**
	 * Get next indexing job ID.
	 * 
	 * @return Unique identifier of some indexing job, or 0 if there are no pending jobs.
	 * @throws SQLException
	 */
	public int getNextIndexJobID() throws SQLException;
	
	/**
	 * Claim indexing job.
	 * 
	 * @param jobID Unique identifier of some indexing job.
	 * @param botName Name of PQL bot that claims job with <code>jobID</code>.
	 * @throws SQLException
	 */
	public void claimIndexJob(int jobID, String botName) throws SQLException;
	
	/**
	 * Start indexing job.
	 * 
	 * @param jobID Unique identifier of some indexing job.
	 * @param botName Name of PQL bot that starts job with <code>jobID</code>.
	 * @return <code>true</code> on successful start; otherwise <code>false</code>.
	 * @throws SQLException
	 */
	public boolean startIndexJob(int jobID, String botName) throws SQLException;
	
	/**
	 * Finish indexing job.
	 * 
	 * @param jobID Unique identifier of some indexing job.
	 * @param botName Name of PQL bot that finishes job with <code>jobID</code>.
	 * @throws SQLException
	 */
	public void finishIndexJob(int jobID, String botName) throws SQLException;
}
