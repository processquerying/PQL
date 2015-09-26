package org.pql.bot;

import java.sql.SQLException;

/**
 * An interface to the PQL bot persistence layer 
 * 
 * @author Artem Polyvyanyy
 */
public interface IPQLBotAlive {
	
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
}
