package org.pql.query;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import org.antlr.v4.runtime.Token;
import org.json.JSONArray;
import org.json.JSONException;
import org.pql.antlr.PQLLexer;
import org.pql.core.IPQLBasicPredicatesOnTasks;
import org.pql.core.PQLException;
import org.pql.core.PQLQuantifier;
import org.pql.core.PQLTask;
import org.pql.core.PQLTrace;
import org.pql.label.ILabelManager;

/**
 * An implementation of the {@link AbstractPQLQuery}} class that relies on MySQL index.
 * 
 * @author Artem Polyvyanyy
 */
public class PQLQueryMySQL extends AbstractPQLQuery {
	
	private String identifier = "";
	private Connection connection = null;
	IPQLBasicPredicatesOnTasks basicPredicates = null;
	
	/**
	 * Constructor of PQL query objects.
	 * @param query
	 * @param logic
	 * @param labelMngr
	 * @param basicPredicates
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */

	
	//A.P.
	@SuppressWarnings("rawtypes")
	public PQLQueryMySQL(AtomicInteger filteredModels, Connection con, String query, ILabelManager labelMngr) throws ClassNotFoundException, SQLException {
		super(query,labelMngr);
		this.connection = con;
		this.basicPredicates = new org.pql.core.PQLBasicPredicatesMySQL(con,labelMngr,filteredModels);
	}

	@Override
	public boolean check() {
		return this.interpret();
	}

	@Override
	public void configure(Object obj) throws PQLException {
		this.identifier = obj.toString();
		this.basicPredicates.configure(this.identifier);
	}
	
