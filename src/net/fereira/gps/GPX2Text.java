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
		xstream.aliasField("groundspeak:cache", Waypoint.class, "geocache");
		xstream.aliasField("groundspeak:name", Groundspeak.class, "name");
		xstream.aliasField("groundspeak:placed_by", Groundspeak.class, "placed_by");
		xstream.aliasField("groundspeak:owner", Groundspeak.class, "owner");
		
		
		xstream.addImplicitCollection(GPX.class, "waypoints");
		xstream.processAnnotations(GPX.class);
		xstream.processAnnotations(Waypoint.class);
		xstream.processAnnotations(Groundspeak.class);
		xstream.processAnnotations(Attribute.class);
		xstream.processAnnotations(CacheLog.class); 
		
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
					
		}
		
		
	}

}
