package org.pql.alignment;

import java.util.List;
import java.util.Map;
import nl.tue.astar.AStarException;
import org.deckfour.xes.classification.XEventClass;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.info.impl.XLogInfoImpl;
import org.deckfour.xes.model.XLog;
import org.pql.api.AlignmentAPI;
import org.processmining.contexts.uitopia.DummyGlobalContext;
import org.processmining.contexts.uitopia.DummyUIPluginContext;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.astar.petrinet.PetrinetReplayerWithoutILP;
import org.processmining.plugins.connectionfactories.logpetrinet.TransEvClassMapping;
import org.processmining.plugins.petrinet.replayer.algorithms.IPNReplayParameter;
import org.processmining.plugins.petrinet.replayer.algorithms.costbasedcomplete.CostBasedCompleteParam;
import org.processmining.plugins.petrinet.replayresult.PNRepResult;
import org.processmining.plugins.petrinet.replayresult.StepTypes;

/**
 * A.P.
 */
public class Replayer extends AbstractReplayer {
	
	public Replayer() {
		
	}
	
public PQLAlignment getAlignment(PetrinetGraph net,  XLog log)
{
		
	DummyUIPluginContext 		context				= new DummyUIPluginContext(new DummyGlobalContext(), "label");
	XEventClass 				dummyEvClass 		= new XEventClass("DUMMY",99999);
	XEventClassifier 			eventClassifier 	= XLogInfoImpl.NAME_CLASSIFIER;
	Marking						initMarking			= AlignmentAPI.getInitialMarking(net);
	Marking[]					finalMarkings		= AlignmentAPI.getFinalMarkings(net); 
	TransEvClassMapping			mapping				= AlignmentAPI.constructMapping(net, log, dummyEvClass, eventClassifier);
	Map<XEventClass, Integer> 	events2costs 		= AlignmentAPI.constructMOTCostFunction(net, log, eventClassifier, dummyEvClass); 
	Map<Transition, Integer> 	transitions2costs 	= AlignmentAPI.constructMOSCostFunction(net);
	PetrinetReplayerWithoutILP 	replayEngine 		= new PetrinetReplayerWithoutILP();
	IPNReplayParameter parameters = new CostBasedCompleteParam(events2costs,transitions2costs);
	parameters.setInitialMarking(initMarking);
	//parameters.setFinalMarkings(finalMarkings[0]);
	parameters.setGUIMode(false);
    parameters.setCreateConn(false);
    
    PNRepResult 				replayResult 		= null;	
    PQLAlignment 				alignment 			= new PQLAlignment();
            
    try {
    	 replayResult = replayEngine.replayLog( context, net, log, mapping, parameters);
            
     } catch (AStarException e) {
            e.printStackTrace();
     	}
		
   //Double traceFitness = (Double) replayResult.getInfo().get("Trace Fitness");
   //System.out.println(traceFitness);
  
   List<Object> nodes = replayResult.first().getNodeInstance();
   List<StepTypes> steps = replayResult.first().getStepTypes();
   
   for(int i=0; i<steps.size(); i++)
   {
	   switch(steps.get(i))
	   {
	   case LMGOOD: 
		   alignment.addMove(new PQLMove(nodes.get(i).toString(),nodes.get(i).toString()));
		   break;
	   case MREAL:
		   alignment.addMove(new PQLMove(nodes.get(i).toString(),"SKIP_STEP"));
		   break;
	   case MINVI:
		   alignment.addMove(new PQLMove("INV_TRANS","SKIP_STEP"));
		   break;
	   case L:
		   alignment.addMove(new PQLMove("SKIP_STEP", nodes.get(i).toString()));
		   break;
	   }
		   
   }
	
   
   return alignment;
	}

}
