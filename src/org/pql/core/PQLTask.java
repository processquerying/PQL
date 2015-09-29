package org.pql.core;

import java.util.Set;

/**
 * Artem Polyvyanyy
 */
public class PQLTask {
	private int			id			= 0;
	private String 		label 		= null;
	private double 		similarity	= 1.0;
	private Set<String> labels 		= null;
	private boolean 	isStar		= false; //A.P.
	
	public PQLTask(String label, double similarity) {
		this.label 		= label == null ? "" : label;
		this.similarity = similarity>=0 && similarity<=1.0 ? similarity : 1.0;
	}
	
	/**
	 * Get original task label.
	 * 
	 * @return Original task label.
	 */
	public String getLabel() {
		return this.label;
	}
	
	/**
	 * Get similarity degree threshold.
	 * 
	 * @return Similarity degree threshold.
	 */
	public double getSimilarity() {
		return this.similarity;
	}
	
	/**
	 * Get similar labels.
	 * 
	 * @return Similar labels.
	 */
	public Set<String> getSimilarLabels() {
		return this.labels;
	}
	
	@Override
	public String toString() {
		return String.format("\"%s\"[%f]=%s", this.getLabel(), this.getSimilarity(), this.getSimilarLabels());
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setSimilarity(double similarity) {
		if (similarity<0.0 || similarity>1.0)
			this.similarity = 1.0;
		else
			this.similarity = similarity;
	}
	
	public void setID(int taskID) {
		this.id = taskID;
	}
	
	public void setLabels(Set<String> labels) {
		this.labels = labels;
	}
	
	@Override
	public int hashCode() {
		int result = this.label.hashCode();
		
		result+=this.id*11;
		result*=this.getSimilarity();
		
		if (this.labels!=null)
			for (String l : this.labels)
				result+=17*l.hashCode();
				
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PQLTask)) return false;
		
		PQLTask task = (PQLTask) obj;
		
		if (task.id>0 && task.id==this.id)
			return true;
		
		if (task.similarity == this.similarity && task.getLabel().equals(this.getLabel()))
			return true;
		
		return false;
	}
	
	
	//A.P.
	public boolean isStar() {
		return this.isStar;
	}
	
	//A.P.
	public void setStar(boolean isStar) {
		this.isStar = isStar;
	}


}
