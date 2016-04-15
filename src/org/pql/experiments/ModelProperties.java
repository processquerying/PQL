package org.pql.experiments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;

import org.jbpt.algo.tree.rpst.RPST;
import org.jbpt.algo.tree.tctree.TCType;
import org.jbpt.petri.Flow;
import org.jbpt.petri.NetSystem;
import org.jbpt.petri.Node;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;
import org.jbpt.petri.io.PNMLSerializer;
import org.jbpt.throwable.SerializationException;
import org.json.JSONException;
import org.xml.sax.SAXException;


public class ModelProperties
{
	
public static void main(String[] args) throws JSONException, ClassNotFoundException, SQLException, IOException, ParserConfigurationException, SAXException, SerializationException
{

	String path = "C:/Temp/pnml/";

	File filePath = new File(path);
	
	//Results title 
	Vector<String> results = new Vector<String>();
	String sepLine = "sep=;\r\n";
	results.add(sepLine);
	String titleLine = "netID;transitions;obsTransitions;places;flows;xorSplits;xorJoins;andSplits;andJoins;polygons;bonds;rigids\r\n";
	results.add(titleLine);
	int counter = 1;
	
	for (File file : filePath.listFiles()) 
	{
		System.out.println(counter++);
		
		int xorSplits = 0;
		int xorJoins = 0;
		int andSplits = 0;
		int andJoins = 0;
		
		int transitions = 0;
		int places = 0;
		int flows = 0;
		int obsTransitions = 0;
		
		int bonds = 0;
		int polygons = 0;
		int rigids = 0;
		
		String pnmlContent = readFile(file);
		PNMLSerializer PNML = new PNMLSerializer();
		NetSystem ns = PNML.parse(pnmlContent.getBytes());
		
		transitions = ns.getTransitions().size();
		places = ns.getPlaces().size();
		flows = ns.getEdges().size();
		obsTransitions = ns.getObservableTransitions().size();
		
		for(Transition t: ns.getTransitions())
			{
				if(ns.getDirectPredecessors(t).size()>1) andJoins++;
				if(ns.getDirectSuccessors(t).size()>1) andSplits++;
			}
		
		for(Place p: ns.getPlaces())
			{
				if(ns.getDirectPredecessors(p).size()>1) xorJoins++;
				if(ns.getDirectSuccessors(p).size()>1) xorSplits++;
			}
		
		RPST<Flow,Node> rpst = new RPST<>(ns);
		polygons = rpst.getRPSTNodes(TCType.POLYGON).size();
		bonds = rpst.getRPSTNodes(TCType.BOND).size();
		rigids = rpst.getRPSTNodes(TCType.RIGID).size();
		
		String line = file.getName()+";"+transitions+";"+obsTransitions+";"+places+";"+flows+";"+xorSplits+";"+xorJoins+";"+andSplits+";"+andJoins+";"+polygons+";"+bonds+";"+rigids+"\r\n";
		results.add(line);

		
/*		System.out.println(file.getName());
		System.out.println(transitions);
		System.out.println(places);
		System.out.println(flows);
		System.out.println(obsTransitions);
		System.out.println(andJoins);
		System.out.println(andSplits);
		System.out.println(xorJoins);
		System.out.println(xorSplits);
		System.out.println(polygons);
		System.out.println(bonds);
		System.out.println(rigids);
		
		IOUtils.invokeDOT("./pics/", file.getName()+".png", ns.toDOT());
*/	 		
		
	}
	
	File Results = writeCSV(results,".\\netPropertiesSAP.csv");
}

private static String readFile(File file) throws IOException {

    StringBuilder fileContents = new StringBuilder((int)file.length());
    Scanner scanner = new Scanner(file);
    String lineSeparator = System.getProperty("line.separator");

    try {
        while(scanner.hasNextLine()) {        
            fileContents.append(scanner.nextLine() + lineSeparator);
        }
        return fileContents.toString();
    } finally {
        scanner.close();
    }
}

public static File writeCSV(Vector<String> lines, String path) throws IOException {
	
	File file = new File(path);
	FileWriter fw = new FileWriter(file, true);
		
		for (int i=0; i<lines.size(); i++) {
			String line = lines.elementAt(i);
			fw.write(line);
			fw.flush();
		}
	
	fw.close();
	return file;
}

}

