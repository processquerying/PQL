package org.pql.experiments;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jbpt.petri.NetSystem;
import org.jbpt.petri.PetriNet;
import org.jbpt.petri.Place;
import org.jbpt.petri.Transition;
import org.jbpt.petri.io.PNMLSerializer;
import org.jbpt.pm.Activity;
import org.jbpt.pm.AndGateway;
import org.jbpt.pm.ControlFlow;
import org.jbpt.pm.FlowNode;
import org.jbpt.pm.Gateway;
import org.jbpt.pm.XorGateway;
import org.jbpt.pm.epc.Epc;
import org.jbpt.pm.epc.util.EPMLParser;
import org.jbpt.throwable.SerializationException;
import org.jbpt.utils.IOUtils;
import org.json.JSONException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.pql.index.IPQLIndex;
import org.pql.index.IndexType;
import org.pql.mc.AbstractLoLA2ModelChecker;
import org.pql.mc.IModelChecker;

public class SoundModels
{
	
@SuppressWarnings({ "rawtypes" })
public static void main(String[] args) throws JSONException, ClassNotFoundException, SQLException, IOException, ParserConfigurationException, SAXException, SerializationException
{
	int counter = 1;
	long lolaWaitTime = 300000l; //5 mins 

	String path = "C:/Temp/epc/synthetic0/";
	String out = "C:/Temp/pnml/";
	File filePath = new File(path);
	
	AbstractLoLA2ModelChecker mc = new AbstractLoLA2ModelChecker("./lola2/win/lola.exe"); 
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	for (File file : filePath.listFiles()) {
		
		//1. read and fix XML
		Document doc = dBuilder.parse(file);
		Element element = (Element) doc.getElementsByTagName("epc").item(0);
	    element.setAttribute("name", "epc");
	    
	    String name = doc.getDocumentElement().getFirstChild().getAttributes().getNamedItem("epcId").toString();
	    name = name.substring(7, name.length());
	    name = name.substring(0, name.length()-6);
	    System.out.println(counter++ + ":" +name);
	   
	    NodeList xornl = doc.getElementsByTagName("XOR");
	    NodeList ornl = doc.getElementsByTagName("OR");
	    NodeList andnl = doc.getElementsByTagName("AND");
	    
	    for(int i=0; i<ornl.getLength(); i++)
	     	doc.renameNode(ornl.item(i), null, "and"); //OR -> AND
	    
	    for(int i=0; i<xornl.getLength(); i++)
	     	doc.renameNode(xornl.item(i), null, "xor");
	    
	    for(int i=0; i<andnl.getLength(); i++)
	      	doc.renameNode(andnl.item(i), null, "and");
	   
	    NodeList enl = doc.getElementsByTagName("event"); //event -> function
	    for(int i=0; i<enl.getLength(); i++)
	    	doc.renameNode(enl.item(i), null, "function");
	
	    //2. parse EPML
	    EPMLParser epmlParser = new EPMLParser(doc);	
		Epc epc = (Epc) epmlParser.getFirstModel();
		//IOUtils.invokeDOT("./pics/", name+"-epc.png", epc.toDOT());
		
		//3. convert to net system
		NetSystem ns =  convertEPCtoNS(epc);
		ns = convertToSESE(ns);
		ns.loadNaturalMarking();
		//IOUtils.invokeDOT("./pics/", name+"-ns.png", ns.toDOT());
		
		//4. check for soundness
		boolean isSound = false;
		CheckSoundnessThread thread = new CheckSoundnessThread(ns,mc);		
		thread.start();
		
		try {
			thread.join(lolaWaitTime);
			isSound = thread.getResult();
			if (thread.isAlive()) {thread.interrupt();}
		} catch (InterruptedException e) {e.printStackTrace();}
	 	
	   	if(thread.isInterrupted())
	   	{
	   		thread.stop();
	   	Runtime.getRuntime().exec("taskkill /F /IM lola.exe");
	   	System.out.println("lola interrupted");	
	   	}
		
		//5. save sound net	
		if(isSound) 
		{
			
			for(Transition t : ns.getTransitions())
			t.setName(t.getLabel());
			
			PNMLSerializer PNML = new PNMLSerializer();
			String pnmlNS = PNML.serializePetriNet(ns);
			System.out.println("done");
			try(  PrintWriter pw = new PrintWriter( out+name+".pnml" )  ){
			    pw.println( pnmlNS );
			}
		
		}
		
	}
}

static NetSystem convertEPCtoNS(Epc epc)
{
	
	NetSystem ns = new NetSystem();
	int ti = 1;
	int pi = 1;
	
	Map<FlowNode,Transition> tasks = new HashMap<FlowNode,Transition>();
	Map<FlowNode,Place> xors = new HashMap<FlowNode,Place>();
	Map<FlowNode,Transition> ands = new HashMap<FlowNode,Transition>();

	for(FlowNode fn : epc.getEntities())
	{
		if (fn instanceof Activity)
		{
			
			Transition t = new Transition();
			t.setName("t"+ti++);
			t.setLabel(fn.getName());
			ns.addTransition(t);
			tasks.put(fn, t);
		}
		
		if (fn instanceof XorGateway)
		{
			Place p = new Place();
			p.setName("p"+pi++);
			ns.addPlace(p);
			xors.put(fn, p);
		}
		
		if (fn instanceof AndGateway)
		{
			Transition t = new Transition();
			t.setName("t"+ti++);
			ns.addTransition(t);
			ands.put(fn, t);
		}

	}
	
	for(ControlFlow<FlowNode> arc : epc.getControlFlow())
	{
		FlowNode source = arc.getSource();
		FlowNode target = arc.getTarget();
		//Source: activity/////////////////////////////////////////////////
		//Activity -> Activity
		if(source instanceof Activity && target instanceof Activity)
		{
			Place p = new Place();
			p.setName("p"+pi++);
			ns.addPlace(p);
				
			ns.addFlow(tasks.get(source), p);
			ns.addFlow(p, tasks.get(target));
		}else
		//Activity -> XorGateway
		if(source instanceof Activity && target instanceof XorGateway)
		{
			ns.addFlow(tasks.get(source), xors.get(target));
		}else 
		//Activity -> AndGateway
		if(source instanceof Activity && target instanceof AndGateway)
		{
			Place p = new Place();
			p.setName("p"+pi++);
			ns.addPlace(p);
			
			ns.addFlow(tasks.get(source), p);
			ns.addFlow(p, ands.get(target));
		}else
	    //source: AndGateway///////////////////////////////////////////////
		//AndGateway -> Activity
		if(source instanceof AndGateway && target instanceof Activity)
		{
			Place p = new Place();
			p.setName("p"+pi++);
			ns.addPlace(p);
				
			ns.addFlow(ands.get(source), p);
			ns.addFlow(p, tasks.get(target));
		}else
		//AndGateway -> AndGateway
		if(source instanceof AndGateway && target instanceof AndGateway)
		{
			Place p = new Place();
			p.setName("p"+pi++);
			ns.addPlace(p);
				
			ns.addFlow(ands.get(source), p);
			ns.addFlow(p, ands.get(target));
		}else	
		//AndGateway -> XorGateway
		if(source instanceof AndGateway && target instanceof XorGateway)
		{
			ns.addFlow(ands.get(source), xors.get(target));
		}else	
       //source: XorGateway/////////////////////////////////////////////////
	   //XorGateway	-> Activity
		if(source instanceof XorGateway && target instanceof Activity)	
		{
			ns.addFlow(xors.get(source), tasks.get(target));
		}else
		//XorGateway -> XorGateway
		if(source instanceof XorGateway && target instanceof XorGateway)	
		{
			Transition t = new Transition();
			t.setName("t"+ti++);
			ns.addTransition(t);
			
			ns.addFlow(xors.get(source), t);
			ns.addFlow(t, xors.get(target));
		}else
		//XorGateway -> AndGateway
		if(source instanceof XorGateway && target instanceof AndGateway)	
		{
			ns.addFlow(xors.get(source), ands.get(target));
		}
		
	}
		
		//add initial places
		for(FlowNode fn : epc.getEntries())
		{
			if (fn instanceof Activity)
			{
				Place p = new Place();
				p.setName("p"+pi++);
				ns.addPlace(p);
					
				ns.addFlow(p,tasks.get(fn));
			}else
			if (fn instanceof AndGateway)
			{
				Place p = new Place();
				p.setName("p"+pi++);
				ns.addPlace(p);
						
				ns.addFlow(p,ands.get(fn));
			}
		}	
			//add final places
			for(FlowNode fn : epc.getExits())
			{
				if (fn instanceof Activity)
				{
					Place p = new Place();
					p.setName("p"+pi++);
					ns.addPlace(p);
						
					ns.addFlow(tasks.get(fn),p);
				}else
				if (fn instanceof AndGateway)
				{
					Place p = new Place();
					p.setName("p"+pi++);
					ns.addPlace(p);
							
					ns.addFlow(ands.get(fn),p);
				}	

		}

	
	return ns;
	
}

static NetSystem convertToSESE(NetSystem ns)
{
	if(ns.getSourcePlaces().size() > 1)
	{	
		
		Transition ti = new Transition();
		ti.setName("ti");
		ns.addTransition(ti);
		
		for(Place p : ns.getSourcePlaces())
		{
			ns.addFlow(ti,p);
		}
		
		Place i = new Place();
		i.setName("i");
		ns.addPlace(i);
		
		ns.addFlow(i,ti);
	
	}
	
	
	if(ns.getSinkPlaces().size() > 1)
	{
		
		Transition to = new Transition();
		to.setName("to");
		ns.addTransition(to);
		
		for(Place p : ns.getSinkPlaces())
		{
			ns.addFlow(p,to);
		}
		
		Place o = new Place();
		o.setName("o");
		ns.addPlace(o);
		ns.addFlow(to,o);
	}
	
	return ns;
	
}

}

class CheckSoundnessThread extends Thread {
	boolean result;
	NetSystem ns;
	AbstractLoLA2ModelChecker mc;
	
	CheckSoundnessThread(NetSystem netSys, AbstractLoLA2ModelChecker modelChecker) {
		this.ns = netSys;
		this.mc = modelChecker;
		this.result = false;
		
	}

	@Override
	public void run() {
		this.result = mc.isSoundWorkflowNet(ns);
		
	}

	public boolean getResult() {
		return this.result;
	}
	
	

	
};
