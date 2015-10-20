package org.pql.query;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.jbpt.persist.MySQLConnection;
import org.pql.core.PQLAttribute;
import org.pql.core.PQLLocation;
import org.pql.core.PQLTask;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerLevenshtein;
import org.pql.label.LabelManagerLuceneVSM;
import org.pql.label.LabelManagerThemisVSM;
import org.pql.label.LabelManagerType;
import org.pql.util.ConcurrentHashSet;

import java.sql.Connection;

public class PQLQueryResult extends MySQLConnection {
	private Set<String>	queryResult = null;
	private IPQLQuery	query = null;
	private String pqlQuery = null;
	private ILabelManager labelMngr = null;
	private int numberOfQueryThreads = 1;
	public AtomicInteger filteredModels = new AtomicInteger(0); //A.P. for experiment 1;
	
	//A.P.
	private PriorityBlockingQueue<String> 		 queue 	= null; 
	private AtomicBoolean 						 netIDsLoaded = null; 
	private String 								 postgreSQLHost = null;
	private String 								 postgreSQLName = null;
	private String 								 postgreSQLUser = null;
	private String 								 postgreSQLPassword = null;
	private Double 								 defaultLabelSimilarity = 1.0;
	private Set<Double> 						 indexedLabelSimilarities = null;
	private String 								 labelSimilarityConfig = null;
	private LabelManagerType 					 labelManagerType = null;

	
	public PQLQueryResult(int numberOfQueryThreads, String mySQLURL, String mySQLUser, String mySQLPassword, 
		String pqlQuery, ILabelManager labelMngr, String postgreSQLHost, 
		String postgreSQLName, String postgreSQLUser, String postgreSQLPassword, String labelSimilarityConfig, 
		Double defaultLabelSimilarity, Set<Double> indexedLabelSimilarities, LabelManagerType labelManagerType) 
				throws ClassNotFoundException, SQLException {
		super(mySQLURL,mySQLUser,mySQLPassword);
		this.numberOfQueryThreads = numberOfQueryThreads > 0 ? numberOfQueryThreads : 1;
		this.pqlQuery = pqlQuery;
		this.labelMngr = labelMngr;
		this.query = new PQLQueryMySQL(this.filteredModels, this.getConnection(), this.pqlQuery, this.labelMngr);//A.P.
		this.queryResult = new ConcurrentHashSet<String>();
		
		//A.P.
		this.queue 	= new  PriorityBlockingQueue<String>();  
		this.netIDsLoaded = new AtomicBoolean(false);
		this.postgreSQLHost = postgreSQLHost;
		this.postgreSQLName = postgreSQLName;
		this.postgreSQLUser = postgreSQLUser;
		this.postgreSQLPassword = postgreSQLPassword;
		this.defaultLabelSimilarity = defaultLabelSimilarity;
		this.indexedLabelSimilarities = new HashSet<Double>();
		this.indexedLabelSimilarities.addAll(indexedLabelSimilarities);
		this.labelSimilarityConfig = labelSimilarityConfig;
		this.labelManagerType = labelManagerType;
	
		if (this.getNumberOfParseErrors()>0) return;
		
			
		this.query();
	}
	
	public PQLQueryResult(int numberOfQueryThreads, String mySQLURL, String mySQLUser, String mySQLPassword, 

			String pqlQuery, ILabelManager labelMngr, String postgreSQLHost, 
			String postgreSQLName, String postgreSQLUser, String postgreSQLPassword, String labelSimilarityConfig, 
			Double defaultLabelSimilarity, Set<Double> indexedLabelSimilarities, LabelManagerType labelManagerType, 
			Set<String> externalIDs) 
					throws ClassNotFoundException, SQLException {
		super(mySQLURL,mySQLUser,mySQLPassword);
		this.numberOfQueryThreads = numberOfQueryThreads > 0 ? numberOfQueryThreads : 1;
		this.pqlQuery = pqlQuery;
		this.labelMngr = labelMngr;
		this.query = new PQLQueryMySQL(this.filteredModels, this.getConnection(), this.pqlQuery,this.labelMngr);//A.P.
		this.queryResult = new ConcurrentHashSet<String>();
		
		//A.P.
		this.queue 	= new  PriorityBlockingQueue<String>();  
		this.netIDsLoaded = new AtomicBoolean(false);
		this.postgreSQLHost = postgreSQLHost;
		this.postgreSQLName = postgreSQLName;
		this.postgreSQLUser = postgreSQLUser;
		this.postgreSQLPassword = postgreSQLPassword;
		this.defaultLabelSimilarity = defaultLabelSimilarity;
		this.indexedLabelSimilarities = new HashSet<Double>();
		this.indexedLabelSimilarities.addAll(indexedLabelSimilarities);
		this.labelSimilarityConfig = labelSimilarityConfig;
		this.labelManagerType = labelManagerType;
	
		if (this.getNumberOfParseErrors()>0) return;
			
			this.query(externalIDs);
		}
	
