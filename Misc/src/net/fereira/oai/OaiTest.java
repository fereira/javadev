package net.fereira.oai;

import java.io.File;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.dlese.dpc.oai.harvester.HarvestMessageHandler;
import org.dlese.dpc.oai.harvester.Harvester;
import org.dlese.dpc.oai.harvester.Hexception;
import org.dlese.dpc.oai.harvester.OAIChangeListener;
import org.dlese.dpc.oai.harvester.OAIErrorException;

import org.xml.sax.SAXException;
 
public class OaiTest {
	
	private final static String userAgent = "jOAI Harvester";
	private final static boolean splitBySet = false;
	private final static boolean writeHeaders = false;
	private final static boolean harvestAll = false;
	private final static boolean harvestAllIfNoDeletedRecord = false;
	private final static int timeOutMilliseconds = 240000; 

	public OaiTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		OaiTest app = new OaiTest();
		String baseURL = "http://localhost:8080/oai/provider";
		String metadataPrefix = "oai_dc";
		String setSpec = "";
		
		HarvestMessageHandler msgHandler = null;
		OAIChangeListener listener = null;
		
		
		Date from = setDate("2000-01-01", "yyyy-mm-dd");
		Date until = setDate("8000-01-01", "yyyy-mm-dd");
		
		//String outdir = "/cul/src/javadev/tmp";
		String outdir = null; // set outdir to null to get results in an array
		// strArray[0] is the identifier
		// strArray[1] contains the xml
		 
		try { 
			String[][] results = Harvester.harvest(baseURL, metadataPrefix,
                    setSpec, from, until, outdir, splitBySet, msgHandler, listener, writeHeaders, harvestAll, harvestAllIfNoDeletedRecord, timeOutMilliseconds);
			//System.out.println("num results "+ results.length);
		    for (String[] strArray: results) {
		    	System.out.println(strArray[1]);
		    	 
		    }
		} catch (Hexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAIErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");
         
		 
         
	}
	
	public static java.util.Date setDate(String s, String fmt) {
	      SimpleDateFormat formatter = new SimpleDateFormat(fmt);
	      ParsePosition pos = new ParsePosition(0);
	      java.util.Date newDate = formatter.parse(s, pos);
	      return newDate;
	}

}
