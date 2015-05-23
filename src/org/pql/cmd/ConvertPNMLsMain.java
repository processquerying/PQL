package org.pql.cmd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.jbpt.petri.Flow;
import org.jbpt.petri.NetSystem;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;
import org.jbpt.petri.io.PNMLSerializer;
import org.jbpt.throwable.SerializationException;
import org.jbpt.utils.IOUtils;

public class ConvertPNMLsMain {

	public static void main(String[] args) throws SerializationException, FileNotFoundException {
		File dir = new File(args[0]);
		
		for (File file : dir.listFiles()) {
			System.out.println(file.getAbsolutePath());
			
			if (!file.isFile()) continue;
			if (!file.getName().endsWith(".pnml")) continue;
			
			PNMLSerializer PNML = new PNMLSerializer();
			NetSystem sys = PNML.parse(file.getAbsolutePath());
			
			NetSystem sys2 = new NetSystem();
			Map<Place,Place> p2p = new HashMap<>();
			Map<Transition,Transition> t2t = new HashMap<>();
			
			int pi;
			pi=1; 
			
			for (Place p : sys.getPlaces()) { 
				Place p2 = new Place();
				p2.setName("p"+pi++);
				sys2.addPlace(p2);
				p2p.put(p,p2);
			}
			
			for (Transition t : sys.getTransitions()) { 
				Transition t2 = new Transition("","");
				t2.getLabel();
				
				String name = t.getId();
				
				System.out.print(name);
				
				if (name.contains("[")) {
					name = name.substring(name.indexOf("[")+1, name.indexOf("]"));
					
					StringTokenizer st = new StringTokenizer(name, "->");
					
					boolean flag = false;
					while (st.hasMoreElements()) {
						String n = (String) st.nextElement();
						n = n.substring(1,n.length()-1).trim();
						
						if (!n.toUpperCase().equals("XOR")) {
							flag = true;
							name = n;
						}
							
					}
					
					if (flag) {
						t2.setLabel(name.trim());
						t2.setName(name.trim());
					}
				}
				
				System.out.println(" => " + t2.getLabel());
				sys2.addTransition(t2);
				t2t.put(t,t2);
			}
			
			for (Flow f : sys.getFlow()) {
				if (f.getSource() instanceof Place) {
					sys2.addFlow(p2p.get(f.getSource()), t2t.get(f.getTarget()));
				}
				else {
					sys2.addFlow(t2t.get(f.getSource()), p2p.get(f.getTarget()));
				}
			}
						
			String pnmlString = PNMLSerializer.serializePetriNet(sys2);
			
			String fileName = args[1] + file.getName(); 
			
			IOUtils.toFile(fileName,pnmlString);
		}

	}

}
