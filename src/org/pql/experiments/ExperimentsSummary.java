package org.pql.experiments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import org.jbpt.persist.MySQLConnection;
import org.jbpt.petri.persist.AbstractPetriNetPersistenceLayerMySQL;
import org.pql.api.PQLAPI;
import org.pql.bot.AbstractPQLBot.NameInUseException;
import org.pql.bot.PQLBot;
import org.pql.bot.PQLBotEx;
import org.pql.core.IPQLBasicPredicatesOnTasks;
import org.pql.core.PQLBasicPredicatesMC;
import org.pql.core.PQLBasicPredicatesMySQL;
import org.pql.index.IndexType;
import org.pql.index.PQLIndexMySQL;
import org.pql.ini.PQLIniFile;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerLevenshtein;
import org.pql.label.LabelManagerLuceneVSM;
import org.pql.label.LabelManagerThemisVSM;
import org.pql.mc.LoLA2ModelChecker;
import org.pql.tool.PQLToolCLI;

public class ExperimentsSummary {
		
public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException, NameInUseException, org.pql.bot.AbstractPQLBotEx.NameInUseException 
{
	//String filePath = "./experiments/Ex1v2Results.csv"; 
	String filePath = "./experiments/Ex3v1Results.csv";
		
	//loading data		
	Vector<Vector<String>> results = new Vector<Vector<String>>();
	results = loadResults(results,filePath);
	
	//Experiment 1
	//File result0 = writeCSV(BotsCollectionSizeTotalTime(results),".\\Ex1_BotsCollectionSizeTotalTime.csv");	
	
	//Experiment 3
	File result1 = writeCSV(ThreadsCollectionSizeAVGTime(results),".\\Ex3_ThreadsCollectionSizeAVGTime.csv");
	File result2 = writeCSV(CategoryCollectionSizeAVGTime(results),".\\Ex3_CategoryCollectionSizeAVGTime.csv");
	File result3 = writeCSV(SubCategoryCollectionSizeAVGTime(results),".\\Ex3_SubCategoryCollectionSizeAVGTime.csv");
	File result4 = writeCSV(GroupCollectionSizeAVGTime(results),".\\Ex3_GroupCollectionSizeAVGTime.csv");
	File result5 = writeCSV(TemplateCollectionSizeAVGTime(results),".\\Ex3_TemplateCollectionSizeAVGTime.csv");
	File result6 = writeCSV(CategoryThreadsAVGTime(results),".\\Ex3_CategoryThreadsAVGTime.csv");
	File result7 = writeCSV(SubCategoryThreadsAVGTime(results),".\\Ex3_SubCategoryThreadsAVGTime.csv");
	File result8 = writeCSV(GroupThreadsAVGTime(results),".\\Ex3_GroupThreadsAVGTime.csv");
		
}
	
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
	
	private static Vector<Vector<String>> loadResults(Vector<Vector<String>> results, String filePath) 
	{

		File file = new File(filePath);
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) 
		{
		    for(String line; (line = br.readLine()) != null; ) 
		    {
		        String[] arr = line.split(";");
		        Vector<String> ls = new Vector<String>();
		        for(String s: arr)
		        {
		        	ls.add(s);
		        }
		        results.add(ls);
		    }
		}catch(Exception e){};

