package org.pql.experiments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.lucene.index.IndexWriter;
import org.jbpt.persist.MySQLConnection;
import org.jbpt.petri.persist.AbstractPetriNetPersistenceLayerMySQL;
import org.pql.api.PQLAPI;
import org.pql.bot.AbstractPQLBot.NameInUseException;
import org.pql.bot.PQLBotEx;
import org.pql.core.PQLBasicPredicatesMC;
import org.pql.core.PQLBasicPredicatesMySQL;
import org.pql.index.IndexType;
import org.pql.index.PQLIndexMySQL;
import org.pql.ini.PQLIniFile;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerLevenshtein;
import org.pql.label.LabelManagerLuceneVSM;
import org.pql.label.LabelManagerThemisVSM;
import org.pql.mc.LoLA2ModelChecker;

public class Experiment2v1 {
		
public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException, NameInUseException, org.pql.bot.AbstractPQLBotEx.NameInUseException {
	
	//input parameters 
	  String collectionPath = args[0];  //apql 
	  int numberOfRuns = Integer.parseInt(args[1]); //3
	  int numberOfNodes = 20; 
	  
	Vector<Set<Double>> simThresholds = new Vector<Set<Double>>();
	simThresholds = getSimThresholds(simThresholds);
	System.out.println(simThresholds);
		
	//setup
	PQLIniFile iniFile = new PQLIniFile();
	if (!iniFile.load()) {
		System.out.println("ERROR: Cannot load PQL ini file.");
		return;
	}
	
	PQLAPI api = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
				iniFile.getPostgreSQLHost(), iniFile.getPostgreSQLName(), iniFile.getPostgreSQLUser(), iniFile.getPostgreSQLPassword(),
				iniFile.getLoLA2Path(),
				iniFile.getLabelSimilaritySeacrhConfiguration(),
				iniFile.getIndexType(),
				iniFile.getLabelManagerType(),
				iniFile.getDefaultLabelSimilarityThreshold(),
				iniFile.getIndexedLabelSimilarityThresholds(),
				iniFile.getNumberOfQueryThreads(),
				iniFile.getDefaultBotMaxIndexTime(),
				iniFile.getDefaultBotSleepTime());
	
	//Objects for getting net properties
	Connection 				conNP = (new MySQLConnection(iniFile.getMySQLURL(),iniFile.getMySQLUser(),iniFile.getMySQLPassword())).getConnection();
	ILabelManager labelMngrNP = null;
	    
