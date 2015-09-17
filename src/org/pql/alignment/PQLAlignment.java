package org.pql.alignment;

import java.util.ArrayList;
import java.util.List;

/**
 * A.P.
 */
public class PQLAlignment {
	
	private List<PQLMove> alignment = null; 
	
	public PQLAlignment() {
		
		alignment = new ArrayList<PQLMove>(); 
	}
	
	public void addMove(PQLMove move){
		
		this.alignment.add(move);
		
	};
	
	public List<PQLMove> getAlignment(){
		return this.alignment;
	};
	
	public void print(){
		for(int i=0; i<this.alignment.size(); i++)
		{System.out.println(alignment.get(i).getMoM() + " - " + alignment.get(i).getMoL());}
	}
	
	public int getAlignmentCost()
	{
		int result = 0;
		
		for(int i=0; i<this.alignment.size(); i++)
		{
			if(alignment.get(i).getMoM().equals("SKIP_STEP"))
			{result++;}
			else {if(!(alignment.get(i).getMoM().equals("INV_TRANS")) && alignment.get(i).getMoL().equals("SKIP_STEP"))
			{result++;}}
		}
		
		return result;
	}
}
