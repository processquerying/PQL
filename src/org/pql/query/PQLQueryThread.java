package org.pql.query;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jbpt.petri.persist.AbstractPetriNetPersistenceLayerMySQL;
import org.pql.core.PQLBasicPredicatesMySQL;
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
	private String setup = null;
	
	
	public PQLQueryThread(String setup, String name, IPQLQuery query, PriorityBlockingQueue<String> queue, Set<String> queryResult, AtomicBoolean netIDsLoaded) {
		super(name);
		this.query = query;
		this.queue = queue;
		this.queryResult = queryResult;
		this.netIDsLoaded = netIDsLoaded;
		this.setup = setup;
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
								this.queryResult.add(id + ": "+ id); //this.queryResult.add(id); - for PQLTestInsertSelect
								else
								this.queryResult.add(id + ": "+ this.query.getBP().getRepairedID()); //this.queryResult.add(this.query.getBP().getRepairedID()); - for PQLTestInsertSelect	
								
							}
						}
					
					}
		}

	}
	

	@SuppressWarnings({ "rawtypes", "unused" })
	public void checkQueryExperiment2Insert()//Used in Experiment #2 for INSERT 
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
						long time = 0L;
						
						int answer = 0;
						
						for(int i=0; i<4; i++)
						{
							
							long start = System.nanoTime();
								
							try {
								this.query.configure(id);
								} catch (PQLException e) {
									e.printStackTrace();
								}
						
							long middle = System.nanoTime();
							
							if (this.query.check()==true) {
								
								if (this.query.getInsertTrace() == null) //A.P. SELECT query
								{
									this.queryResult.add(id);
									answer = 1;
								}
								else //A.P. INSERT query 
								{	
									boolean netRepaired = this.query.getBP().repairNet(this.query.getInsertTrace());
									
									if (!netRepaired)
									{this.queryResult.add(id + ": "+ id); answer = 0;}
									else
									{this.queryResult.add(id + ": "+ this.query.getBP().getRepairedID()); answer = 1;}	
									
								}
					
								
							}
							
							long stop = System.nanoTime();
								
							if(i>0)
							{
							time1 += (middle-start);
							time2 += (stop - middle);
							time += (stop - start);
							}
						}
						
					String outcomeLine = this.setup + id + ";"+modelSize+";"+numberOfPlaces+";"+numberOfTransitions+";"+numberOfFlowArcs+";"+answer +";"+ (double)time1/3 +";"+ (double)time2/3 +";"+ (double)time/3 + "\r\n";
					results.add(outcomeLine);
					
					}
		}	
		
	try {File Results = writeCSV(results,".\\Ex2InsertResults.csv");} catch (IOException e) {e.printStackTrace();}

	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public void checkQueryExperiment2()//Used in Experiment #2 for SELECT
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
						long time = 0L;
						
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
								
								if (this.query.getInsertTrace() == null) //A.P. SELECT query
								{
									this.queryResult.add(id);
									answer = 1;
								}
								else //A.P. INSERT query 
								{	
									boolean netRepaired = this.query.getBP().repairNet(this.query.getInsertTrace());
									
									if (!netRepaired)
									this.queryResult.add(id + ": "+ id);
									else
									this.queryResult.add(id + ": "+ this.query.getBP().getRepairedID());	
									
								}
					
								
							}
							
							long stop = System.nanoTime();
							
							filteredModelsAfterCheck = bp.filteredModels.get();
							
							if(i>0)
							{
							time1 += (middle-start);
							time2 += (stop - middle);
							time += (stop - start);
							}
						}
						
					String outcomeLine = this.setup + id + ";"+modelSize+";"+numberOfPlaces+";"+numberOfTransitions+";"+numberOfFlowArcs+";"+answer+";"+ (filteredModelsAfterCheck - filteredModelsBeforeCheck) +";"+ (double)time1/3 +";"+ (double)time2/3 +";"+ (double)time/3 + "\r\n";
					results.add(outcomeLine);
					
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