	    switch (iniFile.getLabelManagerType()) {
			case THEMIS_VSM:
				labelMngrNP = new LabelManagerThemisVSM(conNP,
						iniFile.getPostgreSQLHost(),iniFile.getPostgreSQLName(),iniFile.getPostgreSQLUser(),iniFile.getPostgreSQLPassword(),
						iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
				break;
			case LUCENE:
				labelMngrNP = new LabelManagerLuceneVSM(conNP,
						iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds(),iniFile.getLabelSimilaritySeacrhConfiguration());
				break;
			default:
				labelMngrNP = new LabelManagerLevenshtein(conNP,
						iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
				break;
	    }
	PQLBasicPredicatesMySQL bpNP = (PQLBasicPredicatesMySQL)new org.pql.core.PQLBasicPredicatesMySQL(conNP,labelMngrNP,new AtomicInteger(0));
	AbstractPetriNetPersistenceLayerMySQL pl = (AbstractPetriNetPersistenceLayerMySQL)bpNP.getPL();
	
	//Results title
		Vector<String> results = new Vector<String>();
		String sepLine = "sep=;\r\n";
		results.add(sepLine);
		String titleLine = "Experiment#;#run;LabelSim;netID;#transitions;#places;#flows;#nodes;nodeCategory;indexTime;startTime;endTime\r\n";
		results.add(titleLine);
	
	//remove index
	IndexWriter iw = labelMngrNP.getIndexWriter();
	api.reset();
	iw.deleteAll();
	iw.commit();
	iw.close();
		
	//store all nets and get their IDs
	String netPath = "./pnml/"+collectionPath+"/";
	storeAll(new File(netPath), api);
	Set<String> externalIDs = new HashSet<String>();
	externalIDs = api.getExternalIDs(); //get nets stored in DB
	Vector<String> nets = new Vector<String>();
	nets.addAll(externalIDs);

Set<Double> LS = new HashSet<Double>();

for(int sim=0; sim<simThresholds.size(); sim++)
{	
	IndexWriter iw2 = labelMngrNP.getIndexWriter();
	api.reset();
	iw2.deleteAll();
	iw2.commit();
	iw2.close();
	
	LS = simThresholds.elementAt(sim);
	
for(int runs=0; runs<numberOfRuns; runs++)
{
	
	//store nets
	for(int i=0; i<nets.size(); i++)
		{
				String nextID = nets.elementAt(i);
				api.storeModel(new File(netPath+nextID),nextID);
		}
		
		//start bot
			Vector<PQLBotEx> bots = new Vector<PQLBotEx>();
			Connection 	connection = (new MySQLConnection(iniFile.getMySQLURL(),iniFile.getMySQLUser(),iniFile.getMySQLPassword())).getConnection();
			ILabelManager labelMngr = null;
			int indexTime = iniFile.getDefaultBotMaxIndexTime();
			    
			    switch (iniFile.getLabelManagerType()) {
					case THEMIS_VSM:
						labelMngr = new LabelManagerThemisVSM(connection,
								iniFile.getPostgreSQLHost(),iniFile.getPostgreSQLName(),iniFile.getPostgreSQLUser(),iniFile.getPostgreSQLPassword(),
								iniFile.getDefaultLabelSimilarityThreshold(),LS);
						break;
					case LUCENE:
						labelMngr = new LabelManagerLuceneVSM(connection,
								iniFile.getDefaultLabelSimilarityThreshold(),LS,iniFile.getLabelSimilaritySeacrhConfiguration());
						break;
					default:
						labelMngr = new LabelManagerLevenshtein(connection,
								iniFile.getDefaultLabelSimilarityThreshold(),LS);
						break;
			    }

			LoLA2ModelChecker 		mc	= new LoLA2ModelChecker(iniFile.getLoLA2Path());
			PQLBasicPredicatesMC    bp	= new PQLBasicPredicatesMC(mc);
			PQLIndexMySQL			index = new PQLIndexMySQL(connection,bp,labelMngr,mc,
							iniFile.getDefaultLabelSimilarityThreshold(),LS,
							iniFile.getIndexType(), iniFile.getDefaultBotMaxIndexTime(), 0);
			
			
			PQLBotEx bot = new PQLBotEx(connection, "bot"+sim, index, mc, IndexType.PREDICATES, indexTime, 0);
			bot.start();
			bots.add(bot);
			
		
		try{
			for (PQLBotEx activeBot : bots) 
			{
				activeBot.join();
			}
		} catch (InterruptedException e) {e.printStackTrace();}
			
		System.out.println("Completed for run#: "+(runs+1) + " sim: "+LS);
		
	//get results
	for(String id : nets)
		{
			//get net properties
			int numberOfPlaces = pl.getNumberOfPlaces(id);
			int numberOfTransitions = pl.getNumberOfTransitions(id);
			int numberOfFlowArcs = pl.getNumberOfFlowArcs(id);
			int nodes = numberOfTransitions + numberOfPlaces;
			int category = getNodeCategory(numberOfNodes, nodes);
			
			//get indexing time
			int intID = api.getInternalID(id);
			int indextime = api.getIndexTime(intID);
			int indexStartTime = api.getIndexStartTime(intID);
			int indexEndTime = api.getIndexEndTime(intID);
			
			results.add("2v1;"+(runs+1)+";"+LS+";"+id+";"+numberOfTransitions+";"+numberOfPlaces+";"+numberOfFlowArcs+";"+nodes+";"+category+";"+indextime+";"+indexStartTime+";"+indexEndTime+"\r\n");
		}
	
	IndexWriter iw3 = labelMngrNP.getIndexWriter();
	api.reset();
	iw3.deleteAll();
	iw3.commit();
	iw3.close();
	
}
}

File Results = writeCSV(results,".\\Ex2v1Results.csv");

}
private static Vector<Set<Double>> getSimThresholds(Vector<Set<Double>> simThresholds) 
{

	String filePath = "./similarity.txt";
	File file = new File(filePath);
	
	try(BufferedReader br = new BufferedReader(new FileReader(file))) 
	{
	    for(String line; (line = br.readLine()) != null; ) 
	    {
	        String[] arr = line.split(";");
	        Set<Double> ls = new HashSet<Double>();
	        for(String s: arr)
	        {
	        	ls.add(Double.parseDouble(s));
	        }
	        simThresholds.add(ls);
	    }
	}catch(Exception e){};

return simThresholds;
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
	
	private static void store(File pnmlFile, String identifier, PQLAPI api) throws SQLException {
		if (pnmlFile==null || identifier==null) {
			System.out.println("Cannot store model.");
			return;
		}
		
		int result =api.storeModel(pnmlFile, identifier);
		
		if (result>0) 
			System.out.println(String.format("Model %s stored under unique identifier %s.", pnmlFile.getAbsolutePath(), identifier));
		else
			System.out.println(String.format("Model %s cannot be stored under identifier %s.", pnmlFile.getAbsolutePath(), identifier));
	}

	private static void storeAll(File pnmlDir, PQLAPI api) throws SQLException {
		for (File file : pnmlDir.listFiles()) {
			if (!file.isFile()) continue;
			if (!file.getName().endsWith(".pnml")) continue;
			
			store(file, file.getName(), api);
		}
	}
	
	
	private static int getNodeCategory(int nodeInc, int nodes){
	int category = nodeInc;
	
	int num = (int)nodes/nodeInc;
	
	if(nodes % nodeInc == 0)
		category = num*nodeInc;
	else
	category = num*nodeInc+nodeInc;
		
	return category;	
	}

	
}

