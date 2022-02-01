package edu.cornell.library.misc.json;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

 

public class JacksonTest {

	public JacksonTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JacksonTest app = new JacksonTest();
        app.run();
	}
	
	public void run() {
		System.out.println("Start.");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> btmap = new HashMap<String, String>();
		try {
			
			Map<String,Object> conceptData = mapper.readValue(new File("/usr/local/src/javadev/data/rice.json"), Map.class);
			JsonNode jsonNode = mapper.readTree(new File("/usr/local/src/javadev/data/rice.json"));
			List<JsonNode> btnodes = jsonNode.findValues("broaderTransitive");
			String uri = new String();
			String prefLabel = new String();
			// iterate over broader term nodes
			for (JsonNode node: btnodes) {
				Iterator it = node.fieldNames();
				
				while (it.hasNext()) {
					String bturi = (String) it.next();
					JsonNode broaderTermNode = node.path(bturi);
					if (broaderTermNode.findValue("uri") != null) {
					   uri = broaderTermNode.findValue("uri").asText();
					} else {
					   uri = "";
					}
					if (broaderTermNode.findValue("prefLabel") != null) {
					  prefLabel = broaderTermNode.findValue("prefLabel").asText();
					} else {
						prefLabel = "";
					}
					if (StringUtils.isNotEmpty(uri) && StringUtils.isNotEmpty(prefLabel)) {
					   btmap.put(prefLabel, uri); 
					}
				}
			}
			
			Iterator iter = btmap.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				System.out.println(key +": "+btmap.get(key));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done.");
	}

}
