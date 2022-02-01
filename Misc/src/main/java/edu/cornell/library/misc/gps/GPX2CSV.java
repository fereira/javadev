package edu.cornell.library.misc.gps;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVParser;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import edu.cornell.library.misc.gps.bo.GPX;
import edu.cornell.library.misc.gps.bo.Waypoint;

public class GPX2CSV {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       GPX2CSV app = new GPX2CSV();
       app.run();
	}
	
	public void run() {

		Writer swriter = new StringWriter();

		ICSVWriter csvWriter = new CSVWriterBuilder(swriter).withSeparator('\t')
				.withQuoteChar(ICSVParser.NULL_CHARACTER).withEscapeChar(ICSVParser.DEFAULT_ESCAPE_CHARACTER)
				.withLineEnd(ICSVWriter.DEFAULT_LINE_END).build();
		
		// Create GPX class with three waypoints
		GPX gpx = new GPX();
		gpx.setName("Test GPX");
		gpx.setDesc("Test Desc");
		List<Waypoint> waypoints = new ArrayList<Waypoint>();
		
		Waypoint wpt1 = new Waypoint();
		wpt1.setLat("42.510767");
		wpt1.setLon("-76.19445");
		wpt1.setName("GCZ9HR");
		wpt1.setDesc("Mary, Mary Quite Contrary by Woodland Clan, Traditional Cache (1/1.5)");
		wpt1.setUrl("http://www.geocaching.com/seek/cache_details.aspx?guid=540c783b-ab0d-48ef-bab2-404e385242a2");
		wpt1.setUrlname("Mary, Mary, Quite Contrary");
		wpt1.setSym("Geocache Found");
		wpt1.setType("Geocache|Traditional Cache");
		waypoints.add(wpt1);
		
		Waypoint wpt2 = new Waypoint();
		wpt2.setLat("42.554117");
		wpt2.setLon("-76.14815");
		wpt2.setName("GCZFV1");
		wpt2.setDesc("The Dark Tower by gregger and wacky, Traditional Cache (1.5/1.5)");
		wpt2.setUrl("http://www.geocaching.com/seek/cache_details.aspx?guid=3ee734ab-afca-48b5-a9c9-b177f38a19b1");
		wpt2.setUrlname("The Dark Tower");
		wpt2.setSym("Geocache Found");
		wpt2.setType("Geocache|Traditional Cache");
		waypoints.add(wpt2);
		
		Waypoint wpt3 = new Waypoint();
		wpt3.setLat("37.885767");
		wpt3.setLon("-75.344633");
		wpt3.setName("GCZNVY");
		wpt3.setDesc("Assateague Island On The Move by climbstuff, Earthcache (2/2)");
		wpt3.setUrl("http://www.geocaching.com/seek/cache_details.aspx?guid=fcad3b5c-ab4b-4ee9-a592-4a28dc3047a0");
		wpt3.setUrlname("Assateague Island On The Move");
		wpt3.setSym("Geocache Found");
		wpt3.setType("Geocache|Earth Cache");
		waypoints.add(wpt3);
		
		gpx.setWaypoints(waypoints);
		ColumnPositionMappingStrategy cpms = new ColumnPositionMappingStrategy();
	    //cpms.setType(Waypoint.class);
		StatefulBeanToCsv<Waypoint> sbc = new StatefulBeanToCsvBuilder<Waypoint>(swriter)
			       .withSeparator(CSVWriter.DEFAULT_SEPARATOR) 
			       .build(); 
		// Write the record to file
		try {
		   sbc.write(gpx.getWaypoints());
		   
		   //for (Waypoint wpt: gpx.getWaypoints()) {			   
		   //   sbc.write(wpt);
		   //}
		   swriter.close(); 
		    
		} catch (Exception ex) {
			ex.printStackTrace();
		 
		}
		System.out.println(swriter.toString());
		System.out.println("Done.");
	}

}