	return results;
	}	
	
	static Vector<String> selectData (Vector<Vector<String>> data, Vector<Integer> columnIndexes, Vector<String> values, int selectColumn)
	{
		Vector<String> out = new Vector<String>();
		
		for(int i=0; i<data.size(); i++)
		{	
			boolean selectRow = true;
			
			for(int j=0; j<columnIndexes.size(); j++)
			{
				if(!(data.elementAt(i).elementAt(columnIndexes.elementAt(j)).equals(values.elementAt(j))))
				{
					selectRow = false;
					break;
				}
			}
			
			if(selectRow)
			out.add(data.elementAt(i).elementAt(selectColumn));
		}
		
		return out;
	}
	
	static Double getAverage (Vector<String> data)
	{
		Double out = 0.0;
		Double sum = 0.0;
		int size = data.size();
		
		if (size==0) return -1.0;
		
		for(int i=0; i<size; i++)
		sum += Double.parseDouble(data.elementAt(i));	
	
		out = sum/size;
		
		return out;
	}
	
	static Double getMedian (Vector<String> data)
	{
		Double out = 0.0;
		
		int size = data.size();
		if (size==0) return -1.0;
		
		Vector<Double> values = new Vector<Double>(); 
		for(int i=0; i<size; i++)
		values.add(Double.parseDouble(data.elementAt(i)));
		Collections.sort(values);
		
		if ( (size & 1) == 0 ) 
		{ 
			out = (values.elementAt((int)(size/2))+values.elementAt((int)(size/2)-1))/2; 
		} 
		else 
		{ 
			out = values.elementAt((int)(size/2));
		}
		
		return out;
	}
	
	static Double getMax (Vector<String> data)
	{
		Double out = 0.0;
		
		int size = data.size();
		if (size==0) return -1.0;
		
		Vector<Double> values = new Vector<Double>(); 
		for(int i=0; i<size; i++)
		values.add(Double.parseDouble(data.elementAt(i)));
		out = Collections.max(values);	
		
		return out;
	}
	
	static Double getMin (Vector<String> data)
	{
		Double out = 0.0;
		
		int size = data.size();
		if (size==0) return -1.0;
		
		Vector<Double> values = new Vector<Double>(); 
		for(int i=0; i<size; i++)
		values.add(Double.parseDouble(data.elementAt(i)));
		out = Collections.min(values);	
		
		return out;
	}

//-------------------------------------------------
	
	static Vector<String> BotsCollectionSizeTotalTime (Vector<Vector<String>> data)
	{
		Vector<String> out = new Vector<String>();
		out.add("sep=;\r\n");
		out.add(";25;50;75;100\r\n");
		
		int bots = 8;
		int runs = 3;
		int kSplit = 4;
		
		Vector<Integer> columns = new Vector<Integer>();
		columns.add(1);
		columns.add(2);
		columns.add(3);
		
		Vector<String> values = new Vector<String>();
		
		
		for(int i=1; i<=bots; i++)
		{	
			String line = i+";";
			
			for(int k=1; k<=kSplit; k++)
			{
				Double total = 0.0;
				
				for(int j=1; j<=runs; j++)
				{
					values.removeAllElements();
					values.add(String.valueOf(i));
					values.add(String.valueOf(j));
					values.add("1");
					Double min = getMin(selectData(data, columns, values, 11));
					
					values.removeAllElements();
					values.add(String.valueOf(i));
					values.add(String.valueOf(j));
					values.add(String.valueOf(k));
					Double max = getMax(selectData(data, columns, values, 12));
					
					total += (max-min);	
				}
				
				total = total/runs;
				line+=total+";";
				
			}
			out.add(line+"\r\n");
		}
		
		return out;
	}
	
