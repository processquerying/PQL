package org.pql.label;

/**
 * @author Artem Polyvyanyy
 */
public class LabelScore {
	private String label = null;
	private Double score = 1.0;
	
	public LabelScore(String label, double score) {
		this.label = label;
		this.score = score;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getScore() {
		return this.score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o==null) return false;
		if (!(o instanceof LabelScore)) return false;
		
		LabelScore ls = (LabelScore) o;
		
		return this.label.equals(ls.label) && this.score.equals(ls.score);
	} 
	
	@Override
	public int hashCode() {
		return this.score.hashCode() * (this.label==null ? 1 : this.label.hashCode());
	}
	
	@Override
	public String toString() {
		return String.format("%s[%s]", this.label, this.score);
	}
}
