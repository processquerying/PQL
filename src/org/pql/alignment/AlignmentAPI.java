package org.pql.alignment;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
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
import org.pql.core.PQLTrace;
import org.processmining.contexts.uitopia.DummyGlobalContext;
import org.processmining.contexts.uitopia.DummyUIPluginContext;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.models.graphbased.directed.petrinet.PetrinetEdge;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.elements.Place;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.InductiveMiner.mining.MiningParameters;
import org.processmining.plugins.InductiveMiner.mining.MiningParametersIM;
import org.processmining.plugins.InductiveMiner.plugins.IMPetriNet;
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
	protected Map<Transition,T> transition2t = null;
	
	
		public AlignmentAPI(INetSystem<F,N,P,T,M> system)
					throws ClassNotFoundException, SQLException {
		this.sys = system;}
		
		public AlignmentAPI(INetSystem<F,N,P,T,M> system, Map<Transition,T> t2t)
				throws ClassNotFoundException, SQLException {
			this.sys = system;
			this.transition2t = t2t;
			}
	
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
		
		public PetrinetGraph constructPetrinetGraphForInsert(PetrinetGraph net, Map<Transition,T> tmap) {
			
		// places
		Map<P,Place> p2p = new HashMap<>();
		for (P p : sys.getPlaces()) {
			Place pp = net.addPlace(p.getId());
			p2p.put(p,pp);
		}
		
		// transitions
		Map<T,Transition> t2t = new HashMap<>();
		this.transition2t = tmap;
		for (T t : sys.getTransitions()) {
			Transition tt = net.addTransition(t.getLabel()); 
			tt.setInvisible(t.isSilent());
			t2t.put(t,tt);
			this.transition2t.put(tt, t);
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

	public Vector<InsertMove> getInsertMoves(PQLAlignment alignment, PQLTrace trace) 
	{	
		Vector<InsertMove> insertMoves = new Vector<InsertMove>();
		
		boolean prevSkip = false;
		Set<IPlace> marking = null;
		Vector<String> labels = null;
		
		for(int i=0; i<alignment.getAlignment().size(); i++)
		{	
			PQLMove move = alignment.getAlignment().get(i);
			
			if(i == alignment.getAlignment().size()-1 && prevSkip)
			{
				if(move.getMoM().equals("SKIP_STEP")) labels.add(move.getMoL());
				insertMoves.add(new InsertMove(marking,labels,i));
				break;
			}
			
			if(i == alignment.getAlignment().size()-1 && !prevSkip && move.getMoM().equals("SKIP_STEP"))
			{
				labels = new Vector<String>();
				labels.add(move.getMoL());
				marking = new HashSet<IPlace>();
				marking.addAll(getMarking(alignment,i-1,this.sys));
				insertMoves.add(new InsertMove(marking,labels,i));
				break;
			}
		
			if(move.getMoM().equals("SKIP_STEP") && !prevSkip)
			{
				labels = new Vector<String>();
				labels.add(move.getMoL());
				marking = new HashSet<IPlace>();
				
				if(i == 0)
				{
					marking.addAll(this.sys.getMarkedPlaces());
				}
				else
				{
					marking.addAll(getMarking(alignment,i-1,this.sys));
					
				}
				prevSkip = true;
				continue;
			}
			
			if(move.getMoM().equals("SKIP_STEP") && prevSkip)
			{
				labels.add(move.getMoL());
				continue;
			}
			
			if(!move.getMoM().equals("SKIP_STEP") && prevSkip)
			{
				insertMoves.add(new InsertMove(marking,labels,i-1));
				prevSkip = false;
				continue;
			}
				
		}
			
		
		return insertMoves;	
	}
	
	public Vector<SkipMove> getSkipMoves(PQLAlignment alignment, PQLTrace trace, P sourceP, P sinkP) {
			
			
			Vector<SkipMove> skipMoves = new Vector<SkipMove>();
			try{
			
			if(trace.getTrace().size() == 0)
			{
				boolean skipNeeded = false;
				for(int i=0; i<alignment.getAlignment().size(); i++)
				{	
					if(alignment.getAlignment().get(i).getMoL().equals("SKIP_STEP") && !alignment.getAlignment().get(i).getMoM().equals("INV_TRANS"))
					{
						skipNeeded = true;
						break;
					}
				}
				
				if(skipNeeded)
				{
					Set<IPlace> fromPlaces 	= new HashSet<IPlace>();
					Set<IPlace> toPlaces 	= new HashSet<IPlace>();
					fromPlaces.addAll(this.sys.getSourcePlaces());
					toPlaces.addAll(this.sys.getSinkPlaces());
					skipMoves.add(new SkipMove(fromPlaces,toPlaces));
					return skipMoves;
				}
			
			}
			
			int firstSyncMovePosition = -1;
			int lastSyncMovePosition = -1;
			
			// Get first sync move position
			for(int i=0; i<alignment.getAlignment().size(); i++)
			{	
				if(!alignment.getAlignment().get(i).getMoL().equals("SKIP_STEP")) 
				{
					firstSyncMovePosition = i;
					break;
				}
			}
			
			//1. Check if skip is needed from the source place
			if(firstSyncMovePosition > 0 && !trace.getTrace().elementAt(0).isAsterisk())
			{	boolean hasVisibleTransitionBefore = false;
				for(int i=0; i<firstSyncMovePosition; i++)
				{	
					if(!alignment.getAlignment().get(i).getMoM().equals("INV_TRANS"))
					{
						hasVisibleTransitionBefore = true;
						break;
					}
				}
				
				if(hasVisibleTransitionBefore)
				{
					Set<IPlace> fromPlaces 	= new HashSet<IPlace>();
					Set<IPlace> toPlaces 	= new HashSet<IPlace>();
					fromPlaces.add(sourceP);
					toPlaces.addAll(getMarking(alignment,firstSyncMovePosition-1,this.sys));
					
					Set<IPlace> duplicatePlaces = new HashSet<IPlace>();
					for(IPlace p : fromPlaces)
					{
						if (toPlaces.contains(p))
						{duplicatePlaces.add(p);}
					}
					for(IPlace p : duplicatePlaces)
					{
						fromPlaces.remove(p);
						toPlaces.remove(p);
					}
					if(!fromPlaces.isEmpty())
					skipMoves.add(new SkipMove(fromPlaces,toPlaces));
				}
			}
			
			//get lastSyncMovePosition
			if(firstSyncMovePosition > -1)
			{
				for(int i=alignment.getAlignment().size()-1; i>=firstSyncMovePosition; i--)
				{	
					if(!alignment.getAlignment().get(i).getMoL().equals("SKIP_STEP"))
					{
					lastSyncMovePosition = i;
					break;
					}
				}	
			}
			
			//2. Check if skip is needed to the sink place 
			if(lastSyncMovePosition > -1 && lastSyncMovePosition < alignment.getAlignment().size()-1 && !trace.getTrace().elementAt(trace.getTrace().size()-1).isAsterisk())
			{	boolean hasVisibleTransitionAfter = false;
				for(int i=lastSyncMovePosition+1; i<alignment.getAlignment().size(); i++)
				{	
					if(!alignment.getAlignment().get(i).getMoM().equals("INV_TRANS"))
					{
						hasVisibleTransitionAfter = true;
						break;
					}
				}
				
				if(hasVisibleTransitionAfter)
				{
					
					Set<IPlace> fromPlaces 	= new HashSet<IPlace>();
					Set<IPlace> toPlaces 	= new HashSet<IPlace>();
					fromPlaces.addAll(getMarking(alignment,lastSyncMovePosition,this.sys));
					toPlaces.add(sinkP);
					Set<IPlace> duplicatePlaces = new HashSet<IPlace>();
					for(IPlace p : fromPlaces)
					{if (toPlaces.contains(p))
						{duplicatePlaces.add(p);}
					}
					for(IPlace p : duplicatePlaces)
					{
						fromPlaces.remove(p);
						toPlaces.remove(p);
					}
					if(!fromPlaces.isEmpty())
					skipMoves.add(new SkipMove(fromPlaces,toPlaces));
				}
			}
			
			//3. check if skips are needed between first and last sync moves
			if((lastSyncMovePosition > (firstSyncMovePosition + 1))) 
			{
				boolean skipNeeded = false;
				int prevSyncMovePosition = firstSyncMovePosition;
				Set<IPlace> fromPlaces 	= null;
				Set<IPlace> toPlaces 	= null;
				
				for(int i=firstSyncMovePosition+1; i<=lastSyncMovePosition; i++)
				{
					
					PQLMove move = alignment.getAlignment().get(i);
					
					//last move
					if(i == lastSyncMovePosition && skipNeeded) 
					{
						toPlaces = new HashSet<IPlace>();
						toPlaces.addAll(getMarking(alignment,i-1,this.sys));
						Set<IPlace> duplicatePlaces = new HashSet<IPlace>();
						for(IPlace p : fromPlaces)
						{if (toPlaces.contains(p))
							{duplicatePlaces.add(p);}
						}
						for(IPlace p : duplicatePlaces)
						{
							fromPlaces.remove(p);
							toPlaces.remove(p);
						}
						if(!fromPlaces.isEmpty())
						skipMoves.add(new SkipMove(fromPlaces,toPlaces));
						
						break;						
					}
					
					if(i == lastSyncMovePosition && !skipNeeded) 
					{break;}
		
					//sync move
					if(!move.getMoL().equals("SKIP_STEP"))
					{
						if(skipNeeded)
						{
							toPlaces = new HashSet<IPlace>();
							toPlaces.addAll(getMarking(alignment,i-1,this.sys));
							Set<IPlace> duplicatePlaces = new HashSet<IPlace>();
							for(IPlace p : fromPlaces)
							{if (toPlaces.contains(p))
								{duplicatePlaces.add(p);}
							}
							for(IPlace p : duplicatePlaces)
							{
								fromPlaces.remove(p);
								toPlaces.remove(p);
							}
							if(!fromPlaces.isEmpty())
							skipMoves.add(new SkipMove(fromPlaces,toPlaces));
						}
						
						prevSyncMovePosition = i; 
						skipNeeded = false;
						continue;
					}
					
					// move on model - observable transition - no prev. seq. skips
					if(move.getMoL().equals("SKIP_STEP") && !move.getMoM().equals("INV_TRANS") && !skipNeeded)
					{
						//check if there is asterisk in trace
						String prevSyncLabel = alignment.getAlignment().get(prevSyncMovePosition).getMoL();
						int prevSyncLabelCount = 0;
						
						//get index of prevSyncLabel in alignment
						if(prevSyncMovePosition>0)
						{
							for(int j=prevSyncMovePosition-1; j>=0; j--)
							{
								if(alignment.getAlignment().get(j).getMoL().equals(prevSyncLabel)) 
								prevSyncLabelCount++;
							}	
						}	
						
						//find this label in trace
						int index = 0;
						int positionInTrace = 0;
						for(int z=0; z<trace.getTrace().size(); z++)
						{
							if(trace.getTrace().elementAt(z).getLabel().equals(prevSyncLabel))
							{
								if(index == prevSyncLabelCount)
								{
									positionInTrace = z; break;
								}else
								{
									index++;
								}
							}
						}
							
						if(positionInTrace < trace.getTrace().size()-1)
						{
							if(!trace.getTrace().elementAt(positionInTrace+1).isAsterisk())
							{
								skipNeeded = true; 
								fromPlaces = new HashSet<IPlace>();
								fromPlaces.addAll(getMarking(alignment,prevSyncMovePosition,this.sys));
							}
						}else //it is last element in trace - not *
						{
							skipNeeded = true; 
							fromPlaces = new HashSet<IPlace>();
							fromPlaces.addAll(getMarking(alignment,prevSyncMovePosition,this.sys));
						}
					}
					
					//(if move is invisible transition OR 
					//if it is move on model - observable transition)
					//AND skipNeeded is true
					//do nothing
				}
			}
			}catch(Exception e){alignment.print(); trace.print(); }
			return skipMoves;
		}
	
	@SuppressWarnings("unchecked")
	private Set<P> getMarking(PQLAlignment alignment, int j, INetSystem<F,N,P,T,M> system)
		{
			system.loadNaturalMarking();
			Set<P> marking = null;
				
			for(int i=0; i<=j; i++)
			{
				PQLMove move = alignment.getAlignment().get(i);
				T t = null;
				
				if(!move.getMoM().equals("SKIP_STEP"))
				t = (T) move.getT();
				
				if(t != null)
				{
				system.fire(t);
				}
			}
				
			marking = system.getMarkedPlaces();
		
			return marking;

		}

	@SuppressWarnings("unchecked")
	public void addSkips(Vector<SkipMove> skipMoves) {
			for(SkipMove sm: skipMoves)
			{
				T t = this.sys.createTransition(); 
				t.setName("");
				t.setLabel("");
				
				Set<IPlace> pp = sm.getSourcePlaces();
				for(IPlace p : pp)
				this.sys.addFlow((P) p,t);
				
				pp = sm.getSinkPlaces();
				for(IPlace p : pp)
				this.sys.addFlow(t,(P) p);
				
			}
			
		}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInsertMoves(Vector<InsertMove> insertMoves, PQLAlignment alignment) {
			
		//get minimum Hitting set
		Vector markings = new Vector();
		for(int i=0; i<insertMoves.size(); i++)
		markings.add(insertMoves.elementAt(i).getMarking());
		HittingSets hs = new HittingSets(markings);
		Vector minHittingSet = hs.getMinSet();
			
		Map<P,Vector<Vector<String>>> insertMap = new HashMap<>();
		
		for(int i=0; i<insertMoves.size(); i++)
		{
			InsertMove move = insertMoves.elementAt(i);
			Vector<String> labels = move.getLabels();
			
			for(int j=0; j<move.getMarking().size(); j++)
			{
				if(insertMap.containsKey(move.getMarking().elementAt(j)))
				{
					insertMap.get((P) move.getMarking().elementAt(j)).add(labels);
					break;
				}else
				{
					if(minHittingSet.contains(move.getMarking().elementAt(j)))
					{
						
						insertMap.put((P) move.getMarking().elementAt(j), new Vector<Vector<String>>());
						insertMap.get((P) move.getMarking().elementAt(j)).add(labels);
						break;
					}
				}	
			}
		}
		
			
		//mining new behaviour
		for(P p : insertMap.keySet())
		{
			PluginContext context = new DummyUIPluginContext(new DummyGlobalContext(), "label");
			MiningParameters parameters = new MiningParametersIM();
			parameters.setNoiseThreshold((float) 0.0);
			
			XLog log = createLog(insertMap.get(p));
			
			Object[] miningResult = IMPetriNet.minePetriNet(context, log, parameters);
			Petrinet minedNet = (Petrinet) miningResult[0];
			Marking initialMarking = (Marking) miningResult[1];
			
			try {
			repairNetSystem(p, minedNet, initialMarking, alignment);
			//IOUtils.invokeDOT("./pics", p.getLabel()+"-mined.png", minedNS.toDOT());
			} catch (SQLException e) {e.printStackTrace();} 
				
		}
			
	
}
	
	@SuppressWarnings("rawtypes")
	public void repairNetSystem(P netP, Petrinet petrinet, Marking marking, PQLAlignment alignment) throws SQLException {
			
			INetSystem<F,N,P,T,M> net = this.sys;
			P sourcePlace = (P) net.getSourcePlaces().iterator().next();
			P sinkPlace = (P) net.getSinkPlaces().iterator().next();
			
			// places, 
			Map<Place,P> p2p = new HashMap<>();
			for (Place pp : petrinet.getPlaces()) {
				
				if(!marking.contains(pp) && (!petrinet.getOutEdges(pp).isEmpty())) 
				{	
					P p = (P) net.createPlace();
					p.setName("p"+p.hashCode());
					p2p.put(pp,p);
				}else //source and sink place
				{
					p2p.put(pp,netP);
				}
			}
					
	
			// transitions
			Map<Transition,T> tt2t = new HashMap<>();
			for (Transition tt : petrinet.getTransitions()) {
				T t = (T) net.createTransition(); 
				
				if(tt.isInvisible())
				{
				t.setLabel(""); t.setName("");}
				else
				{t.setLabel(tt.getLabel());t.setName(tt.getLabel());}
				
				tt2t.put(tt,t);
				
				}
					
			// flow
			for (PetrinetEdge edge : petrinet.getEdges()) {
				if (edge.getSource() instanceof Place && edge.getTarget() instanceof Transition){
					net.addFlow(p2p.get(edge.getSource()), tt2t.get(edge.getTarget()));
					} else {
					net.addFlow(tt2t.get(edge.getSource()), p2p.get(edge.getTarget()));
								}
					}
			
			//adding new source and sink places if we added behaviour to them
			if(sourcePlace.equals(netP))
			{
				P p = (P) net.createPlace();
				p.setName("p"+p.hashCode());
				T t = (T) net.createTransition(); 
				t.setLabel("");	
				t.setName("");
				net.addFlow(p, t);
				net.addFlow(t, netP);
				alignment.addMoveToPosition(new PQLMove("INV_TRANS","SKIP_STEP",null,t),0);
				
			}
			if(sinkPlace.equals(netP))
			{
				P p = (P) net.createPlace();
				p.setName("p"+p.hashCode());
				T t = (T) net.createTransition(); 
				t.setLabel("");	
				t.setName("");
				net.addFlow(netP, t);
				net.addFlow(t, p);
				alignment.addMove(new PQLMove("INV_TRANS","SKIP_STEP",null,t));

			}
				
		}
	
	public XLog createLog(Vector<Vector<String>> traces){
			
			XFactoryNaiveImpl factory = new XFactoryNaiveImpl(); 
			
			XLog log = factory.createLog();
			
			for(int j=0; j<traces.size(); j++)
			{
				XTrace logTrace = factory.createTrace();
				log.add(logTrace);
				Vector<String> trace = traces.elementAt(j);
				for(int i=0; i<trace.size(); i++)
				{
					String taskLabel = trace.elementAt(i);
					XEvent event = factory.createEvent();
					event.getAttributes().put("concept:name",new XAttributeLiteralImpl("concept:name",taskLabel));
					logTrace.add(event);
				}	
			}
			
			return log;		
			
		};
		
	public void updateInsertMovesInAlignment(PQLAlignment alignment) {
		
	for(int i=0; i<alignment.getAlignment().size(); i++)
	{
		if(alignment.getAlignment().get(i).getMoM().equals("SKIP_STEP"))
		{
			alignment.getAlignment().get(i).setMoM(alignment.getAlignment().get(i).getMoL());
		}
	}
			
	}
	
	public PQLAlignment transformAlignment(PQLAlignment alignment, Map<Transition,T> transition2t, Map<T,Set<T>> UT2T, Map<T,T> clonedT2T)	
	{
		PQLAlignment newAlignment = new PQLAlignment();
		this.sys.loadNaturalMarking();
		
	for(int i=0; i<alignment.getAlignment().size(); i++)
	{
		PQLMove move = alignment.getAlignment().get(i);
		
		if(move.getMoM().equals("SKIP_STEP")) //adding move on log, transitions are null
		{
			newAlignment.addMove(new PQLMove(move.getMoM(), move.getMoL()));
		}
		else
		{
			Transition transition = move.getMoMT();
			T unifiedT = transition2t.get(transition);
			T t = clonedT2T.get(unifiedT);
			
			if(t != null) // label unification was not performed on t
			{
				newAlignment.addMove(new PQLMove(move.getMoM(), move.getMoL(), move.getMoMT(), t));
				this.sys.fire(t);
				
			}else
			{
				if(unifiedT.isObservable()) //unified transition
				{
					
					Set<T> enabledTinNetSystem = this.sys.getEnabledTransitions();
					Set<T> clonedT = UT2T.get(unifiedT);
						
					Set<T> tInNS = new HashSet<T>();
					for(T ct : clonedT)
					tInNS.add(clonedT2T.get(ct));
					
					for(T et : enabledTinNetSystem)
					{if(tInNS.contains(et))
					{
						String MoM 	= move.getMoM();
						String MoL 	= move.getMoL();	
						newAlignment.addMove(new PQLMove(MoM, MoL, null, et));
						this.sys.fire(et);
						break;
					}
					}
						
				}
			}	
		}	
	}
		
	this.sys.loadNaturalMarking();	
	return newAlignment;
}

}
