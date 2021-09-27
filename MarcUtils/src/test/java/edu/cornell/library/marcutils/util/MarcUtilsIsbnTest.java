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

public class MarcUtilsIsbnTest extends MarcUtilsBaseTest { 
	
    boolean debug = true;

	@Test
	public void testGetIsbn() {
	     
		List<String> myFnames = new ArrayList<String>();
		myFnames.add(this.harrass);
		
		try {
		    for (String fname: myFnames) {
		        System.out.println(fname);
    			List<Record> records = getRecords(fname);
    			for (Record record: records) {
    			    JSONObject jsonObj = new JSONObject();
    			    JSONArray productIds = marcUtils.getISBN(record, "8261054f-be78-422d-bd51-4ed9f33c3422");
    			    jsonObj.put("productIds", productIds);
    			    System.out.println(jsonObj.toString(3));
    			} 
		    }
		} catch (Exception e) {
		    e.printStackTrace();
			fail(e.getMessage());
		}
		 
	} 

}
