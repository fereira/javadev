package edu.cornell.library.marcutils.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;

public class MarcUtils490Test extends MarcUtilsBaseTest { 
	 
    boolean debug = true;
     
   	@Test
public void testGetSubfields() {
        
        try {
            for (String fname: this.fnames) {
                System.out.println(fname);
                List<Record> records = getRecords(fname);
                for (Record record: records) {
                    List<DataField> fields = record.getDataFields();
                    Iterator<DataField> fieldsIterator = fields.iterator();
                    List<String> eightThirties = new ArrayList<String>();
                    int i = 0;
                    while (fieldsIterator.hasNext()) {
                        
                        DataField field = (DataField) fieldsIterator.next();
                        if (StringUtils.equals(field.getTag(), "490")) {
                            i++;
                            eightThirties.add(i +": "+field.toString()); 
                        }
                    }
                    for (String s: eightThirties) {
                        System.out.println(s);
                    }
                    
                }
            }
            
             
        } catch (Exception e) {
            fail(e.getMessage());
        } 
       
    }
}
