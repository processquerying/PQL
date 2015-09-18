package org.pql.query;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pql.core.PQLAttribute;
import org.pql.core.PQLException;
import org.pql.core.PQLLocation;
import org.pql.core.PQLTask;
import org.pql.logic.ThreeValuedLogicValue;

/**
 * An interface to a PQL query.
 * 
 * @author Artem Polyvyanyy
 */
public interface IPQLQuery {
	
	/**
	 * Check if a process model (as per configuration, {@link IPQLQuery.configure(obj)}) matches this PQL query. 
	 * 
	 * @return
	 */
	public ThreeValuedLogicValue check();
	
	/**
	 * Configure this PQL query.
	 * 
	 * @param obj A configuration object.
	 * @throws PQLException 
	 */
	public void configure(Object obj) throws PQLException;

	/**
	 * Get all variable declarations of this PQL query.
	 * 
	 * @return The {@link Map} of all (variable name, set of tasks) key-value pairs declared in this PQL query.
	 */
	public Map<String, Set<PQLTask>> getVariables();

	/**
	 * Get all {@link PQLAttribute}s that are specified in this PQL query.
	 * 
	 * @return The {@link Set} of all {@link PQLAttribute}s specified in this PQL query.
	 */
	public Set<PQLAttribute> getAttributes();
	
	/**
	 * Get all {@link PQLLocation}s that are specified in this PQL query.
	 * 
	 * @return The {@link Set} of all {@link PQLLocation}s specified in this PQL query.
	 */
	public Set<PQLLocation> getLocations();
	
	/**
	 * Get task interpretations.
	 * 
	 * @return The {@link Map} from {@link PQLTask}s specified in this PQL query to interpreted {@link PQLTask}s, i.e., indexed tasks.
	 */
	public Map<PQLTask,PQLTask> getTaskMap();
	
	/**
	 * Get the number of syntax errors in the PQL query string.
	 * 
	 * @return The number of syntax errors in the PQL query string.
	 */
	public int getNumberOfParseErrors();
	
	/**
	 * Get PQL query string parse error messages.
	 * 
	 * @return The {@link List} of all parse error messages for this PQL query.
	 */
	public List<String> getParseErrorMessages();
}