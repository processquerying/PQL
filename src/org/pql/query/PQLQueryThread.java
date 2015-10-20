package org.pql.query;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.jbpt.petri.persist.AbstractPetriNetPersistenceLayerMySQL;
import org.jbpt.petri.persist.IPetriNetPersistenceLayer;
import org.pql.core.PQLBasicPredicatesMySQL;
import org.pql.core.PQLException;

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
			while(!this.queue.isEmpty() || (!this.netIDsLoaded.get() && this.queue.isEmpty()))
		{	
					String id = this.queue.poll();
					
					if(id != null)
					{//System.out.println(this.name+" started "+id+" "+queue);
						try {
							this.query.configure(id);
							} catch (PQLException e) {
								e.printStackTrace();
							}
						
						if (this.query.check()==true) {
							this.queryResult.add(id);
							}
					//System.out.println(this.name+" completed "+id+" "+queue);
					}
		}

	}
	

	public void checkQueryExperiment2()//Used in Experiment #2 checkQueryExperiment2
	{
		PQLQueryMySQL q = (PQLQueryMySQL) this.query;
		PQLBasicPredicatesMySQL bp = (PQLBasicPredicatesMySQL)q.getBP();
		AbstractPetriNetPersistenceLayerMySQL pl = (AbstractPetriNetPersistenceLayerMySQL) bp.getPL();
		
		Vector<String> results = new Vector<String>();
		
		while(!this.queue.isEmpty() || (!this.netIDsLoaded.get() && this.queue.isEmpty()))
		{	
					String id = this.queue.poll();
					if(id != null)
					{	
						int numberOfPlaces = pl.getNumberOfPlaces(id);
						int numberOfTransitions = pl.getNumberOfTransitions(id);
						int numberOfFlowArcs = pl.getNumberOfFlowArcs(id);
						int modelSize = numberOfPlaces + numberOfTransitions;
						
						
						long time1 = 0L;
						long time2 = 0L;
						
						int answer = 0;
						int filteredModelsBeforeCheck = 0;
						int filteredModelsAfterCheck = 0;
						
						for(int i=0; i<4; i++)
						{
							filteredModelsBeforeCheck = bp.filteredModels.get();
							
							long start = System.nanoTime();
								
							try {
								this.query.configure(id);
								} catch (PQLException e) {
									e.printStackTrace();
								}
							long middle = System.nanoTime();
							
							if (this.query.check()==true) {
								this.queryResult.add(id);
								answer = 1;
							}
							
							long stop = System.nanoTime();
							filteredModelsAfterCheck = bp.filteredModels.get();
							
							if(i>0)
							{
							time1 += (middle-start);
							time2 += (stop - middle);
							}
						}
						
					String outcomeLine = id + ";"+modelSize+";"+numberOfPlaces+";"+numberOfTransitions+";"+numberOfFlowArcs+";"+answer+";"+ (filteredModelsAfterCheck - filteredModelsBeforeCheck) +";"+ (double)time1/3 +";"+ (double)time2/3 + "\r\n";
					results.add(outcomeLine);
					//System.out.println(outcomeLine);
					}
		}	
		
	try {File Results = writeCSV(results,".\\Ex2results.csv");} catch (IOException e) {e.printStackTrace();}

	}
	
	@Override
	public void run() {
		
			checkQuery();
		
	}

	//for Experiment #2
	public static File writeCSV(Vector<String> lines, String path) throws IOException {
		
		File file = new File(path);
		FileWriter fw = new FileWriter(file, true);
			
			for (int i=0; i<lines.size(); i++) {
				String line = lines.elementAt(i);
				fw.write(line);
				fw.flush();
			}
		
		fw.close();
		return file;
	}

}
