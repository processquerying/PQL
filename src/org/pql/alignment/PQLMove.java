package org.pql.alignment;

/**
 * A.P.
 */
public class PQLMove {
	
	private String moveOnModel = null;
	private String moveOnLog = null;
	
	public PQLMove() {}
	
	public PQLMove(String MoM, String MoL) 
	{
		this.moveOnModel = MoM;
		this.moveOnLog = MoL;
	}
	
	public void setMoM( String MoM){
		this.moveOnModel = MoM;
	};
	
	public void setMoL( String MoL){
		this.moveOnLog = MoL;
	};
	
	public String getMoM(){
		return this.moveOnModel;
	};
	
	public String getMoL(){
		return this.moveOnLog;
	};
	
}
