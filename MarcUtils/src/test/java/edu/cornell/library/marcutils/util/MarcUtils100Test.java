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

public class MarcUtils100Test extends MarcUtilsBaseTest { 
	 
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
                    DataField df = (DataField) record.getVariableField("100");
                    String contributorName = "";
                    if (df != null) {
                        List<Subfield> subfields = df.getSubfields();
                        for (Subfield subfield: subfields) {
                            String subfieldAsString = String.valueOf(subfield.getCode());
                            if (subfield.getCode() == '4') {
                                System.out.println("4: "+ subfield.getData());
                            }
                            else if (subfield.getCode() == 'e') {
                                System.out.println("a: "+ subfield.getData());
                            }
                            else if (sfList.contains(subfieldAsString)) {
                                if (!contributorName.isEmpty()) {
                                    contributorName += ", " + subfield.getData();
                                }
                                else {
                                    contributorName +=  subfield.getData();
                                }
                                System.out.println(subfieldAsString + ": "+ contributorName);
                            }
                            
                        }
                    }
                }
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    } 
}
