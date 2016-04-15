package org.pql.api;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.antlr.v4.runtime.misc.TestRig;
import org.jbpt.persist.MySQLConnection;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.jbpt.petri.persist.AbstractPetriNetPersistenceLayerMySQL;
import org.jbpt.petri.persist.IPetriNetPersistenceLayer;
import org.pql.core.AbstractPQLBasicPredicatesMC;
import org.pql.core.IPQLBasicPredicatesOnTasks;
import org.pql.index.AbstractPQLIndexMySQL;
import org.pql.index.IPQLIndex;
import org.pql.index.IndexStatus;
import org.pql.index.IndexType;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerLevenshtein;
import org.pql.label.LabelManagerLuceneVSM;
import org.pql.label.LabelManagerThemisVSM;
import org.pql.label.LabelManagerType;
import org.pql.mc.AbstractLoLA2ModelChecker;
import org.pql.mc.IModelChecker;
import org.pql.query.PQLQueryResult;

/**
 * Artem Polyvyanyy
 */
public class AbstractPQLAPI<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>>
		extends MySQLConnection
		implements IPQLAPI<F,N,P,T,M> {
	
	private IPetriNetPersistenceLayer<F,N,P,T,M> netPersistenceLayer	= null;
	private ILabelManager						 labelMngr				= null;
	private IModelChecker<F,N,P,T,M>			 modelChecker			= null;
	private IPQLBasicPredicatesOnTasks			 basicPredicates		= null;	
	private	IPQLIndex<F,N,P,T,M>	 	 		 pqlIndex				= null;
	private IndexType							 indexType				= null;
	private int									 numberOfQueryThreads	= 1;
	private long								 indexTime				= 86400;
	private long								 sleepTime				= 300;
	//A.P.
	private String 								 postgreSQLHost = null;
	private String 								 postgreSQLName = null;
	private String 								 postgreSQLUser = null;
	private String 								 postgreSQLPassword = null;
	private Double 								 defaultLabelSimilarity = 1.0;
	private Set<Double> 						 indexedLabelSimilarities = null;
	private String 								 labelSimilarityConfig = null;
	private LabelManagerType 					 labelManagerType = null;
			
	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	public AbstractPQLAPI(String mySQLURL, String mySQLUser, String mySQLPassword,
					String postgreSQLHost, String postgreSQLName, String postgreSQLUser, String postgreSQLPassword, 
					String lolaPath,
					String labelSimilarityConfig, 
					IndexType indexType,
					LabelManagerType labelManagerType, 
					Double defaultLabelSimilarity,
					Set<Double> indexedLabelSimilarities,
					int numberOfQueryThreads,
					long indexTime,
					long sleepTime) throws ClassNotFoundException, SQLException, IOException {
		super(mySQLURL,mySQLUser,mySQLPassword);
				
		this.indexType = indexType;
		this.numberOfQueryThreads = numberOfQueryThreads;
		this.indexTime = indexTime;
		this.sleepTime = sleepTime;
		
		switch (labelManagerType) {
			case THEMIS_VSM:
				this.labelMngr = new LabelManagerThemisVSM(this.connection,postgreSQLHost,postgreSQLName,postgreSQLUser,postgreSQLPassword,defaultLabelSimilarity,indexedLabelSimilarities);
				break;
			case LUCENE:
				this.labelMngr = new LabelManagerLuceneVSM(this.connection, defaultLabelSimilarity, indexedLabelSimilarities, labelSimilarityConfig);
				break;
			default:
				this.labelMngr = new LabelManagerLevenshtein(this.connection,defaultLabelSimilarity,indexedLabelSimilarities);
				break;
		}
		
		this.netPersistenceLayer	= new AbstractPetriNetPersistenceLayerMySQL(this.connection);
		this.modelChecker			= new AbstractLoLA2ModelChecker(lolaPath);
		this.basicPredicates		= new AbstractPQLBasicPredicatesMC(this.modelChecker);
		this.pqlIndex				= new AbstractPQLIndexMySQL(this.connection,basicPredicates,this.labelMngr,this.modelChecker,defaultLabelSimilarity,indexedLabelSimilarities,this.indexType, this.indexTime, this.sleepTime);
	
	//A.P. TODO think of a better way to create thread label managers
		  this.postgreSQLHost = postgreSQLHost;
		  this.postgreSQLName = postgreSQLName;
		  this.postgreSQLUser = postgreSQLUser;
		  this.postgreSQLPassword = postgreSQLPassword;
		  this.defaultLabelSimilarity = defaultLabelSimilarity;
		  this.indexedLabelSimilarities = new HashSet<Double>();
		  this.indexedLabelSimilarities.addAll(indexedLabelSimilarities);
		  this.labelSimilarityConfig = labelSimilarityConfig;
		  this.labelManagerType = labelManagerType;
	}

	@Override
	public boolean checkModel(int internalID) throws SQLException {
		return this.pqlIndex.checkNetSystem(internalID);
	}
	

	@Override
	public boolean index(int internalID) throws SQLException {		
		return this.pqlIndex.index(internalID, this.indexType);
	}
	
	@Override
	public boolean deleteIndex(int internalID) throws SQLException {
		return this.pqlIndex.deleteIndex(internalID);
	}

	@Override
	public void parsePQLQuery(String pqlPath) throws Exception {
		String[] input = new String[4];
		input[0] = "PQL";
		input[1] = "query";
		input[2] = "-gui";
		input[3] = pqlPath;
		
		TestRig testRig = new TestRig(input);
		testRig.process();
	}


	@Override
	public void reset() throws SQLException {
		this.netPersistenceLayer.reset();
	}

	@Override
	public INetSystem<F,N,P,T,M> restoreModel(int internalID) throws SQLException {
		return this.netPersistenceLayer.restoreNetSystem(internalID);
	}

	@Override
	public int storeModel(INetSystem<F,N,P,T,M> sys, String externalID) throws SQLException {
		return this.netPersistenceLayer.storeNetSystem(sys,externalID);
	}

	@Override
	public int storeModel(File pnmlFile, String externalID) throws SQLException {
		return this.netPersistenceLayer.storeNetSystem(pnmlFile, externalID);
	}
	
	@Override
	public void cleanupIndex() throws SQLException {
		this.pqlIndex.cleanupIndex();
	}	
	

	@Override
	public int getInternalID(String externalID) throws SQLException {
		return this.netPersistenceLayer.getInternalID(externalID);
	}
	
	@Override
	public String getExternalID(int internalID) throws SQLException {
		return this.netPersistenceLayer.getExternalID(internalID);
	}
	
	@Override
	public PQLQueryResult query(String pqlQuery) throws ClassNotFoundException, SQLException {		

		PQLQueryResult result = new PQLQueryResult(this.numberOfQueryThreads, this.mysqlURL, 
				this.mysqlUser, this.mysqlPassword, pqlQuery, labelMngr, this.postgreSQLHost, 
				this.postgreSQLName, this.postgreSQLUser, this.postgreSQLPassword, this.labelSimilarityConfig, 
				this.defaultLabelSimilarity, this.indexedLabelSimilarities, this.labelManagerType);//A.P.
		result.disconnect();//A.P.
		
		return result;
	}
	

	@Override
	public PQLQueryResult query(String pqlQuery, Set<String> externalIDs) throws ClassNotFoundException, SQLException {

		PQLQueryResult result = new PQLQueryResult(this.numberOfQueryThreads, this.mysqlURL, 
				this.mysqlUser, this.mysqlPassword, pqlQuery, labelMngr, this.postgreSQLHost, 
				this.postgreSQLName, this.postgreSQLUser, this.postgreSQLPassword, this.labelSimilarityConfig, 
				this.defaultLabelSimilarity, this.indexedLabelSimilarities, this.labelManagerType, externalIDs);//A.P.
		result.disconnect();//A.P.

		return result;
	}

	@Override
	public int storeModel(byte[] pnmlByteContent, String externalID) throws SQLException {
		return this.netPersistenceLayer.storeNetSystem(pnmlByteContent, externalID);
	}

	@Override
	public IndexStatus getIndexStatus(int internalID) throws SQLException {
		return this.pqlIndex.getIndexStatus(internalID);
	}

	@Override
	public boolean deleteModel(int internalID) throws SQLException {
		return this.netPersistenceLayer.deleteNetSystem(internalID) > 0;
	}
	
	//A.P.
	public Set<String> getExternalIDs()
	{
		Set<String> externalIDs = new HashSet<String>();
		try {
			
			Set<Integer> internalIDs = this.netPersistenceLayer.getAllInternalIDs();
			for(Integer id: internalIDs)
			externalIDs.add(this.getExternalID(id));
			
		} catch (SQLException e) {e.printStackTrace();
		}
		
		return externalIDs;
	}

}
