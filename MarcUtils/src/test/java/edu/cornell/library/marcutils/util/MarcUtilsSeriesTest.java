package edu.cornell.library.marcutils.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;

public class MarcUtilsSeriesTest extends MarcUtilsBaseTest { 
	 
    boolean debug = true;
     
   	@Test
    public void testGetSubfields() {
   	    List<String> myFnames = new ArrayList<String>();
        myFnames.add(this.harrassowitz);
        
        try {
            for (String fname: myFnames) {
                System.out.println(fname);
                List<Record> records = getRecords(fname);
                int recNum = 1;
                for (Record record: records) {
                    System.out.println("Record: "+ recNum++);
                    List<DataField> fields = record.getDataFields();
                    Iterator<DataField> fieldsIterator = fields.iterator();
                    List<String> seriesFields = new ArrayList<String>();
                    int i = 0;
                    
                    while (fieldsIterator.hasNext()) {
                        
                        DataField field = (DataField) fieldsIterator.next();
                        if (StringUtils.equals(field.getTag(), "490") || StringUtils.equals(field.getTag(), "830") ) {
                            seriesFields.add(field.toString());
                        }
                    }
                    for (String s: seriesFields) {
                        System.out.println(s);
                    }
                    System.out.println();
                    
                }
            }
            
             
        } catch (Exception e) {
            fail(e.getMessage());
        } 
       
    }
}
