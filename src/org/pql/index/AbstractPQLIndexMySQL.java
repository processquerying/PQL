package org.pql.index;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jbpt.persist.MySQLConnection;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.pql.core.IPQLBasicPredicatesOnTasks;
import org.pql.core.PQLTask;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerLevenshtein;
import org.pql.logic.IThreeValuedLogic;
import org.pql.logic.ThreeValuedLogicValue;

/**
 * @author Artem Polyvyanyy
 */
public class AbstractPQLIndexMySQL<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
				extends MySQLConnection
				implements IPQLIndex<F,N,P,T,M> {
	
	protected String 	PETRI_NET_IDENTIFIER_TO_ID	= "{? = CALL pql.jbpt_petri_nets_identifier2id(?)}";
	
	protected String	PQL_CAN_OCCUR_CREATE		= "{CALL pql.pql_can_occur_create(?,?,?)}";
	protected String	PQL_ALWAYS_OCCURS_CREATE	= "{CALL pql.pql_always_occurs_create(?,?,?)}";
	protected String	PQL_CAN_CONFLICT_CREATE		= "{CALL pql.pql_can_conflict_create(?,?,?,?)}";
	protected String	PQL_CAN_COOCCUR_CREATE		= "{CALL pql.pql_can_cooccur_create(?,?,?,?)}";	
	protected String	PQL_TOTAL_CAUSAL_CREATE		= "{CALL pql.pql_total_causal_create(?,?,?,?)}";
	protected String	PQL_TOTAL_CONCUR_CREATE		= "{CALL pql.pql_total_concur_create(?,?,?,?)}";
	
	ILabelManager				labelMngr		= null;
	IPQLBasicPredicatesOnTasks	basicPredicates = null;
	
	public AbstractPQLIndexMySQL(String mysqlURL, String mysqlUser, String mysqlPassword, IPQLBasicPredicatesOnTasks basicPredicates, IThreeValuedLogic logic, double defaultSim, Set<Double> indexedSims) throws ClassNotFoundException, SQLException {
		super(mysqlURL,mysqlUser,mysqlPassword);
	
		this.labelMngr		 = new LabelManagerLevenshtein(mysqlURL,mysqlUser,mysqlPassword,defaultSim,indexedSims);
		this.basicPredicates = basicPredicates;
	}
	
	@Override
	public int indexBehavioralRelations(INetSystem<F,N,P,T,M> sys, int netID) throws SQLException {
		Set<String> labels = new HashSet<String>();
		for (T t : sys.getTransitions()) {
			if (t.isSilent()) continue;
			
			labels.add(t.getLabel().trim());
		}
		
		Set<PQLTask> tasks = new HashSet<PQLTask>();
		for (String label : labels) {
			for (Double sim : this.labelMngr.getIndexedSimilarities()) {
				PQLTask task = new PQLTask(label,sim);
				labelMngr.loadTask(task, this.labelMngr.getIndexedSimilarities());
				tasks.add(task);
			}
		}
		
		this.basicPredicates.configure(sys);
		
		// index unary relations
		Map<Set<String>,ThreeValuedLogicValue> canOccurMap		= new HashMap<Set<String>,ThreeValuedLogicValue>();
		Map<Set<String>,ThreeValuedLogicValue> alwaysOccursMap	= new HashMap<Set<String>,ThreeValuedLogicValue>();
		ThreeValuedLogicValue canOccurValue		= null;
		ThreeValuedLogicValue alwaysOccursValue = null;
		for (PQLTask task : tasks) {
			// canOccur
			canOccurValue = canOccurMap.get(task.getSimilarLabels());
			if (canOccurValue==null) { 
				canOccurValue = this.basicPredicates.canOccur(task);
				canOccurMap.put(task.getSimilarLabels(), canOccurValue);
			}
			this.indexUnaryPredicate(this.PQL_CAN_OCCUR_CREATE, netID, task, canOccurValue);
			
			//alwaysOccurs
			alwaysOccursValue = alwaysOccursMap.get(task.getSimilarLabels());
			if (alwaysOccursValue==null) {
				alwaysOccursValue = this.basicPredicates.alwaysOccurs(task);
				alwaysOccursMap.put(task.getSimilarLabels(), alwaysOccursValue);
			}
			this.indexUnaryPredicate(this.PQL_ALWAYS_OCCURS_CREATE, netID, task, alwaysOccursValue);
		}
		canOccurMap.clear();
		alwaysOccursMap.clear();
		
		// index symmetric binary relations
		Map<Set<String>,Map<Set<String>,ThreeValuedLogicValue>> totalConcurMap	= new HashMap<Set<String>,Map<Set<String>,ThreeValuedLogicValue>>();
		Map<Set<String>,Map<Set<String>,ThreeValuedLogicValue>> canCooccurMap	= new HashMap<Set<String>,Map<Set<String>,ThreeValuedLogicValue>>();
		
		ThreeValuedLogicValue totalConcurValue	= null;
		ThreeValuedLogicValue canCooccurValue	= null;
		
		for (PQLTask taskA : tasks) {
			for (PQLTask taskB : tasks) {
				
				canCooccurValue = this.checkSymmetricRelation(canCooccurMap,taskA.getSimilarLabels(),taskB.getSimilarLabels());
				if (canCooccurValue==null) {
					canCooccurValue = this.basicPredicates.canCooccur(taskA,taskB);
					this.storeSymmetricRelation(canCooccurMap,taskA.getSimilarLabels(),taskB.getSimilarLabels(),canCooccurValue);
				}
				this.indexBinaryPredicate(this.PQL_CAN_COOCCUR_CREATE,netID,taskA,taskB,canCooccurValue);
				
				totalConcurValue = this.checkSymmetricRelation(totalConcurMap,taskA.getSimilarLabels(),taskB.getSimilarLabels());
				if (totalConcurValue==null) {
					totalConcurValue = this.basicPredicates.totalConcur(taskA,taskB);
					this.storeSymmetricRelation(totalConcurMap,taskA.getSimilarLabels(),taskB.getSimilarLabels(),totalConcurValue);
				}
				this.indexBinaryPredicate(this.PQL_TOTAL_CONCUR_CREATE,netID,taskA,taskB,totalConcurValue);
			}
		}
		canCooccurMap.clear();
		totalConcurMap.clear();
		
		// index asymmetric binary relations
		for (PQLTask taskA : tasks) {
			for (PQLTask taskB : tasks) {
				this.indexBinaryPredicate(this.PQL_CAN_CONFLICT_CREATE,netID,taskA,taskB,this.basicPredicates.canConflict(taskA,taskB));
				this.indexBinaryPredicate(this.PQL_TOTAL_CAUSAL_CREATE,netID,taskA,taskB,this.basicPredicates.totalCausal(taskA,taskB));
			}
		}
		
		return 0;
	}

	private void storeSymmetricRelation(Map<Set<String>, Map<Set<String>, ThreeValuedLogicValue>> map,
			Set<String> labels1, Set<String> labels2, ThreeValuedLogicValue value) {
		Map<Set<String>,ThreeValuedLogicValue> ls2v = map.get(labels1);
		if (ls2v==null) {
			 Map<Set<String>,ThreeValuedLogicValue> newls2v = new HashMap<Set<String>,ThreeValuedLogicValue>();
			 newls2v.put(labels2, value);
			 map.put(labels1, newls2v);
		}
		else {
			ls2v.put(labels2, value);
		}
		
		ls2v = map.get(labels2);
		if (ls2v==null) {
			 Map<Set<String>,ThreeValuedLogicValue> newls2v = new HashMap<Set<String>,ThreeValuedLogicValue>();
			 newls2v.put(labels1, value);
			 map.put(labels2, newls2v);
		}
		else {
			ls2v.put(labels1, value);
		}
	}

	private ThreeValuedLogicValue checkSymmetricRelation(Map<Set<String>, Map<Set<String>, ThreeValuedLogicValue>> map,
			Set<String> labels1, Set<String> labels2) {
		Map<Set<String>,ThreeValuedLogicValue> ls2v = map.get(labels1);
		if (ls2v==null) return null;

		return ls2v.get(labels2);
	}

	private void indexUnaryPredicate(String call, int netID, PQLTask task, ThreeValuedLogicValue value) throws SQLException {
		if (task.getID()<1) return;
		if (value==ThreeValuedLogicValue.UNKNOWN) return;
		
		CallableStatement cs = connection.prepareCall(call);
		
		cs.setInt(1, netID);
		cs.setInt(2, task.getID());
		cs.setBoolean(3,value==ThreeValuedLogicValue.TRUE ? true : false);
		
		cs.execute();
		
		cs.close();
	}
	

	private void indexBinaryPredicate(String call, int netID, PQLTask taskA, PQLTask taskB, ThreeValuedLogicValue value) throws SQLException {
		if (value==ThreeValuedLogicValue.UNKNOWN) return;
		
		CallableStatement cs = connection.prepareCall(call);
		
		cs.setInt(1, netID);
		cs.setInt(2,taskA.getID());
		cs.setInt(3,taskB.getID());
		boolean v = value==ThreeValuedLogicValue.TRUE ? true : false;
		cs.setBoolean(4,v);
		
		cs.execute();
		
		cs.close();
	}

}
