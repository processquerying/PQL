package org.pql.alignment;

import org.jbpt.petri.ITransition;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;

/**
 * A.P.
 */
public class PQLMove{
	
	private String moveOnModel = null;
	private Transition moveOnModelTransition = null;
	private String moveOnLog = null;
	private ITransition T = null;
	
	public PQLMove() {}
	
	public PQLMove(String MoM, String MoL) 
	{
		this.moveOnModel = MoM;
		this.moveOnLog = MoL;
	}
	
	public PQLMove(String MoM, String MoL, Transition MoMT) 
	{
		this.moveOnModel = MoM;
		this.moveOnLog = MoL;
		this.moveOnModelTransition = MoMT;
	}

	public PQLMove(String MoM, String MoL, Transition MoMT, ITransition t) 
	{
		this.moveOnModel = MoM;
		this.moveOnLog = MoL;
		this.moveOnModelTransition = MoMT;
		this.T = t;
	}

	
	public void setMoM( String MoM){
		this.moveOnModel = MoM;
	};
	
	public void setMoMT(Transition MoMT){
		this.moveOnModelTransition = MoMT;
	};
	
	public void setMoL( String MoL){
		this.moveOnLog = MoL;
	};
	
	public String getMoM(){
		return this.moveOnModel;
	};
	
	public Transition getMoMT(){
		return this.moveOnModelTransition;
	};
	
	public String getMoL(){
		return this.moveOnLog;
	};
	
	public void setT( ITransition t){
		this.T = t;
	};
	
	public ITransition getT(){
		return this.T;
	};
	

	
}
