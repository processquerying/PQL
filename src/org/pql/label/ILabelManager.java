package org.pql.label;

import java.sql.SQLException;
import java.util.Set;
import java.util.Vector;

import org.apache.lucene.index.IndexWriter;
import org.json.JSONArray;
import org.pql.core.PQLTask;
import org.pql.ini.IPQLIniFile;

/**
 * ILabelManager interface.
 * 
 * @author Artem Polyvyanyy
 */

public interface ILabelManager {
	/**
	 * Get default label similarity threshold value.
	 * 
	 * The default label similarity threshold is used for the interpretation of the PQL "~" symbol 
	 * when directly preceding a label string, e.g., ~"A" in PQL refers to the set of all labels for 
	 * which the similarity score with "A" is at least the threshold value.
	 * 
	 * The label similarity score is obtained based on the label manager type of this PQL instance,
	 * refer to {@link IPQLIniFile}.{@code getLabelManagerType()}.      
	 * 
	 * @return Default label similarity threshold value used with this PQL instance.  
	 */

	public double getDefaultLabelSimilarityThreshold();
	
	/**
	 * Get indexed label similarity threshold values.
	 * 
	 * The indexed label similarity thresholds are the thresholds that are used for populating
	 * the PQL index, i.e., the PQL instance is optimized for computing behavioral predicates/relations
	 * for these label similarity threshold values.
	 * 
	 * @return The set of all indexed label similarity threshold values used with this PQL instance.
	 */
	public Set<Double> getIndexedLabelSimilarityThresholds();
	
	/**
	 * Index label for faster similarity scoring.
	 * 
	 * @param label Label to index.
	 * @return Unique identifier of the indexed label, or {@code 0} if the label was not indexed.
	 */
	public int indexLabel(String label) throws SQLException;
	
	/**
	 * Index {@link PQLTask}.
	 * 
	 * @param label Label of the {@link PQLTask} to index.
	 * @return Unique identifier of the indexed {@link PQLTask}, or {@code 0} if the {@link PQLTask} was not indexed.
	 * @throws SQLException
	 */
	public int indexTask(String label) throws SQLException;
	
	/**
	 * TODO: reconsider use of this methode.
	 * 
	 * Get unique identifier of a {@link PQLTask}. 
	 * 
	 * @param label Label.
	 * @param similarity Label similarity threshold value. 
	 * @return Unique identifier of the {@link PQLTask} defined for the given label and similarity threshold value.
	 * @throws SQLException
	 */
	public int getTaskID(String label, double similarity) throws SQLException;
	
	public boolean loadTask(PQLTask task, Set<Double> similarities) throws SQLException;
	
	public Set<String> getLabels(int taskID) throws SQLException;
	
	public Set<String> getAllLabels(String netIdentifier) throws SQLException;
	
	public Set<String> getAllLabels(int id) throws SQLException;

	/**
	 * Get similar labels.
	 * 
	 * @param label Label string.
	 * @param n Number of similar labels to look for.
	 * @returns The set of {@code n} most similar labels to the given label.
	 */
	public Set<LabelScore> getSimilarLabels(String label, int n);
	
	/**
	 * Get similar labels.
	 * 
	 * @param label Label string.
	 * @param sim Label similarity threshold value.
	 * @returns The set of all labels that are similar to the given label with the similarity score which is equal or greater than the given similarity threshold value.
	 */
	public Set<LabelScore> getSimilarLabels(String label, double sim);

	
	//A.P.
	public boolean loadTasks(Vector<PQLTask> tasks, Set<Double> similarities)
			throws SQLException;
    //A.P.
	public void getTaskIDs(JSONArray labels, JSONArray similarities, Vector<PQLTask> tasks) throws SQLException;
	
	
}
