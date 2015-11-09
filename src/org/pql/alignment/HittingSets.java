
package org.pql.alignment;
import java.util.Vector;




/**
* This class implements a collection of hitting sets.
* Hitting Sets uses conflict sets to calculate the 
* diagnosis candidates.
*
* @author Sujeevan ASEERVATHAM
* @version %I%, %G%
*/
public class HittingSets
{

	/** 
	* The conflict sets from which the hitting sets are calculated.
	*/
	protected Vector conflicts;
	/**The hitting sets of the conflict.*/
	protected Vector hittingSets;




	/**
	* Creates hitting sets from system conflicts.
	*
	* @param conflicts The conflict sets.
	*/
	public HittingSets(Vector conflicts)
	{
		this.conflicts=conflicts;
		
		hittingSets=new Vector();
		calculateHittingSets(this.conflicts, hittingSets);
	}


	/**
	* Method used to get the hitting sets.
	*
	* @return The hitting sets.
	*/
	public Vector getSets()
	{
		return hittingSets;
	}

	//A.P.
	public Vector getMinSet()
	{
		Vector minHittingSet = null;
		
		for(int i=0; i<hittingSets.size(); i++)
		{
			if(minHittingSet == null) 
			{
				minHittingSet = (Vector) hittingSets.elementAt(i);
			} 
			else
			{
				if(minHittingSet.size() > ((Vector) hittingSets.elementAt(i)).size())
				{
					minHittingSet = (Vector) hittingSets.elementAt(i);
				}
			}	
		}	
		
		return minHittingSet;
	}





	/**
	* Calculates the hitting sets from a set of conflicts.
	* This method implements a Boolean Algebraic algorithm to 
	* calculate the hitting sets.
	*
	* @param cs  The conflict sets.
	* @param hs  The vector in which the hitting sets should be stored.
	*/
	protected void calculateHittingSets(Vector cs, Vector hs)
	{
		Vector tmpVector;
		Vector common;
	
		//no conflict means empty hitting set
		if((cs==null)||(cs.size()==0))
			return;
		
		
		//-----------------------------------------
		// case 1:
		// H(~e) -> e
		
		tmpVector=(Vector)cs.get(0);
		if((cs.size()==1) && (tmpVector.size()==1))
		{
			hs.add(new Vector(tmpVector));
			return;
		}
			
		
		//-----------------------------------------
		// case 2:
		// H(~e.C) -> e + H(C)
		
		//first get the common atom
		common=null;
		for(int i=0; i<cs.size(); i++)
		{
			tmpVector=(Vector)cs.get(i);
			if(tmpVector.size()!=0)
			{
				if(common==null)
					common=new Vector(tmpVector);
				else
				{
					common.retainAll(tmpVector);
					if(common.size()==0)
						break;
				}
			}
		}
			
		if(!common.isEmpty())
		{
			//common atom found
			
			Vector newSet=new Vector(cs.size());
			for(int i=0; i<cs.size(); i++)
			{
				Vector v=new Vector((Vector)cs.get(i));
				v.removeAll(common);
				if(!v.isEmpty())
					newSet.add(v);
			}
			
			for(int i=0; i<common.size(); i++)
			{
				Vector v=new Vector(1);
				v.add(common.get(i));
				hs.add(v);
			}
			
			calculateHittingSets(newSet, hs);
			return;
		}

		
		
		//-----------------------------------------
		// case 3:		
		//H(~e+C)=e.H(C)
		
		//first get the isolated atom
		common=new Vector();
		for(int i=0; i< cs.size(); i++)
		{
			Vector v=(Vector)cs.get(i);
			if(v.size()==1)
				common.add(v.get(0));
		}
		
		if(!common.isEmpty())
		{
			//an isolated atom was found
			//remove super-set
			Vector newSet=new Vector(cs.size());
			for(int i=0; i<cs.size(); i++)
			{
				Vector v=new Vector((Vector)cs.get(i));
				if( (!v.isEmpty()) && (!v.removeAll(common)) )
					newSet.add(v);
			}
							
			//recursive call
			int n=hs.size();
			calculateHittingSets(newSet, hs);
			
			//append the isolated atom
			if(n==hs.size())
				hs.add(common);
			else
			{
				for(int i=n; i<hs.size(); i++)
				{
					((Vector)hs.get(i)).addAll(0,common);
				}
			}
									
			return;
		}
		
		
		//-----------------------------------------
		// case 4:		
		//H(C)=e.H(C1)+H(C2)
		
		//get a random (the first one, here) atom
		Object val=null;
		for(int i=0; i<cs.size(); i++)
		{
			tmpVector=(Vector)cs.get(i);
			if(!tmpVector.isEmpty())
			{
				val=tmpVector.get(0);
				break;
			}
		}
		
		if(val!=null)
		{
			//there was at least one atom
			//calculate C1 and C2
			Vector c1=new Vector(cs.size());
			Vector c2=new Vector(cs.size());
			
			for(int i=0; i<cs.size(); i++)
			{
				Vector v=(Vector)cs.get(i);
				if(!v.isEmpty())
				{
					Vector v2=new Vector(v);
					if(v2.contains(val))
						v2.remove(val);
					else
						c1.add(new Vector(v));
						
					//don't duplicate set
					boolean found=false;
					for(int j=0; (j<c2.size())&&(!found); j++)
					{
						Vector v3=(Vector)c2.get(j);
						if(v2.containsAll(v3))
						{
							//v2 is a super-set of v3
							//so don't add it
							found=true;
						}
						else if(v3.contains(v2))
						{
							//v2 is a subset of v3
							//so replace v3 by v2
							c2.setElementAt(v2,j);
							found=true;
						} 
					}
					if(!found)
						c2.add(v2);
				}
			}
			
			//make recursive call for C1 and C2
			int n0=hs.size();
			calculateHittingSets(c1, hs);
			int n1=hs.size();
			calculateHittingSets(c2, hs);
			
			for(int i=n0; i<n1; i++)
				((Vector)hs.get(i)).add(0,val);

			return;
		}
	}


}

