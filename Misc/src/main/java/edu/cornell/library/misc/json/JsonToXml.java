package edu.cornell.library.misc.json;

import java.io.File; 

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.json.XML; 
 

public class JsonToXml {

	public JsonToXml() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JsonToXml app = new JsonToXml();
        app.run();
	}
	
	public void run() {
		//System.out.println("Start."); 
		JSONObject jsonObject = null;
		try {
 
 			String jsonString  = FileUtils.readFileToString(new File("/usr/local/src/grid/grid_test.json"));
 			 
 			jsonObject = new JSONObject(jsonString);
 			String xml = XML.toString(jsonObject, "root");
 			System.out.println(xml);
 			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Done.");
	}
	
	 
	
	 
}
