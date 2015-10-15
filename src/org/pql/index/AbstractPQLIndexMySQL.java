package org.pql.index;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.jbpt.petri.persist.PetriNetPersistenceLayerMySQL;
import org.pql.bot.AbstractPQLBot;
import org.pql.core.IPQLBasicPredicatesOnTasks;
import org.pql.core.PQLTask;
import org.pql.label.ILabelManager;
import org.pql.logic.IThreeValuedLogic;
import org.pql.logic.ThreeValuedLogicValue;
import org.pql.mc.IModelChecker;

/**
 * An implementation of the {@link IPQLIndex} interface
 * 
 * @author Artem Polyvyanyy
 */
public class AbstractPQLIndexMySQL<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
				implements IPQLIndex<F,N,P,T,M> {
	
	protected Connection connection = null;
	protected String 	PQL_INDEX_GET_TYPE			= "{? = CALL pql.pql_index_get_type(?)}";
	protected String 	PQL_INDEX_GET_STATUS		= "{? = CALL pql.pql_index_get_status(?)}";
	protected String 	PQL_INDEX_DELETE			= "{? = CALL pql.pql_index_delete(?)}";
	protected String 	PQL_INDEX_CLEANUP			= "{CALL pql.pql_index_cleanup()}";
	
	protected String	PQL_CAN_OCCUR_CREATE		= "{CALL pql.pql_can_occur_create(?,?,?)}";
	protected String	PQL_ALWAYS_OCCURS_CREATE	= "{CALL pql.pql_always_occurs_create(?,?,?)}";
	protected String	PQL_CAN_CONFLICT_CREATE		= "{CALL pql.pql_can_conflict_create(?,?,?,?)}";
	protected String	PQL_CAN_COOCCUR_CREATE		= "{CALL pql.pql_can_cooccur_create(?,?,?,?)}";	
	protected String	PQL_TOTAL_CAUSAL_CREATE		= "{CALL pql.pql_total_causal_create(?,?,?,?)}";
	protected String	PQL_TOTAL_CONCUR_CREATE		= "{CALL pql.pql_total_concur_create(?,?,?,?)}";
	
	protected String	PQL_INDEX_GET_NEXT_JOB		= "{? = CALL pql.pql_index_get_next_job()}";
	protected String	PQL_INDEX_CLAIM_JOB			= "{CALL pql.pql_index_claim_job(?,?)}";
	protected String	PQL_INDEX_START_JOB			= "{? = CALL pql.pql_index_start_job(?,?)}";
	protected String	PQL_INDEX_FINISH_JOB		= "{CALL pql.pql_index_finish_job(?,?)}";
	protected String	PQL_INDEX_CANNOT			= "{CALL pql.pql_index_cannot(?)}";
	
	//TODO create CallableStatement for each DB query and check if it is null
	
	ILabelManager					labelMngr		= null;
	IPQLBasicPredicatesOnTasks		basicPredicates = null;
	IModelChecker<F,N,P,T,M>		MC 				= null;
	PetriNetPersistenceLayerMySQL	PNPersist		= null;
	
	IndexType indexType = IndexType.PREDICATES; 
	long indexTime = 86400;
	long sleepTime = 300;
	
	public AbstractPQLIndexMySQL(Connection con, IPQLBasicPredicatesOnTasks basicPredicates, ILabelManager labelManager, 
			IModelChecker<F,N,P,T,M> mc,
			IThreeValuedLogic logic, double defaultSim, Set<Double> indexedSims, 
			IndexType indexType, long indexTime, long sleepTime) throws ClassNotFoundException, SQLException {
		//super(mysqlURL,mysqlUser,mysqlPassword);
		this.connection = con;
		this.labelMngr		 = labelManager;
		this.basicPredicates = basicPredicates;
		
		this.MC 			 = mc;
		
		this.PNPersist		 = new PetriNetPersistenceLayerMySQL(connection);
	}
	
	@Override
	public boolean index(int internalID, IndexType type) throws SQLException {		
		AbstractPQLBot<F,N,P,T,M> bot = null;
		try {
			bot = new AbstractPQLBot<F,N,P,T,M>(this.connection,
					null, this, this.MC, this.indexType, this.indexTime, this.sleepTime, false);
		
			boolean result = bot.index(internalID);
			bot.terminate();
			return result;
		
		} catch (ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean constructIndex(int internalID, IndexType type) throws SQLException {
		// check index status
		IndexStatus status = this.getIndexStatus(internalID);
		if (status!=IndexStatus.INDEXING) return false;
		
		// get Petri net to index
		@SuppressWarnings("unchecked")
		INetSystem<F,N,P,T,M> sys = (INetSystem<F,N,P,T,M>) this.PNPersist.restoreNetSystem(internalID);
		if (sys==null) return false;
		sys.loadNaturalMarking();
		
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
		
		if (type==IndexType.PREDICATES) {
			try {
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
					this.indexUnaryPredicate(this.PQL_CAN_OCCUR_CREATE, internalID, task, canOccurValue);
					
					//alwaysOccurs
					alwaysOccursValue = alwaysOccursMap.get(task.getSimilarLabels());
					if (alwaysOccursValue==null) {
						alwaysOccursValue = this.basicPredicates.alwaysOccurs(task);
						alwaysOccursMap.put(task.getSimilarLabels(), alwaysOccursValue);
					}
					this.indexUnaryPredicate(this.PQL_ALWAYS_OCCURS_CREATE, internalID, task, alwaysOccursValue);
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
						this.indexBinaryPredicate(this.PQL_CAN_COOCCUR_CREATE,internalID,taskA,taskB,canCooccurValue);
						
						totalConcurValue = this.checkSymmetricRelation(totalConcurMap,taskA.getSimilarLabels(),taskB.getSimilarLabels());
						if (totalConcurValue==null) {
							totalConcurValue = this.basicPredicates.totalConcur(taskA,taskB);
							this.storeSymmetricRelation(totalConcurMap,taskA.getSimilarLabels(),taskB.getSimilarLabels(),totalConcurValue);
						}
						this.indexBinaryPredicate(this.PQL_TOTAL_CONCUR_CREATE,internalID,taskA,taskB,totalConcurValue);
					}
				}
				canCooccurMap.clear();
				totalConcurMap.clear();
				
				// index asymmetric binary relations
				for (PQLTask taskA : tasks) {
					for (PQLTask taskB : tasks) {
						this.indexBinaryPredicate(this.PQL_CAN_CONFLICT_CREATE,internalID,taskA,taskB,this.basicPredicates.canConflict(taskA,taskB));
						this.indexBinaryPredicate(this.PQL_TOTAL_CAUSAL_CREATE,internalID,taskA,taskB,this.basicPredicates.totalCausal(taskA,taskB));
					}
				}
				
				return true;	
			}
			catch (Exception e) {
				e.printStackTrace();
				
				return false;
			}	
		}
		
		return false;		
	}

	@Override
	public IndexType getIndexType(int internalID) throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_GET_TYPE);
		
		cs.registerOutParameter(1, java.sql.Types.TINYINT);
		cs.setInt(2,internalID);
		cs.execute();
		
		int result = cs.getInt(1);
		
		switch (result) {
			case 0: return IndexType.PREDICATES;
			default: return null;
		}
	}

	@Override
	public IndexStatus getIndexStatus(int internalID) throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_GET_STATUS);
		
		cs.registerOutParameter(1, java.sql.Types.TINYINT);
		cs.setInt(2,internalID);
		cs.execute();
		
		int result = cs.getInt(1);
		
		switch (result) {
			case -1:	return IndexStatus.UNINDEXED;
			case 0:		return IndexStatus.INDEXING;
			case 1:		return IndexStatus.INDEXED;
			case 2:		return IndexStatus.CANNOTINDEX;
			default:	return null;
		}
	}

	@Override
	public boolean deleteIndex(int internalID) throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_DELETE);
		
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		cs.setInt(2, internalID);
		
		cs.execute();
		
		return cs.getBoolean(1);
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

	@Override
	public void cleanupIndex() throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_CLEANUP);
		
		cs.execute();
		
		cs.close();
	}
	
	@Override
	public int getNextIndexingJob() throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_GET_NEXT_JOB);
		
		cs.registerOutParameter(1, java.sql.Types.INTEGER);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		cs.close();
		
		return result;
	}

	@Override
	public void requestIndexing(int jobID, String botName) throws SQLException {
		if (botName == null || botName.isEmpty()) return;
		
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_CLAIM_JOB);
		
		cs.setInt(1, jobID);
		cs.setString(2, botName);
		
		cs.execute();
		cs.close();
	}

	@Override
	public boolean startIndexing(int jobID, String botName) throws SQLException {
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
	public void finishIndexing(int jobID, String botName) throws SQLException {
		if (botName == null || botName.isEmpty()) return;
		
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_FINISH_JOB);
		
		cs.setInt(1, jobID);
		cs.setString(2, botName);
		
		cs.execute();
		cs.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkNetSystem(int internalID) throws SQLException {
		INetSystem<F,N,P,T,M> sys = (INetSystem<F,N,P,T,M>) this.PNPersist.restoreNetSystem(internalID);
	
		if (sys==null) return false;
		
		sys.loadNaturalMarking();		
		
		boolean result = this.MC.isIndexable(sys);
		
		if (!result) this.cannnotIndex(internalID);
		
		return result;
	}
	
	private void cannnotIndex(int internalID) throws SQLException {
		CallableStatement cs = connection.prepareCall(this.PQL_INDEX_CANNOT);
		
		cs.setInt(1, internalID);
		
		cs.execute();
		
		cs.close();
	}
}
