package org.pql.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import org.pql.api.PQLAPI;
import org.pql.ini.PQLIniFile;
import org.pql.query.PQLQueryResult;

public class PQLExperimentExecutesTimeTest {
private static PQLAPI pqlAPI = null;
	
public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
	
	  int numberOfExperiments = 10; 
	  int numberOfThreads = 3; 
	  String comment = "labelChecking";
	
		PQLIniFile iniFile = new PQLIniFile();
		if (!iniFile.load()) {
			System.out.println("ERROR: Cannot load PQL ini file.");
			return;
		}

	Vector<String> results = new Vector<String>();
	String titleLine = "numberOfExperiments;numberOfThreads;totalTime;timePerQuery;answersPerQuery;trace;comment\r\n";
	results.add(titleLine);
		
			pqlAPI = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
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
			
					String trace = "~\"A\",*,\"B\",*";
					//String trace = "";
						
					System.out.println("Trace: <"+trace+">");
					String pqlQuery = "SELECT * FROM * WHERE Executes(<"+trace+">);";
						
					long time = 0L;
					int answersCount = 0;
						
					for (int i=0; i< numberOfExperiments; i++) 
					{
						
						long start = System.currentTimeMillis();
						PQLQueryResult queryResult = pqlAPI.query(pqlQuery);
						long stop = System.currentTimeMillis();
						
							time += (stop-start);
							answersCount += queryResult.getSearchResults().size();
					}
					
					
					System.out.println("Total time: \t"+time);
					System.out.println("Number of threads: \t"+numberOfThreads);
					System.out.println("Time per query: \t"+(double)time/(numberOfExperiments-0));
					System.out.println("Answers per query: \t"+(double)answersCount/(numberOfExperiments-0));
					
					String outcomeLine = numberOfExperiments + ";"+numberOfThreads+";"+time+";"+(double)time/(numberOfExperiments-1)+";"+(double)answersCount/(numberOfExperiments-1)+";<"+trace+">;"+comment+"\r\n";
					
					results.add(outcomeLine);
					
		pqlAPI.disconnect();
			
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
