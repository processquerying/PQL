package org.pql.label;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import org.pql.core.PQLTask;

/**
 * TODO: Document ILabelManager interface.
 * 
 * @author Artem Polyvyanyy
 */
public interface ILabelManager {
	
	public double getDefaultLabelSimilarityThreshold();
	
	public int indexLabel(String label);
	
	public int indexTask(String label) throws SQLException;
	
	public int getTaskID(String label, double similarity) throws SQLException;

	public Set<String> getLabels(int taskID) throws SQLException;
	
	public boolean loadTask(PQLTask task, Set<Double> similarities) throws SQLException;
	
	public Set<Double> getIndexedSimilarities();
	
	public Set<String> getAllLabels(String netIdentifier) throws SQLException;
	
	public Set<LabelScore> search(String searchString, int n);
	
	public Set<LabelScore> search(String searchString, double sim);
}
