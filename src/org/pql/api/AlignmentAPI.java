package org.pql.api;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
import org.jbpt.petri.NetSystem;
import org.jbpt.petri.io.PNMLSerializer;

/**
 * A.P.
 */
public class AlignmentAPI extends MySQLConnection
		 {
	
	protected static String 	JBPT_PETRI_NETS_GET_PNML_CONTENT = "{? = CALL pql.jbpt_petri_nets_get_pnml_content(?)}";//A.P.
	
	
		public AlignmentAPI(String mySQLURL, String mySQLUser, String mySQLPassword)
					throws ClassNotFoundException, SQLException {
		super(mySQLURL,mySQLUser,mySQLPassword);}

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

	public static PetrinetGraph constructNet(byte[] netFile, PetrinetGraph net) {
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
	
	public static XLog getLog(PQLTrace trace)
	{
		
		XFactoryNaiveImpl factory = new XFactoryNaiveImpl(); 
		
		XLog log = factory.createLog();
		
		XTrace logtrace = factory.createTrace();
		log.add(logtrace);
		
		Vector<PQLTask> tasks = new Vector<PQLTask>();
		tasks.addAll(trace.getTrace());
		
		
		for(int i=0; i<tasks.size(); i++)
		{String task = tasks.elementAt(i).getLabel();
		if(!(task.equals("UniverseSymbol")))//skip *
		{XEvent event = factory.createEvent();
		event.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name",task));
		logtrace.add(event);
		}
		}	
		
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

	public static Map<Transition, Integer> constructMOSCostFunction(PetrinetGraph net) {
		Map<Transition,Integer> costMOS = new HashMap<Transition,Integer>();
		
		for (Transition  t : net.getTransitions())
			if (t.isInvisible() || t.getLabel().equals(""))
				costMOS.put(t,0);
			else
				costMOS.put(t,1);	
		
		return costMOS;
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
