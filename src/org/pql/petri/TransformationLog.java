package org.pql.petri;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;

/**
 * @author Artem Polyvyanyy
 */
public class TransformationLog<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
			extends ArrayList<Set<INetSystemTransformation<F,N,P,T,M>>> {

	private static final long serialVersionUID = 8409240433082320507L;

	public TransformationLog() {
		super();
	}

	public TransformationLog(
			Collection<? extends Set<INetSystemTransformation<F,N,P,T,M>>> c) {
		super(c);
	}

	public boolean add(INetSystemTransformation<F,N,P,T,M> e) {
		Set<INetSystemTransformation<F,N,P,T,M>> s = new HashSet<INetSystemTransformation<F,N,P,T,M>>();
		s.add(e);
		return super.add(s);
	}
	
	public boolean add(INetSystemTransformation<F,N,P,T,M> e1, INetSystemTransformation<F,N,P,T,M> e2) {
		Set<INetSystemTransformation<F,N,P,T,M>> s = new HashSet<INetSystemTransformation<F,N,P,T,M>>();
		s.add(e1);
		s.add(e2);
		return super.add(s);
	}
}
