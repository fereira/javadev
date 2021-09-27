package edu.cornell.library.marcutils.util;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;

public class MarcUtilsVendorIdTest extends MarcUtilsBaseTest { 
	
	boolean debug = true;

	@Test
	public void testGetVendorId() {
		//String fname = requestors; 
		try {
		    for (String fname: this.fnames) {
    			List<Record> records = getRecords(fname);
    			for (Record record: records) {
    				DataField df = (DataField) record.getVariableField("961");
    				String vendorId = marcUtils.getVendorItemId(df);
    				 
    				if (debug) {
    					System.out.println(fname + " - vendorId: " + vendorId);
    				} else {
    					assertNotNull(vendorId);
    					assertTrue(vendorId.length() > 0);
    				} 
    			}
		    }
		} catch (Exception e) {
		    e.printStackTrace();
			fail(e.getMessage());
		}
		 
	} 

}
