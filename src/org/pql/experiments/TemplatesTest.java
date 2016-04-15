package org.pql.experiments;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.parsers.ParserConfigurationException;
import org.jbpt.persist.MySQLConnection;
import org.jbpt.throwable.SerializationException;
import org.json.JSONException;
import org.pql.ini.PQLIniFile;
import org.pql.query.IPQLQuery;
import org.pql.query.PQLQueryMySQL;
import org.xml.sax.SAXException;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerLevenshtein;
import org.pql.label.LabelManagerLuceneVSM;
import org.pql.label.LabelManagerThemisVSM;

public class TemplatesTest {

public static void main(String[] args) throws JSONException, ClassNotFoundException, SQLException, IOException, ParserConfigurationException, SAXException, SerializationException
{
	
	PQLIniFile iniFile = new PQLIniFile();
	if (!iniFile.load()) {
		System.out.println("ERROR: Cannot load PQL ini file.");
		return;
	}
	
	Connection 	con = (new MySQLConnection(iniFile.getMySQLURL(),iniFile.getMySQLUser(),iniFile.getMySQLPassword())).getConnection();
	
	ILabelManager labelMngrNP = null;
    
    switch (iniFile.getLabelManagerType()) {
		case THEMIS_VSM:
			labelMngrNP = new LabelManagerThemisVSM(con,
					iniFile.getPostgreSQLHost(),iniFile.getPostgreSQLName(),iniFile.getPostgreSQLUser(),iniFile.getPostgreSQLPassword(),
					iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
			break;
		case LUCENE:
			labelMngrNP = new LabelManagerLuceneVSM(con,
					iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds(),iniFile.getLabelSimilaritySeacrhConfiguration());
			break;
		default:
			labelMngrNP = new LabelManagerLevenshtein(con,
					iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
			break;
    }

	//------------------------------------
	Vector<Vector<String>> templates = new Vector<Vector<String>>();
	templates = Experiment3v1.getTemplates(templates);
	System.out.println(templates);
	
	Vector<String> labels = new Vector<String>();
	labels = Experiment3v1.getLabels(con);
	System.out.println(labels);
	
	for(int t=0; t<templates.size(); t++)
	{
		String template = templates.elementAt(t).elementAt(4);
		String query = Experiment3v1.generateQuery(labels, template);
		System.out.println(query);
		IPQLQuery q = new PQLQueryMySQL(new AtomicInteger(0), con, query, labelMngrNP);
		System.out.println(q.getNumberOfParseErrors());
	}
	
}
	
}
