package org.pql.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import org.jbpt.petri.persist.AbstractPetriNetPersistenceLayerMySQL;
import org.jbpt.petri.persist.IPetriNetPersistenceLayer;
import org.pql.api.PQLAPI;
import org.pql.core.PQLTrace;
import org.pql.ini.PQLIniFile;
import org.pql.query.PQLQueryResult;

public class PQLTestInsertSelect {
// do changes in PQLQueryThread and PQLBasicPredicatesMySQL (commented)	
@SuppressWarnings({ "rawtypes", "static-access" })
public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
	
	  int numberOfExperiments = Integer.parseInt(args[0]); //1000
	
	  PQLIniFile iniFile = new PQLIniFile();
		if (!iniFile.load()) {
			System.out.println("ERROR: Cannot load PQL ini file.");
			return;
		}
		
	LabelLoader ll = new LabelLoader(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(), 12, 21);
	
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
		
		IPetriNetPersistenceLayer	PL = new AbstractPetriNetPersistenceLayerMySQL(pqlAPI.getConnection());
		
		for(int experiment=1; experiment <= numberOfExperiments; experiment++)
		{
			int traceLength = ll.randInt(4, 10);
			
			PQLTrace trace = ll.getTrace(traceLength);
			
			int numberOfAsterisks = ll.randInt(0, 2);
					
			trace = ll.addAsterisks(trace, numberOfAsterisks);
			
			int numberOfTildas = ll.randInt(0, 2);
							
			trace = ll.addTildas(trace, numberOfTildas);
					
			String queryTrace = ll.getQueryTrace(trace);
			
			for(int i=44000; i<50000; i++)
			PL.deleteNetSystem(i);
			
			String setup = experiment + ";"+traceLength+";"+numberOfAsterisks+";"+numberOfTildas+";<"+queryTrace+">;";
			String insertQuery = "INSERT <"+queryTrace+"> INTO *;";
			PQLQueryResult insertQueryResult = pqlAPI.query(insertQuery,setup);
			String selectQuery = "SELECT * FROM * WHERE Executes(<"+queryTrace+">);";
			PQLQueryResult selectQueryResult = pqlAPI.query(selectQuery,setup);
					
			System.out.println("Experiment: " + experiment);
			if(!selectQueryResult.getSearchResults().containsAll(insertQueryResult.getSearchResults()))
			{System.out.println(queryTrace);
			System.out.println("insertQueryResult: " + insertQueryResult.getSearchResults());
			System.out.println("selectQueryResult: " + selectQueryResult.getSearchResults());}
					 	 				 			
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
