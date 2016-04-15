package org.pql.experiments;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import org.jbpt.throwable.SerializationException;
import org.json.JSONException;
import org.pql.api.PQLAPI;
import org.pql.ini.PQLIniFile;
import org.pql.query.PQLQueryResult;
import org.xml.sax.SAXException;


public class MacroTest {
		
public static void main(String[] args) throws JSONException, ClassNotFoundException, SQLException, IOException, ParserConfigurationException, SAXException, SerializationException
{
	PQLIniFile iniFile = new PQLIniFile();
	if (!iniFile.load()) {
		System.out.println("ERROR: Cannot load PQL ini file.");
		return;
	}
	
	PQLAPI pqlAPI = new PQLAPI(iniFile.getMySQLURL(), iniFile.getMySQLUser(), iniFile.getMySQLPassword(),
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
	
    long start = System.currentTimeMillis();
	PQLQueryResult queryResult = pqlAPI.query("SELECT * FROM * WHERE (GetTasks() EXCEPT GetTasksAlwaysOccurs(GetTasks()) IS SUBSET OF GetTasksCanOccur(GetTasks()));");
	long end = System.currentTimeMillis();
	System.out.println(end-start);
	Set<String> res = queryResult.getSearchResults();
	System.out.println(res);
	System.out.println(queryResult.getNumberOfParseErrors());

	/*
	SELECT * FROM * WHERE CanOccur({\"A\",\"B\",\"C\"}, ALL);
	SELECT * FROM * WHERE CanOccur({\"A\",\"B\",\"C\"}, ANY);
	SELECT * FROM * WHERE AlwaysOccurs({\"A\",\"B\",\"C\"}, ALL);
	SELECT * FROM * WHERE AlwaysOccurs({\"A\",\"B\",\"C\"}, ANY);
	SELECT * FROM * WHERE Cooccur(\"A\", {\"A\",\"B\",\"C\"}, ALL);
	SELECT * FROM * WHERE Cooccur(\"A\", {\"A\",\"B\",\"C\"}, ANY);
	SELECT * FROM * WHERE Conflict(\"A\", {\"A\",\"B\",\"C\"}, ALL);
	SELECT * FROM * WHERE Conflict(\"A\", {\"A\",\"B\",\"C\"}, ANY);
	SELECT * FROM * WHERE TotalCausal(\"A\", {\"A\",\"B\",\"C\"}, ALL);
	SELECT * FROM * WHERE TotalCausal(\"A\", {\"A\",\"B\",\"C\"}, ANY);
	SELECT * FROM * WHERE TotalConcurrent(\"A\", {\"A\",\"B\",\"C\"}, ALL);
	SELECT * FROM * WHERE TotalConcurrent(\"A\", {\"A\",\"B\",\"C\"}, ANY);
	SELECT * FROM * WHERE Cooccur({\"A\",\"B\",\"C\"}, {\"A\",\"B\",\"C\"}, ALL);
	SELECT * FROM * WHERE Cooccur({\"A\",\"B\",\"C\"}, {\"A\",\"B\",\"C\"}, ANY);
	SELECT * FROM * WHERE Cooccur({\"A\",\"B\",\"C\"}, {\"A\",\"B\",\"C\"}, EACH);
	SELECT * FROM * WHERE Conflict({\"A\",\"B\",\"C\"}, {\"A\",\"B\",\"C\"}, ALL);
	SELECT * FROM * WHERE Conflict({\"A\",\"B\",\"C\"}, {\"A\",\"B\",\"C\"}, ANY);
	SELECT * FROM * WHERE Conflict({\"A\",\"B\",\"C\"}, {\"A\",\"B\",\"C\"}, EACH);
	SELECT * FROM * WHERE TotalCausal({\"A\",\"B\",\"C\"}, {\"A\",\"B\",\"C\"}, ALL);
	SELECT * FROM * WHERE TotalCausal({\"A\",\"B\",\"C\"}, {\"A\",\"B\",\"C\"}, ANY);
	SELECT * FROM * WHERE TotalCausal({\"A\",\"B\",\"C\"}, {\"A\",\"B\",\"C\"}, EACH);
	SELECT * FROM * WHERE TotalConcurrent({\"A\",\"B\",\"C\"}, {\"A\",\"B\",\"C\"}, ALL);
	SELECT * FROM * WHERE TotalConcurrent({\"A\",\"B\",\"C\"}, {\"A\",\"B\",\"C\"}, ANY);
	SELECT * FROM * WHERE TotalConcurrent({\"A\",\"B\",\"C\"}, {\"A\",\"B\",\"C\"}, EACH);
	*/
	
	/*
	SELECT * FROM * WHERE \"A\" IN GetTasksCanOccur(GetTasks());
	SELECT * FROM * WHERE \"A\" IN GetTasksAlwaysoccur(GetTasks());
	SELECT * FROM * WHERE \"A\" IN GetTasksCooccur(GetTasks(), GetTasks(), ALL);
	SELECT * FROM * WHERE \"A\" IN GetTasksCooccur(GetTasks(), GetTasks(), ANY);
	SELECT * FROM * WHERE \"A\" IN GetTasksConflict(GetTasks(), GetTasks(), ALL);
	SELECT * FROM * WHERE \"A\" IN GetTasksConflict(GetTasks(), GetTasks(), ANY);
	SELECT * FROM * WHERE \"A\" IN GetTasksTotalCausal(GetTasks(), GetTasks(), ALL);
	SELECT * FROM * WHERE \"A\" IN GetTasksTotalCausal(GetTasks(), GetTasks(), ANY);
	SELECT * FROM * WHERE \"A\" IN GetTasksTotalConcurrent(GetTasks(), GetTasks(), ALL);
	SELECT * FROM * WHERE \"A\" IN GetTasksTotalConcurrent(GetTasks(), GetTasks(), ANY);
	SELECT * FROM * WHERE \"A\" IN GetTasksAlwaysOccurs(GetTasks()) UNION GetTasksTotalCausal(GetTasks(), GetTasks(), ALL);
	SELECT * FROM * WHERE \"A\" IN GetTasksCanOccur(GetTasks()) INTERSECT GetTasksConflict(GetTasks(), GetTasks(), ANY);
	SELECT * FROM * WHERE \"A\" IN GetTasksCooccur(GetTasks(), GetTasks(), ANY) EXCEPT GetTasksTotalConcurrent(GetTasks(), GetTasks(), ANY);
	SELECT * FROM * WHERE \"A\" IN (GetTasksCooccur(GetTasks(), GetTasks(), ANY) EXCEPT GetTasksTotalConcurrent(GetTasks(), GetTasks(), ANY)) UNION (GetTasksCanOccur(GetTasks()) INTERSECT GetTasksConflict(GetTasks(), GetTasks(), ALL));
	SELECT * FROM * WHERE \"A\" IN (GetTasksCooccur(GetTasks(), GetTasks(), ANY) UNION GetTasksTotalConcurrent(GetTasks(), GetTasks(), ALL)) INTERSECT (GetTasksCanOccur(GetTasks()) EXCEPT GetTasksConflict(GetTasks(), GetTasks(), ALL));
	SELECT * FROM * WHERE (GetTasks() EXCEPT GetTasksConflict(GetTasks(), GetTasks(), ALL)) EQUALS GetTasksTotalCausal(GetTasks(), GetTasks(), ALL); 
    SELECT * FROM * WHERE GetTasksCooccur(GetTasks(), GetTasks(), ANY) NOT EQUALS GetTasksTotalConcurrent(GetTasks(), GetTasks(), ANY); 
    SELECT * FROM * WHERE (GetTasks() EXCEPT GetTasksCooccur(GetTasks(), GetTasks(), ALL)) OVERLAPS WITH GetTasksConflict(GetTasks(), GetTasks(), ANY); 
    SELECT * FROM * WHERE (GetTasks() EXCEPT GetTasksAlwaysOccurs(GetTasks()) IS SUBSET OF GetTasksCanOccur(GetTasks()); 
    SELECT * FROM * WHERE GetTasksTotalCausal(GetTasks(), GetTasks(), ALL) IS PROPER SUBSET OF (GetTasks() EXCEPT GetTasksTotalConcurrent(GetTasks(), GetTasks(), ALL)); 
	*/
	
	/*
	SELECT * FROM * WHERE (GetTasksCooccur(GetTasks(), GetTasks(), ALL)) EQUALS GetTasksTotalConcurrent(GetTasks(), GetTasks(), ALL) OR ((GetTasksCanOccur(GetTasks()) OVERLAPS WITH GetTasksConflict(GetTasks(), GetTasks(), ALL)) AND ((GetTasks() EXCEPT GetTasksTotalCausal(GetTasks(), GetTasks(), ANY)) IS SUBSET OF GetTasksAlwaysOccurs(GetTasks())));
    SELECT * FROM * WHERE (NOT (GetTasksCooccur(GetTasks(), GetTasks(), ANY) OVERLAPS WITH GetTasksTotalConcurrent(GetTasks(), GetTasks(), ANY))) AND ((GetTasksConflict(GetTasks(), GetTasks(), ALL) IS PROPER SUBSET OF GetTasksCanOccur(GetTasks())) AND ((GetTasks() EXCEPT GetTasksTotalCausal(GetTasks(), GetTasks(), ALL)) NOT EQUALS GetTasksAlwaysOccurs(GetTasks())));
	*/
 
}

}
