package org.pql.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import org.pql.api.PQLAPI;
import org.pql.core.PQLTrace;
import org.pql.ini.PQLIniFile;
import org.pql.query.PQLQueryResult;

public class PQLExperiment2Insert {
	
//for this experiment function checkQueryExperiment2Insert in class PQLQueryThread was used
	
@SuppressWarnings({ "rawtypes", "unused", "static-access" })
public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
	
	  int numberOfExperiments = Integer.parseInt(args[0]); //1000
	
	  PQLIniFile iniFile = new PQLIniFile();
		if (!iniFile.load()) {
			System.out.println("ERROR: Cannot load PQL ini file.");
			return;
		}
		
	LabelLoader ll = new LabelLoader(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword());
	
	Vector<String> results = new Vector<String>();
	results.add("sep=;\r\n");
	results.add("Experiment#;traceLength;numberOfAsterisks;numberOfTildas;trace;ID;modelSize;numberOfPlaces;numberOfTransitions;numberOfArcs;netRepaired;time1;time2;time\r\n");  
	File Results = writeCSV(results,".\\Ex2InsertResults.csv");
			
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
		
		for(int experiment=1; experiment <= numberOfExperiments; experiment++)
		{
			int traceLength = ll.randInt(4, 10);
			
			PQLTrace trace = ll.getTrace(traceLength);
			
			int numberOfAsterisks = ll.randInt(0, 2);
					
			trace = ll.addAsterisks(trace, numberOfAsterisks);
			
			int numberOfTildas = ll.randInt(0, 2);
							
			trace = ll.addTildas(trace, numberOfTildas);
					
			String queryTrace = ll.getQueryTrace(trace);
			
			String setup = experiment + ";"+traceLength+";"+numberOfAsterisks+";"+numberOfTildas+";<"+queryTrace+">;";
		
			String pqlQuery = "INSERT <"+queryTrace+"> INTO *;";
			
			PQLQueryResult queryResult = pqlAPI.query(pqlQuery,setup);
					
			System.out.println("Experiment: " + experiment);
		}
		
		pqlAPI.disconnect();
	
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
