package org.pql.query;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jbpt.persist.MySQLConnection;
import org.pql.core.PQLAttribute;
import org.pql.core.PQLLocation;
import org.pql.core.PQLTask;
import org.pql.label.ILabelManager;
import org.pql.util.ConcurrentHashSet;

public class PQLQueryResult extends MySQLConnection {
	private Set<String>	queryResult = null;
	private IPQLQuery	query = null;
	
	private String pqlQuery = null;
	private ILabelManager labelMngr = null;
	
	private int numberOfQueryThreads = 1;
	
	private List<Set<String>> netIDs = null;
	
	public PQLQueryResult(int numberOfQueryThreads, String mySQLURL, String mySQLUser, String mySQLPassword, 
			String pqlQuery, ILabelManager labelMngr) throws ClassNotFoundException, SQLException {
		super(mySQLURL,mySQLUser,mySQLPassword);
		
		this.numberOfQueryThreads = numberOfQueryThreads > 0 ? numberOfQueryThreads : 1;
		
		this.pqlQuery = pqlQuery;
		this.labelMngr = labelMngr;
		
		this.query = new PQLQueryMySQL(this.mysqlURL, this.mysqlUser, this.mysqlPassword, this.pqlQuery, this.labelMngr);
		
		if (this.getNumberOfParseErrors()==0) {
			this.netIDs = new ArrayList<Set<String>>();
			
			for (int i=0; i<this.numberOfQueryThreads; i++) {
				this.netIDs.add(new HashSet<String>());
			}
			
			CallableStatement cs = connection.prepareCall("{CALL pql.pql_get_indexed_ids()}");
			ResultSet res = cs.executeQuery();
			
			int i = 0;
			while (res.next()) {
				this.netIDs.get((i++) % this.numberOfQueryThreads).add(res.getString(2));
			}
		}
		
		this.query();
	}
	
	public PQLQueryResult(int numberOfQueryThreads, String mySQLURL, String mySQLUser, String mySQLPassword, 
			String pqlQuery, ILabelManager labelMngr, Set<String> externalIDs) throws ClassNotFoundException, SQLException {
		super(mySQLURL,mySQLUser,mySQLPassword);		
		
		this.numberOfQueryThreads = numberOfQueryThreads > 0 ? numberOfQueryThreads : 1;
		
		this.pqlQuery = pqlQuery;
		this.labelMngr = labelMngr;
		
		this.query = new PQLQueryMySQL(this.mysqlURL, this.mysqlUser, this.mysqlPassword, this.pqlQuery, this.labelMngr);
		
		if (this.getNumberOfParseErrors()==0) {
			this.netIDs = new ArrayList<Set<String>>();
			
			for (int i=0; i<this.numberOfQueryThreads; i++) {
				this.netIDs.add(new HashSet<String>());
			}
			
			int i = 0;
			for (String id : externalIDs) {
				this.netIDs.get((i++) % this.numberOfQueryThreads).add(id);
			}
		}
		
		this.query();
	}
	
	private void query() throws ClassNotFoundException, SQLException {
		this.queryResult = new ConcurrentHashSet<String>();
		
		if (this.getNumberOfParseErrors()>0) return;
		
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		
		int q=0;
		ThreadGroup tg = new ThreadGroup("PQL");
		tg.setDaemon(true);
		tg.setMaxPriority(Thread.MAX_PRIORITY);
		
		PQLQueryThread qThread = new PQLQueryThread(tg, "PQL"+(q++), this.query, this.netIDs.get(0), this.queryResult);
		qThread.setPriority(Thread.MAX_PRIORITY);
		qThread.start();
		
		for (int j=1; j<this.numberOfQueryThreads; j++) {
			IPQLQuery newQuery = new PQLQueryMySQL(this.mysqlURL, this.mysqlUser, this.mysqlPassword, this.pqlQuery, this.labelMngr);
			PQLQueryThread newThread = new PQLQueryThread(tg, "PQL"+(q++), newQuery, this.netIDs.get(j), this.queryResult);
			newThread.setPriority(Thread.MAX_PRIORITY);
			newThread.start();
		}
		
		// TODO improve termination on completion of all query threads
		while (!tg.isDestroyed()) {
			try {
				Thread.sleep(50L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
	}

	/**
	 * Get number of parse errors in the last query.
	 * 
	 * @return Number of parse errors in the last query.
	 */
	public int getNumberOfParseErrors() {
		return this.query.getNumberOfParseErrors();
	}

	/**
	 * Get parse error messages of the last query.
	 * 
	 * @return Parse error messages of the last query.  
	 */
	public List<String> getParseErrorMessages() {
		return this.query.getParseErrorMessages();
	}
	
	/**
	 * Get identifiers of Petri nets that match the query. 
	 * 
	 * @return Set of all external identifiers that match the query.
	 */
	public Set<String> getSearchResults() {
		return this.queryResult;
	}
	
	/**
	 * Get all variable declarations of this PQL query.
	 * 
	 * @return The {@link Map} of all (variable name, set of tasks) key-value pairs declared in this PQL query.
	 */
	public Map<String,Set<PQLTask>> getVariables() {
		return this.query.getVariables();
	}
	
	/**
	 * Get all {@link PQLAttribute}s of this PQL query.
	 * 
	 * @return The {@link Set} of all {@link PQLAttribute}s of this PQL query.
	 */
	public Set<PQLAttribute> getAttributes() {
		return this.query.getAttributes();
	}
	
	/**
	 * Get all {@link PQLLocation}s of this PQL query.
	 * 
	 * @return The {@link Set} of all {@link PQLLocation}s of this PQL query.
	 */
	public Set<PQLLocation> getLocations() {
		return this.query.getLocations();
	}
	
	public Map<PQLTask,PQLTask> getTaskMap() {
		return this.query.getTaskMap();
	}
}
