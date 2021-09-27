package edu.cornell.library.marcutils.util;

import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;

public class MarcUtilsNineFortyEightTest extends MarcUtilsBaseTest { 
	 
    boolean debug = true;
    
    // ignore test until it's determined what to do with notes
	@Test
	public void testGetReceivingNote() {
		//String fname = requestors;
		try {
		    for (String fname: this.fnames) {
		        System.out.println(fname);
    			List<Record> records = getRecords(fname);
    			for (Record record: records) {
    				DataField nineFortyEight = (DataField) record.getVariableField("948");
    				if (nineFortyEight != null) {
    				    List<Subfield> subfields = nineFortyEight.getSubfields();
    				    for (Subfield subfield: subfields) {
    				        System.out.println(subfield.toString());
    				    }
    				} 
    			}
		    }
		} catch (Exception e) {
			fail(e.getMessage());
		}
		 
	} 

}
