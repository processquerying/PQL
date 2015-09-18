package org.pql.core;

/**
 * Interface to computation of basic PQL predicates.
 * 
 * @author Artem Polyvyanyy
 */
public interface IPQLBasicPredicates {
	
	/**
	 * Configure computation of basic PQL predicates.
	 * 
	 * @param obj Configuration object
	 * @throws {@link PQLException} 
	 */
	public void configure(Object obj) throws PQLException;
}
