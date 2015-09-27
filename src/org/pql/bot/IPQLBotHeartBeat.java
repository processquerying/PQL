package org.pql.bot;

import java.sql.SQLException;

/**
 * The PQL heart beat interface. 
 * 
 * @author Artem Polyvyanyy
 */
public interface IPQLBotHeartBeat {
	
	/**
	 * Send alive message. 
	 * 
	 * @param botName Name of a PQL bot that sends alive message.
	 * @throws SQLException
	 */
	public void alive(String botName) throws SQLException;
	
	/**
	 * Check if a PQL bot with a given name is alive.
	 * 
	 * @param botName Name of a PQL bot.
	 * @return {@code true} if a PQL bot with the given name is alive; {@code false} otherwise. 
	 * @throws SQLException
	 */
	public boolean isAlive(String botName) throws SQLException;
}
