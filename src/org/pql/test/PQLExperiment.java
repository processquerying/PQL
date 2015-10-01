package org.pql.test;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import org.jbpt.persist.MySQLConnectionStatic;
import org.pql.api.PQLAPI;
import org.pql.ini.PQLIniFile;
import org.pql.query.PQLQueryResult;

public class PQLExperiment extends TestCase {
	private static PQLAPI	pqlAPI	= null;
	private static int numberOfExperiments = 100;
	private static List<String> labels = null;
	
	private static Random rand = new Random(System.currentTimeMillis());
	
	public PQLExperiment() throws ClassNotFoundException, SQLException, IOException {
		PQLIniFile iniFile = new PQLIniFile();
		if (!iniFile.load()) {
			System.out.println("ERROR: Cannot load PQL ini file.");
			return;
		}
		
		PQLExperiment.pqlAPI = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
				iniFile.getPostgreSQLHost(), iniFile.getPostgreSQLName(), iniFile.getPostgreSQLUser(), iniFile.getPostgreSQLPassword(),
				iniFile.getLoLA2Path(),
				iniFile.getLabelSimilaritySeacrhConfiguration(),
				iniFile.getThreeValuedLogicType(),  
				iniFile.getIndexType(),
				iniFile.getLabelManagerType(),
				iniFile.getDefaultLabelSimilarityThreshold(),
				iniFile.getIndexedLabelSimilarities(),
				iniFile.getNumberOfQueryThreads(),
				iniFile.getDefaultBotMaxIndexTime(),
				iniFile.getDefaultBotSleepTime());
		
		// load all labels
		LabelLoader ll = new LabelLoader(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword());
		PQLExperiment.labels = ll.getLabels();
	}

	public void test001() throws ClassNotFoundException, SQLException {
		String pqlQueryTpl = "SELECT * FROM * WHERE CanConflict(\"%s\",\"%s\");";
		
		long time = 0L;
		int answersCount = 0;
		for (int i=0; i< PQLExperiment.numberOfExperiments; i++) {
			String label1 = PQLExperiment.labels.get(PQLExperiment.randInt(0,PQLExperiment.randInt(0, PQLExperiment.labels.size()-1)));
			String label2 = PQLExperiment.labels.get(PQLExperiment.randInt(0,PQLExperiment.randInt(0, PQLExperiment.labels.size()-1)));
			
			String pqlQuery = String.format(pqlQueryTpl, label1, label2);
			
			long start = System.currentTimeMillis();
			PQLQueryResult queryResult = PQLExperiment.pqlAPI.query(pqlQuery);
			long stop = System.currentTimeMillis();
			
			time += (stop-start);
			
			answersCount += queryResult.getSearchResults().size();
		}
		
		System.out.println("PQL query No. 1");
		System.out.println("Total time:\t"+time);
		System.out.println("Time per query:\t"+time/PQLExperiment.numberOfExperiments);
		System.out.println("Answers per query:\t"+(double)answersCount/PQLExperiment.numberOfExperiments);
	}
	
	public static int randInt(int min, int max) {
	    
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public class LabelLoader extends MySQLConnectionStatic {

		protected LabelLoader(String url, String user, String password) throws ClassNotFoundException, SQLException {
			super(url, user, password);
		} 
		
		public List<String> getLabels() throws SQLException {
			List<String> result = new ArrayList<String>();
			
			PreparedStatement cs = connection.prepareStatement("SELECT label FROM pql.jbpt_labels;");
			ResultSet res = cs.executeQuery();
			
			while (res.next()) {
				result.add(res.getString(1));
			}
			
			return result;
		}
	}
}
