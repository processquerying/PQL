package org.pql.bot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @author Artem Polyvyanyy
 */
public class PQLBot {

	public static void main(String[] args) throws InterruptedException {
		
		CommandLineParser parser = new DefaultParser();
		
		String botName = null;
		int sleep = 10;
		
	    try {
	    	// create Options object
	    	Options options = new Options();
	    	
	    	// create options
	    	Option helpOption	= Option.builder("h").longOpt("help").optionalArg(true).desc("print this message").hasArg(false).build();
	    	Option nameOption	= Option.builder("n").longOpt("name").hasArg().optionalArg(true).desc("name of this bot (maximum 36 characters)").valueSeparator().argName("botName").build();
	    	
	    	// add options
	    	options.addOption(helpOption);
	    	options.addOption(nameOption);
	    	
	        // parse the command line arguments
	        CommandLine cmd = parser.parse(options, args);
	        
	        // handle help
	        if(cmd.hasOption("h")) {
	        	HelpFormatter formatter = new HelpFormatter();
	        	formatter.printHelp("PQL",options);
	        	return;
	        }
	        
	        // handle name
	        botName = cmd.getOptionValue("n");
	        if (botName==null) botName = UUID.randomUUID().toString();
	        else if (botName.length()>36) botName=botName.substring(0,36);
	        
	        
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        
	        
	        while (true) {
	        	System.out.println(String.format("%s> Bot %s sleeps for %s seconds.", dateFormat.format(new Date()), botName, sleep));
	        	Thread.sleep(sleep * 1000);
	        	
	        	// index  
	        	// TODO
	        	
	        }

	    }
	    catch( ParseException exp ) {
	        // oops, something went wrong
	        System.err.println( "CLI parsing failed. Reason: " + exp.getMessage());
	    }


	}

}
