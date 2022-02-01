package edu.cornell.library.misc.gps;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;



public class CommandLineTester {
 
	private String updateYear;
	private boolean noupdate;

	public CommandLineTester() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUpdateYear() {
		return this.updateYear;
	}

	public void setUpdateYear(String updateYear) {
		this.updateYear = updateYear;
	}

	public boolean isNoupdate() {
		return noupdate;
	}

	public void setNoupdate(boolean noupdate) {
		this.noupdate = noupdate;
	}

	public static void main(String[] args) {
		CommandLineTester app = new CommandLineTester();
 
		app.getArgs(args);
		
		System.out.println("updateYear: "+ app.getUpdateYear());
		System.out.println("noupdate: "+ app.isNoupdate());
	}
	
	public  void getArgs(String[] args) {
		String appName = this.getClass().getSimpleName();
		Options options = new Options();
		options.addOption("updateYear", true, "Update Year"); 
		options.addOption("noupdate", false, "Just print output, no update");
		
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		try {
			CommandLine cmd = parser.parse( options, args);
			if (cmd.hasOption("updateYear")) {
				this.setUpdateYear(cmd.getOptionValue("updateYear"));
			} else { 
				formatter.printHelp(appName, options );
				System.exit(0);
			}
			if (cmd.hasOption("noupdate")) {
				this.setNoupdate(true);
			}
		} catch (ParseException e) {
			formatter.printHelp(appName, options );
			System.exit(0);
		}
	}

}
