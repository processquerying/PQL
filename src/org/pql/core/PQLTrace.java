package org.pql.core;

import java.util.Vector;

/**
 * A.P.
 */
public class PQLTrace {
	
	private Vector<PQLTask> trace = new Vector<PQLTask>(); // TO DO: consider also '*', it is now a PQLTask "UniverseSymbol"
	
	public PQLTrace(Vector<PQLTask> tasks) {
		this.trace.addAll(tasks);
		
	}
	
	public void print(){
		String out = "";
		for(int i=0; i<trace.size(); i++)
		{out += trace.elementAt(i).getLabel() + " ";}	
		System.out.println(out);
	};
	
	public Vector<PQLTask> getTrace(){
		return trace;
	};
	
}
