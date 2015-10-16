package org.pql.alignment;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.deckfour.xes.classification.XEventClass;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XLog;
import org.pql.core.PQLTask;
import org.pql.core.PQLTrace;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.elements.Place;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.connectionfactories.logpetrinet.TransEvClassMapping;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;


/**
 * A.P.
 */

public class AlignmentAPI <F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
					
		 {
	
	protected INetSystem<F,N,P,T,M> sys = null;
	
		public AlignmentAPI(INetSystem<F,N,P,T,M> system)
					throws ClassNotFoundException, SQLException {
		this.sys = system;}
		
		public boolean netStartsWithSilentTransitions()
		{
			boolean result = false;
			
			if(sys.getMarking().isEmpty()) return false;
			
			Set<T> enabledTransitions = new HashSet<T>();
			enabledTransitions.addAll(sys.getEnabledTransitions());
			
			for(T t: enabledTransitions)
			{
				if(t.isSilent())
				return true;
			}
			
			return result;
		}
		
		public boolean netHasAllTraceLabels(PQLTrace trace, Set<String> netLabels)
		{
			boolean hasAllLabels = true;
			
			if(trace.hasAsterisk())
		  	{
			  	for(int i=1; i<trace.getTrace().size()-1; i++)
			  	{
			  		
			  		PQLTask task = trace.getTrace().elementAt(i);
			  		
			  		if(!task.isAsterisk())
			  		{
				  		boolean netHasLabel = false;
				  		for(String t: task.getSimilarLabels())
				  		{
				  			if (netLabels.contains(t)) netHasLabel = true;
				  		}
				  		if (!netHasLabel) 
				  		return false;
				  	}
			  	}
		  	}
		  	else
		  	{
		  		for(int i=0; i<trace.getTrace().size(); i++)
			  	{
			  			PQLTask task = trace.getTrace().elementAt(i);
			  			
				  		boolean netHasLabel = false;
				  		for(String t: task.getSimilarLabels())
				  		{
				  			if (netLabels.contains(t)) netHasLabel = true;
				  		}
				  		if (!netHasLabel) 
				  		return false;
				  	
			  	}
		  		
		  	}	
			
			return hasAllLabels;
			
		}
		
		public Set<String> getAllLabels()
		{
			Set<String> labels = new HashSet<String>();
			Set<T> transitions = this.sys.getTransitions();
			
			for(T t: transitions)
			{
				if(t.isObservable()) labels.add(t.getLabel());
			}
			
			return labels;
		}
		
		
		public PetrinetGraph constructPetrinetGraph(PetrinetGraph net) {
			
		// places
		Map<P,Place> p2p = new HashMap<>();
		for (P p : sys.getPlaces()) {
			Place pp = net.addPlace(p.getId());
			p2p.put(p,pp);
		}
		
		// transitions
		Map<T,Transition> t2t = new HashMap<>();
		for (T t : sys.getTransitions()) {
			Transition tt = net.addTransition(t.getLabel());
			tt.setInvisible(t.isSilent());
			t2t.put(t,tt);
		}
		
		// flow
		for (F f : sys.getFlow()) {
			if (f.getSource() instanceof IPlace) {
				net.addArc(p2p.get(f.getSource()),t2t.get(f.getTarget()));
			} else {
				net.addArc(t2t.get(f.getSource()),p2p.get(f.getTarget()));
			}
		}
	
		return net;
	}
	
		public Marking getInitialMarking(PetrinetGraph net) {
			Marking initMarking = new Marking();
			
			// initial marking
			Set<P> markedPlaces = new HashSet<P>();
			markedPlaces.addAll(sys.getMarkedPlaces());
			
			for(P x: markedPlaces)
			{
							
				for(Place y: net.getPlaces())
				{
					if (y.getLabel() == x.getId())
					{
						initMarking.add(y);
					}
				}
			}
			
				
				
			return initMarking;
		}
		
		public TransEvClassMapping constructMapping(PetrinetGraph net, XLog log, XEventClass dummyEvClass, XEventClassifier eventClassifier) {
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

		public Map<Transition, Integer> constructMOSCostFunction(PetrinetGraph net) {
			Map<Transition,Integer> costMOS = new HashMap<Transition,Integer>();
			
			for (Transition  t : net.getTransitions())
				if (t.isInvisible() || t.getLabel().equals(""))
					costMOS.put(t,0);
				else
					costMOS.put(t,1);	
			
			return costMOS;
		}
		
		public Map<Transition, Integer> constructMOSCostFunctionForAsterisk(PetrinetGraph net) {
			Map<Transition,Integer> costMOS = new HashMap<Transition,Integer>();
			
			for (Transition  t : net.getTransitions())
				costMOS.put(t,0);	
			
			return costMOS;
		}

		public Map<XEventClass, Integer> constructMOTCostFunction(PetrinetGraph net, XLog log, XEventClassifier eventClassifier, XEventClass dummyEvClass) {
			Map<XEventClass,Integer> costMOT = new HashMap<XEventClass,Integer>();		
			XLogInfo summary = XLogInfoFactory.createLogInfo(log,eventClassifier);
			
			for (XEventClass evClass : summary.getEventClasses().getClasses()) {
				costMOT.put(evClass,1);
			}
			
			costMOT.put(dummyEvClass,1);
			
			return costMOT;
		}
		
		public Map<XEventClass, Integer> constructMOTCostFunctionForAsterisk(PetrinetGraph net, XLog log, XEventClassifier eventClassifier, XEventClass dummyEvClass) {
			Map<XEventClass,Integer> costMOT = new HashMap<XEventClass,Integer>();		
			XLogInfo summary = XLogInfoFactory.createLogInfo(log,eventClassifier);
			
			for (XEventClass evClass : summary.getEventClasses().getClasses()) {
				costMOT.put(evClass,1);
			}
			
			costMOT.put(dummyEvClass,0);
			
			return costMOT;
		}
	
	
}
