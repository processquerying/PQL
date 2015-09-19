package org.pql.query;

import java.util.Set;

import org.pql.core.PQLException;
import org.pql.logic.ThreeValuedLogicValue;

/**
 * @author Artem Polyvyanyy
 */
public class PQLQueryThread extends Thread {

	private IPQLQuery query = null;
	private Set<String> externalIDs = null;
	private Set<String> queryResult = null;
	
	public PQLQueryThread(ThreadGroup group, String name, IPQLQuery query, Set<String> externalIDs, Set<String> queryResult) {
		super(group, name);
		
		this.query = query;
		this.externalIDs = externalIDs;
		this.queryResult = queryResult;
	}
	
	@Override
	public void run() {
		
		for (String id : this.externalIDs) {
			try {
				this.query.configure(id);
			} catch (PQLException e) {
				e.printStackTrace();
			}
			
			if (this.query.check()==ThreeValuedLogicValue.TRUE) {
				this.queryResult.add(id);
			}
		}
	}
}
