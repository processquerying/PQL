package org.pql.mc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jbpt.petri.IFlow;
import org.jbpt.petri.IMarking;
import org.jbpt.petri.INetSystem;
import org.jbpt.petri.INode;
import org.jbpt.petri.IPlace;
import org.jbpt.petri.ITransition;
import org.jbpt.petri.structure.PetriNetStructuralChecks;

/**
 * @author Artem Polyvyanyy
 */
public class AbstractLoLAModelChecker<F extends IFlow<N>, N extends INode, P extends IPlace, T extends ITransition, M extends IMarking<F,N,P,T>> 
				implements IModelChecker<F,N,P,T,M> {
	
	private String lolaDir = "win"; // or win

	public AbstractLoLAModelChecker(String lolaDir) {
		if (lolaDir==null || lolaDir.isEmpty()) return;
		this.lolaDir = lolaDir;
	}

	@Override
	public boolean checkCTL(INetSystem<F,N,P,T,M> sys, String formula) {
		boolean result = false;
		
		//System.out.println(formula);
		try 
	    { 
			String line;
			Process p = Runtime.getRuntime().exec ("cmd /c .\\util\\"+this.lolaDir+"\\lola-modelchecking.exe -A");
			
			BufferedReader input	= new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedWriter output	= new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));			
			BufferedReader error 	= new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			String cmd = this.sys2lola(sys) + " FORMULA " + formula;
			//System.out.println(cmd);
			output.write(cmd);
			output.close();

			while ((line = input.readLine()) != null) {
				//System.out.println(line);
				if (line.toLowerCase().equals("result: true")) result = true;
			}
			input.close();

			while ((line = error.readLine()) != null)
			error.close();
	    } 
	    catch(IOException e) {}  
 		
		return result;
	}
	
	@Override
	public boolean checkStatePredicate(INetSystem<F,N,P,T,M> sys, String formula) {
		boolean result = false;
		
		try 
	    { 
			String line;
			Process p = Runtime.getRuntime().exec ("cmd /c .\\util\\"+this.lolaDir+"\\lola-statepredicate.exe -A");
			
			BufferedReader input	= new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedWriter output	= new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));			
			BufferedReader error 	= new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			String cmd = this.sys2lola(sys) + " FORMULA " + formula;
			//System.out.println(cmd);
			output.write(cmd);
			output.close();

			while ((line = input.readLine()) != null) {
				//System.out.println(line);
				if (line.toLowerCase().trim().equals("state found!")) result = true;
			}
			input.close();

			while ((line = error.readLine()) != null)
			error.close();
	    } 
	    catch(IOException e) {}  
 		
		return result;
	}
	
	@Override
	public boolean checkEventuallyProp(INetSystem<F,N,P,T,M> sys, String formula) {
		boolean result = false;
		
		try 
	    { 
			String line;
			Process p = Runtime.getRuntime().exec ("cmd /c .\\util\\"+this.lolaDir+"\\lola-eventuallyprop.exe -A");
			
			BufferedReader input	= new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedWriter output	= new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));			
			BufferedReader error 	= new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			String cmd = this.sys2lola(sys) + " FORMULA " + formula;
			//System.out.println(cmd);
			output.write(cmd);
			output.close();

			while ((line = input.readLine()) != null)
				if (line.toLowerCase().trim().equals("state found!")) result = true;
			input.close();

			while ((line = error.readLine()) != null)
			error.close();
	    } 
	    catch(IOException e) {}  
 		
		return result;
	}
	
	@Override
	public boolean checkLiveProp(INetSystem<F,N,P,T,M> sys, String formula) {
		boolean result = false;
		
		try 
	    { 
			String line;
			Process p = Runtime.getRuntime().exec ("cmd /c .\\util\\"+this.lolaDir+"\\lola-liveprop.exe -A");
			
			BufferedReader input	= new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedWriter output	= new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));			
			BufferedReader error 	= new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			String cmd = this.sys2lola(sys) + " FORMULA " + formula;
			//System.out.println(cmd);
			output.write(cmd);
			output.close();

			while ((line = input.readLine()) != null) {
				//System.out.println(line);
				if (line.toLowerCase().trim().equals("predicate is live!")) result = true;
			}
			input.close();

			while ((line = error.readLine()) != null)
			error.close();
	    } 
	    catch(IOException e) {}  
 		
		return result;
	}
	
	@Override
	public boolean isLive(INetSystem<F,N,P,T,M> sys, T t) {
		if (sys==null) return false;
		if (!sys.getTransitions().contains(t)) return false;
		
		return this.checkLiveProp(sys, this.getLolaAtLeastOneTokenAtEachPlaceCondition(sys.getPreset(t)));
	}

	@Override
	public boolean isLive(INetSystem<F,N,P,T,M> sys) {
		if (sys==null) return false;
		
		for (T t : sys.getTransitions()) {
			if (!this.isLive(sys, t))
				return false;
		}
		
		return true;
	}

	@Override
	public boolean isReachable(INetSystem<F,N,P,T,M> sys, Collection<P> marking) {
		boolean result = false;
		
		try
	    { 
			String line;
			Process p = Runtime.getRuntime().exec ("cmd /c .\\util\\"+this.lolaDir+"\\lola-reachability.exe -A");
			
			BufferedReader input	= new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedWriter output	= new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));			
			BufferedReader error 	= new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			String cmd = this.sys2lola(sys) + "ANALYSE MARKING ";
			Collection<P> pSet = new HashSet<P>(marking);			
			Iterator<P> i = pSet.iterator();
			P place = i.next();
			cmd += place.getName() + ": " + Collections.frequency(marking,place);
			while (i.hasNext()) {
				cmd += " , ";
				place = i.next();
				cmd += place.getName() + ": " + Collections.frequency(marking,place);
			}
			output.write(cmd);
			output.close();

			while ((line = input.readLine()) != null)
				if (line.toLowerCase().trim().equals("state found!")) result = true;
			input.close();

			while ((line = error.readLine()) != null)
			error.close();
	    } 
	    catch(IOException e) {}  
 		
		return result;
	}
	
	@Override
	public boolean isBounded(INetSystem<F,N,P,T,M> sys) {
		boolean result = false;
		
		try
	    { 
			String line;
			Process p = Runtime.getRuntime().exec ("cmd /c .\\util\\"+this.lolaDir+"\\lola-boundednet.exe -A");
			
			BufferedReader input	= new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedWriter output	= new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));			
			BufferedReader error 	= new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			String cmd = this.sys2lola(sys);
			output.write(cmd);
			output.close();

			while ((line = input.readLine()) != null)
				if (line.toLowerCase().trim().equals("net is bounded!")) result = true;
			input.close();

			while ((line = error.readLine()) != null)
			error.close();
	    } 
	    catch(IOException e) {}  
 		
		return result;
	}
	
	@Override
	public boolean isSoundWorkflowNet(INetSystem<F,N,P,T,M> sys) {
		if (sys==null) return false;
		
		PetriNetStructuralChecks<F,N,P,T> check = new PetriNetStructuralChecks<F,N,P,T>();
		boolean wf = check.isWorkflowNet(sys);
		
		if (!wf) return false;
		
		P i = sys.getSourcePlaces().iterator().next();
		P o = sys.getSinkPlaces().iterator().next();
		
		for (P p : sys.getPlaces()) {
			if (p.equals(i)) {
				if (sys.getMarking().get(p)!=1) return false;
			} else {
				if (sys.getMarking().get(p)!=0) return false;
			}
		}
		
		T t = sys.createTransition();
		t.setName("TEMP");
		
		sys.addTransition(t);
		sys.addFlow(o,t);
		sys.addFlow(t,i);
		
		boolean result = true;
		
		if (!this.isBounded(sys)) result = false;
		
		if (result)
			if (!this.isLive(sys)) result = false;
		
		sys.removeTransition(t);
		
		return result;
	}

	private String sys2lola(INetSystem<F,N,P,T,M> sys) {
		if (sys.getPlaces().isEmpty() || sys.getTransitions().isEmpty()) return "";
		
		// PLACES
		String result = "PLACE ";
		Iterator<P> ip = sys.getPlaces().iterator();
		P p = ip.next();
		result += p.getName();
		while (ip.hasNext()) {
			result += ", ";
			p = ip.next();
			result += p.getName();
		}
		
		// MARKING
		result += "; MARKING ";
		M marking = sys.getMarking();
		Iterator<Map.Entry<P,Integer>> im = marking.entrySet().iterator();
		Map.Entry<P,Integer> m = im.next();
		result += m.getKey().getName()+": "+m.getValue().toString();
		while (im.hasNext()) {
			result += ", ";
			m = im.next();
			result += m.getKey().getName()+": "+m.getValue().toString();
		}
		
		// TRANSITIONS
		result += "; ";
		for (T t : sys.getTransitions()) {
			result += "TRANSITION " + t.getName() + " CONSUME ";
			
			Iterator<P> ip1 = sys.getPreset(t).iterator();
			p = ip1.next();
			result += p.getName()+": 1";
			while (ip1.hasNext()) {
				result += ", ";
				p = ip1.next();
				result += p.getName()+": 1";
			}
			
			result+= "; PRODUCE ";
			
			Iterator<P> ip2 = sys.getPostset(t).iterator();
			p = ip2.next();
			result += p.getName()+": 1";
			while (ip2.hasNext()) {
				result += ", ";
				p = ip2.next();
				result += p.getName()+": 1";
			}
			
			result += "; ";
		}
		
		return result;
	}
	
	private String getLolaAtLeastOneTokenAtEachPlaceCondition(Collection<P> places) {
		String result = "";
		if (places==null || places.isEmpty()) return result;
		
		Iterator<P> i = places.iterator();
		P p = i.next();
		result += p.getName() + " > 0";
		while (i.hasNext()) {
			result += " AND ";
			p = i.next();
			result += p.getName() + " > 0";
		}
		
		return result;
	}

	@Override
	public boolean canReachMarkingWithAtLeastOneTokenAtEachPlace(INetSystem<F,N,P,T,M> sys, Set<P> places) {
		return this.checkCTL(sys, String.format("EXPATH EVENTUALLY %s", this.getLolaAtLeastOneTokenAtEachPlaceCondition(places)));
	}

	@Override
	public boolean isBounded(INetSystem<F, N, P, T, M> sys, P p) {
		// TODO Auto-generated method stub
		return false;
	}
}
