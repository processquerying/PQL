package org.pql.query;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.antlr.v4.runtime.Token;
import org.pql.antlr.PQLLexer;
import org.pql.core.IPQLBasicPredicatesOnTasks;
import org.pql.core.PQLException;
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
