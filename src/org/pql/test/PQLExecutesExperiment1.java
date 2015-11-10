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

public class PQLExecutesExperiment1 {
	
		
public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
	
	  int numberOfRepetitions = Integer.parseInt(args[0]); //4
	  int maxTraceLength = Integer.parseInt(args[1]); //10
	  int maxNumberOfThreads = Integer.parseInt(args[2]); //8
	  int numberOfExperiments = Integer.parseInt(args[3]); //100
	
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
	
	//results - averages
	
	String avgTitleLine = ";";
	for(int i=0; i<maxNumberOfThreads-1; i++)
	{
		avgTitleLine += (i+1)+";";
	}
	avgTitleLine += maxNumberOfThreads+"\r\n";
	
	Vector<String> avgTimeResults = new Vector<String>();
	avgTimeResults.add(sepLine);
	avgTimeResults.add(avgTitleLine);

	Vector<String> avgAnswersResults = new Vector<String>();
	avgAnswersResults.add(sepLine);
	avgAnswersResults.add(avgTitleLine);

	Vector<String> minResults = new Vector<String>();
	minResults.add(sepLine);
	minResults.add(avgTitleLine);
	
	Vector<String> maxResults = new Vector<String>();
	maxResults.add(sepLine);
	maxResults.add(avgTitleLine);


	
	for(int traceLength=4; traceLength <= maxTraceLength; traceLength++)
	{
		Vector<Double> totalTimePerQuery = new Vector<Double>();
		Vector<Double> totalAnswersPerQuery = new Vector<Double>();
		Vector<Double> min = new Vector<Double>();
		Vector<Double> max = new Vector<Double>();
		
		for(int i=0; i<maxNumberOfThreads; i++)
		{
			totalTimePerQuery.add(0.0);
			totalAnswersPerQuery.add(0.0);
			max.add(-1.0);
			min.add(1000000000000000000000000000.0);
		}

		for(int experiment=1; experiment <= numberOfExperiments; experiment++)
		{
	
			PQLTrace trace = ll.getTrace(traceLength);
			
			int numberOfAsterisks = ll.randInt(0, 2);
					
			trace = ll.addAsterisks(trace, numberOfAsterisks);
			
			int numberOfTildas = ll.randInt(0, 2);
							
			trace = ll.addTildas(trace, numberOfTildas);
					
			String queryTrace = ll.getQueryTrace(trace);
			//System.out.println("Trace: <"+queryTrace+">");
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
							AtomicInteger filteredModels = new AtomicInteger(0);
									
							for (int i=0; i < numberOfRepetitions; i++) 
							{
								long start = System.nanoTime();
								PQLQueryResult queryResult = pqlAPI.query(pqlQuery);
								long stop = System.nanoTime();
									
								if(i>0)
								{
									time += (stop-start);
									answersCount += queryResult.getSearchResults().size();
									filteredModels.addAndGet(queryResult.filteredModels.get());
								}
							}
							
							totalTimePerQuery.set(numberOfThreads-1, totalTimePerQuery.elementAt(numberOfThreads-1) + (double)time/(numberOfRepetitions-1));
							totalAnswersPerQuery.set(numberOfThreads-1, totalAnswersPerQuery.elementAt(numberOfThreads-1) + (double)answersCount/(numberOfRepetitions-1));
				 			min.set(numberOfThreads-1, Math.min(min.elementAt(numberOfThreads-1),(double)time/(numberOfRepetitions-1)));				
				 			max.set(numberOfThreads-1, Math.max(max.elementAt(numberOfThreads-1),(double)time/(numberOfRepetitions-1)));				
							
							String outcomeLine = experiment + ";"+numberOfRepetitions + ";"+traceLength+";"+numberOfThreads+";"+numberOfAsterisks+";"+numberOfTildas+";"+time+";"+(double)time/(numberOfRepetitions-1)+";"+(double)answersCount/(numberOfRepetitions-1)+";<"+queryTrace+">;"+(double)filteredModels.get()/(numberOfRepetitions-1)+"\r\n";
							results.add(outcomeLine);
						
							pqlAPI.disconnect();
					}
			}
		
		String avgTimeLine = traceLength + ";";
		String avgAnswersLine = traceLength + ";";
		String minLine = traceLength + ";";
		String maxLine = traceLength + ";";
		for(int i=0; i<maxNumberOfThreads-1; i++)
		{
			avgTimeLine += totalTimePerQuery.elementAt(i)/numberOfExperiments + ";";
			avgAnswersLine+= totalAnswersPerQuery.elementAt(i)/numberOfExperiments + ";";
			minLine += min.elementAt(i) + ";";
			maxLine += max.elementAt(i) + ";";
		}
		avgTimeLine += totalTimePerQuery.elementAt(maxNumberOfThreads-1)/numberOfExperiments + "\r\n";
		avgAnswersLine+= totalAnswersPerQuery.elementAt(maxNumberOfThreads-1)/numberOfExperiments + "\r\n";
		minLine += min.elementAt(maxNumberOfThreads-1) + "\r\n";
		maxLine += max.elementAt(maxNumberOfThreads-1) + "\r\n";
		
		avgTimeResults.add(avgTimeLine);
		avgAnswersResults.add(avgAnswersLine);
		minResults.add(minLine);
		maxResults.add(maxLine);
	
		System.out.println("AVG TIME\t\t"+avgTimeLine);
		System.out.println("AVG Answers\t\t"+avgAnswersLine);
		System.out.println("MIN TIME\t\t"+minLine);
		System.out.println("MAX TIME\t\t"+maxLine);
		System.out.println("---------");
			
}	
		File allresults = writeCSV(results,".\\Ex1results.csv");
		File avgTimeresults = writeCSV(avgTimeResults,".\\Ex1avgTimeResults.csv");
		File avgAnswersresults = writeCSV(avgAnswersResults,".\\Ex1avgAnswersResults.csv");
		File minresults = writeCSV(minResults,".\\Ex1minResults.csv");
		File maxresults = writeCSV(maxResults,".\\Ex1maxResults.csv");


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
