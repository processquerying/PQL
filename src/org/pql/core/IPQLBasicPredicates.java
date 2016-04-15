package org.pql.core;

import org.json.JSONArray;

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

	//A.P.
	boolean checkUnaryPredicateMacroV2(String op, String q, JSONArray ids);
	//A.P.
	boolean checkBinaryPredicateMacro(String op, String q, JSONArray ids1,
			JSONArray ids2);

}
