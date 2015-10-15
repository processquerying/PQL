package org.pql.query;

import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.pql.core.PQLException;
import org.pql.logic.ThreeValuedLogicValue;

/**
 * @author Artem Polyvyanyy
 * 
 */
public class PQLQueryThread extends Thread {

	private IPQLQuery query = null;
	private Set<String> queryResult = null;
	private PriorityBlockingQueue<String> queue = null;//A.P.
	private AtomicBoolean netIDsLoaded = null;//A.P.
	private String name = null;//A.P.
	
	public PQLQueryThread(String name, IPQLQuery query, PriorityBlockingQueue<String> queue, Set<String> queryResult, AtomicBoolean netIDsLoaded) {
		super(name);
		this.query = query;
		this.queue = queue;
		this.queryResult = queryResult;
		this.netIDsLoaded = netIDsLoaded;
		this.name = name;
	}

	public void checkQuery()
	{
		while(!(this.queue.isEmpty()) || (!this.netIDsLoaded.get() && this.queue.isEmpty()))
		{	
			if(!(this.queue.isEmpty()))
			{		
					String id = this.queue.poll();
					if(id != null)
					{//System.out.println(this.name+" started "+id+" "+queue);
					try {
						this.query.configure(id);
						} catch (PQLException e) {
							e.printStackTrace();
						}
						
					if (this.query.check()==ThreeValuedLogicValue.TRUE) {
						this.queryResult.add(id);
					}
					//System.out.println(this.name+" completed "+id+" "+queue);
					}
			}
		}

	}
	
	@Override
	public void run() {
		
		checkQuery();			
	}

	
}
