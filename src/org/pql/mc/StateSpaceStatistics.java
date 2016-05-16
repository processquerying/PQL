package org.pql.mc;

/** 
 * @author Artem Polyvyanyy
 */
public class StateSpaceStatistics {
	private long nOfStates= 0L;
	private long nOfTransitions= 0L;
	
	public StateSpaceStatistics(long states, long transitions) {
		this.nOfStates = states;
		this.nOfTransitions = transitions;
	}
	
	public long getNumberOfStates() {
		return nOfStates;
	}
	
	public long getNumberOfTransitions() {
		return nOfTransitions;
	}
}
