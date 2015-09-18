package org.pql.bot;

import java.sql.CallableStatement;
import java.sql.SQLException;

import org.jbpt.persist.MySQLConnection;

/**
 * An implementation of the {@link IPQLBotPersistenceLayer} interface for MySQL.
 * 
 * @author Artem Polyvyanyy
 */
public class PQLBotPersistenceLayerMySQL extends MySQLConnection implements
		IPQLBotPersistenceLayer {
	
	protected String	PQL_INDEX_BOTS_ALIVE		= "{CALL pql.pql_index_bots_alive(?)}";
	protected String	PQL_INDEX_BOTS_IS_ALIVE		= "{? = CALL pql.pql_index_bots_is_alive(?)}";
	protected String	PQL_INDEX_GET_NEXT_JOB		= "{? = CALL pql.pql_index_get_next_job()}";
	protected String	PQL_INDEX_CLAIM_JOB			= "{CALL pql.pql_index_claim_job(?,?)}";
	protected String	PQL_INDEX_START_JOB			= "{? = CALL pql.pql_index_start_job(?,?)}";
	protected String	PQL_INDEX_FINISH_JOB		= "{CALL pql.pql_index_finish_job(?,?)}";

	public PQLBotPersistenceLayerMySQL(String url, String user, String password) throws ClassNotFoundException, SQLException {
		super(url, user, password);
	}

	@Override
	public void alive(String botName) throws SQLException {
		if (botName == null || botName.isEmpty()) return;
		
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_BOTS_ALIVE);
		
		cs.setString(1, botName);
		
		cs.execute();
		cs.close();
	}

	@Override
	public int getNextIndexJobID() throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_GET_NEXT_JOB);
		
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		cs.close();
		
		return result;
	}

	@Override
	public void claimIndexJob(int jobID, String botName) throws SQLException {
		if (botName == null || botName.isEmpty()) return;
		
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_CLAIM_JOB);
		
		cs.setInt(1, jobID);
		cs.setString(2, botName);
		
		cs.execute();
		cs.close();
	}

	@Override
	public boolean startIndexJob(int jobID, String botName) throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_START_JOB);
		
		cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
		cs.setInt(2, jobID);
		cs.setString(3, botName);
		
		cs.execute();
		
		boolean result = cs.getBoolean(1);
		
		cs.close();
		
		return result;
	}

	@Override
	public void finishIndexJob(int jobID, String botName) throws SQLException {
		if (botName == null || botName.isEmpty()) return;
		
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_FINISH_JOB);
		
		cs.setInt(1, jobID);
		cs.setString(2, botName);
		
		cs.execute();
		cs.close();
	}

	@Override
	public boolean isAlive(String botName) throws SQLException {
		if (botName == null || botName.isEmpty()) return false;
		
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_BOTS_IS_ALIVE);
		
		cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
		cs.setString(2, botName);
		
		cs.execute();
		
		boolean result = cs.getBoolean(1);
		
		cs.close();
		
		return result;
	}

}
