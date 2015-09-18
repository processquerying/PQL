package org.pql.api;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.antlr.v4.runtime.misc.TestRig;
import org.deckfour.xes.classification.XEventClass;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.extension.std.XLifecycleExtension;
import org.deckfour.xes.factory.XFactoryNaiveImpl;
import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XAttributeLiteral;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XAttributeBooleanImpl;
import org.deckfour.xes.model.impl.XAttributeLiteralImpl;
import org.deckfour.xes.model.impl.XAttributeMapImpl;
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
import org.pql.core.PQLTask;
import org.pql.core.PQLTrace;
import org.pql.index.AbstractPQLIndexMySQL;
import org.pql.index.IPQLIndex;
import org.pql.index.IndexStatus;
import org.pql.index.IndexType;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerLevenshtein;
import org.pql.label.LabelManagerType;
import org.pql.label.LabelManagerVSM;
import org.pql.logic.IThreeValuedLogic;
import org.pql.logic.KleeneLogic;
import org.pql.logic.ThreeValuedLogicType;
import org.pql.logic.ThreeValuedLogicValue;
import org.pql.mc.AbstractLoLA2ModelChecker;
import org.pql.mc.IModelChecker;
import org.pql.query.IPQLQuery;
import org.pql.query.PQLQueryMySQL;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetFactory;
import org.processmining.models.graphbased.directed.petrinet.elements.Place;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.connectionfactories.logpetrinet.TransEvClassMapping;
import org.jbpt.petri.Flow;
import org.jbpt.petri.NetSystem;
import org.jbpt.petri.io.PNMLSerializer;

/**
 * Artem Polyvyanyy
 */
public class AbstractPQLAPI<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>>
		extends MySQLConnection
		implements IPQLAPI<F,N,P,T,M> {
	
	private IThreeValuedLogic					 logic					= null;
	private IPetriNetPersistenceLayer<F,N,P,T,M> netPersistenceLayer	= null;
	private ILabelManager						 labelMngr				= null;
	private IModelChecker<F,N,P,T,M>			 modelChecker			= null;
	private IPQLBasicPredicatesOnTasks			 basicPredicates		= null;	
	private	IPQLIndex<F,N,P,T,M>	 	 		 pqlIndex				= null;
	private IndexType							 indexType				= null;
	
	protected String	PQL_INDEX_CANNOT	= "{CALL pql.pql_index_cannot(?)}";

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AbstractPQLAPI(String mySQLURL, String mySQLUser, String mySQLPassword,
					String postgreSQLHost, String postgreSQLName, String postgreSQLUser, String postgreSQLPassword, 
					String lolaPath,
					ThreeValuedLogicType threeValuedLogicType, 
					IndexType indexType,
					LabelManagerType labelManagerType, 
					Double defaultLabelSimilarity,
					Set<Double> indexedLabelSimilarities) throws ClassNotFoundException, SQLException {
		super(mySQLURL,mySQLUser,mySQLPassword);
		
		this.indexType = indexType;
		
		switch (threeValuedLogicType) {
			default: 
				this.logic = new KleeneLogic();
		}
		
		switch (labelManagerType) {
			case VSM:
				this.labelMngr = new LabelManagerVSM(mySQLURL,mySQLUser,mySQLPassword,postgreSQLHost,postgreSQLName,postgreSQLUser,postgreSQLPassword,defaultLabelSimilarity,indexedLabelSimilarities);
				break;
			case LEVENSHTEIN:
				this.labelMngr = new LabelManagerLevenshtein(mySQLURL,mySQLUser,mySQLPassword,defaultLabelSimilarity,indexedLabelSimilarities);
				break;
		}
		
		this.netPersistenceLayer	= new AbstractPetriNetPersistenceLayerMySQL(mySQLURL,mySQLUser,mySQLPassword);
		this.modelChecker			= new AbstractLoLA2ModelChecker(lolaPath);
		this.basicPredicates		= new AbstractPQLBasicPredicatesMC(this.modelChecker,this.logic);
		this.pqlIndex				= new AbstractPQLIndexMySQL(mySQLURL,mySQLUser,mySQLPassword,basicPredicates,this.labelMngr,this.logic,defaultLabelSimilarity,indexedLabelSimilarities);
	}

	@Override
	public boolean checkNetSystem(int internalID) throws SQLException {
		INetSystem<F,N,P,T,M> sys = this.restoreNetSystem(internalID);
		
		boolean result = true;
		if (sys==null) result = false;
		
		sys.loadNaturalMarking();		
		
		if (result) result = this.modelChecker.isSoundWorkflowNet(sys);
		
		if (!result) this.cannnotIndex(internalID);
		
		return result;
	}
	
	private void cannnotIndex(int internalID) throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_CANNOT);
		
		cs.setInt(1, internalID);
		
		cs.execute();
		
		cs.close();
	}
	

	@Override
	public boolean index(int internalID) throws SQLException {		
		return this.pqlIndex.index(internalID, this.indexType);
	}
	
	@Override
	public boolean deleteIndex(int internalID) throws SQLException {
		return this.pqlIndex.deleteIndex(internalID)>0 ? true : false;
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
	public INetSystem<F,N,P,T,M> restoreNetSystem(int internalID) throws SQLException {
		return this.netPersistenceLayer.restoreNetSystem(internalID);
	}

	@Override
	public int storeNetSystem(INetSystem<F,N,P,T,M> sys, String externalID) throws SQLException {
		return this.netPersistenceLayer.storeNetSystem(sys,externalID);
	}

	@Override
	public int storeNetSystem(File pnmlFile, String externalID) throws SQLException {
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
		IPQLQuery query = new PQLQueryMySQL(this.mysqlURL, this.mysqlUser, this.mysqlPassword, pqlQuery, logic, labelMngr);
		
		PQLQueryResult result = new PQLQueryResult(this.mysqlURL, this.mysqlUser, this.mysqlPassword, query);
		
		return result;
	}

	@Override
	public PQLQueryResult query(String pqlQuery, Set<String> externalIDs) throws ClassNotFoundException, SQLException {
		IPQLQuery query = new PQLQueryMySQL(this.mysqlURL, this.mysqlUser, this.mysqlPassword, pqlQuery, logic, labelMngr);
		
		PQLQueryResult result = new PQLQueryResult(this.mysqlURL, this.mysqlUser, this.mysqlPassword, query, externalIDs);
		
		return result;
	}

	@Override
	public int storeNetSystem(byte[] pnmlByteContent, String externalID) throws SQLException {
		return this.netPersistenceLayer.storeNetSystem(pnmlByteContent, externalID);
	}

	@Override
	public IndexStatus getIndexStatus(int internalID) throws SQLException {
		return this.pqlIndex.getIndexStatus(internalID);
	}	
}
