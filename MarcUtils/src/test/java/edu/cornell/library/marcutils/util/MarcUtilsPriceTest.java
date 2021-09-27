package edu.cornell.library.marcutils.util;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;

public class MarcUtilsPriceTest extends MarcUtilsBaseTest { 
	
    boolean debug = true;
    protected final Log logger = LogFactory.getLog(getClass());

	@Test
	public void testGetPrice() {
		//String fname = amazonFO; 
		try {
		    for (String fname: this.fnames) {
    			List<Record> records = getRecords(fname);
    			for (Record record: records) {
    				DataField nineEightyOne = (DataField) record.getVariableField("981");
    				String price = marcUtils.getPrice(nineEightyOne);
    				if (debug) {
    					System.out.println(fname + " - price: " + price);
    				} else {
    					assertNotNull(price);
    					assertTrue(price.length() > 0);
    				} 
    			}
		    }
		} catch (Exception e) {
			fail(e.getMessage());
		}
		 
	} 

}
