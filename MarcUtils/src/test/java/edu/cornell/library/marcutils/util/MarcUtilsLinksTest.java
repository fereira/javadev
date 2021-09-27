package edu.cornell.library.marcutils.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;


public class MarcUtilsLinksTest extends MarcUtilsBaseTest {
	
	boolean debug = true;

	@Test
	public void testGetLinks() {
		//String fname = requestors;
		for (String fname: this.fnames) {
			try {
				List<Record> records = getRecords(fname);
				for (Record record: records) {
					JSONArray eresources = marcUtils.getLinks(record);
					 
					if (debug && eresources != null) {
				       System.out.println(eresources.toString(3));
					} else {
						assertNotNull(eresources);
						if (eresources.length() > 0) {
							JSONObject obj = (JSONObject) eresources.get(0);
							assertNotNull(obj);
						}
					}
				}
		    } catch (Exception e) {
			    fail(e.getMessage());
		    }
		}
		
	}	 

}
