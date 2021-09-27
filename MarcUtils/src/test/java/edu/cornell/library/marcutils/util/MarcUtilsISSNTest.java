package edu.cornell.library.marcutils.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;

public class MarcUtilsISSNTest extends MarcUtilsBaseTest { 
	
    boolean debug = true;

	@Test
	public void testGetISSN() {
	    JSONArray identifiers = new JSONArray();
		List<String> myFnames = new ArrayList<String>();
		myFnames.add(this.amazonFO);
		
		try {
		    for (String fname: fnames) {
		        System.out.println(fname);
    			List<Record> records = getRecords(fname);
    			for (Record record: records) {
    				DataField field = (DataField) record.getVariableField("022");
    				if (field != null) {
    				    List<Subfield> subfields =  field.getSubfields();
    				    for (Subfield subfield: subfields) {
    				        if (subfield.getCode() == 'a') {
    	                        JSONObject identifier = new JSONObject();
    	                        String fullValue = subfield.getData();
    	                        if (field.getSubfield('c') != null) fullValue += " " + field.getSubfieldsAsString("c");
    	                        if (field.getSubfield('q') != null) fullValue += " " + field.getSubfieldsAsString("q");
    	                        identifier.put("value",fullValue);
    	                        
    	                        identifier.put("identifierTypeId", "ISSN");
    	                        identifiers.put(identifier);
    	                    } else if (subfield.getCode() == 'l') {
    	                        JSONObject identifier = new JSONObject();
    	                        String fullValue = subfield.getData();
    	                        if (field.getSubfield('c') != null) fullValue += " " + field.getSubfieldsAsString("c");
    	                        if (field.getSubfield('q') != null) fullValue += " " + field.getSubfieldsAsString("q");
    	                        identifier.put("value", fullValue);
    	                        identifier.put("identifierTypeId", "Linking ISSN");
    	                        identifiers.put(identifier);
    	                    } else {
    	                        JSONObject identifier = new JSONObject();
    	                        String fullValue = "";
    	                        if (field.getSubfield('z') != null) fullValue += field.getSubfieldsAsString("z");
    	                        if (field.getSubfield('y') != null) fullValue += " " +  field.getSubfieldsAsString("y");
    	                        if (field.getSubfield('m') != null) fullValue += " " + field.getSubfieldsAsString("m");
    	                        if (fullValue != "") {
    	                            identifier.put("value", fullValue);
    	                            identifier.put("identifierTypeId",  "Invalid ISSN");
    	                            identifiers.put(identifier);
    	                        }
    	                    }
    				    }
    				}
    			}
    			System.out.println(identifiers.toString(3));
		    }
		} catch (Exception e) {
		    e.printStackTrace();
			fail(e.getMessage());
		}		 
	} 

}
