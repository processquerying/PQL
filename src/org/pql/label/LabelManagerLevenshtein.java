package org.pql.label;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.jbpt.persist.MySQLConnection;
import org.pql.core.PQLTask;

/**
 * @author Artem Polyvyanyy
 */
public class LabelManagerLevenshtein extends MySQLConnection implements ILabelManager {
	private double		defaultSim	= 1.0;
	private Set<Double> indexedSims = null;
	private double		minSim		= 1.0;
	
	protected String JBPT_LABELS_CREATE = "{? = CALL pql.jbpt_labels_create(?)}";
	
	protected String PQL_TASKS_CREATE			= "{? = CALL pql.pql_tasks_create(?,?)}";
	protected String PQL_TASKS_SIM_CREATE		= "{? = CALL pql.pql_tasks_sim_create(?,?,?)}";
	protected String PQL_TASKS_GET_SIM			= "{CALL pql.pql_tasks_get_sim(?)}";
	protected String PETRI_NET_GET_NET_LABELS	= "{CALL pql.jbpt_get_net_labels(?)}";
	
	protected String PQL_LEVENSHTEIN_SEARCH		= "{CALL pql.pql_levenshtein_label_sim_search(?)}";
	
	public LabelManagerLevenshtein(String mysqlURL, String mysqlUser, String mysqlPassword,			
			double defaultSim, Set<Double> indexedSims) throws ClassNotFoundException, SQLException {
		super(mysqlURL,mysqlUser,mysqlPassword);
		
		this.defaultSim	 = defaultSim;
		this.indexedSims = indexedSims;
	
		// set minimal similarity threshold
		for (Double d : this.indexedSims) {
			if (d<=0.0 || d>1.0) continue;
			if (d < this.minSim) this.minSim = d;
		}
	}
	
	public boolean loadTask(PQLTask task, Set<Double> similarities) throws SQLException {
		String label = "";
		if (task.getLabel()!=null) label = task.getLabel();
		double s = task.getSimilarity(); 
		if (s<0.0) s=0.0;
		if (s>1.0) s=1.0;
		
		// label
		String newLabel = label;
		boolean flag = false;
		
		CallableStatement cs = connection.prepareCall(PQL_LEVENSHTEIN_SEARCH);
		cs.setString(1, label);
		ResultSet res = cs.executeQuery();
		if (res.next()) {
			newLabel = res.getString(1);
			if (res.getDouble(2)<0.8)	// TODO: improve handling of similarity threshold
				flag = true;
		}
		else flag = true;
		
		if (flag) {
			task.setSimilarity(s);
			Set<String> labels = new HashSet<String>();
			labels.add(label);
			task.setLabels(labels);
			return false;
		}
		
		// similarity
		double newS = s;
		double minDelta = Double.MAX_VALUE;
		for (Double sim : similarities) {
			double delta = Math.abs(sim-s);
			if (delta < minDelta) {
				newS = sim;
				minDelta = delta;
			}
			else if (delta == minDelta && sim<newS)
				newS = sim;
		}
		s = newS;
		if (s<0.0) s=0.0;
		if (s>1.0) s=1.0;
		
		int taskID = this.getTaskID(newLabel,s);
		if (taskID<1) {
			task.setSimilarity(s);
			Set<String> labels = new HashSet<String>();
			labels.add(label);
			labels.add(newLabel);
			task.setLabels(labels);
			return false;
		}
		
		// update task
		task.setID(taskID);
		task.setSimilarity(s);
		Set<String> labels = this.getLabels(taskID);
		labels.add(label);
		task.setLabels(labels);
		
		return true;
	}

	@Override
	public double getDefaultLabelSimilarityThreshold() {
		return this.defaultSim;
	}

	@Override
	public int indexLabel(String label) throws SQLException {
		int labelID =  this.createLabel(label);
		
		return labelID;
	}
	
	private int createLabel(String label) throws SQLException {
		CallableStatement cs = connection.prepareCall(JBPT_LABELS_CREATE);
		
		cs.registerOutParameter(1, java.sql.Types.TINYINT);
		cs.setString(2,label);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		cs.close();
		
		return result;
	}

	@Override
	public int indexTask(String label) throws SQLException {
		for (Double d : indexedSims) {
			this.createTask(label,d);
		}
		
		CallableStatement cs = connection.prepareCall(PQL_LEVENSHTEIN_SEARCH);
		cs.setString(1, label);
		
		ResultSet res = cs.executeQuery();
		
		while (res.next()) {
			for (Double d : indexedSims) {
				if (d <= res.getDouble(2)) {
					this.createTaskSim(label,res.getString(1),d);
				}
			}
		}
		
		
		return 0;
	}
	
	private int createTaskSim(String labelA, String labelB, Double similarity) throws SQLException {
		CallableStatement cs = connection.prepareCall(PQL_TASKS_SIM_CREATE);
		
		cs.registerOutParameter(1,java.sql.Types.TINYINT);
		cs.setString(2,labelA);
		cs.setString(3,labelB);
		cs.setDouble(4,similarity);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		cs.close();
		
		return result;
		
	}

	private int createTask(String label, Double similarity) throws SQLException {
		CallableStatement cs = connection.prepareCall(PQL_TASKS_CREATE);
		
		cs.registerOutParameter(1, java.sql.Types.TINYINT);
		cs.setString(2,label);
		cs.setDouble(3,similarity);
		
		cs.execute();
		
		int result = cs.getInt(1);
		
		cs.close();
		
		return result;
	}

	@Override
	public int getTaskID(String label, double similarity) throws SQLException {
		return this.createTask(label, similarity);
	}

	@Override
	public Set<String> getLabels(int taskID) throws SQLException {
		Set<String> result = new HashSet<String>();
		
		CallableStatement cs = connection.prepareCall(PQL_TASKS_GET_SIM);
		cs.setInt(1,taskID);
		
		ResultSet res = cs.executeQuery();
		
		while (res.next()) {
			result.add(res.getString(1));
		}
		
		return result;
	}

	@Override
	public Set<Double> getIndexedSimilarities() {
		return this.indexedSims;
	}
	
	@Override
	public Set<String> getAllLabels(String netIdentifier) throws SQLException {
		Set<String> result = new HashSet<String>();
		
		CallableStatement cs = connection.prepareCall(this.PETRI_NET_GET_NET_LABELS);
		cs.setString(1, netIdentifier);
		
		ResultSet res = cs.executeQuery();
		
		while (res.next()) {
			result.add(res.getString(1));
		}
		
		return result;
	}
}
