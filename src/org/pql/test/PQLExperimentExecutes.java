package org.pql.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import org.pql.api.AbstractPQLAPI;
import org.pql.api.PQLAPI;
import org.pql.core.PQLTrace;
import org.pql.ini.PQLIniFile;
import org.pql.query.PQLQueryResult;

public class PQLExperimentExecutes {
	private static PQLAPI pqlAPI	= null;
	static LabelLoader ll = null;
	
public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
	
	  int numberOfExperiments = Integer.parseInt(args[0]); //6
	  int maxTraceLength = Integer.parseInt(args[1]); //10
	  int maxNumberOfTildas = Integer.parseInt(args[2]); //5
	  int maxNumberOfAsterisks = Integer.parseInt(args[3]); //5
	  int maxNumberOfThreads = Integer.parseInt(args[4]); //2
	
		PQLIniFile iniFile = new PQLIniFile();
		if (!iniFile.load()) {
			iniFile.create();
			iniFile.load();
			//System.out.println("ERROR: Cannot load PQL ini file.");
			//return;
		}

			
	Vector<String> results = new Vector<String>();
	String titleLine = "numberOfExperiments;traceLength;numberOfThreads;numberOfAsterisks;numberOfTildas;totalTime;timePerQuery;answersPerQuery;trace\r\n";

	results.add(titleLine);
	ll = new LabelLoader(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword());
	
	for(int traceLength=0; traceLength <= maxTraceLength; traceLength++)
	{
		PQLTrace trace = ll.getTrace(traceLength);
			
		for(int numberOfThreads=1; numberOfThreads <= maxNumberOfThreads; numberOfThreads++)	
		{
		
			pqlAPI = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
					iniFile.getPostgreSQLHost(), iniFile.getPostgreSQLName(), iniFile.getPostgreSQLUser(), iniFile.getPostgreSQLPassword(),
					iniFile.getLoLA2Path(),
					iniFile.getLabelSimilaritySeacrhConfiguration(),
					iniFile.getThreeValuedLogicType(),  
					iniFile.getIndexType(),
					iniFile.getLabelManagerType(),
					iniFile.getDefaultLabelSimilarityThreshold(),
					iniFile.getIndexedLabelSimilarityThresholds(),
					numberOfThreads,
					iniFile.getDefaultBotMaxIndexTime(),
					iniFile.getDefaultBotSleepTime());
			
			
			for(int numberOfAsterisks=0; numberOfAsterisks <= maxNumberOfAsterisks; numberOfAsterisks++)	
			{
				if(numberOfAsterisks > trace.getTrace().size()) continue;
				
				trace = ll.addAsterisks(trace, numberOfAsterisks);
				
				for(int numberOfTildas=0; numberOfTildas <= maxNumberOfTildas; numberOfTildas++)	
				{
					if(numberOfTildas > (trace.getTrace().size() - numberOfAsterisks)) continue;
					
					trace = ll.addTildas(trace, numberOfTildas);
				
					String queryTrace = ll.getQueryTrace(trace);
					System.out.println("Trace: <"+queryTrace+">");
					String pqlQuery = "SELECT * FROM * WHERE Executes(<"+queryTrace+">);";
					
					long time = 0L;
					int answersCount = 0;
						
					for (int i=0; i< numberOfExperiments; i++) 
					{
						
						//long start = System.currentTimeMillis();
						long start = System.nanoTime();
						PQLQueryResult queryResult = pqlAPI.query(pqlQuery);
						long stop = System.nanoTime();
						//long stop = System.currentTimeMillis();
						
						if(i>0)
						{
							time += (stop-start);
							answersCount += queryResult.getSearchResults().size();
						}
					}
					
					//System.out.println("Total time:\t"+time);
					System.out.println("Number of threads: "+numberOfThreads);
					System.out.println("Time per query:\t"+(double)time/(numberOfExperiments-1));
					//System.out.println("Answers per query:\t"+(double)answersCount/(numberOfExperiments-1));
					
					String outcomeLine = numberOfExperiments + ";"+traceLength+";"+numberOfThreads+";"+numberOfAsterisks+";"+numberOfTildas+";"+time+";"+(double)time/(numberOfExperiments-1)+";"+(double)answersCount/(numberOfExperiments-1)+";<"+queryTrace+">\r\n";
					
					results.add(outcomeLine);
					trace = ll.removeTildas(trace);
				}
				trace = ll.removeAsterisks(trace);
			}
		pqlAPI.disconnect();
				
		}
	}	
		File file = writeCSV(results);

	}
	
	public static File writeCSV(Vector<String> lines) throws IOException {
		
		File file = new File(".\\results.csv");
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
