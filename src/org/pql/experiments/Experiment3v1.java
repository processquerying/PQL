package org.pql.experiments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.lucene.index.IndexWriter;
import org.jbpt.persist.MySQLConnection;
import org.jbpt.petri.persist.AbstractPetriNetPersistenceLayerMySQL;
import org.pql.api.PQLAPI;
import org.pql.bot.AbstractPQLBot.NameInUseException;
import org.pql.bot.PQLBot;
import org.pql.bot.PQLBotEx;
import org.pql.core.IPQLBasicPredicatesOnTasks;
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
import org.pql.query.PQLQueryResult;
import org.pql.tool.PQLToolCLI;

public class Experiment3v1 {
	private static Random rand = new Random(System.currentTimeMillis());
		
public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException, NameInUseException, org.pql.bot.AbstractPQLBotEx.NameInUseException {
	
	//input parameters 
	  String collectionPath = args[0];  //apql 
	  int numberOfBots = Integer.parseInt(args[1]); //8
	  int kSplit = Integer.parseInt(args[2]); //4
	  int numberOfThreads = Integer.parseInt(args[3]); //1-8
	  int nPerTemplate = Integer.parseInt(args[4]); //3
	  int numberOfRepetitions = Integer.parseInt(args[5]); //3
	  int numberOfRuns = Integer.parseInt(args[6]); //3
	  
	Vector<Vector<String>> templates = new Vector<Vector<String>>();
	templates = getTemplates(templates);
	System.out.println(templates);
	
	//setup
	PQLIniFile iniFile = new PQLIniFile();
	if (!iniFile.load()) {
		System.out.println("ERROR: Cannot load PQL ini file.");
		return;
	}
	
	PQLAPI mainApi = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
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
	
	//Objects for cleaning Lucene index
		Connection 				mainCon = (new MySQLConnection(iniFile.getMySQLURL(),iniFile.getMySQLUser(),iniFile.getMySQLPassword())).getConnection();
		ILabelManager 			mainLabelMngr = null;
		    
		    switch (iniFile.getLabelManagerType()) {
				case THEMIS_VSM:
					mainLabelMngr = new LabelManagerThemisVSM(mainCon,
							iniFile.getPostgreSQLHost(),iniFile.getPostgreSQLName(),iniFile.getPostgreSQLUser(),iniFile.getPostgreSQLPassword(),
							iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
					break;
				case LUCENE:
					mainLabelMngr = new LabelManagerLuceneVSM(mainCon,
							iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds(),iniFile.getLabelSimilaritySeacrhConfiguration());
					break;
				default:
					mainLabelMngr = new LabelManagerLevenshtein(mainCon,
							iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
					break;
		    }
	
	//Results title
		Vector<String> results = new Vector<String>();
		String sepLine = "sep=;\r\n";
		results.add(sepLine);
		String titleLine = "Experiment#;run#;collection#;Threads#;queryTime;answersPerQuery;category;subcategory;group;templateID;template;query\r\n";
		results.add(titleLine);
		
	//remove index
		IndexWriter iw = mainLabelMngr.getIndexWriter();
		mainApi.reset();
		iw.deleteAll();
		iw.commit();
		iw.close();
	
	//store all nets and get their IDs
	String netPath = "./pnml/"+collectionPath+"/";
	storeAll(new File(netPath), mainApi);
	Set<String> externalIDs = new HashSet<String>();
	externalIDs = mainApi.getExternalIDs(); 
	Vector<String> nets = new Vector<String>();
	nets.addAll(externalIDs);
	
	//get all labels
	Vector<String> labels = new Vector<String>();
	labels = getLabels(mainCon);
	System.out.println(labels);
	
//for each run
for(int run=0; run < numberOfRuns; run++)
{	
	
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

//Objects for cleaning Lucene index
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
	
	//remove index
		IndexWriter iw2 = labelMngrNP.getIndexWriter();
		api.reset();
		iw2.deleteAll();
		iw2.commit();
		iw2.close();

	//prepare collection
	int numberOfNets = externalIDs.size();
	int netIncrement = (int) numberOfNets/kSplit; //value is truncated to int
	Collections.shuffle(nets);
	
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

	//perform querying on empty index
	for(int t=0; t<templates.size(); t++)
	{
		String template = templates.elementAt(t).elementAt(4);
		System.out.println(template);
		
		for(int n=0; n<nPerTemplate; n++)
		{
			String query = generateQuery(labels, template);
			System.out.println(query);
			
			//querying
					for(int threads=1; threads<(numberOfThreads+1); threads++)
					{
						PQLAPI	pqlAPI = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
										iniFile.getPostgreSQLHost(), iniFile.getPostgreSQLName(), iniFile.getPostgreSQLUser(), iniFile.getPostgreSQLPassword(),
										iniFile.getLoLA2Path(),
										iniFile.getLabelSimilaritySeacrhConfiguration(),
										iniFile.getIndexType(),
										iniFile.getLabelManagerType(),
										iniFile.getDefaultLabelSimilarityThreshold(),
										iniFile.getIndexedLabelSimilarityThresholds(),
										threads,
										iniFile.getDefaultBotMaxIndexTime(),
										iniFile.getDefaultBotSleepTime());
							
							long time = 0L;
							int answersCount = 0;
									
							for (int r=0; r < numberOfRepetitions; r++) 
							{
								long startTime = System.nanoTime();
								PQLQueryResult queryResult = pqlAPI.query(query);
								long stopTime = System.nanoTime();
									
								time += (stopTime-startTime);
								answersCount += queryResult.getSearchResults().size();
								
								System.gc();
							}
								
							results.add("3v1;"+(run+1)+";"+(0)+";"+threads+";"+time/numberOfRepetitions+";"+answersCount/numberOfRepetitions+";"+templates.elementAt(t).elementAt(0)+";"+templates.elementAt(t).elementAt(1)+";"+templates.elementAt(t).elementAt(2)+";"+templates.elementAt(t).elementAt(3)+";"+templates.elementAt(t).elementAt(4)+query+"\r\n");
							
							pqlAPI.disconnect();
					
			}
		}		
	} 
	
	//store and index nets
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
		for(int j=0; j<numberOfBots; j++)
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
			
			
			PQLBotEx bot = new PQLBotEx(connection, "bot"+i+j, index, mc, IndexType.PREDICATES, indexTime, 0);
			bot.start();
			bots.add(bot);
			}
		
		try{
			for (PQLBotEx bot : bots) 
			{
			    bot.join();
			}
		} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Indexing completed for k: " + (i+1) + " run: " + (run+1));
		
		//perform querying for 1 k
		for(int t=0; t<templates.size(); t++)
		{
			String template = templates.elementAt(t).elementAt(4);
			System.out.println(template);
			
			for(int n=0; n<nPerTemplate; n++)
			{
				String query = generateQuery(labels, template);
				System.out.println(query);
				
				//querying
						for(int threads=1; threads<(numberOfThreads+1); threads++)
						{
							PQLAPI	pqlAPI = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
											iniFile.getPostgreSQLHost(), iniFile.getPostgreSQLName(), iniFile.getPostgreSQLUser(), iniFile.getPostgreSQLPassword(),
											iniFile.getLoLA2Path(),
											iniFile.getLabelSimilaritySeacrhConfiguration(),
											iniFile.getIndexType(),
											iniFile.getLabelManagerType(),
											iniFile.getDefaultLabelSimilarityThreshold(),
											iniFile.getIndexedLabelSimilarityThresholds(),
											threads,
											iniFile.getDefaultBotMaxIndexTime(),
											iniFile.getDefaultBotSleepTime());
								
								long time = 0L;
								int answersCount = 0;
										
								for (int r=0; r < numberOfRepetitions; r++) 
								{
									long startTime = System.nanoTime();
									PQLQueryResult queryResult = pqlAPI.query(query);
									long stopTime = System.nanoTime();
										
									time += (stopTime-startTime);
									answersCount += queryResult.getSearchResults().size();
								}
									
								results.add("3v1;"+(run+1)+";"+(i+1)+";"+threads+";"+time/numberOfRepetitions+";"+answersCount/numberOfRepetitions+";"+templates.elementAt(t).elementAt(0)+";"+templates.elementAt(t).elementAt(1)+";"+templates.elementAt(t).elementAt(2)+";"+templates.elementAt(t).elementAt(3)+";"+templates.elementAt(t).elementAt(4)+query+"\r\n");
								
								pqlAPI.disconnect();
						
				}
			}		
		} 
}

}//run complete

