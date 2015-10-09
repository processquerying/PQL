package org.pql.ini;

import java.util.Set;

import org.pql.api.IPQLAPI;
import org.pql.index.IPQLIndex;
import org.pql.index.IndexType;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerThemisVSM;
import org.pql.label.LabelManagerType;

/**
 * Interface to the PQL initialization file.
 * 
 * @author Artem Polyvyanyy
 */
public interface IPQLIniFile {

	/**
	 * Create the PQL initialization file.
	 * 
	 * @return {@code true} if PQL file created successfully; {@code false} otherwise.
	 */
	boolean create();
	
	/**
	 * Load the PQL initialization file.
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
	 * Get a path to the LoLA2 executable file.
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
	
	/**
	 * Get PostgreSQL host.
	 * This parameter is used when initializing {@link LabelManagerThemisVSM} instances.
	 *
	 * @return PostgreSQL host.
	 */
	public String getPostgreSQLHost();

	/**
	 * Get PostgreSQL name.
	 * This parameter is used when initializing {@link LabelManagerThemisVSM} instances.
	 *
	 * @return PostgreSQL name.
	 */
	public String getPostgreSQLName();

	/**
	 * Get PostgreSQL user.
	 * This parameter is used when initializing {@link LabelManagerThemisVSM} instances.
	 *
	 * @return PostgreSQL user.
	 */
	public String getPostgreSQLUser();

	/**
	 * Get PostgreSQL password.
	 * This parameter is used when initializing {@link LabelManagerThemisVSM} instances.
	 * 
	 * TODO: Secure password!
	 *
	 * @return PostgreSQL password.
	 */
	public String getPostgreSQLPassword();

	/**
	 * Get MySQL URL.
	 * This parameter is used when initializing {@link IPQLAPI} instances.
	 *
	 * @return MySQL URL.
	 */
	public String getMySQLURL();

	/**
	 * Get MySQL user.
	 * This parameter is used when initializing {@link IPQLAPI} instances.
	 *
	 * @return MySQL user.
	 */
	public String getMySQLUser();

	/**
	 * Get MySQL password.
	 * This parameter is used when initializing {@link IPQLAPI} instances.
	 * 
	 * TODO: Secure password!
	 *
	 * @return MySQL password.
	 */
	public String getMySQLPassword();

	/**
	 * Get default time (in seconds) that a PQL Bot sleeps after finishing indexing a model and starting indexing the next model.   
	 * 
	 * @return Time (in seconds) that PQL Bots are allowed to sleep between indexing jobs.
	 */
	public Integer getDefaultBotSleepTime();

	/**
	 * Get default maximal time (in seconds) that a PQL Bot can spend on indexing a model.
	 * 
	 * @return Time (in seconds) that PQL Bots are allowed to spend indexing a single model..
	 */
	public Integer getDefaultBotMaxIndexTime();
	
	/**
	 * Get type of {@link IPQLIndex} to use with this PQL instance.
	 * 
	 * @return {@link IndexType} to use with this PQL instance.
	 */
	public IndexType getIndexType();
	
	/**
	 * Get number of threads to use when interpreting PQL queries.
	 * 
	 * @return Number of threads for interpreting PQL queries.
	 */
	public Integer getNumberOfQueryThreads();
	
	/**
	 * Get label similarity search configuration parameter.
	 * 
	 * @return Label similarity search configuration parameter.
	 */
	public String getLabelSimilaritySeacrhConfiguration();
}
