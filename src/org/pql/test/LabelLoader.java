package org.pql.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import org.jbpt.persist.MySQLConnection;
import org.jbpt.petri.Flow;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.jbpt.petri.Marking;
import org.jbpt.petri.Node;
import org.jbpt.petri.Place;
import org.jbpt.petri.Run;
import org.jbpt.petri.Transition;
import org.jbpt.petri.persist.AbstractPetriNetPersistenceLayerMySQL;
import org.jbpt.petri.persist.IPetriNetPersistenceLayer;
import org.pql.core.PQLTask;
import org.pql.core.PQLTrace;

public class LabelLoader<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>>
extends MySQLConnection
{
	private static Random 							rand = new Random(System.currentTimeMillis());
	private IPetriNetPersistenceLayer<F,N,P,T,M> 	PL = new AbstractPetriNetPersistenceLayerMySQL<F,N,P,T,M>(connection); 
	private Set<Integer> 							netIDs = PL.getAllInternalIDs();
	private Set<String> 							externalIDs = new HashSet<String>();
	

	protected LabelLoader(String url, String user, String password, int start, int end) throws SQLException, ClassNotFoundException {
		super(url, user, password);
		
		netIDs = new HashSet<Integer>();
		for(int i=start; i<end+1; i++)
		netIDs.add(i);
		} 

	protected LabelLoader(String url, String user, String password) throws SQLException, ClassNotFoundException {
		super(url, user, password);
			} 

	
	protected LabelLoader(String url, String user, String password, Set<String> externalIDs) throws SQLException, ClassNotFoundException {
			super(url, user, password);
			this.externalIDs.addAll(externalIDs);
			this.netIDs.removeAll(netIDs);
			for(String s: externalIDs)
			{
				this.netIDs.add(PL.getInternalID(s));
			}
			} 

	public PQLTrace getTrace(int length) throws SQLException
	{
		PQLTrace trace = new PQLTrace();
		boolean traceCreated = false;
		Vector<Integer> nets = new Vector<Integer>();
		nets.addAll(netIDs);
		
		while(!traceCreated)
		{
			Integer netID = nets.elementAt(randInt(0,nets.size()-1));
			//System.out.println("netID:" + netID);
			INetSystem<F,N,P,T,M> netSystem = PL.restoreNetSystem(netID);
			netSystem.loadNaturalMarking();
			
			Vector<String> run = getRun(netSystem, length);
			if (run.size() == length)
			{
				for(int i=0; i<length; i++)
				{
					trace.addTask(new PQLTask(run.elementAt(i), 1.0));
				}
				traceCreated = true;	
			}
		}
		
		return trace;
	}
	
	public Vector<String> getRun(INetSystem<F,N,P,T,M> netSystem, Integer length) throws SQLException
	{
		Vector<String> trace = new Vector<String>();
		
		INetSystem<Flow, Node, Place, Transition, Marking> sys = (INetSystem<Flow, Node, Place, Transition, Marking>) netSystem;
		Integer startNode = randInt(0, 4);
		Integer numberOfSteps = length + startNode;
		
		Run run = new Run(sys);
		run.setNetSystem(sys);
		
		//System.out.println("numberOfSteps: "+numberOfSteps);
		
		while(trace.size() < numberOfSteps && run.getPossibleExtensions().size()>0)
		{
		Vector<Transition> enabledTransitions = new Vector<Transition>();
		enabledTransitions.addAll(run.getPossibleExtensions());
		Integer position = randInt(0, enabledTransitions.size()-1);
		
		Transition t = enabledTransitions.elementAt(position);
		
		if(!t.isSilent())trace.add(t.getLabel());
		sys.getMarking().fire(t);
		run.setNetSystem(sys);
		}
	
		//System.out.println("Run: "+trace);
		
		for(int i=0; i<startNode; i++)
		{
			if(trace.size()>0) trace.remove(0);
		}
			
		return trace;
	}

	
	public PQLTrace addAsterisks(PQLTrace trace, int numberOfAsterisks)
	{
		int traceSize = trace.getTrace().size();
		boolean hasSymbols = true;
		
		while(hasSymbols && numberOfAsterisks>0 && traceSize>0)
		{
			hasSymbols = false;
			for(int i=0; i<trace.getTrace().size(); i++)
			{
				if(!trace.getTrace().elementAt(i).isAsterisk()) hasSymbols = true;
			}
			
			Integer position = randInt(0, traceSize-1);
			if(!trace.getTrace().elementAt(position).isAsterisk())
			{
				trace.getTrace().elementAt(position).setAsterisk(true);
				numberOfAsterisks--;
			}
		}
	
		//if * is not a part of a trace
/*		for(int i=0; i<numberOfAsterisks; i++)
		{
			PQLTask asterisk = new PQLTask("Asterisk",1.0);
			asterisk.setAsterisk(true);
			Integer position = randInt(0, traceSize); 
			trace.addTask(asterisk, position);
			traceSize++;
		}
*/		
		return trace;
	}
	
	public PQLTrace addTildas(PQLTrace trace, int numberOfTildas)
	{
		int numberOfTasks = 0;
		for(int i=0; i<trace.getTrace().size(); i++)
		{
			if(!trace.getTrace().elementAt(i).isAsterisk())
			numberOfTasks++;	
		}
		
		while(numberOfTasks > 0 && numberOfTildas > 0)
		{
			Integer position = randInt(0, trace.getTrace().size()-1);
			
			if((trace.getTrace().elementAt(position).getSimilarity() > 0.0) && !trace.getTrace().elementAt(position).isAsterisk())
			{
				trace.getTrace().elementAt(position).setSimilarity(0.0);
				numberOfTildas--;
				numberOfTasks--;
			}

		}
		
		return trace;
	}

	public PQLTrace removeTildas(PQLTrace trace)
	{
		for(int i=0; i<trace.getTrace().size(); i++)
		{
			trace.getTrace().elementAt(i).setSimilarity(1.0);	
		}
		return trace;
	}
	
	public PQLTrace removeAsterisks(PQLTrace trace)
	{
		for(int i=0; i<trace.getTrace().size(); i++)
		{
			trace.getTrace().elementAt(i).setAsterisk(false);	
		}
		return trace;
	}


	public String getQueryTrace(PQLTrace trace)
	{
		String queryTrace = "";
		
		for(int i=0; i<trace.getTrace().size(); i++)
		{
			PQLTask event = trace.getTrace().elementAt(i);
			if(event.isAsterisk())
			{queryTrace += "*,";}
			else
			{
				if(event.getSimilarity() > 0.0)
				{
					queryTrace += "\""+event.getLabel()+"\",";
				}
				else
				{
					queryTrace += "~\""+event.getLabel()+"\",";
				}
			}	
				
		}
		
		if(trace.getTrace().size()>0)
		{queryTrace = queryTrace.substring(0,queryTrace.length()-1);}
		
		return queryTrace;
	}
	
	public static int randInt(int min, int max) {
	    
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}


	/*		
	public List<String> getLabels() throws SQLException {
			List<String> result = new ArrayList<String>();
			
			PreparedStatement cs = connection.prepareStatement("SELECT label FROM pql.jbpt_labels;");
			ResultSet res = cs.executeQuery();
			
			while (res.next()) {
				result.add(res.getString(1));
			}
			
			return result;
		}
*/	
	
	}