//ThreadsCollectionSizeAVGTime
	static Vector<String> ThreadsCollectionSizeAVGTime (Vector<Vector<String>> data)
	{
		Vector<String> out = new Vector<String>();
		out.add("sep=;\r\n");
		out.add(";0;25;50;75;100\r\n");
		
		int threads = 8;
		int runs = 3;
		int kSplit = 4;
		
		Vector<Integer> columns = new Vector<Integer>();
		columns.add(1); //run#
		columns.add(2); //collection#
		columns.add(3); //threads#
		
		Vector<String> values = new Vector<String>();
		
		
		for(int i=1; i<=threads; i++)
		{	
			String line = i+";";
			
			for(int k=0; k<=kSplit; k++)
			{
				Double avg = 0.0;
				
				for(int j=1; j<=runs; j++)
				{
					values.removeAllElements();
					values.add(String.valueOf(j));
					values.add(String.valueOf(k));
					values.add(String.valueOf(i));
					Double runAvg = getAverage(selectData(data, columns, values, 4));
					
					avg += runAvg;	
				}
				
				avg = avg/runs;
				line+=avg+";";
				
			}
			out.add(line+"\r\n");
		}
		
		return out;
	}
	
	//CategoryCollectionSizeAVGTime
	static Vector<String> CategoryCollectionSizeAVGTime (Vector<Vector<String>> data)
	{
		Vector<String> out = new Vector<String>();
		out.add("sep=;\r\n");
		out.add(";0;25;50;75;100\r\n");
		
		int runs = 3;
		int kSplit = 4;
		int category = 3;
		
		Vector<Integer> columns = new Vector<Integer>();
		columns.add(1); //run#
		columns.add(2); //collection#
		columns.add(3); //threads#
		columns.add(6); //category#
		
		Vector<String> values = new Vector<String>();
		
		for(int i=1; i<=category; i++)
		{	
			String line = i+";";
			
			for(int k=0; k<=kSplit; k++)
			{
				Double avg = 0.0;
				
				for(int j=1; j<=runs; j++)
				{
					values.removeAllElements();
					values.add(String.valueOf(j));//run#
					values.add(String.valueOf(k));//collection#
					values.add("1");//threads#
					values.add(String.valueOf(i));//category#
					Double runAvg = getAverage(selectData(data, columns, values, 4));
					
					avg += runAvg;	
				}
				
				avg = avg/runs;
				line+=avg+";";
				
			}
			out.add(line+"\r\n");
		}
		
		return out;
	}
	
	//SubCategoryCollectionSizeAVGTime
		static Vector<String> SubCategoryCollectionSizeAVGTime (Vector<Vector<String>> data)
		{
			Vector<String> out = new Vector<String>();
			out.add("sep=;\r\n");
			out.add(";0;25;50;75;100\r\n");
			
			int runs = 3;
			int kSplit = 4;
						
			Vector<Integer> columns = new Vector<Integer>();
			columns.add(1); //run#
			columns.add(2); //collection#
			columns.add(3); //threads#
			columns.add(7); //subcategory#
			
			Vector<String> values = new Vector<String>();
			Vector<String> subCat = new Vector<String>();
			subCat.add("1.a");
			subCat.add("1.b");
			subCat.add("2.a");
			subCat.add("2.b");
			subCat.add("3.a");
			subCat.add("3.b");
			
			
			for(int i=0; i<subCat.size(); i++)
			{	
				String line = subCat.elementAt(i)+";";
				
				for(int k=0; k<=kSplit; k++)
				{
					Double avg = 0.0;
					
					for(int j=1; j<=runs; j++)
					{
						values.removeAllElements();
						values.add(String.valueOf(j));//run#
						values.add(String.valueOf(k));//collection#
						values.add("1");//threads#
						values.add(String.valueOf(subCat.elementAt(i)));//subcategory#
						Double runAvg = getAverage(selectData(data, columns, values, 4));
						
						avg += runAvg;	
					}
					
					avg = avg/runs;
					line+=avg+";";
					
				}
				out.add(line+"\r\n");
			}
			
			return out;
		}
	
		//GroupCollectionSizeAVGTime
		static Vector<String> GroupCollectionSizeAVGTime (Vector<Vector<String>> data)
		{
			Vector<String> out = new Vector<String>();
			out.add("sep=;\r\n");
			out.add(";0;25;50;75;100\r\n");
			
			int runs = 3;
			int kSplit = 4;
						
			Vector<Integer> columns = new Vector<Integer>();
			columns.add(1); //run#
			columns.add(2); //collection#
			columns.add(3); //threads#
			columns.add(8); //group#
			
			Vector<String> values = new Vector<String>();
			Vector<String> groups = new Vector<String>();
			groups.add("1.a.1");
			groups.add("1.b.1");
			groups.add("2.a.1");
			groups.add("2.a.2");
			groups.add("2.a.3");
			groups.add("2.b.1");
			groups.add("2.b.2");
			groups.add("2.b.3");
			groups.add("3.a.1");
			groups.add("3.a.2");
			groups.add("3.a.3");
			groups.add("3.b.1");
			groups.add("3.b.2");
			groups.add("3.b.3");
			groups.add("3.b.4");
			
				
			
			for(int i=0; i<groups.size(); i++)
			{	
				String line = groups.elementAt(i)+";";
				
				for(int k=0; k<=kSplit; k++)
				{
					Double avg = 0.0;
					
					for(int j=1; j<=runs; j++)
					{
						values.removeAllElements();
						values.add(String.valueOf(j));//run#
						values.add(String.valueOf(k));//collection#
						values.add("1");//threads#
						values.add(String.valueOf(groups.elementAt(i)));//group#
						Double runAvg = getAverage(selectData(data, columns, values, 4));
						
						avg += runAvg;	
					}
					
					avg = avg/runs;
					line+=avg+";";
					
				}
				out.add(line+"\r\n");
			}
			
			return out;
		}
		
		//TemplateCollectionSizeAVGTime
		static Vector<String> TemplateCollectionSizeAVGTime (Vector<Vector<String>> data)
		{
			Vector<String> out = new Vector<String>();
			out.add("sep=;\r\n");
			out.add(";0;25;50;75;100\r\n");
			
			int runs = 3;
			int kSplit = 4;
						
			Vector<Integer> columns = new Vector<Integer>();
			columns.add(1); //run#
			columns.add(2); //collection#
			columns.add(3); //threads#
			columns.add(9); //template#
			
			Vector<String> values = new Vector<String>();
			Vector<String> templates = new Vector<String>();
			templates.add("1.a.1-1");
			templates.add("1.a.1-2");
			templates.add("1.b.1-1");
			templates.add("1.b.1-2");
			templates.add("1.b.1-3");
			templates.add("1.b.1-4");
			templates.add("2.a.1.a-1");
			templates.add("2.a.1.a-2");
			templates.add("2.a.1.b-1");
			templates.add("2.a.1.b-2");
			templates.add("2.a.1.b-3");
			templates.add("2.a.1.b-4");
			templates.add("2.a.2.a-1.2");
			templates.add("2.a.2.a-1.3");
			templates.add("2.a.2.a-1.4");
			templates.add("2.a.2.a-2.2");
			templates.add("2.a.2.a-2.3");
			templates.add("2.a.2.a-2.4");
			templates.add("2.a.2.b-1.2");
			templates.add("2.a.2.b-1.3");
			templates.add("2.a.2.b-1.4");
			templates.add("2.a.2.b-2.2");
			templates.add("2.a.2.b-2.3");
			templates.add("2.a.2.b-2.4");
			templates.add("2.a.2.b-3.2");
			templates.add("2.a.2.b-3.3");
			templates.add("2.a.2.b-3.4");
			templates.add("2.a.2.b-4.2");
			templates.add("2.a.2.b-4.3");
			templates.add("2.a.2.b-4.4");
			templates.add("2.a.3.a-1.2");
			templates.add("2.a.3.a-1.3");
			templates.add("2.a.3.a-1.4");
			templates.add("2.a.3.a-2.2");
			templates.add("2.a.3.a-2.3");
			templates.add("2.a.3.a-2.4");
			templates.add("2.a.3.b-1.2");
			templates.add("2.a.3.b-1.3");
			templates.add("2.a.3.b-1.4");
			templates.add("2.a.3.b-2.2");
			templates.add("2.a.3.b-2.3");
			templates.add("2.a.3.b-2.4");
			templates.add("2.a.3.b-3.2");
			templates.add("2.a.3.b-3.3");
			templates.add("2.a.3.b-3.4");
			templates.add("2.a.3.b-4.2");
			templates.add("2.a.3.b-4.3");
			templates.add("2.a.3.b-4.4");
			templates.add("2.b.1.1");
			templates.add("2.b.1.2");
			templates.add("2.b.1.3");
			templates.add("2.b.2.1");
			templates.add("2.b.2.2");
			templates.add("2.b.2.3");
			templates.add("2.b.3.1");
			templates.add("2.b.3.2");
			templates.add("3.a.1.a-1.all.2");
			templates.add("3.a.1.a-1.all.3");
			templates.add("3.a.1.a-1.all.4");
			templates.add("3.a.1.a-1.any.2");
			templates.add("3.a.1.a-1.any.3");
			templates.add("3.a.1.a-1.any.4");
			templates.add("3.a.1.a-2.all.2");
			templates.add("3.a.1.a-2.all.3");
			templates.add("3.a.1.a-2.all.4");
			templates.add("3.a.1.a-2.any.2");
			templates.add("3.a.1.a-2.any.3");
			templates.add("3.a.1.a-2.any.4");
			templates.add("3.a.2.b-1.all.2");
			templates.add("3.a.2.b-1.all.3");
			templates.add("3.a.2.b-1.all.4");
			templates.add("3.a.2.b-1.any.2");
			templates.add("3.a.2.b-1.any.3");
			templates.add("3.a.2.b-1.any.4");
			templates.add("3.a.2.b-2.all.2");
			templates.add("3.a.2.b-2.all.3");
			templates.add("3.a.2.b-2.all.4");
			templates.add("3.a.2.b-2.any.2");
			templates.add("3.a.2.b-2.any.3");
			templates.add("3.a.2.b-2.any.4");
			templates.add("3.a.2.b-3.all.2");
			templates.add("3.a.2.b-3.all.3");
			templates.add("3.a.2.b-3.all.4");
			templates.add("3.a.2.b-3.any.2");
			templates.add("3.a.2.b-3.any.3");
			templates.add("3.a.2.b-3.any.4");
			templates.add("3.a.2.b-4.all.2");
			templates.add("3.a.2.b-4.all.3");
			templates.add("3.a.2.b-4.all.4");
			templates.add("3.a.2.b-4.any.2");
			templates.add("3.a.2.b-4.any.3");
			templates.add("3.a.2.b-4.any.4");
			templates.add("3.a.3.b-1.all.2");
			templates.add("3.a.3.b-1.all.3");
			templates.add("3.a.3.b-1.all.4");
			templates.add("3.a.3.b-1.any.2");
			templates.add("3.a.3.b-1.any.3");
			templates.add("3.a.3.b-1.any.4");
			templates.add("3.a.3.b-1.each.2");
			templates.add("3.a.3.b-1.each.3");
			templates.add("3.a.3.b-1.each.4");
			templates.add("3.a.3.b-2.all.2");
			templates.add("3.a.3.b-2.all.3");
			templates.add("3.a.3.b-2.all.4");
			templates.add("3.a.3.b-2.any.2");
			templates.add("3.a.3.b-2.any.3");
			templates.add("3.a.3.b-2.any.4");
			templates.add("3.a.3.b-2.each.2");
			templates.add("3.a.3.b-2.each.3");
			templates.add("3.a.3.b-2.each.4");
			templates.add("3.a.3.b-3.all.2");
			templates.add("3.a.3.b-3.all.3");
			templates.add("3.a.3.b-3.all.4");
			templates.add("3.a.3.b-3.any.2");
			templates.add("3.a.3.b-3.any.3");
			templates.add("3.a.3.b-3.any.4");
			templates.add("3.a.3.b-3.each.2");
			templates.add("3.a.3.b-3.each.3");
			templates.add("3.a.3.b-3.each.4");
			templates.add("3.a.3.b-4.all.2");
			templates.add("3.a.3.b-4.all.3");
			templates.add("3.a.3.b-4.all.4");
			templates.add("3.a.3.b-4.any.2");
			templates.add("3.a.3.b-4.any.3");
			templates.add("3.a.3.b-4.any.4");
			templates.add("3.a.3.b-4.each.2");
			templates.add("3.a.3.b-4.each.3");
			templates.add("3.a.3.b-4.each.4");
			templates.add("3.b.1.a-1");
			templates.add("3.b.1.a-2");
			templates.add("3.b.2.b-1.all");
			templates.add("3.b.2.b-1.any");
			templates.add("3.b.2.b-2.all");
			templates.add("3.b.2.b-2.any");
			templates.add("3.b.2.b-3.all");
			templates.add("3.b.2.b-3.any");
			templates.add("3.b.2.b-4.all");
			templates.add("3.b.2.b-4.any");
			templates.add("3.b.3.1.union");
			templates.add("3.b.3.2.inter");
			templates.add("3.b.3.3.diff");
			templates.add("3.b.3.4.combo");
			templates.add("3.b.3.5.combo");
			templates.add("3.b.4.1.ident");
			templates.add("3.b.4.2.diff");
			templates.add("3.b.4.3.overlap");
			templates.add("3.b.4.4.subset");
			templates.add("3.b.4.5.proper");
			templates.add("3.b.4.7.combo");
			templates.add("3.b.4.6.combo");

			for(int i=0; i<templates.size(); i++)
			{	
				String line = templates.elementAt(i)+";";
				
				for(int k=0; k<=kSplit; k++)
				{
					Double avg = 0.0;
					
					for(int j=1; j<=runs; j++)
					{
						values.removeAllElements();
						values.add(String.valueOf(j));//run#
						values.add(String.valueOf(k));//collection#
						values.add("1");//threads#
						values.add(String.valueOf(templates.elementAt(i)));//template#
						Double runAvg = getAverage(selectData(data, columns, values, 4));
						
						avg += runAvg;	
					}
					
					avg = avg/runs;
					line+=avg+";";
					
				}
				out.add(line+"\r\n");
			}
			
			return out;
		}
		
		//CategoryThreadsAVGTime
		static Vector<String> CategoryThreadsAVGTime (Vector<Vector<String>> data)
		{
			Vector<String> out = new Vector<String>();
			out.add("sep=;\r\n");
			out.add(";1;2;3;4;5;6;7;8\r\n");
			
			int threads = 8;
			int runs = 3;
			int category = 3;
			
			Vector<Integer> columns = new Vector<Integer>();
			columns.add(1); //run#
			columns.add(2); //collection#
			columns.add(3); //threads#
			columns.add(6); //category#
			
			Vector<String> values = new Vector<String>();
			
			
			for(int i=1; i<=category; i++)
			{	
				String line = i+";";
				
				for(int k=1; k<=threads; k++)
				{
					Double avg = 0.0;
					
					for(int j=1; j<=runs; j++)
					{
						values.removeAllElements();
						values.add(String.valueOf(j));//run#
						values.add("4");//collection#
						values.add(String.valueOf(k));//threads#
						values.add(String.valueOf(i));//category#
						Double runAvg = getAverage(selectData(data, columns, values, 4));
						
						avg += runAvg;	
					}
					
					avg = avg/runs;
					line+=avg+";";
					
				}
				out.add(line+"\r\n");
			}
			
			return out;
		}
		
		//SubCategoryThreadsAVGTime
			static Vector<String> SubCategoryThreadsAVGTime (Vector<Vector<String>> data)
			{
				Vector<String> out = new Vector<String>();
				out.add("sep=;\r\n");
				out.add(";1;2;3;4;5;6;7;8\r\n");
				
				int threads = 8;
				int runs = 3;
							
				Vector<Integer> columns = new Vector<Integer>();
				columns.add(1); //run#
				columns.add(2); //collection#
				columns.add(3); //threads#
				columns.add(7); //subcategory#
				
				Vector<String> values = new Vector<String>();
				Vector<String> subCat = new Vector<String>();
				subCat.add("1.a");
				subCat.add("1.b");
				subCat.add("2.a");
				subCat.add("2.b");
				subCat.add("3.a");
				subCat.add("3.b");
				
				
				for(int i=0; i<subCat.size(); i++)
				{	
					String line = subCat.elementAt(i)+";";
					
					for(int k=1; k<=threads; k++)
					{
						Double avg = 0.0;
						
						for(int j=1; j<=runs; j++)
						{
							values.removeAllElements();
							values.add(String.valueOf(j));//run#
							values.add("4");//collection#
							values.add(String.valueOf(k));//threads#
							values.add(String.valueOf(subCat.elementAt(i)));//subcategory#
							Double runAvg = getAverage(selectData(data, columns, values, 4));
							
							avg += runAvg;	
						}
						
						avg = avg/runs;
						line+=avg+";";
						
					}
					out.add(line+"\r\n");
				}
				
				return out;
			}
		
			//GroupThreadsAVGTime
			static Vector<String> GroupThreadsAVGTime (Vector<Vector<String>> data)
			{
				Vector<String> out = new Vector<String>();
				out.add("sep=;\r\n");
				out.add(";1;2;3;4;5;6;7;8\r\n");
				
				int threads = 8;
				int runs = 3;
				//int kSplit = 4;
							
				Vector<Integer> columns = new Vector<Integer>();
				columns.add(1); //run#
				columns.add(2); //collection#
				columns.add(3); //threads#
				columns.add(8); //group#
				
				Vector<String> values = new Vector<String>();
				Vector<String> groups = new Vector<String>();
				groups.add("1.a.1");
				groups.add("1.b.1");
				groups.add("2.a.1");
				groups.add("2.a.2");
				groups.add("2.a.3");
				groups.add("2.b.1");
				groups.add("2.b.2");
				groups.add("2.b.3");
				groups.add("3.a.1");
				groups.add("3.a.2");
				groups.add("3.a.3");
				groups.add("3.b.1");
				groups.add("3.b.2");
				groups.add("3.b.3");
				groups.add("3.b.4");
				
					
				
				for(int i=0; i<groups.size(); i++)
				{	
					String line = groups.elementAt(i)+";";
					
					for(int k=1; k<=threads; k++)
					{
						Double avg = 0.0;
						
						for(int j=1; j<=runs; j++)
						{
							values.removeAllElements();
							values.add(String.valueOf(j));//run#
							values.add("4");//collection#
							values.add(String.valueOf(k));//threads#
							values.add(String.valueOf(groups.elementAt(i)));//group#
							Double runAvg = getAverage(selectData(data, columns, values, 4));
							
							avg += runAvg;	
						}
						
						avg = avg/runs;
						line+=avg+";";
						
					}
					out.add(line+"\r\n");
				}
				
				return out;
			}
}
