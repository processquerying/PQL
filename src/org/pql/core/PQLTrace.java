package org.pql.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.deckfour.xes.factory.XFactoryNaiveImpl;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XAttributeLiteralImpl;
import org.deckfour.xes.model.impl.XAttributeTimestampImpl;

/**
 * A.P.
 */
public class PQLTrace {
	
	private Vector<PQLTask> trace = new Vector<PQLTask>(); 
	private XLog traceLog = null;
	private Map<Vector<String>,String> replacementMap = new HashMap <Vector<String>,String>();
	private boolean hasAsterisk = false;
	
	
	public PQLTrace() {}
	
	public PQLTrace(Vector<PQLTask> tasks) {
		this.trace.addAll(tasks);
		
	}
	
	public boolean isAsterisk()
	{
		
		if(trace.size() == 0) return false;
		if(!hasAsterisk) return false;
		
		for(int i=1; i<trace.size()-1; i++)
		{
			if(!trace.elementAt(i).isAsterisk()) return false;
		}
		
		return true;
	}
	
	public void print(){
		
		for(int i=0; i<trace.size(); i++)
		System.out.println(i+": "+ trace.elementAt(i).getLabel() + ": " + trace.elementAt(i).getSimilarity() + ": " + trace.elementAt(i).getSimilarLabels() + ": " + trace.elementAt(i).isAsterisk());
		System.out.println("hasStars: " + this.hasAsterisk);
		System.out.println("replacementMap: " + this.replacementMap);
		String logTrace = "";
		for(XTrace t: this.traceLog)
		{for(XEvent e: t)
			{
			logTrace += e.getAttributes().get("concept:name") + " - ";
			}
		}
		System.out.println("Log: "+logTrace);
	 		
	};
	
	public Vector<PQLTask> getTrace(){
		return this.trace;
	};
	
	public XLog getTraceLog(){
		return this.traceLog;
	};
	
	public Map<Vector<String>,String> getReplacementMap(){
		return this.replacementMap;
	};
	
	public void addTask(PQLTask task){
		trace.add(task);
	};
	
	public void addTask(PQLTask task, int position){
		trace.add(position,task);
	};
	
	public void setHasAsterisk(boolean hasStars){
		
		this.hasAsterisk = hasStars;
	};
	
	public boolean hasAsterisk(){
		
		return this.hasAsterisk;
	};

	
	public void createInsertTraceLog(){
		
		XFactoryNaiveImpl factory = new XFactoryNaiveImpl(); 
		
		XLog log = factory.createLog();
		
		XTrace logTrace = factory.createTrace();
		log.add(logTrace);
		
		for(int i=0; i<trace.size(); i++)
		{
			if(!trace.elementAt(i).isAsterisk())
			{
			String taskLabel = trace.elementAt(i).getLabel();
			XEvent event = factory.createEvent();
			event.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name",taskLabel));
			event.getAttributes().put("time:timestamp",new XAttributeTimestampImpl("time:timestamp",i));
			logTrace.add(event);
			}
		}	
		
		this.traceLog = log;		
		
	};

	
	public void createTraceLog(){
		
		XFactoryNaiveImpl factory = new XFactoryNaiveImpl(); 
		
		XLog log = factory.createLog();
		
		XTrace logTrace = factory.createTrace();
		log.add(logTrace);
		
		for(int i=0; i<trace.size(); i++)
		{
			String taskLabel = trace.elementAt(i).getLabel();
			XEvent event = factory.createEvent();
			event.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name",taskLabel));
			logTrace.add(event);
		}	
		
		this.traceLog = log;		
		
	};
	
	public void createLogForTraceWithAsterisk(){
		
		XFactoryNaiveImpl factory = new XFactoryNaiveImpl(); 
		XLog log = factory.createLog();
		XTrace logTrace = factory.createTrace();
		log.add(logTrace);
		

		Integer i = 0;
		Integer index = 0;
		Vector<Vector<String>> sequences = new Vector<Vector<String>>();
		sequences.add(new Vector<String>());
		
	while (i<trace.size())
	{
		String currentTaskLabel = trace.elementAt(i).getLabel();	
		
		if(!trace.elementAt(i).isAsterisk())
		{
			sequences.elementAt(index).add(currentTaskLabel);
			i++;
		}else
		{
			
			if(sequences.elementAt(index).size() > 1)
			{
				String replacement = this.replacementMap.get(sequences.elementAt(index));
				XEvent event = factory.createEvent();
				event.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name",replacement));
				logTrace.add(event);
			}
			
			if(sequences.elementAt(index).size() == 1)
			{
				String task = sequences.elementAt(index).elementAt(0);
				XEvent event = factory.createEvent();
				event.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name",task));
				logTrace.add(event);
				
			}
			
			sequences.add(new Vector<String>());
			index++;
			i++;
		}
		
		if(i == trace.size())
		{ 
			if(sequences.elementAt(index).size() > 1)
			{
				String replacement = this.replacementMap.get(sequences.elementAt(index));
				XEvent event = factory.createEvent();
				event.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name",replacement));
				logTrace.add(event);
			}
			
			if(sequences.elementAt(index).size() == 1)
			{
				String task = sequences.elementAt(index).elementAt(0);
				XEvent event = factory.createEvent();
				event.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name",task));
				logTrace.add(event);
				
			}
		}
	}
			
	this.traceLog = log;		
	};
	
	
	public void addStartEnd(int hashCode){
		
		this.addTask(new PQLTask("i_t"+hashCode,1.0),0);
		this.addTask(new PQLTask("o_t"+hashCode,1.0));
	
	}
	
	public void createReplacementMap(){
		
		Integer i = 0;
		Integer index = 0;
		
		Vector<Vector<String>> sequences = new Vector<Vector<String>>();
		sequences.add(new Vector<String>());
		
		while (i<trace.size())
		{
		String currentTaskLabel = trace.elementAt(i).getLabel();	
		
		if(!trace.elementAt(i).isAsterisk())
		{
			sequences.elementAt(index).add(currentTaskLabel);
			i++;
		}else
		{
			if(sequences.elementAt(index).size() > 1)
			{
				this.replacementMap.put(sequences.elementAt(index),"replacement"+index.toString()+this.hashCode());
			}
			sequences.add(new Vector<String>());
			index++;
			i++;
		}
		if(i == trace.size() && sequences.elementAt(index).size() > 1)
		{this.replacementMap.put(sequences.elementAt(index),"replacement"+index.toString()+this.hashCode());}
		}
				
	};


}
