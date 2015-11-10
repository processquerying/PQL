package org.pql.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import org.pql.api.PQLAPI;
import org.pql.core.PQLTrace;
import org.pql.ini.PQLIniFile;
import org.pql.query.PQLQueryResult;

public class PQLExecutesExperiment3v2 {
	
		
public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
	
	  int numberOfRepetitions = Integer.parseInt(args[0]); //4
	  int maxNumberOfThreads = Integer.parseInt(args[1]); //8
	  int numberOfExperiments = Integer.parseInt(args[2]); //1000
	  int indexIncrement = Integer.parseInt(args[3]); //50
	  
	
	PQLIniFile iniFile = new PQLIniFile();
	if (!iniFile.load()) {
		System.out.println("ERROR: Cannot load PQL ini file.");
		return;
	}
	
	//used for indexing
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

	Set<String> externalIDs = new HashSet<String>();
	externalIDs = api.getExternalIDs();
	
	//generate 1000 traces with random length (4-10), number of asterisks (0-2) and tildas (0-2)
	LabelLoader ll = new LabelLoader(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(), externalIDs);
	Vector<String> traces = new Vector<String>();
	Vector<Integer> traceLengthVector = new Vector<Integer>();
	Vector<Integer> numberOfAsterisksVector = new Vector<Integer>();
	Vector<Integer> numberOfTildasVector = new Vector<Integer>();
	
	for(int i=0; i<numberOfExperiments; i++)
	{
		int traceLength = ll.randInt(4, 10);
		PQLTrace trace = ll.getTrace(traceLength);
		traceLengthVector.add(traceLength);
		
		int numberOfAsterisks = ll.randInt(0, 2);
		trace = ll.addAsterisks(trace, numberOfAsterisks);
		numberOfAsterisksVector.add(numberOfAsterisks);
		
		int numberOfTildas = ll.randInt(0, 2);
		trace = ll.addTildas(trace, numberOfTildas);
		numberOfTildasVector.add(numberOfTildas);
				
		String queryTrace = ll.getQueryTrace(trace);
		traces.add(queryTrace);
	
	}
	
	api.reset();
		
	//results - details	
	Vector<String> results = new Vector<String>();
	String sepLine = "sep=;\r\n";
	results.add(sepLine);
	String titleLine = "indexedModels;Experiment#;numberOfRepetitions;traceLength;numberOfThreads;numberOfAsterisks;numberOfTildas;totalTime;timePerQuery;answersPerQuery;trace;filteredModels\r\n";
	results.add(titleLine);
	
	//results - summary
	String avgTitleLine = ";";
	for(int i=0; i<maxNumberOfThreads-1; i++)
	{
		avgTitleLine += (i+1)+";";
	}
	avgTitleLine += maxNumberOfThreads+"\r\n";
	
	Vector<String> avgTimeResults = new Vector<String>();
	avgTimeResults.add(sepLine);
	avgTimeResults.add(avgTitleLine);

	Vector<String> minResults = new Vector<String>();
	minResults.add(sepLine);
	minResults.add(avgTitleLine);
	
