package org.pql.alignment;

import java.util.List;
import java.util.Map;

import nl.tue.astar.AStarException;

import org.deckfour.xes.classification.XEventClass;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.info.impl.XLogInfoImpl;
import org.deckfour.xes.model.XLog;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
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
public class Replayer<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
	extends AbstractReplayer {
	
	private AlignmentAPI<F,N,P,T,M> api = null;
	
	public Replayer(AlignmentAPI<F,N,P,T,M> api) {
		this.api = api;
	}
	
public PQLAlignment getAlignment(PetrinetGraph net,  XLog log)
{
		
	DummyUIPluginContext 		context				= new DummyUIPluginContext(new DummyGlobalContext(), "label");
	XEventClass 				dummyEvClass 		= new XEventClass("DUMMY",99999);
	XEventClassifier 			eventClassifier 	= XLogInfoImpl.NAME_CLASSIFIER;
	Marking						initMarking			= api.getInitialMarking(net);
	TransEvClassMapping			mapping				= api.constructMapping(net, log, dummyEvClass, eventClassifier);
	Map<XEventClass, Integer> 	events2costs 		= api.constructMOTCostFunction(net, log, eventClassifier, dummyEvClass); 
	Map<Transition, Integer> 	transitions2costs 	= api.constructMOSCostFunction(net);
	PetrinetReplayerWithoutILP 	replayEngine 		= new PetrinetReplayerWithoutILP();
	IPNReplayParameter parameters = new CostBasedCompleteParam(events2costs,transitions2costs);
	parameters.setInitialMarking(initMarking);
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
	default:
		break;
	   }
		   
   }
	
   
   return alignment;
	}


public PQLAlignment getAlignmentWithAsterisk(PetrinetGraph net,  XLog log)
{
	
DummyUIPluginContext 		context				= new DummyUIPluginContext(new DummyGlobalContext(), "label");
XEventClass 				dummyEvClass 		= new XEventClass("DUMMY",99999);
XEventClassifier 			eventClassifier 	= XLogInfoImpl.NAME_CLASSIFIER;
Marking						initMarking			= api.getInitialMarking(net);
TransEvClassMapping			mapping				= api.constructMapping(net, log, dummyEvClass, eventClassifier);
Map<XEventClass, Integer> 	events2costs 		= api.constructMOTCostFunctionForAsterisk(net, log, eventClassifier, dummyEvClass); 
Map<Transition, Integer> 	transitions2costs 	= api.constructMOSCostFunctionForAsterisk(net);
PetrinetReplayerWithoutILP 	replayEngine 		= new PetrinetReplayerWithoutILP();
IPNReplayParameter parameters = new CostBasedCompleteParam(events2costs,transitions2costs);
parameters.setInitialMarking(initMarking);
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
//System.out.println("traceFitness: "+traceFitness);

List<Object> nodes = replayResult.first().getNodeInstance();
List<StepTypes> steps = replayResult.first().getStepTypes();

/*System.out.println("initMarking: "+initMarking);
System.out.println("places: "+net.getPlaces());
System.out.println("mapping: "+mapping);
System.out.println("events2costs: "+events2costs);
System.out.println("transitions2costs: "+transitions2costs);
System.out.println("nodes: "+nodes);
System.out.println("steps: "+steps);
*/

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
	default:
		break;
   }
	   
}

return alignment;
}


}
