package org.pql.experiments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
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

public class Experiment1v1 {
//'shift sets' split 
	
public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException, NameInUseException, org.pql.bot.AbstractPQLBotEx.NameInUseException {
	
	//input parameters
	  String collectionPath = args[0];  //apql 
	  int numberOfBots = Integer.parseInt(args[1]); //8
	  int kSplit = Integer.parseInt(args[2]); //4
	  int numberOfNodes = 20; //Integer.parseInt(args[3]); //20
	  int minBots = 1; //Integer.parseInt(args[4]);//1
	  		
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
		String titleLine = "Experiment#;#bots;run;percentile#;netID;#transitions;#places;#flows;#nodes;nodeCategory;indexTime;startTime;endTime\r\n";
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
	externalIDs = api.getExternalIDs(); 
	Vector<String> nets = new Vector<String>();
	nets.addAll(externalIDs);
	//System.out.println(nets);
	Collections.shuffle(nets);
	System.out.println(nets);
	
	//remove index
	IndexWriter iw2 = labelMngrNP.getIndexWriter();
	api.reset();
	iw2.deleteAll();
	iw2.commit();
	iw2.close();
	
	//prepare collection
	int numberOfNets = externalIDs.size();
	int netIncrement = (int) numberOfNets/kSplit; //value is truncated to int
	
	Vector<Vector<String>> netsExt = new Vector<Vector<String>>();
	
	int start = 0;
	for(int j=0; j<kSplit; j++)
	{
		int last = netIncrement+netIncrement*j;
		if(j == kSplit-1){last = numberOfNets;}
		Vector<String> netExt = new Vector<String>();
		for(int i=start; i<last; i++)
		{
			netExt.add(nets.elementAt(i));
		}
		netsExt.add(netExt);
		start += netIncrement;
	}
	
	for(int i=0; i<netsExt.size(); i++)
	System.out.println(netsExt.elementAt(i));

for(int botNum=minBots; botNum<(numberOfBots+1); botNum++)
{	
	//remove index
		IndexWriter iw3 = labelMngrNP.getIndexWriter();
		api.reset();
		iw3.deleteAll();
		iw3.commit();
		iw3.close();

for(int run=0; run<kSplit; run++)
	{	
	//store and index nets for one run
	for(int i=0; i<netsExt.size(); i++)
		{
		//store one k
		for(int j=0; j<netsExt.elementAt(i).size(); j++)
			{
				String nextID = netsExt.elementAt(i).elementAt(j);
				api.storeModel(new File(netPath+nextID),nextID);
			}
		
		//index one k
		Vector<PQLBotEx> bots = new Vector<PQLBotEx>();
		
		for(int j=0; j<botNum; j++)
		{
			Connection 				connection = (new MySQLConnection(iniFile.getMySQLURL(),iniFile.getMySQLUser(),iniFile.getMySQLPassword())).getConnection();
			ILabelManager labelMngr = null;
			int indexTime = iniFile.getDefaultBotMaxIndexTime();
			    
			    switch (iniFile.getLabelManagerType()) {
					case THEMIS_VSM:
						labelMngr = new LabelManagerThemisVSM(connection,
								iniFile.getPostgreSQLHost(),iniFile.getPostgreSQLName(),iniFile.getPostgreSQLUser(),iniFile.getPostgreSQLPassword(),
								iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
						break;
					case LUCENE:
						labelMngr = new LabelManagerLuceneVSM(connection,
								iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds(),iniFile.getLabelSimilaritySeacrhConfiguration());
						break;
					default:
						labelMngr = new LabelManagerLevenshtein(connection,
								iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
						break;
			    }

			LoLA2ModelChecker 		mc	= new LoLA2ModelChecker(iniFile.getLoLA2Path());
			PQLBasicPredicatesMC    bp	= new PQLBasicPredicatesMC(mc);
			PQLIndexMySQL			index = new PQLIndexMySQL(connection,bp,labelMngr,mc,
							iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds(),
							iniFile.getIndexType(), iniFile.getDefaultBotMaxIndexTime(), 0);
			
			
			PQLBotEx bot = new PQLBotEx(connection, "bot"+botNum+i+j, index, mc, IndexType.PREDICATES, indexTime, 0);
			bot.start();
			bots.add(bot);
			}
		
		try{
			for (PQLBotEx bot : bots) 
			{
			    bot.join();
			}
		} catch (InterruptedException e) {e.printStackTrace();}
			
		System.out.println("Indexing completed for k: " + (i+1) + " bots: " + botNum + " run: " + run);
		}
	//one run indexed----------------------------------------------------------
	
	//get run results
	for(int kf=0; kf<kSplit; kf++)
	{	
		for(String id : netsExt.elementAt(kf))
		{
			//get net properties
			int numberOfPlaces = pl.getNumberOfPlaces(id);
			int numberOfTransitions = pl.getNumberOfTransitions(id);
			int numberOfFlowArcs = pl.getNumberOfFlowArcs(id);
			int nodes = numberOfTransitions + numberOfPlaces;
			
			//get indexing time
			int intID = api.getInternalID(id);
			int indextime = api.getIndexTime(intID);
			int indexStartTime = api.getIndexStartTime(intID);
			int indexEndTime = api.getIndexEndTime(intID);
			int category = getNodeCategory(numberOfNodes, nodes);
			
			results.add("1v1;"+botNum+";"+(run+1)+";"+(kf+1)+";"+id+";"+numberOfTransitions+";"+numberOfPlaces+";"+numberOfFlowArcs+";"+nodes+";"+category+";"+indextime+";"+indexStartTime+";"+indexEndTime+"\r\n");
			
		}
	}
	
	//remove index
		IndexWriter iw4 = labelMngrNP.getIndexWriter();
		api.reset();
		iw4.deleteAll();
		iw4.commit();
		iw4.close();
	
	//shuffle nets
	Vector<String> temp = new Vector<String>();
	temp.addAll(netsExt.elementAt(kSplit-1));
	netsExt.add(0, temp);
	netsExt.remove(kSplit);
	System.out.println("Finished for #bots "+ botNum +" run "+run);	
	}
System.out.println("Finished for #bots "+ botNum);
}	

File Results = writeCSV(results,".\\Ex1v1Results.csv");

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
