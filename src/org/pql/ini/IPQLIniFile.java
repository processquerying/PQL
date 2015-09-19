package org.pql.ini;

import java.util.Set;

import org.pql.index.IndexType;
import org.pql.label.LabelManagerType;
import org.pql.logic.ThreeValuedLogicType;

/**
 * Interface to the PQL initialization file.
 * 
 * @author Artem Polyvyanyy
 */
public interface IPQLIniFile {

	/**
	 * Create PQL ini file.
	 * 
	 * @return {@code true} if PQL file created successfully; otherwise {@code false}.
	 */
	boolean create();
	
	/**
	 * Load PQL ini file.
	 * 
	 * @return {@code true} if PQL file loaded successfully; otherwise {@code false}.
	 */
	boolean load();
	
	public LabelManagerType getLabelManagerType();

	public String getLolaPath();

	public Double getDefaultLabelSimilarity();

	public Set<Double> getIndexedLabelSimilarities();
	
	public String getPostgreSQLHost();

	public String getPostgreSQLName();

	public String getPostgreSQLUser();

	public String getPostgreSQLPassword();

	public String getMySQLURL();

	public String getMySQLUser();

	public String getMySQLPassword();

	public Integer getDefaultBotSleepTime();

	public Integer getDefaultBotMaxIndexTime();
	
	public ThreeValuedLogicType getThreeValuedLogicType();
	
	public IndexType getIndexType();
	
	/**
	 * Get number of threads to use when interpreting PQL queries.
	 * 
	 * @return Number of threads.
	 */
	public Integer getNumberOfQueryThreads();
}
