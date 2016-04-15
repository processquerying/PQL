package org.pql.query;

import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.pql.core.PQLException;

/**
 * @author Artem Polyvyanyy
 * 
 */
public class PQLQueryThread extends Thread {

	private IPQLQuery query = null;
	private Set<String> queryResult = null;
	private PriorityBlockingQueue<String> queue = null;
	private AtomicBoolean netIDsLoaded = null;
	
	public PQLQueryThread(String name, IPQLQuery query, PriorityBlockingQueue<String> queue, Set<String> queryResult, AtomicBoolean netIDsLoaded) {
		super(name);
		this.query = query;
		this.queue = queue;
		this.queryResult = queryResult;
		this.netIDsLoaded = netIDsLoaded;
		}

	public void checkQuery()
	{
			while(!this.queue.isEmpty() || (!this.netIDsLoaded.get() && this.queue.isEmpty()))
		{	
					String id = this.queue.poll();
					
					if(id != null)
					{
						try {
							this.query.configure(id);
							} catch (PQLException e) {
								e.printStackTrace();
							}
						
						if (this.query.check()==true) 
						{
							if (this.query.getInsertTrace() == null) //SELECT query
							{
								this.queryResult.add(id);
							}
							else //INSERT query 
							{	
								
								boolean netRepaired = this.query.getBP().repairNet(this.query.getInsertTrace());
								
								if (!netRepaired)
								this.queryResult.add(id + ": "+ id); 
								else
								this.queryResult.add(id + ": "+ this.query.getBP().getRepairedID()); 	
								
							}
						}
					
					}
		}

	}

	@Override
	public void run() {
		
			checkQuery();
		
	}

}
