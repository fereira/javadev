package edu.cornell.library.marcutils.util;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;

public class MarcUtils700Test extends MarcUtilsBaseTest { 
	 
    boolean debug = true;
    String[] subfieldArray = {"a","b","c","d","f","g","j","k","l","n","p","t","u"};
    List<String> sfList = Arrays.asList(subfieldArray);
   	@Test
   	public void testGetSubfields() {
        //String fname = requestors;
        try {
            for (String fname: this.fnames) {
                System.out.println(fname);
                List<Record> records = getRecords(fname);
                for (Record record: records) {
                    DataField df = (DataField) record.getVariableField("490");
                    
                    if (df != null) {
                        List<Subfield> subfields = df.getSubfields();
                        for (Subfield subfield: subfields) {
                            char code = subfield.getCode();
                            System.out.println(code + ": "+ subfield.getData());                         
                            
                        }
                    }
                }
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }        
    }

}
