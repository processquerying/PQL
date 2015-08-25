package org.pql.api;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jbpt.persist.MySQLConnection;
import org.pql.core.PQLAttribute;
import org.pql.core.PQLLocation;
import org.pql.core.PQLTask;
import org.pql.logic.ThreeValuedLogicValue;
import org.pql.query.IPQLQuery;

public class PQLQueryResult extends MySQLConnection {
	private IPQLQuery query = null; 
	private Set<String> queryResult = null;
	
	protected PQLQueryResult(String mySQLURL, String mySQLUser, String mySQLPassword, IPQLQuery query) throws ClassNotFoundException, SQLException {
		super(mySQLURL,mySQLUser,mySQLPassword);
		this.query = query;
		this.queryResult = new HashSet<String>();
		
		if (this.getNumberOfParseErrors()==0) {
			CallableStatement cs = connection.prepareCall("{CALL pql.pql_get_indexed_ids()}");
			
			ResultSet res = cs.executeQuery();
			
			while (res.next()) {
				this.query.configure(res.getString(2));
				
				if (this.query.check()==ThreeValuedLogicValue.TRUE) {
					this.queryResult.add(res.getString(2));
				}
			}
		}
	}
	
	protected PQLQueryResult(String mySQLURL, String mySQLUser, String mySQLPassword, IPQLQuery query, Set<String> externalIDs) throws ClassNotFoundException, SQLException {
		super(mySQLURL,mySQLUser,mySQLPassword);
		this.query = query;
		this.queryResult = new HashSet<String>();
		
		if (this.getNumberOfParseErrors()==0) {
			
			for (String s : externalIDs) {
				this.query.configure(s);
				if (this.query.check()==ThreeValuedLogicValue.TRUE)
					this.queryResult.add(s);
			}
		}
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
