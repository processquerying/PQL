package org.pql.petri;

import java.util.Map;
import java.util.Set;
import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;

/**
 * @author Artem Polyvyanyy
 */
public interface ILabelUnificationTransformation<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
					extends INetSystemTransformation<F,N,P,T,M> {
	
	public T getUnifiedTransition();
	
	public Set<String> getLabels();
	
	public Map<T, Set<T>> getUT2T(); //A.P.

}
