package org.pql.query;

import java.util.Iterator;
import java.util.Set;

import org.pql.core.PQLException;
import org.pql.logic.ThreeValuedLogicValue;

/**
 * @author Artem Polyvyanyy
 */
public class PQLQueryThread extends Thread {

	private IPQLQuery query = null;
	private Iterator<String> i = null;
	private Set<String> queryResult = null;
	
	public PQLQueryThread(IPQLQuery query, Iterator<String> i, Set<String> queryResult) {
		this.query = query;
		this.i = i;
		this.queryResult = queryResult;
	}
	
	@Override
	public void run() {
		while (i.hasNext()) {
			String nextID = i.next();
			
			try {
				this.query.configure(nextID);
			} catch (PQLException e) {
				e.printStackTrace();
			}
			
			if (this.query.check()==ThreeValuedLogicValue.TRUE) {
				this.queryResult.add(nextID);
			}
		}
	}

}
