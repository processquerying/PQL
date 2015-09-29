package org.pql.api;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import org.deckfour.xes.classification.XEventClass;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.factory.XFactoryNaiveImpl;
import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XAttributeLiteralImpl;
import org.jbpt.persist.MySQLConnection;
import org.pql.core.PQLTask;
import org.pql.core.PQLTrace;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.elements.Place;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.connectionfactories.logpetrinet.TransEvClassMapping;
import org.jbpt.petri.Flow;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.ITransition;
import org.jbpt.petri.NetSystem;
import org.jbpt.petri.Node;
import org.jbpt.petri.io.PNMLSerializer;

/**
 * A.P.
 */
public class AlignmentAPI extends MySQLConnection
		 {
	
	protected static String 	JBPT_PETRI_NETS_GET_PNML_CONTENT = "{? = CALL pql.jbpt_petri_nets_get_pnml_content(?)}";
	
		public AlignmentAPI(String mySQLURL, String mySQLUser, String mySQLPassword)
					throws ClassNotFoundException, SQLException {
		super(mySQLURL,mySQLUser,mySQLPassword);}
	
		public static NetSystem getStarTransformation(NetSystem sys, PQLTrace trace, Map<String, ITransition> transitionMap)
		{
			Map<Vector<String>,String> replacementMap = new HashMap<Vector<String>,String>();
			replacementMap = trace.getReplacementMap();
			
			//create control place
			org.jbpt.petri.Place controlPlace = sys.createPlace();
			controlPlace.setName("ControlPlace");
			controlPlace.setLabel("ControlPlace");
			
			//add double arcs to all unified transitions - prev. version
		/*	for (Map.Entry<PQLTask, ITransition> entry : transitionMap.entrySet())
			{
			    sys.addFlow(controlPlace, (Node) entry.getValue());
			    sys.addFlow((Node) entry.getValue(), controlPlace);
			}
			sys.getMarking().put(controlPlace, 1);
	*/		
			//add double arcs to all visible transitions
			Set<org.jbpt.petri.Transition> trans = sys.getTransitions();
			Iterator it0 = trans.iterator();
			while (it0.hasNext())
			{
				org.jbpt.petri.Transition tr = (org.jbpt.petri.Transition) it0.next();
			    Node node = (Node) tr;
				if(!tr.isSilent())
				{
			    sys.addFlow(controlPlace, node);
			    sys.addFlow(node, controlPlace);
				}
			}
			sys.getMarking().put(controlPlace, 1);
	
			
			//iterate through sequences
			for (Map.Entry<Vector<String>,String> seqEntry : replacementMap.entrySet())
			{
			Vector<String> sequence = new Vector<String>();
			sequence.addAll(seqEntry.getKey());
			String replacement = seqEntry.getValue();
					
			//create sequence transitions
			Vector<org.jbpt.petri.Transition> seqTransitions = new Vector<org.jbpt.petri.Transition>();
			for(int i=0; i<sequence.size(); i++)
			{
				org.jbpt.petri.Transition tr = sys.createTransition();
				tr.setName("t"+Math.abs(tr.hashCode()));
				seqTransitions.add(tr);
				seqTransitions.elementAt(i).setLabel(sequence.elementAt(i)+"-new");
						
			}
			org.jbpt.petri.Transition trrep = sys.createTransition();
			trrep.setName("t"+Math.abs(trrep.hashCode()));
			
			seqTransitions.add(trrep);
			seqTransitions.elementAt(seqTransitions.size()-1).setLabel(replacement);
			
			//add arc from control place to the first element in the sequence
			sys.addFlow(controlPlace, seqTransitions.elementAt(0));
				
			//create places and flows between new transitions
			for(int i=0; i<seqTransitions.size()-1;i++)
			{
				org.jbpt.petri.Place tp = sys.createPlace();
				tp.setName("p"+Math.abs(tp.hashCode()));
				sys.addFlow(seqTransitions.elementAt(i),tp);
				sys.addFlow(tp,seqTransitions.elementAt(i+1));
				
				//PQLTask curTask = new PQLTask(sequence.elementAt(i),1.0);
	
				org.jbpt.petri.Transition curT = null;
					
				for (Map.Entry<String, ITransition> entry : transitionMap.entrySet())
				{
				    if(entry.getKey().equals(sequence.elementAt(i)))
				    {curT = (org.jbpt.petri.Transition) entry.getValue();
				    }
				}
				
				Iterator it = sys.getTransitions().iterator();
				
				while(it.hasNext())
				{
					org.jbpt.petri.Transition t = (org.jbpt.petri.Transition) it.next();
					if (curT == t)
					{
						for (org.jbpt.petri.Place x : sys.getPreset(t)) 
							{if(!x.equals(controlPlace)){sys.addFlow(x,seqTransitions.elementAt(i));}}
						for (org.jbpt.petri.Place x : sys.getPostset(t)) 
							{if(!x.equals(controlPlace)){sys.addFlow(seqTransitions.elementAt(i),x);}}
						//System.out.println(sys.getPreset(t) + "---" + sys.getPostset(t));
					}
					
				}
			
			}
			
			//add arc from the last element in the sequence to the control place
			sys.addFlow(seqTransitions.elementAt(seqTransitions.size()-1),controlPlace);
			
			}
			
			return sys;
		}
  	
		//not used		
		public static NetSystem getOneTransformation(NetSystem sys, PQLTrace trace, Vector<String> sequence, String replacement, Map<PQLTask, ITransition> transitionMap, org.jbpt.petri.Place controlPlace)
		{
			
			//add double arcs to all unified transitions
			for (Map.Entry<PQLTask, ITransition> entry : transitionMap.entrySet())
			{
			    sys.addFlow(controlPlace, (Node) entry.getValue());
			    sys.addFlow((Node) entry.getValue(), controlPlace);
			}
			sys.getMarking().put(controlPlace, 1);
			
			//create sequence transitions
			Vector<org.jbpt.petri.Transition> seqTransitions = new Vector<org.jbpt.petri.Transition>();
			for(int i=0; i<sequence.size(); i++)
			{
				seqTransitions.add(sys.createTransition());
				seqTransitions.elementAt(i).setLabel(sequence.elementAt(i));
						
			}
			seqTransitions.add(sys.createTransition());
			seqTransitions.elementAt(seqTransitions.size()-1).setLabel(replacement);
			
			//add arc from control place to the first element in the sequence
			sys.addFlow(controlPlace, seqTransitions.elementAt(0));
			
			//System.out.println("tm2: "+transitionMap);
			
			
			//create places and flows between new transitions
			for(int i=0; i<seqTransitions.size()-1;i++)
			{
				org.jbpt.petri.Place tp = sys.createPlace();
				sys.addFlow(seqTransitions.elementAt(i),tp);
				sys.addFlow(tp,seqTransitions.elementAt(i+1));
				
				PQLTask curTask = new PQLTask(sequence.elementAt(i),1.0);
				//Set<String> labels = new HashSet<String>();
				//labels.add(sequence.elementAt(i));
				//curTask.setLabels(labels);
				
				//System.out.println(transitionMap.get(curTask));
				
				org.jbpt.petri.Transition curT = null;// = (org.jbpt.petri.Transition) transitionMap.get(curTask);
				
				
				for (Map.Entry<PQLTask, ITransition> entry : transitionMap.entrySet())
				{
				    if(entry.getKey().getLabel().equals(curTask.getLabel()))
				    {curT = (org.jbpt.petri.Transition) entry.getValue();
				    //System.out.println(entry.getValue());
				    //System.out.println(curT);
				    }
				}
		
				
				//System.out.println(curTask + "+++" + curT);
				//System.out.println(sys.getPreset(t) + "---" + sys.getPostset(t));
				
				Iterator it = sys.getTransitions().iterator();
				
				while(it.hasNext())
				{
					org.jbpt.petri.Transition t = (org.jbpt.petri.Transition) it.next();
					if (curT == t)
					{
						for (org.jbpt.petri.Place x : sys.getPreset(t)) sys.addFlow(x,seqTransitions.elementAt(i));
						for (org.jbpt.petri.Place x : sys.getPostset(t)) sys.addFlow(seqTransitions.elementAt(i),x);
						System.out.println(sys.getPreset(t) + "---" + sys.getPostset(t));
					}
					
				}
				
				
		
			}
			
			//add arc from the last element in the sequence to the control place
			sys.addFlow(seqTransitions.elementAt(seqTransitions.size()-1),controlPlace);
			
			
		
				
			return sys;
		}
 	
		//method moved to PQLTrace
		public static Map<Vector<String>,String> getTraceReplaysments(PQLTrace trace)
		{
			trace.addTask(new PQLTask("i_t",1.0),0);
			trace.addTask(new PQLTask("o_t",1.0));
			
			Map<Vector<String>,String> map = new HashMap<Vector<String>,String>();
			
			Integer i = 0;
			Integer index = 0;
			
			Vector<Vector<String>> array = new Vector<Vector<String>>();
			array.add(new Vector<String>());
			
			while (i<trace.getTrace().size())
			{
			String current = trace.getTrace().elementAt(i).getLabel();	
			
			if(!current.equals("UniverseSymbol"))
			{
				array.elementAt(index).add(current);
				i++;
			}else
			{
				if(array.elementAt(index).size() > 1)
				{
					map.put(array.elementAt(index),"repl"+index.toString());
				}
				array.add(new Vector<String>());
				index++;
				i++;
			}
			if(i == trace.getTrace().size() && array.elementAt(index).size() > 1)
			{map.put(array.elementAt(index),"repl"+index.toString());}
			}		
			return map;
		}
		
		//not used
		public static XLog getLogForStars(PQLTrace trace, Map<Vector<String>,String> map)
	{
			
			XFactoryNaiveImpl factory = new XFactoryNaiveImpl(); 
			XLog log = factory.createLog();
			XTrace logtrace = factory.createTrace();
			log.add(logtrace);
			
	
			Integer i = 0;
			Integer index = 0;
			Vector<Vector<String>> array = new Vector<Vector<String>>();
			array.add(new Vector<String>());
			
		while (i<trace.getTrace().size())
		{
			String current = trace.getTrace().elementAt(i).getLabel();	
			
			if(!current.equals("UniverseSymbol"))
			{
				array.elementAt(index).add(current);
				i++;
			}else
			{
				
				if(array.elementAt(index).size() > 1)
				{
					String replacement = map.get(array.elementAt(index));
					XEvent event = factory.createEvent();
					event.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name",replacement));
					logtrace.add(event);
					//System.out.println(replacement);	
					
				}
				
				if(array.elementAt(index).size() == 1)
				{
					String task = array.elementAt(index).elementAt(0);
					XEvent event = factory.createEvent();
					event.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name",task));
					logtrace.add(event);
					//System.out.println(task);
					
				}
				
				//add * to trace - not needed
				//XEvent starEvent = factory.createEvent();
				//starEvent.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name","UniverseSymbol"));
				//logtrace.add(starEvent);
				//System.out.println("UniverseSymbol");	
				
				array.add(new Vector<String>());
				index++;
				i++;
			}
			
			if(i == trace.getTrace().size())
			{ 
				if(array.elementAt(index).size() > 1)
				{
					String replacement = map.get(array.elementAt(index));
					XEvent event = factory.createEvent();
					event.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name",replacement));
					logtrace.add(event);
					//System.out.println(replacement);
				}
				
				if(array.elementAt(index).size() == 1)
				{
					String task = array.elementAt(index).elementAt(0);
					XEvent event = factory.createEvent();
					event.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name",task));
					logtrace.add(event);
					//System.out.println(task);
					
				}
			}
		}
				
		
			return log;
	}

	
		public static NetSystem addStartEnd(NetSystem sys, PQLTrace trace) {
			
			//add i
			Set<org.jbpt.petri.Place> sourcePlaces = sys.getSourcePlaces();
			
			if(!sourcePlaces.isEmpty())
			{
				org.jbpt.petri.Place i = sys.createPlace();
				i.setName("p"+Math.abs(i.hashCode()));
				
				org.jbpt.petri.Transition ti = sys.createTransition();
				ti.setLabel(trace.getTrace().elementAt(0).getLabel());
				ti.setName(trace.getTrace().elementAt(0).getLabel());
			
				sys.addFlow(i, ti);
								
				for (org.jbpt.petri.Place p : sourcePlaces) 
				{
					sys.addFlow(ti,p);
				}
				
				sys.getMarking().clear();
				sys.getMarking().put(i, 1);
				//System.out.println("ti: "+ti);
			}
			
			//add o
			Set<org.jbpt.petri.Place> sinkPlaces = sys.getSinkPlaces();
			if(!sinkPlaces.isEmpty())
			{
				org.jbpt.petri.Place o = sys.createPlace();
				o.setName("p"+Math.abs(o.hashCode()));
				
				org.jbpt.petri.Transition to = sys.createTransition();
				to.setLabel(trace.getTrace().elementAt(trace.getTrace().size()-1).getLabel());
				to.setName(trace.getTrace().elementAt(trace.getTrace().size()-1).getLabel());
				
				sys.addFlow(to,o);
								
				for (org.jbpt.petri.Place p : sinkPlaces) 
				{
					sys.addFlow(p,to);
				}
				//System.out.println("to: "+to);
			}
			
			
			return sys;
		}

		
		public static String getPnmlContent(int internalID) throws SQLException {
		
		String pnml = "";
		CallableStatement cs = connection.prepareCall(JBPT_PETRI_NETS_GET_PNML_CONTENT);
		cs.registerOutParameter(1, java.sql.Types.VARCHAR);
		cs.setInt(2, internalID);
		cs.execute();
		if(!(cs.getString(1).equals(null)))
		{pnml = cs.getString(1);}
		cs.close();
		return pnml;
	
	}

		
		public static PetrinetGraph constructPetrinetGraphFromNetSystemWithMarking(NetSystem sys, PetrinetGraph net) {
		
		//int pi,ti;
		//pi = ti = 1;
		//for (org.jbpt.petri.Place p : sys.getPlaces()) 
		//	p.setName("p"+pi++);
		//for (org.jbpt.petri.Transition t : sys.getTransitions()) 
		//	t.setName("t"+ti++);
		
		// places
		Map<org.jbpt.petri.Place,Place> p2p = new HashMap<>();
		for (org.jbpt.petri.Place p : sys.getPlaces()) {
			Place pp = net.addPlace(p.getId());
			p2p.put(p,pp);
			//System.out.println("p: "+ p.getId());
			//System.out.println("pp: "+ pp.getLabel());
		}
		
		// transitions
		Map<org.jbpt.petri.Transition,Transition> t2t = new HashMap<>();
		for (org.jbpt.petri.Transition t : sys.getTransitions()) {
			Transition tt = net.addTransition(t.getLabel());
			tt.setInvisible(t.isSilent());
			t2t.put(t,tt);
		}
		
		// flow
		for (Flow f : sys.getFlow()) {
			if (f.getSource() instanceof org.jbpt.petri.Place) {
				net.addArc(p2p.get(f.getSource()),t2t.get(f.getTarget()));
			} else {
				net.addArc(t2t.get(f.getSource()),p2p.get(f.getTarget()));
			}
		}
		
		
		// initial marking
	/*	Set<org.jbpt.petri.Place> markedPlaces = new HashSet<org.jbpt.petri.Place>();
		markedPlaces.addAll(sys.getMarkedPlaces());
		
		Marking initMarking = new Marking();
		//net.a
		
		for(org.jbpt.petri.Place x: markedPlaces)
		{
			Place px = p2p.get(x);
			initMarking.add(px);
		}
	*/	
		//System.out.println("correct init marking: "+initMarking);
		
		// add unique start node
		/*if (sys.getSourceNodes().isEmpty()) {
			Place i = net.addPlace("START_P");
			Transition t = net.addTransition("");
			t.setInvisible(true);
			net.addArc(i,t);
			
			for (org.jbpt.petri.Place p : sys.getMarkedPlaces()) {
				net.addArc(t,p2p.get(p));	
			}
			
		}
		 */		
		return net;
	}

		
		public static PetrinetGraph constructPetrinetGraphFromNetSystem(NetSystem sys, PetrinetGraph net) {
		
		int pi,ti;
		pi = ti = 1;
		for (org.jbpt.petri.Place p : sys.getPlaces()) 
			p.setName("p"+pi++);
		for (org.jbpt.petri.Transition t : sys.getTransitions()) 
			t.setName("t"+ti++);
		
		// places
		Map<org.jbpt.petri.Place,Place> p2p = new HashMap<>();
		for (org.jbpt.petri.Place p : sys.getPlaces()) {
			Place pp = net.addPlace(p.getName());
			p2p.put(p,pp);
		}
		
		// transitions
		Map<org.jbpt.petri.Transition,Transition> t2t = new HashMap<>();
		for (org.jbpt.petri.Transition t : sys.getTransitions()) {
			Transition tt = net.addTransition(t.getLabel());
			tt.setInvisible(t.isSilent());
			t2t.put(t,tt);
		}
		
		// flow
		for (Flow f : sys.getFlow()) {
			if (f.getSource() instanceof org.jbpt.petri.Place) {
				net.addArc(p2p.get(f.getSource()),t2t.get(f.getTarget()));
			} else {
				net.addArc(t2t.get(f.getSource()),p2p.get(f.getTarget()));
			}
		}
		
		// add unique start node
		if (sys.getSourceNodes().isEmpty()) {
			Place i = net.addPlace("START_P");
			Transition t = net.addTransition("");
			t.setInvisible(true);
			net.addArc(i,t);
			
			for (org.jbpt.petri.Place p : sys.getMarkedPlaces()) {
				net.addArc(t,p2p.get(p));	
			}
			
		}
		
		return net;
	}
	
		
	public static PetrinetGraph constructPetriNetFromByte(byte[] netFile, PetrinetGraph net) {
		PNMLSerializer PNML = new PNMLSerializer();
		NetSystem sys = PNML.parse(netFile);
		
		int pi,ti;
		pi = ti = 1;
		for (org.jbpt.petri.Place p : sys.getPlaces()) 
			p.setName("p"+pi++);
		for (org.jbpt.petri.Transition t : sys.getTransitions()) 
			t.setName("t"+ti++);
		
		// places
		Map<org.jbpt.petri.Place,Place> p2p = new HashMap<>();
		for (org.jbpt.petri.Place p : sys.getPlaces()) {
			Place pp = net.addPlace(p.getName());
			p2p.put(p,pp);
		}
		
		// transitions
		Map<org.jbpt.petri.Transition,Transition> t2t = new HashMap<>();
		for (org.jbpt.petri.Transition t : sys.getTransitions()) {
			Transition tt = net.addTransition(t.getLabel());
			tt.setInvisible(t.isSilent());
			t2t.put(t,tt);
		}
		
		// flow
		for (Flow f : sys.getFlow()) {
			if (f.getSource() instanceof org.jbpt.petri.Place) {
				net.addArc(p2p.get(f.getSource()),t2t.get(f.getTarget()));
			} else {
				net.addArc(t2t.get(f.getSource()),p2p.get(f.getTarget()));
			}
		}
		
		// add unique start node
		if (sys.getSourceNodes().isEmpty()) {
			Place i = net.addPlace("START_P");
			Transition t = net.addTransition("");
			t.setInvisible(true);
			net.addArc(i,t);
			
			for (org.jbpt.petri.Place p : sys.getMarkedPlaces()) {
				net.addArc(t,p2p.get(p));	
			}
			
		}
		
		return net;
	}
	

	public static PetrinetGraph constructPetriNetFromString(String netFile, PetrinetGraph net) {
		PNMLSerializer PNML = new PNMLSerializer();
		NetSystem sys = PNML.parse(netFile);
		
		int pi,ti;
		pi = ti = 1;
		for (org.jbpt.petri.Place p : sys.getPlaces()) 
			p.setName("p"+pi++);
		for (org.jbpt.petri.Transition t : sys.getTransitions()) 
			t.setName("t"+ti++);
		
		// places
		Map<org.jbpt.petri.Place,Place> p2p = new HashMap<>();
		for (org.jbpt.petri.Place p : sys.getPlaces()) {
			Place pp = net.addPlace(p.getName());
			p2p.put(p,pp);
		}
		
		// transitions
		Map<org.jbpt.petri.Transition,Transition> t2t = new HashMap<>();
		for (org.jbpt.petri.Transition t : sys.getTransitions()) {
			Transition tt = net.addTransition(t.getLabel());
			tt.setInvisible(t.isSilent());
			t2t.put(t,tt);
		}
		
		// flow
		for (Flow f : sys.getFlow()) {
			if (f.getSource() instanceof org.jbpt.petri.Place) {
				net.addArc(p2p.get(f.getSource()),t2t.get(f.getTarget()));
			} else {
				net.addArc(t2t.get(f.getSource()),p2p.get(f.getTarget()));
			}
		}
		
		// add unique start node
		if (sys.getSourceNodes().isEmpty()) {
			Place i = net.addPlace("START_P");
			Transition t = net.addTransition("");
			t.setInvisible(true);
			net.addArc(i,t);
			
			for (org.jbpt.petri.Place p : sys.getMarkedPlaces()) {
				net.addArc(t,p2p.get(p));	
			}
			
		}
		
		return net;
	}
	
	//not used
	public static XLog getLogNoStars(PQLTrace trace)
	{
		
		XFactoryNaiveImpl factory = new XFactoryNaiveImpl(); 
		
		XLog log = factory.createLog();
		
		XTrace logtrace = factory.createTrace();
		log.add(logtrace);
		
		Vector<PQLTask> tasks = new Vector<PQLTask>();
		tasks.addAll(trace.getTrace());
		
		//add start transition
		//XEvent event1 = factory.createEvent();
		//event1.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name","i_t"));
		//logtrace.add(event1);
		
		
		for(int i=0; i<tasks.size(); i++)
		{String task = tasks.elementAt(i).getLabel();
		//if(!(task.equals("UniverseSymbol")))//skip *
		//{
		XEvent event = factory.createEvent();
		event.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name",task));
		logtrace.add(event);
		//}
		}	
		
		//add end transition
		//XEvent event2 = factory.createEvent();
		//event2.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name","o_t"));
		//logtrace.add(event2);
			
		
		return log;
	}

	public static Marking[] getFinalMarkings(PetrinetGraph net) {			
		Marking finalMarking = new Marking();
		
		for (Place p : net.getPlaces()) {
			if (net.getOutEdges(p).isEmpty())
				finalMarking.add(p);
		}
			
		Marking[] finalMarkings = new Marking[1];
		finalMarkings[0] = finalMarking;
		
		return finalMarkings;
	}

	public static Marking getInitialMarking(PetrinetGraph net) {
		Marking initMarking = new Marking();
		
		for (Place p : net.getPlaces()) {
			if (net.getInEdges(p).isEmpty())
				initMarking.add(p);
		}
		
		return initMarking;
	}
	
	public static Marking getInitialMarkingFromNS(PetrinetGraph net, NetSystem sys) {
		Marking initMarking = new Marking();
		
		// initial marking
		Set<org.jbpt.petri.Place> markedPlaces = new HashSet<org.jbpt.petri.Place>();
		markedPlaces.addAll(sys.getMarkedPlaces());
		
		for(org.jbpt.petri.Place x: markedPlaces)
		{
						
			for(Place y: net.getPlaces())
			{
				if (y.getLabel() ==x.getId())
				{
					initMarking.add(y);
				}
			}
		}
		
			
			
		return initMarking;
	}

	public static Map<Transition, Integer> constructMOSCostFunction(PetrinetGraph net) {
		Map<Transition,Integer> costMOS = new HashMap<Transition,Integer>();
		
		for (Transition  t : net.getTransitions())
			if (t.isInvisible() || t.getLabel().equals(""))
				costMOS.put(t,0);
			else
				costMOS.put(t,1);	
		
		return costMOS;
	}
	
	public static Map<Transition, Integer> constructMOSCostFunctionForStars(PetrinetGraph net, XLog log, XEventClassifier eventClassifier) {
		Map<Transition,Integer> costMOS = new HashMap<Transition,Integer>();
		
		XLogInfo summary = XLogInfoFactory.createLogInfo(log,eventClassifier);
		
		for (Transition  t : net.getTransitions())
			costMOS.put(t,0);	
		
	/*	for (XEventClass evClass : summary.getEventClasses().getClasses()) 
		{
			String event = evClass.getId();
			
			for (Transition  t : net.getTransitions())
				if (t.getLabel().equals(event))
					costMOS.put(t,1);
		}	
	*/	return costMOS;
	}

	public static Map<XEventClass, Integer> constructMOTCostFunction(PetrinetGraph net, XLog log, XEventClassifier eventClassifier, XEventClass dummyEvClass) {
		Map<XEventClass,Integer> costMOT = new HashMap<XEventClass,Integer>();		
		XLogInfo summary = XLogInfoFactory.createLogInfo(log,eventClassifier);
		
		for (XEventClass evClass : summary.getEventClasses().getClasses()) {
			costMOT.put(evClass,1);
		}
		
		costMOT.put(dummyEvClass,1);
		
		return costMOT;
	}
	
	public static Map<XEventClass, Integer> constructMOTCostFunctionForStars(PetrinetGraph net, XLog log, XEventClassifier eventClassifier, XEventClass dummyEvClass) {
		Map<XEventClass,Integer> costMOT = new HashMap<XEventClass,Integer>();		
		XLogInfo summary = XLogInfoFactory.createLogInfo(log,eventClassifier);
		
		for (XEventClass evClass : summary.getEventClasses().getClasses()) {
			costMOT.put(evClass,1);
		}
		
		costMOT.put(dummyEvClass,0);
		
		return costMOT;
	}

	public static TransEvClassMapping constructMapping(PetrinetGraph net, XLog log, XEventClass dummyEvClass, XEventClassifier eventClassifier) {
		TransEvClassMapping mapping = new TransEvClassMapping(eventClassifier, dummyEvClass);
		
		XLogInfo summary = XLogInfoFactory.createLogInfo(log,eventClassifier);
		
		for (Transition t : net.getTransitions()) {
			boolean mapped = false;
			
			for (XEventClass evClass : summary.getEventClasses().getClasses()) {
				String id = evClass.getId();
				
				if (t.getLabel().equals(id)) {
					mapping.put(t, evClass);
					mapped = true;
					break;
				}
			}
			
			if (!mapped && !t.isInvisible()) {
				mapping.put(t, dummyEvClass);
			}
				
		}
		
		return mapping;
	}
	
}