	Vector<String> maxResults = new Vector<String>();
	maxResults.add(sepLine);
	maxResults.add(avgTitleLine);

int indexedModels = 0;

while(!externalIDs.isEmpty())
{	
	for(int i=0; i < indexIncrement; i++)
	{
	
	if(externalIDs.iterator().hasNext())
		{	
			String nextID = externalIDs.iterator().next();
			api.storeModel(new File("./pnml/sap/"+nextID),nextID);
			api.index(api.getInternalID(nextID));
			externalIDs.remove(nextID);
			indexedModels ++;
			System.out.println("Indexed net\t\t"+nextID);
		}
	}
	
	
	
	Vector<Double> totalTimePerQuery = new Vector<Double>();
	Vector<Double> min = new Vector<Double>();
	Vector<Double> max = new Vector<Double>();
	
	for(int i=0; i<maxNumberOfThreads; i++)
	{
		totalTimePerQuery.add(0.0);
		max.add(-1.0);
		min.add(1000000000000000000000000000.0);
	}
		
		for(int experiment=0; experiment < numberOfExperiments; experiment++)
		{
	
			String queryTrace = traces.elementAt(experiment);
			String pqlQuery = "SELECT * FROM * WHERE Executes(<"+queryTrace+">);";
							
				for(int numberOfThreads=1; numberOfThreads <= maxNumberOfThreads; numberOfThreads++)	
					{
								
						PQLAPI	pqlAPI = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
										iniFile.getPostgreSQLHost(), iniFile.getPostgreSQLName(), iniFile.getPostgreSQLUser(), iniFile.getPostgreSQLPassword(),
										iniFile.getLoLA2Path(),
										iniFile.getLabelSimilaritySeacrhConfiguration(),
										iniFile.getIndexType(),
										iniFile.getLabelManagerType(),
										iniFile.getDefaultLabelSimilarityThreshold(),
										iniFile.getIndexedLabelSimilarityThresholds(),
										numberOfThreads,
										iniFile.getDefaultBotMaxIndexTime(),
										iniFile.getDefaultBotSleepTime());
							
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
							
							totalTimePerQuery.set(numberOfThreads-1, totalTimePerQuery.elementAt(numberOfThreads-1) + (double)time/(numberOfRepetitions-1));
							min.set(numberOfThreads-1, Math.min(min.elementAt(numberOfThreads-1),(double)time/(numberOfRepetitions-1)));				
				 			max.set(numberOfThreads-1, Math.max(max.elementAt(numberOfThreads-1),(double)time/(numberOfRepetitions-1)));				
							
							String outcomeLine = indexedModels +";"+ (experiment+1) + ";"+numberOfRepetitions + ";"+traceLengthVector.elementAt(experiment)+";"+numberOfThreads+";"+numberOfAsterisksVector.elementAt(experiment)+";"+numberOfTildasVector.elementAt(experiment)+";"+time+";"+(double)time/(numberOfRepetitions-1)+";"+(double)answersCount/(numberOfRepetitions-1)+";<"+queryTrace+">;"+(double)filteredModels/(numberOfRepetitions-1)+"\r\n";
							results.add(outcomeLine);
						
							pqlAPI.disconnect();
					}
			
	}	
		
	String avgTimeLine = indexedModels + ";";
	String minLine = indexedModels + ";";
	String maxLine = indexedModels + ";";
	
	for(int i=0; i<maxNumberOfThreads-1; i++)
	{
		avgTimeLine += totalTimePerQuery.elementAt(i)/numberOfExperiments + ";";
		minLine += min.elementAt(i) + ";";
		maxLine += max.elementAt(i) + ";";
	}
	avgTimeLine += totalTimePerQuery.elementAt(maxNumberOfThreads-1)/numberOfExperiments + "\r\n";
	minLine += min.elementAt(maxNumberOfThreads-1) + "\r\n";
	maxLine += max.elementAt(maxNumberOfThreads-1) + "\r\n";
	
	avgTimeResults.add(avgTimeLine);
	minResults.add(minLine);
	maxResults.add(maxLine);

	System.out.println("Indexed:\t\t"+indexedModels);
	System.out.println("AVG TIME\t\t"+avgTimeLine);
	System.out.println("MIN TIME\t\t"+minLine);
	System.out.println("MAX TIME\t\t"+maxLine);

}

ll.disconnect();

File allresults = writeCSV(results,".\\Ex3results.csv");
File avgTimeresults = writeCSV(avgTimeResults,".\\Ex3avgTimeResults.csv");
File minresults = writeCSV(minResults,".\\Ex3minResults.csv");
File maxresults = writeCSV(maxResults,".\\Ex3maxResults.csv");

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