	//A.P. 
	private void query() throws ClassNotFoundException, SQLException {
	
		Set<PQLQueryMySQL> queries = new HashSet<PQLQueryMySQL>();
		Set<Thread> threads = new HashSet<Thread>();
		
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
				
		//start threads
		int q=1;
		for (int j=1; j<this.numberOfQueryThreads; j++) {
			
			Connection con = (new MySQLConnection(mysqlURL,mysqlUser,mysqlPassword)).getConnection(); 
			
			//TODO improve creation of thread label managers
			ILabelManager threadLabelMngr = null;
			try {
				switch (labelManagerType) 
				{
				case THEMIS_VSM:
					threadLabelMngr = new LabelManagerThemisVSM(con,postgreSQLHost,postgreSQLName,postgreSQLUser,postgreSQLPassword,defaultLabelSimilarity,indexedLabelSimilarities);
					break;
				case LUCENE:
					threadLabelMngr = new LabelManagerLuceneVSM(con, defaultLabelSimilarity, indexedLabelSimilarities, labelSimilarityConfig);
					break;
				default:
					threadLabelMngr = new LabelManagerLevenshtein(con,defaultLabelSimilarity,indexedLabelSimilarities);
					break;
				}
			} catch (IOException e) {e.printStackTrace();}
	
			IPQLQuery threadQuery = new PQLQueryMySQL(this.filteredModels, con, this.pqlQuery, threadLabelMngr);
			queries.add((PQLQueryMySQL) threadQuery);
			PQLQueryThread newThread = new PQLQueryThread("PQL"+(q++), threadQuery, queue, this.queryResult, this.netIDsLoaded);
			
			newThread.setPriority(Thread.MAX_PRIORITY);
			newThread.start();
			threads.add(newThread);
		}
		
			CallableStatement cs = connection.prepareCall("{CALL pql.pql_get_indexed_ids()}");
			ResultSet res = cs.executeQuery();
		
			while (res.next()) {
				queue.put(res.getString(2));
			}
			
			this.netIDsLoaded.set(true);
			
		queries.add((PQLQueryMySQL) this.query);
		PQLQueryThread mainThread = new PQLQueryThread("PQLmain", this.query, queue, this.queryResult, this.netIDsLoaded);
		mainThread.checkQuery();
		
		try{
			for (Thread thread : threads) {
			    thread.join();
			}
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		for (PQLQueryMySQL query : queries)
			query.disconnect();
		
		Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
		
	}

	private void query(Set<String> externalIDs) throws ClassNotFoundException, SQLException {
		
		Set<PQLQueryMySQL> queries = new HashSet<PQLQueryMySQL>();
		Set<Thread> threads = new HashSet<Thread>();
		
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
				
		//start threads
		int q=1;
		for (int j=1; j<this.numberOfQueryThreads; j++) {
			
			Connection con = (new MySQLConnection(mysqlURL,mysqlUser,mysqlPassword)).getConnection(); 
			ILabelManager threadLabelMngr = null;
			try {
				switch (labelManagerType) 
				{
				case THEMIS_VSM:
					threadLabelMngr = new LabelManagerThemisVSM(con,postgreSQLHost,postgreSQLName,postgreSQLUser,postgreSQLPassword,defaultLabelSimilarity,indexedLabelSimilarities);
					break;
				case LUCENE:
					threadLabelMngr = new LabelManagerLuceneVSM(con, defaultLabelSimilarity, indexedLabelSimilarities, labelSimilarityConfig);
					break;
				default:
					threadLabelMngr = new LabelManagerLevenshtein(con,defaultLabelSimilarity,indexedLabelSimilarities);
					break;
				}
			} catch (IOException e) {e.printStackTrace();}
	
			IPQLQuery threadQuery = new PQLQueryMySQL(this.filteredModels, con, this.pqlQuery, threadLabelMngr);
			queries.add((PQLQueryMySQL) threadQuery);
			PQLQueryThread newThread = new PQLQueryThread("PQL"+(q++), threadQuery, queue, this.queryResult, this.netIDsLoaded);

			newThread.setPriority(Thread.MAX_PRIORITY);
			newThread.start();
			threads.add(newThread);
		}
		
			for (String id: externalIDs) {
				queue.put(id);
			}
			
			this.netIDsLoaded.set(true);
			
		queries.add((PQLQueryMySQL) this.query);
		PQLQueryThread qThread = new PQLQueryThread("PQLmain", this.query, queue, this.queryResult, this.netIDsLoaded);
		qThread.checkQuery();
		
		try{
			for (Thread thread : threads) {
			    thread.join();
			}
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		for (PQLQueryMySQL query : queries)
			query.disconnect();
		
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
