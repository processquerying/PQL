package org.pql.alignment;

import java.util.ArrayList;
import java.util.List;

import org.jbpt.petri.ITransition;


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
	
	public void addMoveToPosition(PQLMove move, int position){
		
		this.alignment.add(position,move);
		
	};
	
	public void removeMove(int position){
		
		this.alignment.remove(position);
		
	};
	
	public List<PQLMove> getAlignment(){
		return this.alignment;
	};
	
	public void print(){
		for(int i=0; i<this.alignment.size(); i++)
		{System.out.println(alignment.get(i).getMoM() + " - " + alignment.get(i).getMoL() + " - " + alignment.get(i).getMoMT() + " - " + alignment.get(i).getT());}

	}
	
	
	public int getInsertAlignmentCost()
	{
		int result = 0;
		
		for(int i=0; i<this.alignment.size(); i++)
		{
			if(alignment.get(i).getMoM().equals("SKIP_STEP"))
			{result++;}
			
		}
		
		return result;
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

	
	public int getAlignmentCostForAsterisk()
	{
		int result = 0;
		
		for(int i=0; i<this.alignment.size(); i++)
		{
			if(alignment.get(i).getMoM().equals("SKIP_STEP"))
			{result++;}
		}
		
		return result;
	}
	
	public int getIndex(ITransition t)
	{
		int result = -1;
		
		for(int i=0; i<alignment.size(); i++)
		{
			if(alignment.get(i).getT() != null)
			{
				if(alignment.get(i).getT().equals(t)) {result = i; break;}
			}
		}
		
		return result;
	}
	
	public int getUTIndex(ITransition t2, ITransition ut)
	{
		int result = -1;
		
		for(int i=getIndex(t2)-1; i>0; i--)
		{
			if(alignment.get(i).getT() != null)
			{
				if(alignment.get(i).getT().equals(ut)) {result = i; break;}
			}
		}
		
		return result;
	}

	
}
