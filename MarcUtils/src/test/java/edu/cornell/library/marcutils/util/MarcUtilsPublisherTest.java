package edu.cornell.library.marcutils.util;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;

public class MarcUtilsPublisherTest extends MarcUtilsBaseTest { 
	
    boolean debug = true;

	@Test
	public void testGetPublisher() {
		//String fname = amazonFO; 
		try {
		    for (String fname: this.fnames) {
    			List<Record> records = getRecords(fname);
    			for (Record record: records) {
    				DataField df = (DataField) record.getVariableField("264");
    				//System.out.println(df.toString());
    				String publisher = marcUtils.getPublisher(df);
    				if (debug) {
    					System.out.println(fname + " - publisher: " + publisher);
    				} else {
    					assertNotNull(publisher);
    					assertTrue(publisher.length() > 0);
    				} 
    			}
		    }
		} catch (Exception e) {
			fail(e.getMessage());
		}		 
	} 

}
