package org.pql.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import org.pql.api.PQLAPI;
import org.pql.core.PQLTrace;
import org.pql.ini.PQLIniFile;
import org.pql.query.PQLQueryResult;

public class PQLExecutesExperiment1v2 {
	
		
public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
	
	  int numberOfRepetitions = Integer.parseInt(args[0]); //4
	  int maxTraceLength = Integer.parseInt(args[1]); //10
	  int numberOfExperiments = Integer.parseInt(args[2]); //1000
	
		PQLIniFile iniFile = new PQLIniFile();
		if (!iniFile.load()) {
			System.out.println("ERROR: Cannot load PQL ini file.");
			return;
		}
		
	LabelLoader ll = new LabelLoader(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword());
		
	//results - details	
	Vector<String> results = new Vector<String>();
	String sepLine = "sep=;\r\n";
	results.add(sepLine);
	String titleLine = "Experiment#;numberOfRepetitions;traceLength;numberOfThreads;numberOfAsterisks;numberOfTildas;totalTime;timePerQuery;answersPerQuery;trace;filteredModels\r\n";
	results.add(titleLine);
	
	//results - summary
	String summaryTitle = "traceLength;avgTime;avgAnswers;maxTime;minTime\r\n";
	Vector<String> summary = new Vector<String>();
	summary.add(sepLine);
	summary.add(summaryTitle);

	PQLAPI	pqlAPI = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
			iniFile.getPostgreSQLHost(), iniFile.getPostgreSQLName(), iniFile.getPostgreSQLUser(), iniFile.getPostgreSQLPassword(),
			iniFile.getLoLA2Path(),
			iniFile.getLabelSimilaritySeacrhConfiguration(),
			iniFile.getIndexType(),
			iniFile.getLabelManagerType(),
			iniFile.getDefaultLabelSimilarityThreshold(),
			iniFile.getIndexedLabelSimilarityThresholds(),
			1,
			iniFile.getDefaultBotMaxIndexTime(),
			iniFile.getDefaultBotSleepTime());

	for(int traceLength=4; traceLength <= maxTraceLength; traceLength++)
	{
		Double totalTimePerQuery = 0.0;
		Double totalAnswersPerQuery = 0.0;
		Double min = 1000000000000000000000000000.0;
		Double max = -1.0;
		
		for(int experiment=1; experiment <= numberOfExperiments; experiment++)
		{
	
			PQLTrace trace = ll.getTrace(traceLength);
			
			int numberOfAsterisks = ll.randInt(0, 2);
					
			trace = ll.addAsterisks(trace, numberOfAsterisks);
			
			int numberOfTildas = ll.randInt(0, 2);
							
			trace = ll.addTildas(trace, numberOfTildas);
					
			String queryTrace = ll.getQueryTrace(trace);
			String pqlQuery = "SELECT * FROM * WHERE Executes(<"+queryTrace+">);";
							
							long time = 0L;
							int answersCount = 0;
							int filteredModels = 0;
									
							for (int i=0; i < numberOfRepetitions; i++) 
							{
								long start = System.nanoTime();
								PQLQueryResult queryResult = pqlAPI.query(pqlQuery);
								long stop = System.nanoTime();
									
								if(i>0)
								{
									time += (stop-start);
									answersCount += queryResult.getSearchResults().size();
									filteredModels += queryResult.filteredModels.get();
								}
							}
							
							totalTimePerQuery += (double)time/(numberOfRepetitions-1);
							totalAnswersPerQuery += (double)answersCount/(numberOfRepetitions-1);
				 			max = Math.max(max,(double)time/(numberOfRepetitions-1));		
				 			min = Math.min(min,(double)time/(numberOfRepetitions-1));				
					 									
							String outcomeLine = experiment + ";"+numberOfRepetitions + ";"+traceLength+";1;"+numberOfAsterisks+";"+numberOfTildas+";"+time+";"+(double)time/(numberOfRepetitions-1)+";"+(double)answersCount/(numberOfRepetitions-1)+";<"+queryTrace+">;"+(double)filteredModels/(numberOfRepetitions-1)+"\r\n";
							results.add(outcomeLine);
						
			}
		
		String summaryLine = traceLength + ";" + totalTimePerQuery/numberOfExperiments + ";" + totalAnswersPerQuery/numberOfExperiments + ";" + max + ";" + min + "\r\n";
		summary.add(summaryLine);
		System.out.println(summaryLine);
	}

pqlAPI.disconnect();
		
File Results = writeCSV(results,".\\Ex1results.csv");
File Summary = writeCSV(summary,".\\Ex1summary.csv");
			
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