	public void disconnect() {
		try {
			this.connection.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean interpretUnaryPredicate(Token op, PQLTask task) {
		PQLTask dbTask = this.task2task.get(task); 
		
		if (dbTask==null) {
			dbTask = new PQLTask(task.getLabel(), task.getSimilarity());
			
			try {
				labelMngr.loadTask(dbTask, this.labelMngr.getIndexedLabelSimilarityThresholds());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			this.task2task.put(task,dbTask);
		}
		
		switch (op.getType()) {
			case PQLLexer.CAN_OCCUR		: return basicPredicates.canOccur(dbTask);
			case PQLLexer.ALWAYS_OCCURS	: return basicPredicates.alwaysOccurs(dbTask);
		}
	
		return false;
	}
	
	//A.P. - V2
		@Override
		public boolean interpretBinaryPredicateMacro(Token op, Set<PQLTask> set1, Set<PQLTask> set2, PQLQuantifier Q) {
			
			Vector<PQLTask> noDBtasks1 = new Vector<PQLTask>();
			Vector<PQLTask> DBtasks1 = new Vector<PQLTask>();
			Set<Integer> taskIDs1 = new HashSet<Integer>();
			Vector<PQLTask> noDBtasks2 = new Vector<PQLTask>();
			Vector<PQLTask> DBtasks2 = new Vector<PQLTask>();
			Set<Integer> taskIDs2 = new HashSet<Integer>();
		
			
			for(PQLTask task : set1)
			{
				PQLTask dbTask = this.task2task.get(task); 
				if(dbTask==null)
				{
					dbTask = new PQLTask(task.getLabel(), task.getSimilarity());
					DBtasks1.add(dbTask);
					noDBtasks1.add(task);
				}
				else{taskIDs1.add(dbTask.getID());}
			}
			
			for(PQLTask task : set2)
			{
				PQLTask dbTask = this.task2task.get(task); 
				if(dbTask==null)
				{
					dbTask = new PQLTask(task.getLabel(), task.getSimilarity());
					DBtasks2.add(dbTask);
					noDBtasks2.add(task);
				}
				else{taskIDs2.add(dbTask.getID());}
			}
	
			//get task IDs
			if(DBtasks1.size()>0)
			{	try {
				labelMngr.loadTasks(DBtasks1, this.labelMngr.getIndexedLabelSimilarityThresholds());
				} catch (SQLException e) {e.printStackTrace();}
			}
			
			if(DBtasks2.size()>0)
			{	try {
				labelMngr.loadTasks(DBtasks2, this.labelMngr.getIndexedLabelSimilarityThresholds());
				} catch (SQLException e) {e.printStackTrace();}
			}
		
			//update task2task and taskIDs
			for (int i=0; i<DBtasks1.size(); i++) {
				this.task2task.put(noDBtasks1.elementAt(i),DBtasks1.elementAt(i));
				taskIDs1.add(DBtasks1.elementAt(i).getID());
			}
			
			for (int i=0; i<DBtasks2.size(); i++) {
				this.task2task.put(noDBtasks2.elementAt(i),DBtasks2.elementAt(i));
				taskIDs2.add(DBtasks2.elementAt(i).getID());
			}
		
			JSONArray ids1 = new JSONArray();
			for(Integer taskID : taskIDs1)
			{
				ids1.put(taskID);
			}
			
			JSONArray ids2 = new JSONArray();
			for(Integer taskID : taskIDs2)
			{
				ids2.put(taskID);
			}
		
			//quantifier
			String q = "";
			if (Q==PQLQuantifier.ANY) {q = "any";} else if (Q==PQLQuantifier.ALL) {q = "all";} else {q = "each";}
						
			switch (op.getType()) 
			{
				case PQLLexer.COOCCUR	: 
					return basicPredicates.checkCooccurMacro(q, ids1, ids2); //TODO optimize for sets of task IDs
				case PQLLexer.CAN_COOCCUR	: 
					return basicPredicates.checkBinaryPredicateMacro("cancooccur", q, ids1, ids2);
				case PQLLexer.CONFLICT	: 
					return basicPredicates.checkConflictMacro(q, ids1, ids2); //TODO optimize for sets of task IDs
				case PQLLexer.CAN_CONFLICT	: 
					return basicPredicates.checkCanConflictMacro(q, ids1, ids2); //TODO optimize for sets of task IDs
				case PQLLexer.TOTAL_CAUSAL	: 
					return basicPredicates.checkTotalCausalMacro(q, ids1, ids2); //TODO optimize for sets of task IDs
				case PQLLexer.TOTAL_CONCUR	: 
					return basicPredicates.checkTotalConcurMacro(q, ids1, ids2); //TODO optimize for sets of task IDs
			}
			
			return false;
		}
	
	//A.P.
	@Override
	public boolean interpretUnaryPredicateMacroV2(Token op, Set<PQLTask> tasks, PQLQuantifier Q) {
		
		Vector<PQLTask> noDBtasks = new Vector<PQLTask>();
		Vector<PQLTask> DBtasks = new Vector<PQLTask>();
		Set<Integer> taskIDs = new HashSet<Integer>();
		
		for(PQLTask task : tasks)
		{
			PQLTask dbTask = this.task2task.get(task); 
			if(dbTask==null)
			{
				dbTask = new PQLTask(task.getLabel(), task.getSimilarity());
				DBtasks.add(dbTask);
				noDBtasks.add(task);
			}
			else{taskIDs.add(dbTask.getID());}
		}
		
		//get task IDs
		if(DBtasks.size()>0)
		{	try {
			labelMngr.loadTasks(DBtasks, this.labelMngr.getIndexedLabelSimilarityThresholds());
			} catch (SQLException e) {e.printStackTrace();}
		}
		
		//update task2task and taskIDs
		for (int i=0; i<DBtasks.size(); i++) {
			this.task2task.put(noDBtasks.elementAt(i),DBtasks.elementAt(i));
			taskIDs.add(DBtasks.elementAt(i).getID());
		}
		
		JSONArray ids = new JSONArray();
		for(Integer id : taskIDs)
		{
			ids.put(id);
		}
		
		//operation and quantifier
		String o = "";
		String q = "";
		switch (op.getType()) 
		{
			case PQLLexer.CAN_OCCUR		: 
				o = "canoccur";
				break;
			case PQLLexer.ALWAYS_OCCURS	: 
				o = "alwaysoccurs";
				break;
		}
		
		if (Q==PQLQuantifier.ANY) {q = "any";} else {q = "all";}
		
		return basicPredicates.checkUnaryPredicateMacroV2(o, q, ids);
	}
	
	
	//A.P. 
	@Override
    protected boolean interpretUnaryTracePredicate(Token op, PQLTrace trace) {
	
		if(trace.isAsterisk()) return true;
		
		PQLTrace dbTrace = new PQLTrace();
		
		for(int i=0; i<trace.getTrace().size(); i++) {
			PQLTask task = trace.getTrace().elementAt(i);
			PQLTask dbTask = null;
			
			if(task.getSimilarity() == 1.0) {
				dbTask = new PQLTask(task.getLabel(), task.getSimilarity());
				Set<String> similarLabels = new HashSet<String>();
				similarLabels.add(task.getLabel());
				dbTask.setLabels(similarLabels);
			}
			else {
				dbTask = this.task2task.get(task); 
			
				if (dbTask==null) {
					dbTask = new PQLTask(task.getLabel(), task.getSimilarity());
					
					try {
						labelMngr.loadTask(dbTask, this.labelMngr.getIndexedLabelSimilarityThresholds());
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}
					
					this.task2task.put(task,dbTask);
				}
			}
			dbTask.setAsterisk(task.isAsterisk());
			dbTrace.addTask(dbTask);
		}
		
		dbTrace.setHasAsterisk(trace.hasAsterisk());
		
		//create replacement map
		if (dbTrace.hasAsterisk()){
			dbTrace.createReplacementMap();
		}
			
		//create XLog
		if (dbTrace.hasAsterisk()) {
			dbTrace.createLogForTraceWithAsterisk();
		} 
		else {
			dbTrace.createTraceLog();
		}

		switch (op.getType()) {
			case PQLLexer.EXECUTES: 
				return basicPredicates.executes(dbTrace);
		}

		return false;	
	}
	
	//A.P. 
	@Override
	protected PQLTrace interpretInsertTrace(PQLTrace trace) {
	
		PQLTrace dbTrace = new PQLTrace();
		
		for(int i=0; i<trace.getTrace().size(); i++) {
			PQLTask task = trace.getTrace().elementAt(i);
			PQLTask dbTask = null;
			
			if(task.getSimilarity() == 1.0) {
				dbTask = new PQLTask(task.getLabel(), task.getSimilarity());
				Set<String> similarLabels = new HashSet<String>();
				similarLabels.add(task.getLabel());
				dbTask.setLabels(similarLabels);
			}
			else {
				dbTask = this.task2task.get(task); 
			
				if (dbTask==null) {
					dbTask = new PQLTask(task.getLabel(), task.getSimilarity());
					
					try {
						labelMngr.loadTask(dbTask, this.labelMngr.getIndexedLabelSimilarityThresholds());
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}
					
					this.task2task.put(task,dbTask);
				}
			}
			dbTask.setAsterisk(task.isAsterisk());
			dbTrace.addTask(dbTask);
		}
		
		dbTrace.setHasAsterisk(trace.hasAsterisk());
		
		//create XLog
		dbTrace.createInsertTraceLog();

		return dbTrace;	
	}

	
	@Override
	protected boolean interpretBinaryPredicate(Token op, PQLTask taskA, PQLTask taskB) {
		PQLTask dbTaskA = this.task2task.get(taskA);
		PQLTask dbTaskB = this.task2task.get(taskB);
		
		
		if (dbTaskA==null) {
			dbTaskA = new PQLTask(taskA.getLabel(), taskA.getSimilarity());
			
			try {
				labelMngr.loadTask(dbTaskA, this.labelMngr.getIndexedLabelSimilarityThresholds());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			this.task2task.put(taskA,dbTaskA);
		}
		
		if (dbTaskB==null) {
			dbTaskB = new PQLTask(taskB.getLabel(), taskB.getSimilarity());
			
			try {
				labelMngr.loadTask(dbTaskB, this.labelMngr.getIndexedLabelSimilarityThresholds());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			this.task2task.put(taskB,dbTaskB);
		}
		
		switch (op.getType()) {
			case PQLLexer.CAN_CONFLICT	: return basicPredicates.canConflict(dbTaskA, dbTaskB);
			case PQLLexer.CAN_COOCCUR	: return basicPredicates.canCooccur(dbTaskA, dbTaskB);
			case PQLLexer.CONFLICT		: return basicPredicates.conflict(dbTaskA, dbTaskB);
			case PQLLexer.COOCCUR		: return basicPredicates.cooccur(dbTaskA, dbTaskB);
			case PQLLexer.TOTAL_CAUSAL	: return basicPredicates.totalCausal(dbTaskA, dbTaskB);
			case PQLLexer.TOTAL_CONCUR	: return basicPredicates.totalConcur(dbTaskA, dbTaskB);
		}
	
		return false;
	}

	@Override
	protected Set<PQLTask> getAllTasks() {
		Set<PQLTask> result = new HashSet<PQLTask>();
		
		try {
			for (String label : this.labelMngr.getAllLabels(this.identifier)) {
				result.add(new PQLTask(label, 1.0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//A.P.
	@Override
	public IPQLBasicPredicatesOnTasks getBP()
	{
		return this.basicPredicates;
	}
}