File Results = writeCSV(results,".\\Ex3v1Results.csv");

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
	
	public static Vector<Vector<String>> getTemplates(Vector<Vector<String>> templates) 
	{
		String filePath = "./templates.csv";
		File file = new File(filePath);
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) 
		{
		    for(String line; (line = br.readLine()) != null; ) 
		    {
		    	Vector<String> templateLine = new Vector<String>();
		    	String[] arr = line.split("\\?");
		        for(String s: arr)
		        {
		        	templateLine.add(s);
		        }
		        		    	
		    	templates.add(templateLine);
		    }
		}catch(Exception e){e.printStackTrace();};

		return templates;
	}

	public static Vector<String> getLabels(Connection connection) throws SQLException {
		Set<String> result = new HashSet<String>();
		Vector<String> labels = new Vector<String>();
		PreparedStatement cs = connection.prepareStatement("SELECT label FROM pql.jbpt_labels;");
		ResultSet res = cs.executeQuery();
		
		while (res.next()) {
			result.add(res.getString(1));
		}
		
		labels.addAll(result);
		
		return labels;
	}
	
	public static int randInt(int min, int max) {
	    
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	
	public static String generateQuery(Vector<String> labels, String template)
	{
		String query = template;
		int numberOfReplacements = template.length() - template.replace("%", "").length();
		
		for(int i=0; i<numberOfReplacements; i++)
		{
			String randLabel = labels.elementAt(randInt(0,labels.size()-1));
			query = query.replaceFirst("%", randLabel);
		}
		return query;
	}

}
