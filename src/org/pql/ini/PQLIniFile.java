package org.pql.ini;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.ini4j.Ini;
import org.pql.index.IndexType;
import org.pql.label.LabelManagerType;

/**
 * PQL ini file manager.
 * 
 * @author Artem Polyvyanyy
 */
public class PQLIniFile implements IPQLIniFile {
	final private String iniFile = "./PQL.ini";
	
	private String lola2Path = null;
	
	private Double 		defaultLabelSimilarityThreshold		= null;
	private Set<Double> indexedLabelSimilarityThresholds	= null;
	
	private LabelManagerType labelManagerType		= null;
	private String		labelSimilarityConfig		= null;
	private String 		postgresqlHost				= null;
	private String 		postgresqlName				= null;
	private String 		postgresqlUser				= null;
	private String 		postgresqlPwd				= null;	
	private String 		mysqlURL					= null;
	private String 		mysqlUser					= null;
	private String 		mysqlPwd					= null;
	private Integer 	defaultBotSleepTime			= 300;
	private Integer 	defaultBotMaxIndexTime		= 86400;
	private Integer		numberOfQueryThreads		= 1;
	
	@Override
	public boolean create() {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(this.iniFile));
			out.write("[mysql]\n");
			out.write("url = jdbc:mysql://localhost:3306/mysql\n");
			out.write("user = user\n");
			out.write("password = password\n");
			out.write("\n");
			out.write("[postgresql]\n");
			out.write("host = localhost\n");
			out.write("name = themis\n");
			out.write("user = user\n");
			out.write("password = password\n");
			out.write("\n");
			out.write("[lola]\n");
			out.write("lolaPath = .\\\\lola2\\\\win\\\\lola.exe\n");
			out.write("\n");
			out.write("[pql]\n");
			out.write("labelSimilaritySearch = lucene\n");
			out.write("labelSimilarityConfig = ./lucene/\n");
			out.write("defaultLabelSimilarityThreshold = 0.75\n");
			out.write("indexedLabelSimilarityThresholds = 0.5,0.75,1.0\n");
			out.write("numberOfQueryThreads = 2\n");
			out.write("\n");
			out.write("[bot]\n");
			out.write("defaultBotSleepTime = 300\n");
			out.write("defaultBotMaxIndexTime = 86400\n");
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
	
	@Override
	public boolean load() {
		try {
			File file = new File(this.iniFile);
			
			// read configuration from file
			Ini ini = new Ini();
			ini.load(new FileReader(file));
			
			// load lola section
	        Ini.Section section = ini.get("lola");
	        this.lola2Path = section.get("lolaPath");
	        
	        // load pql section
	        section = ini.get("pql");
	        String labelSimilaritySearch = section.get("labelSimilaritySearch").trim().toLowerCase();
	        switch (labelSimilaritySearch) {
		        case "lucene":
		        	this.labelManagerType = LabelManagerType.LUCENE;
		        	break;
		        case "vsm":
		        	this.labelManagerType = LabelManagerType.THEMIS_VSM;
		        	break;
		        default:
		        	this.labelManagerType = LabelManagerType.LEVENSHTEIN;
	        }
	        this.labelSimilarityConfig = section.get("labelSimilarityConfig").trim();
	        this.numberOfQueryThreads = Integer.parseInt(section.get("numberOfQueryThreads"));
	        this.defaultLabelSimilarityThreshold = Double.parseDouble(section.get("defaultLabelSimilarityThreshold"));
	        String similarities = section.get("indexedLabelSimilarityThresholds");
	        StringTokenizer st = new StringTokenizer(similarities,",");
	        this.indexedLabelSimilarityThresholds = new HashSet<Double>();
	        while (st.hasMoreTokens())  this.indexedLabelSimilarityThresholds.add(Double.parseDouble(st.nextToken()));	        
	        this.indexedLabelSimilarityThresholds.add(1.0);
	        this.indexedLabelSimilarityThresholds.add(this.defaultLabelSimilarityThreshold);
	        
	        // load postgresql section
	        section = ini.get("postgresql");
	        this.postgresqlHost = section.get("host");
	        this.postgresqlName = section.get("name");
	        this.postgresqlUser = section.get("user");
	        this.postgresqlPwd 	= section.get("password");
	        
	        // load mysql section
	        section = ini.get("mysql");
	        this.mysqlURL	= section.get("url");
	        this.mysqlUser	= section.get("user");
	        this.mysqlPwd	= section.get("password");
	        
	        // load bot section
	        section = ini.get("bot");
	        this.defaultBotSleepTime = Integer.parseInt(section.get("defaultBotSleepTime"));
	        this.defaultBotMaxIndexTime = Integer.parseInt(section.get("defaultBotMaxIndexTime"));
	        
	        return true;
		} catch (IOException e) {
			return false;
		} catch (NumberFormatException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public LabelManagerType getLabelManagerType() {
		return this.labelManagerType;
	}

	@Override
	public String getLoLA2Path() {
		return this.lola2Path;
	}

	@Override
	public Double getDefaultLabelSimilarityThreshold() {
		return this.defaultLabelSimilarityThreshold;
	}

	@Override
	public Set<Double> getIndexedLabelSimilarityThresholds() {
		return this.indexedLabelSimilarityThresholds;
	}

	@Override
	public String getPostgreSQLHost() {
		return this.postgresqlHost;
	}

	@Override
	public String getPostgreSQLName() {
		return this.postgresqlName;
	}

	@Override
	public String getPostgreSQLUser() {
		return this.postgresqlUser;
	}

	@Override
	public String getPostgreSQLPassword() {
		return this.postgresqlPwd;
	}

	@Override
	public String getMySQLURL() {
		return this.mysqlURL;
	}

	@Override
	public String getMySQLUser() {
		return this.mysqlUser;
	}

	@Override
	public String getMySQLPassword() {
		return this.mysqlPwd;
	}

	@Override
	public Integer getDefaultBotSleepTime() {
		return this.defaultBotSleepTime;
	}

	@Override
	public Integer getDefaultBotMaxIndexTime() {
		return this.defaultBotMaxIndexTime;
	}

	@Override
	public IndexType getIndexType() {
		return IndexType.PREDICATES;
	}

	@Override
	public Integer getNumberOfQueryThreads() {
		return this.numberOfQueryThreads;
	}

	@Override
	public String getLabelSimilaritySeacrhConfiguration() {
		return this.labelSimilarityConfig;
	}
}
