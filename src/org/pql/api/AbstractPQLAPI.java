package org.pql.api;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.misc.TestRig;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.jbpt.petri.io.PNMLSerializer;
import org.jbpt.petri.persist.IPetriNetPersistenceLayer;
import org.pql.index.IPQLIndex;
import org.pql.label.ILabelManager;
import org.pql.logic.IThreeValuedLogic;
import org.pql.logic.ThreeValuedLogicValue;
import org.pql.mc.IModelChecker;
import org.pql.query.IPQLQuery;
import org.pql.query.PQLQueryMySQL;

/**
 * Artem Polyvyanyy
 */
public class AbstractPQLAPI<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
		implements IPQLAPI<F,N,P,T,M> {
	
	private String mysqlURL		= null;
	private String mysqlUser		= null;
	private String mysqlPassword	= null;
	
	private IModelChecker<F,N,P,T,M>			 modelChecker			= null;
	private IPetriNetPersistenceLayer<F,N,P,T,M> netPersistenceLayer	= null;
	private IPQLIndex<F,N,P,T,M>	 	 		 pqlPersistenceLayer	= null;
	private ILabelManager						 labelMngr				= null;
	private IThreeValuedLogic					 logic					= null;
	private IPQLQuery							 pqlQuery				= null;
	
	public AbstractPQLAPI(String mysqlURL, String mysqlUser, String mysqlPassword, 
			IModelChecker<F,N,P,T,M> modelChecker, 
			IThreeValuedLogic logic, 
			IPetriNetPersistenceLayer<F,N,P,T,M> netPersistenceLayer, 
			IPQLIndex<F,N,P,T,M> pqlPersistenceLayer, 
			ILabelManager labelManager) {
		
		this.mysqlPassword = mysqlPassword;
		this.mysqlUser = mysqlUser;
		this.mysqlURL = mysqlUser;
		
		this.modelChecker			= modelChecker;
		this.netPersistenceLayer	= netPersistenceLayer;
		this.pqlPersistenceLayer	= pqlPersistenceLayer;
		this.labelMngr				= labelManager;
		this.logic 					= logic;
	}

	@Override
	public boolean checkNetSystem(INetSystem<F,N,P,T,M> sys) {
		if (sys==null) return false;	
		return modelChecker.isSoundWorkflowNet(sys);
	}

	@Override
	public int indexNetSystem(INetSystem<F,N,P,T,M> sys, String identifier, Set<Double> similarities) throws SQLException {
		// proposed identifier must be available
		int netID = netPersistenceLayer.identifier2NetID(identifier);
		
		if (netID!=0) return 0;
		
		netID = netPersistenceLayer.storeNetSystem(sys,identifier);
		
		// index labels
		for (T t : sys.getTransitions()) {
			if (t.isSilent()) continue;
			
			this.labelMngr.indexLabel(t.getLabel());
		}
		
		// index tasks
		for (T t : sys.getTransitions()) {
			if (t.isSilent()) continue;
			
			this.labelMngr.indexTask(t.getLabel());
		}
				
		// index behavioral relations
		this.pqlPersistenceLayer.indexBehavioralRelations(sys, netID);
		
		return netID;
	}
	
	@Override
	public int deleteNetSystem(String identifier) throws SQLException {
		return this.netPersistenceLayer.deleteNetSystem(identifier);
	}
	
	@SuppressWarnings("unchecked")
	public INetSystem<F,N,P,T,M> bytes2NetSystem(byte[] pnmlContent) {
		PNMLSerializer PNML = new PNMLSerializer();
		
		return (INetSystem<F,N,P,T,M>) PNML.parse(pnmlContent);
	}

	@Override
	public ThreeValuedLogicValue checkQuery(String pqlQuery, String netIdentifier) throws ClassNotFoundException, SQLException {
		this.pqlQuery = new PQLQueryMySQL(mysqlURL, mysqlUser, mysqlPassword, pqlQuery,logic,labelMngr);
		
		if (this.pqlQuery.getNumberOfParseErrors()==0) {
			this.pqlQuery.configure(netIdentifier);
			return this.pqlQuery.check();
		}
		
		return null;
	}

	@Override
	public void visualiseQuery(String pqlQuery) {
		String[] input = new String[4];
		input[0] = "PQL";
		input[1] = "query";
		input[2] = "-gui";
		input[3] = pqlQuery;
		try {
			TestRig testRig = new TestRig(input);
			testRig.process();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public IPQLQuery getLastQuery() {
		return this.pqlQuery;
	}

	@Override
	public int getLastNumberOfParseErrors() {
		return this.pqlQuery.getNumberOfParseErrors();
	}

	@Override
	public List<String> getLastParseErrorMessages() {
		return this.pqlQuery.getParseErrorMessages();
	}

	@Override
	public void prepareQuery(String pqlQuery) throws ClassNotFoundException, SQLException {
		this.pqlQuery = new PQLQueryMySQL(mysqlURL, mysqlUser, mysqlPassword, pqlQuery,logic,labelMngr);	
	}

	@Override
	public Set<String> checkLastQuery(Set<String> identifiers) {
		Set<String> result = new HashSet<String>(); 
		
		if (identifiers!=null && this.pqlQuery.getNumberOfParseErrors()==0) {
			for (String identifier : identifiers) {
				this.pqlQuery.configure(identifier);			
				
				if (this.pqlQuery.check()==ThreeValuedLogicValue.TRUE)
					result.add(identifier);
			}
		}
		
		return result;
	}

	@Override
	public Set<String> checkLastQuery() throws SQLException {
		Set<String> result = new HashSet<String>();
		
		for (String identifier : netPersistenceLayer.getAllIdentifiers()) {
			this.pqlQuery.configure(identifier);
			if (this.pqlQuery.check()==ThreeValuedLogicValue.TRUE)
				result.add(identifier);
		}
		
		return result;
	}

	@Override
	public void reset() throws SQLException {
		this.netPersistenceLayer.reset();
	}
}
