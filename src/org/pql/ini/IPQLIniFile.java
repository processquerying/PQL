package org.pql.ini;

import java.util.Set;

import org.pql.index.IndexType;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerType;
import org.pql.logic.ThreeValuedLogicType;

/**
 * TODO: document IPQLIniFile interface.
 * Interface to the PQL initialization file.
 * 
 * @author Artem Polyvyanyy
 */
public interface IPQLIniFile {

	/**
	 * Create a PQL initialization file.
	 * 
	 * @return {@code true} if PQL file created successfully; {@code false} otherwise.
	 */
	boolean create();
	
	/**
	 * Load PQL initialization file.
	 * 
	 * @return {@code true} if PQL file loaded successfully; {@code false} otherwise.
	 */
	boolean load();
	
	/**
	 * Get type of {@link ILabelManager} to use with this PQL instance.
	 * 
	 * @return {@link LabelManagerType} to use with this PQL instance. 
	 */
	public LabelManagerType getLabelManagerType();

	/**
	 * Get a path to the LoLA2 executable.
	 * 
	 * LoLA2 tool can be obtained and compiled from http://service-technology.org/lola.
	 * 
	 * @return String that contains a path to the LoLA2 executable file.
	 */
	public String getLoLA2Path();

	/**
	 * Get default label similarity threshold value.
	 * 
	 * The default label similarity threshold is used for the interpretation of the PQL "~" symbol 
	 * when directly preceding a label string, e.g., ~"A" in PQL refers to the set of all labels for 
	 * which the similarity score with "A" is at least the threshold value.
	 * 
	 * The label similarity score is obtained based on the label manager type of this PQL instance,
	 * refer to {@link IPQLIniFile}.{@code getLabelManagerType()}.      
	 * 
	 * @return Default label similarity threshold value to use with this PQL instance.  
	 */
	public Double getDefaultLabelSimilarityThreshold();

	/**
	 * Get indexed label similarity threshold values.
	 * 
	 * The indexed label similarity thresholds are the thresholds that are used for populating
	 * the PQL index, i.e., the PQL instance is optimized for computing behavioral predicates/relations
	 * for these label similarity threshold values.
	 * 
	 * @return Indexed label similarity threshold values to use with this PQL instance.
	 */
	public Set<Double> getIndexedLabelSimilarityThresholds();
		
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
	
	/**
	 * Get label similarity search configuration parameter.
	 * 
	 * @return Label similarity search configuration parameter.
	 */
	public String getLabelSimilaritySeacrhConfiguration();
}
