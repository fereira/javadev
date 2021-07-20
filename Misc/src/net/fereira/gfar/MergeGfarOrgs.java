package net.fereira.gfar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang.StringUtils;

public class MergeGfarOrgs {
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final Object [] FILE_HEADER = {"Name","Acronym","Description","Type","Website","Location city","Country","geolocation",
"countryiso3","Region","Geographic scope","Address","Email","Field Agrovoc","Field","Nid","Source URL"};
	
	public MergeGfarOrgs() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MergeGfarOrgs app = new MergeGfarOrgs();
        app.run();
	}
	
	public void run() {
		System.out.println("start");
		final URL url = null;
		File inFile = new File("/usr/local/src/javadev/data/egfar/egfar-orgs-Aug.csv");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("/usr/local/src/javadev/data/egfar/egfar-orgs-Aug.csv");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Reader reader = null;
		CSVParser parser = null;
		try {
			//reader = new InputStreamReader(new BOMInputStream(url.openStream()), "UTF-8");
			reader = new InputStreamReader(fis, "UTF-8");
			parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 * "Name","Acronym","Description","Type","Website","Location city","Country","geolocation",
		 * "countryiso3","Region","Geographic scope","Address","Email","Field Agrovoc","Field","Nid","Source URL"
		 */
		Map<String, GfarOrg> orgMap = new HashMap<String, GfarOrg>();
		int nrec = 0;
		try {
		    for (final CSVRecord record : parser) {
		    	GfarOrg gfarOrg = new GfarOrg();
		        gfarOrg.setName(record.get("Name"));
		        gfarOrg.setAcronym(record.get("Acronym"));
		        gfarOrg.setDescription(record.get("Description"));
		        gfarOrg.setType(record.get("Type"));		        
		        gfarOrg.setWebsite(record.get("Website"));
		        gfarOrg.setLocationCity(record.get("Location city"));
		        gfarOrg.setCountry(record.get("Country"));
		        gfarOrg.setGeolocation(record.get("geolocation"));
		        gfarOrg.setCountryiso3(record.get("countryiso3"));
		        gfarOrg.setRegion(record.get("Region"));
		        gfarOrg.setGeographicScope(record.get("Geographic scope"));
		        gfarOrg.setAddress(record.get("Address"));
		        gfarOrg.setEmail(record.get("Email"));
		        gfarOrg.setFieldAgrovoc(record.get("Field Agrovoc"));
		        gfarOrg.setField(record.get("Field"));
		        gfarOrg.setNid(record.get("Nid"));		        
		        gfarOrg.setSourceURL(record.get("Source URL"));
		         
		        orgMap.put(record.get("Name"), gfarOrg);
		        nrec++; 
		    }
		} finally {
		    try {
				parser.close();
				reader.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
		System.out.println("Read this many records: "+ nrec);
		System.out.println("orgMap size: "+ orgMap.size());
		
		List<GfarOrg> orgList = new ArrayList<GfarOrg>();
		Iterator iter = orgMap.keySet().iterator();
		while (iter.hasNext()) {
			orgList.add((GfarOrg) orgMap.get(iter.next()));
		}
		
		FileWriter fileWriter = null;		 
	    CSVPrinter csvFilePrinter = null;
	    

	    CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(this.NEW_LINE_SEPARATOR);
	    csvFileFormat.withDelimiter(',');
	    csvFileFormat.withRecordSeparator(this.NEW_LINE_SEPARATOR);
	    csvFileFormat.withQuote('"');
	    
	    String outFileName = "/usr/local/src/javadev/data/egfar/egfar-orgs-all.csv";
	    
	    try {
	    	//initialize FileWriter object
			fileWriter = new FileWriter(outFileName);
			
			//initialize CSVPrinter object 
	        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
	        
	        //Create CSV file header
	        csvFilePrinter.printRecord(FILE_HEADER);
	        
	        for (GfarOrg gfarOrg: orgList) {
	        	List<String> orgRecord = new ArrayList<String>();
	        	orgRecord.add(StringUtils.replace(gfarOrg.getName(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getAcronym(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getDescription(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getType(),"\"", ""));		        
	        	orgRecord.add(StringUtils.replace(gfarOrg.getWebsite(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getLocationCity(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getCountry(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getGeolocation(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getCountryiso3(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getRegion(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getGeographicScope(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getAddress(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getEmail(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getFieldAgrovoc(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getField(),"\"", ""));
	        	orgRecord.add(StringUtils.replace(gfarOrg.getNid(),"\"", ""));		        
	        	orgRecord.add(StringUtils.replace(gfarOrg.getSourceURL(),"\"", "")); 
	        	csvFilePrinter.printRecord(orgRecord);
	        }
	    } catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
			}
		}

		System.out.println("done.");
		
	}

}
