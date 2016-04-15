package org.pql.petri;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.pql.core.PQLTrace;

/** 
 * @author A.P.
 */
public class AbstractTraceExecutionWithWildcardCharactersTesterTransformation <F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
				extends AbstractNetSystemTransformation<F,N,P,T,M> 
				{

	
	public AbstractTraceExecutionWithWildcardCharactersTesterTransformation(INetSystem<F, N, P, T, M> sys) {
		super(sys);
		}

		
	@Override
	public NetSystemTransformationType getType() {
		return NetSystemTransformationType.WILDCARD_CHARACTERS;
	}
	
	@Override
	protected void applyTransormation() {
	}


	public void addStartEnd(PQLTrace trace) {
		
		//add i
		Set<P> sourcePlaces = sys.getSourcePlaces();
		
		if(!sourcePlaces.isEmpty())
		{
			P i = sys.createPlace();
			i.setName("p"+Math.abs(i.hashCode()));
			
			T ti = sys.createTransition();
			ti.setLabel(trace.getTrace().elementAt(0).getLabel());
			ti.setName(trace.getTrace().elementAt(0).getLabel());
		
			sys.addFlow(i, ti);
							
			for (P p : sourcePlaces) 
			{
				sys.addFlow(ti,p);
			}
			
			sys.getMarking().clear();
			sys.getMarking().put(i, 1);
			
		}
		
		//add o
		Set<P> sinkPlaces = sys.getSinkPlaces();
		if(!sinkPlaces.isEmpty())
		{
			P o = sys.createPlace();
			o.setName("p"+Math.abs(o.hashCode()));
			
			T to = sys.createTransition();
			to.setLabel(trace.getTrace().elementAt(trace.getTrace().size()-1).getLabel());
			to.setName(trace.getTrace().elementAt(trace.getTrace().size()-1).getLabel());
			
			sys.addFlow(to,o);
							
			for (P p : sinkPlaces) 
			{
				sys.addFlow(p,to);
			}
			
		}
		
		
	}


	public void applyWildcardTransformation(PQLTrace trace, Map<String, ITransition> transitionMap) {
		
		Map<Vector<String>,String> replacementMap = new HashMap<Vector<String>,String>();
		replacementMap = trace.getReplacementMap();
		
		//create control place
		P controlPlace = sys.createPlace();
		controlPlace.setName("ControlPlace");
		
		//add double arcs to all visible transitions
		Set<T> trans = sys.getTransitions();
		Iterator<T> it0 = trans.iterator();
		while (it0.hasNext())
		{
			T tr = it0.next();
		    if(!tr.isSilent())
			{
		    sys.addFlow(controlPlace, tr);
		    sys.addFlow(tr, controlPlace);
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
		Vector<T> seqTransitions = new Vector<T>();
		for(int i=0; i<sequence.size(); i++)
		{
			T tr = sys.createTransition();
			tr.setName("t"+Math.abs(tr.hashCode()));
			seqTransitions.add(tr);
			seqTransitions.elementAt(i).setLabel(sequence.elementAt(i)+"-new");
					
		}
		T trrep = sys.createTransition();
		trrep.setName("t"+Math.abs(trrep.hashCode()));
		
		seqTransitions.add(trrep);
		seqTransitions.elementAt(seqTransitions.size()-1).setLabel(replacement);
		
		//add arc from control place to the first element in the sequence
		sys.addFlow(controlPlace, seqTransitions.elementAt(0));
			
		//create places and flows between new transitions
		for(int i=0; i<seqTransitions.size()-1;i++)
		{
			P tp = sys.createPlace();
			tp.setName("p"+Math.abs(tp.hashCode()));
			sys.addFlow(seqTransitions.elementAt(i),tp);
			sys.addFlow(tp,seqTransitions.elementAt(i+1));
			
			org.jbpt.petri.Transition curT = null;
				
			for (Map.Entry<String, ITransition> entry : transitionMap.entrySet())
			{
			    if(entry.getKey().equals(sequence.elementAt(i)))
			    {curT = (org.jbpt.petri.Transition) entry.getValue();
			    }
			}
			
			Iterator<T> it = sys.getTransitions().iterator();
			
			while(it.hasNext())
			{
				T t = it.next();
				if (curT == t)
				{
					for (P x : sys.getPreset(t)) 
						{if(!x.equals(controlPlace)){sys.addFlow(x,seqTransitions.elementAt(i));}}
					for (P x : sys.getPostset(t)) 
						{if(!x.equals(controlPlace)){sys.addFlow(seqTransitions.elementAt(i),x);}}
				}
				
			}
		
		}
		
		//add arc from the last element in the sequence to the control place
		sys.addFlow(seqTransitions.elementAt(seqTransitions.size()-1),controlPlace);
		
		}
	}
	

}
