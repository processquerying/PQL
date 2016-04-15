package org.pql.label;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.pql.core.PQLTask;

/**
 * An abstract implementation of {@link ILabelManager} interface.
 *  
 * @author Artem Polyvyanyy
 */
public abstract class AbstractLabelManagerMySQL 
				implements ILabelManager {
	protected Connection 	connection 	= null;
	protected double		defaultSim	= 1.0;
	protected Set<Double> 	indexedSims = null;
	protected double		minSim		= 1.0;
	
	// MySQL matters
	protected String JBPT_LABELS_CREATE 			= "{? = CALL pql.jbpt_labels_create(?)}";	
	protected String PQL_TASKS_CREATE				= "{? = CALL pql.pql_tasks_create(?,?)}";
	protected String PQL_TASKS_SIM_CREATE			= "{? = CALL pql.pql_tasks_sim_create(?,?,?)}";
	protected String PQL_TASKS_GET_SIM				= "{CALL pql.pql_tasks_get_sim(?)}";
	protected String PETRI_NET_GET_NET_LABELS_INT	= "{CALL pql.jbpt_get_net_labels_ext_id(?)}";
	protected String PETRI_NET_GET_NET_LABELS_EXT	= "{CALL pql.jbpt_get_net_labels_int_id(?)}";
	protected String PQL_GET_TASK_IDS				= "{CALL pql.pql_get_task_ids(?,?)}";//A.P.
	
	//A.P.
	protected CallableStatement JBPT_LABELS_CREATE_CS = null;
	protected CallableStatement PQL_TASKS_CREATE_CS = null;
	protected CallableStatement PQL_GET_TASK_IDS_CS = null;
	protected CallableStatement PQL_TASKS_SIM_CREATE_CS = null;
	protected CallableStatement PQL_TASKS_GET_SIM_CS = null;
	protected CallableStatement PETRI_NET_GET_NET_LABELS_INT_CS = null;
	protected CallableStatement PETRI_NET_GET_NET_LABELS_EXT_CS = null;
	
	protected AbstractLabelManagerMySQL(Connection con,double defaultSim, Set<Double> indexedSims)
			throws ClassNotFoundException, SQLException {
		this.connection = con;		
		this.defaultSim	 = defaultSim;
		this.indexedSims = indexedSims;
		
		Set<Double> toRemove = new HashSet<Double>();

		// set minimal similarity threshold
		for (Double d : this.indexedSims) {
			if (d<=0.0 || d>1.0) {
				toRemove.add(d);
				continue;
			}
			if (d < this.minSim) this.minSim = d;
		}
		
		this.indexedSims.removeAll(toRemove);
	}
	
	@Override
	public double getDefaultLabelSimilarityThreshold() {
		return this.defaultSim;
	}
	
	@Override
	public Set<Double> getIndexedLabelSimilarityThresholds() {
		return this.indexedSims;
	}

	@Override
	public int indexTask(String label) throws SQLException {
		for (Double d : indexedSims) {
			this.createTask(label,d);
		}
		
		Set<LabelScore> searchResults = this.getSimilarLabels(label, 10);
		
		for (LabelScore ls : searchResults) {
			for (Double d : indexedSims) {
				if (d <= ls.getScore()) {
					this.createTaskSim(label,ls.getLabel(),d);
				}
			}
		}
		
		return 0;
	}

	@Override
	public int getTaskID(String label, double similarity) throws SQLException {
		
		if(this.PQL_TASKS_CREATE_CS == null)
		this.PQL_TASKS_CREATE_CS = this.connection.prepareCall(PQL_TASKS_CREATE);
		
		this.PQL_TASKS_CREATE_CS.registerOutParameter(1, java.sql.Types.TINYINT);
		this.PQL_TASKS_CREATE_CS.setString(2,label);
		this.PQL_TASKS_CREATE_CS.setDouble(3,similarity);
		
		this.PQL_TASKS_CREATE_CS.execute();
		
		int result = this.PQL_TASKS_CREATE_CS.getInt(1); 
		
		return result;
	}
	
	@Override
	public void getTaskIDs(JSONArray labels, JSONArray similarities, Vector<PQLTask> tasks) throws SQLException {
	
		if(this.PQL_GET_TASK_IDS_CS == null)
		this.PQL_GET_TASK_IDS_CS = this.connection.prepareCall(PQL_GET_TASK_IDS);
		
		this.PQL_GET_TASK_IDS_CS.setObject(1,labels.toString());
		this.PQL_GET_TASK_IDS_CS.setObject(2,similarities.toString());
		this.PQL_GET_TASK_IDS_CS.execute();
		ResultSet rs = this.PQL_GET_TASK_IDS_CS.getResultSet();	
		
		while(rs.next())
		{	
			int id = rs.getInt(1);
			double sim = rs.getDouble(2);
			String label = rs.getString(3);
			
			for(int i=0; i<labels.length(); i++)
			{
				try {
					if(labels.getString(i).equals(label) && similarities.getDouble(i)==sim)
					{
						tasks.elementAt(i).setID(id);
						Set<String> taskLabels = this.getLabels(id); //TODO optimization: getting labels for a set of task IDs
						taskLabels.add(label);
						tasks.elementAt(i).setLabels(taskLabels);
					}
				} catch (JSONException e) {e.printStackTrace();}
			}
		}
		
		for(PQLTask task :tasks)
		{
			Integer id = task.getID();
			
			if(id == null)
				task.setID(0);
		}
		
	}


	@Override
	public Set<String> getLabels(int taskID) throws SQLException {
		Set<String> result = new HashSet<String>();
		
		if(this.PQL_TASKS_GET_SIM_CS == null)
		PQL_TASKS_GET_SIM_CS = connection.prepareCall(PQL_TASKS_GET_SIM);
		
		PQL_TASKS_GET_SIM_CS.setInt(1,taskID);
		
		ResultSet res = PQL_TASKS_GET_SIM_CS.executeQuery();
		
		while (res.next()) {
			result.add(res.getString(1));
		}
		
		return result;
	}

	@Override
	public boolean loadTask(PQLTask task, Set<Double> similarities) throws SQLException {
		String label = "";
		if (task.getLabel()!=null) label = task.getLabel();
		double s = task.getSimilarity(); 
		if (s<0.0) s=0.0;
		if (s>1.0) s=1.0;
		
		// label
		Set<LabelScore> search = this.getSimilarLabels(label,1);
		String newLabel = label;
		boolean flag = false;
		if (search!=null && !search.isEmpty()) {
			LabelScore ls = search.iterator().next();
			newLabel = ls.getLabel();
			if (ls.getScore()<0.8)	// TODO: improve handling of similarity threshold
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
	

	
    //A.P.
	@Override
	public boolean loadTasks(Vector<PQLTask> tasks, Set<Double> similarities) throws SQLException {
	JSONArray json_labels = new JSONArray();
	JSONArray json_similarities = new JSONArray();
		
	for(PQLTask task : tasks)
	{
		String label = "";
		if (task.getLabel()!=null) label = task.getLabel();
		double s = task.getSimilarity(); 
		if (s<0.0) s=0.0;
		if (s>1.0) s=1.0;
		
		// label
		Set<LabelScore> search = this.getSimilarLabels(label,1);
		String newLabel = label;
		
		if (search!=null && !search.isEmpty()) {
			LabelScore ls = search.iterator().next();
			if (ls.getScore()>=0.8)	// TODO: improve handling of similarity threshold
			newLabel = ls.getLabel();
		}
		json_labels.put(newLabel);
		
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
		try {json_similarities.put(s);} catch (JSONException e) {e.printStackTrace();}	
		
		//update task similarity and labels
		task.setSimilarity(s);
		Set<String> labels = new HashSet<String>();
		labels.add(label);
		labels.add(newLabel);
		task.setLabels(labels);
	}
	
		//get task ids
		this.getTaskIDs(json_labels,json_similarities,tasks);
		
		return true;
	}

	@Override
	public Set<String> getAllLabels(String externalID) throws SQLException {
		Set<String> result = new HashSet<String>();
		
		if(PETRI_NET_GET_NET_LABELS_EXT_CS == null)
		PETRI_NET_GET_NET_LABELS_EXT_CS = connection.prepareCall(this.PETRI_NET_GET_NET_LABELS_INT);
		
		PETRI_NET_GET_NET_LABELS_EXT_CS.setString(1, externalID);
		
		ResultSet res = PETRI_NET_GET_NET_LABELS_EXT_CS.executeQuery();
		
		while (res.next()) {
			result.add(res.getString(1));
		}
		
		return result;
	}
	
	@Override
	public Set<String> getAllLabels(int id) throws SQLException {
		Set<String> result = new HashSet<String>();
		
		if(PETRI_NET_GET_NET_LABELS_INT_CS == null)
		PETRI_NET_GET_NET_LABELS_INT_CS = connection.prepareCall(this.PETRI_NET_GET_NET_LABELS_EXT);
		
		PETRI_NET_GET_NET_LABELS_INT_CS.setInt(1, id);
		
		ResultSet res = PETRI_NET_GET_NET_LABELS_INT_CS.executeQuery();
		
		while (res.next()) {
			result.add(res.getString(1));
		}
		
		return result;
	}
	
	private int createTask(String label, Double similarity) throws SQLException {
		
		if(PQL_TASKS_CREATE_CS == null)
		PQL_TASKS_CREATE_CS = connection.prepareCall(PQL_TASKS_CREATE);
		
		PQL_TASKS_CREATE_CS.registerOutParameter(1, java.sql.Types.TINYINT);
		PQL_TASKS_CREATE_CS.setString(2,label);
		PQL_TASKS_CREATE_CS.setDouble(3,similarity);
		
		PQL_TASKS_CREATE_CS.execute();
		
		int result = PQL_TASKS_CREATE_CS.getInt(1);
		
		return result;
	}
	
	private int createTaskSim(String labelA, String labelB, Double similarity) throws SQLException {
		
		if(PQL_TASKS_SIM_CREATE_CS == null)
		PQL_TASKS_SIM_CREATE_CS = connection.prepareCall(PQL_TASKS_SIM_CREATE);
		
		PQL_TASKS_SIM_CREATE_CS.registerOutParameter(1,java.sql.Types.TINYINT);
		PQL_TASKS_SIM_CREATE_CS.setString(2,labelA);
		PQL_TASKS_SIM_CREATE_CS.setString(3,labelB);
		PQL_TASKS_SIM_CREATE_CS.setDouble(4,similarity);
		
		PQL_TASKS_SIM_CREATE_CS.execute();
		
		int result = PQL_TASKS_SIM_CREATE_CS.getInt(1);
		
		return result;
		
	}
	
	protected int createLabel(String label) throws SQLException {
		
		if(JBPT_LABELS_CREATE_CS == null)
		JBPT_LABELS_CREATE_CS = connection.prepareCall(JBPT_LABELS_CREATE);
		
		JBPT_LABELS_CREATE_CS.registerOutParameter(1, java.sql.Types.TINYINT);
		JBPT_LABELS_CREATE_CS.setString(2,label);
		
		JBPT_LABELS_CREATE_CS.execute();
		
		int result = JBPT_LABELS_CREATE_CS.getInt(1);
		
		return result;
	}

}
