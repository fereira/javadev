package net.fereira.gps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.converters.extended.NamedCollectionConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import net.fereira.gps.bo.GPX;
import net.fereira.gps.bo.Groundspeak;
import net.fereira.gps.bo.Waypoint;
import net.fereira.gps.bo.Attribute;
import net.fereira.gps.bo.CacheLog;

public class GPX2Text {

	public GPX2Text() {
		
		
	}
	
	public static void main(String[] args) {
		GPX2Text app = new GPX2Text();
		if (args.length != 1) {
			System.err.println("You must provide a gpx file name");
			System.exit(-1);
		}
		app.run(args[0]);
	}
	
	public  void run(String filename) {
		
		XStream xstream = new XStream(new DomDriver());
		xstream.registerConverter(new GPXConverter());
		
		xstream.registerLocalConverter(GPX.class, "waypoints", new NamedCollectionConverter(xstream.getMapper(),
	            "waypoint", Object.class));
		
		xstream.alias("gpx", GPX.class);
		xstream.alias("wpt", Waypoint.class); 
		xstream.alias("groundspeak:cache", Groundspeak.class); 
		xstream.aliasField("groundspeak:attribute", Attribute.class, "value");
		
		xstream.addImplicitCollection(GPX.class, "waypoints");
		 
		xstream.autodetectAnnotations(true);
		
		GPX gpx = new GPX();
		try {
			String gpxXml = FileUtils.readFileToString(new File(filename));
			gpx = (GPX) xstream.fromXML(gpxXml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("name: "+ gpx.getName());
		System.out.println("desc: "+ gpx.getDesc());
		List<Waypoint> waypoints = gpx.getWaypoints();
		for (Waypoint wpt: waypoints) {
			System.out.println("wpt/name: "+wpt.getName());
			System.out.println("wpt/desc: "+wpt.getDesc());
			System.out.println("wpt/lat: "+wpt.getLat());
			System.out.println("wpt/lon: "+wpt.getLon());
			System.out.println("wpt/url: "+wpt.getUrl());
			System.out.println("wpt/urlname: "+wpt.getUrlname());
			System.out.println("wpt/sym: "+wpt.getSym());
			System.out.println("wpt/type: "+wpt.getType());
			Groundspeak groundspeak = wpt.getGroundspeak();
			System.out.println("wpt/groundspeak:name : "+groundspeak.getName());
			System.out.println("wpt/groundspeak:placed_by : "+groundspeak.getPlaced_by());
			System.out.println("wpt/groundspeak:owner : "+groundspeak.getOwner());
			System.out.println("wpt/groundspeak:type : "+groundspeak.getType());
			System.out.println("wpt/groundspeak:container : "+groundspeak.getContainer());
			System.out.println("wpt/groundspeak:difficulty : "+groundspeak.getDifficulty());
			System.out.println("wpt/groundspeak:terrain : "+groundspeak.getTerrain());
			System.out.println("wpt/groundspeak:country : "+groundspeak.getCountry());
			System.out.println("wpt/groundspeak:state : "+groundspeak.getState());
			
			List<Attribute> attributes = groundspeak.getAttributes();
			for (Attribute attribute: attributes) {
				System.out.println("wpt/groundspeak:attribute : "+attribute.toString());
				System.out.println("wpt/groundspeak:attribute id: "+attribute.getId());
				System.out.println("wpt/groundspeak:attribute inc: "+attribute.getInc());
			}
			System.out.println("wpt/groundspeak:short_description : "+groundspeak.getShortDescription());
			System.out.println("wpt/groundspeak:long_description : "+groundspeak.getLongDescription());
			System.out.println("wpt/groundspeak:encoded_hints : "+groundspeak.getHint());
			List<CacheLog> logs = groundspeak.getLogs();
			for (CacheLog log: logs) {
				System.out.println("wpt/groundspeak:log : "+log.getText());
				System.out.println("wpt/groundspeak:log type: "+log.getType());
				System.out.println("wpt/groundspeak:log date: "+log.getDate());
				System.out.println("wpt/groundspeak:log finder: "+log.getFinder());
			}
			System.out.println();		
		}
		
		System.out.println();
		
		
	}

}
