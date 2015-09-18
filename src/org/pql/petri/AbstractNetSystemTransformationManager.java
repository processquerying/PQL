package org.pql.petri;

import java.util.HashSet;
import java.util.Set;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;

/**
 * @author Artem Polyvyanyy
 */
public class AbstractNetSystemTransformationManager<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> {

	private TransformationLog<F,N,P,T,M> history = null;
	
	private INetSystem<F,N,P,T,M> sys = null;
	
	public AbstractNetSystemTransformationManager(INetSystem<F,N,P,T,M> sys) {
		this.sys = sys;
		
		this.history = new TransformationLog<F,N,P,T,M>();
	}
	
	public TransformationLog<F,N,P,T,M> getTransformationHistory() {
		return new TransformationLog<F,N,P,T,M>(this.history);
	}
	
	public INetSystem<F,N,P,T,M> getNetSystem() {
		return this.sys;
	}
	
	public void transform(TransformationLog<F,N,P,T,M> log) {
		// revert history tail
		if (this.history.size()>log.size()) {
			for (int i=this.history.size()-1; i>=log.size(); i--)
				for (INetSystemTransformation<F,N,P,T,M> tr : this.history.get(i))
					tr.revert();
			
			this.history.subList(log.size(),this.history.size()).clear();
		}
		
		int historySize = this.history.size();
		
		// fix history
		for (int i=0; i<log.size(); i++) {
			if (i<historySize) {
				Set<INetSystemTransformation<F,N,P,T,M>> trsOld = this.history.get(i);
				Set<INetSystemTransformation<F,N,P,T,M>> trsNew = log.get(i);
				
				if (trsOld.equals(trsNew)) {
					for (INetSystemTransformation<F,N,P,T,M> trNew : trsNew) {
						for (INetSystemTransformation<F,N,P,T,M> trOld : trsOld) {
							if (trOld.equals(trNew)) {
								trNew.substitute(trOld);
								break;
							}
						}
					}
					
					this.history.set(i,trsNew);
					continue;
				}
				else {
					Set<INetSystemTransformation<F,N,P,T,M>> trsNewOld = new HashSet<INetSystemTransformation<F,N,P,T,M>>(trsNew);
					trsNewOld.retainAll(trsOld);
					
					// revert history tail
					for (int j=historySize-1; j>i; j--)
						for (INetSystemTransformation<F,N,P,T,M> tr : this.history.get(j))
							tr.revert();
					
					// fix current log step
					for (INetSystemTransformation<F,N,P,T,M> tr : trsOld) 
						if (!trsNewOld.contains(tr)) tr.revert();
					
					for (INetSystemTransformation<F,N,P,T,M> tr : trsNew) {
						if (trsNewOld.contains(tr)) {
							for (INetSystemTransformation<F,N,P,T,M> trOld : trsOld) {
								if (trOld.equals(tr)) {
									tr.substitute(trOld);
									break;
								}
							}
						}
						else
							tr.apply();
					}
					
					// update history step
					this.history.set(i,trsNew);
					
					// remove history tail
					this.history.subList(i+1, this.history.size()).clear();
					
					historySize = i;
				}
			}
			else {
				for (INetSystemTransformation<F,N,P,T,M> tr : log.get(i)) 
					tr.apply();
				
				this.history.add(log.get(i));
			}
		}
	}	
}
