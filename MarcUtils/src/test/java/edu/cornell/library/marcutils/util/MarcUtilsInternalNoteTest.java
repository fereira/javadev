package edu.cornell.library.marcutils.util;

import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;

public class MarcUtilsInternalNoteTest extends MarcUtilsBaseTest { 
	 
    boolean debug =  true;
    
    // ignore test until it's determined what to do with notes
	@Test
	public void testGetInternalNote() {
		//String fname = coutts;
		try {
		    for (String fname: this.fnames) {
    			List<Record> records = getRecords(fname);
    			for (Record record: records) {
    				DataField nineEighty = (DataField) record.getVariableField("980");
    				String notes = marcUtils.getInternalNotes(nineEighty);
    				if (debug) {
    					System.out.println(fname + " internal note: " + notes);
    				} else {
    					assertNotNull(notes);
    					assertTrue(notes.length() > 0);
    				}
    			}
		    }
		} catch (Exception e) {
			fail(e.getMessage());
		}
		 
	} 

}
