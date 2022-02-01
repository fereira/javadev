package edu.cornell.library.misc.json;

import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonMergeTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
	
	ObjectMapper mapper = new ObjectMapper();

    ObjectNode node1 = mapper.createObjectNode();
    
    
    node1.put("name", "Sriram");
    node1.put("age", 2);
    node1.put("hobby", "painting");
    
    ObjectNode node2 = mapper.createObjectNode();
    node2.put("name", "Sriram");
    node2.put("hobby", "Singing");
    node2.put("extraField", "read all about it");
    ArrayNode arrayNode = mapper.createArrayNode();
    ObjectNode node3 = mapper.createObjectNode();
    ObjectNode node4 = mapper.createObjectNode();
    node3.put("keyword", "one");
    node4.put("keyword", "two");
    arrayNode.add(node3);
    arrayNode.add(node4);
     
    node2.put("keywordArray1", arrayNode);
     
    
    System.out.println(node2.toString());
    JsonNode merged = merge(node1, node2) ;
    System.out.println(merged.toString());
  }
  
  public static JsonNode merge(JsonNode mainNode, JsonNode updateNode) {

	    Iterator<String> fieldNames = mainNode.fieldNames();
	    while (fieldNames.hasNext()) {
            
	        String fieldName = fieldNames.next();
	        System.out.println("fieldName: "+fieldName);
	        JsonNode jsonNode = mainNode.get(fieldName);
	        System.out.println("main node type: "+ mainNode.getNodeType());
	        // if field exists and is an embedded object
	        if (jsonNode != null && jsonNode.isObject()) {
	        	System.out.println("recursive merge"); 
	            merge(jsonNode, updateNode.get(fieldName));
	        }
	        else {
	            if (mainNode instanceof ObjectNode) {
	                // Overwrite field
	            	System.out.println("Overwrite field: "+fieldName);
	                JsonNode value = updateNode.get(fieldName);
	                ((ObjectNode) mainNode).put(fieldName, value);
	            }
	        }
	         

	    }
	    // Now get updateNode field names and add any that are not in the mainNode
	    Iterator<String> updateFieldNames = updateNode.fieldNames();
	    while (updateFieldNames.hasNext()) {
	    	String updateFieldName = updateFieldNames.next();
	    	if (! mainNode.has(updateFieldName)) {
	    		System.out.println("Adding field: "+ updateFieldName);
	    		JsonNode newNode = updateNode.get(updateFieldName);
	    		System.out.println("type: "+newNode.getNodeType());
	    	}
	    }

	    return mainNode;
	}

}