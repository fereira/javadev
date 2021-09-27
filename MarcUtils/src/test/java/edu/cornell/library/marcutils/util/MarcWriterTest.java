package edu.cornell.library.marcutils.util;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.marc4j.MarcJsonWriter;
import org.marc4j.MarcStreamWriter;
import org.marc4j.MarcWriter;
import org.marc4j.converter.impl.AnselToUnicode;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;

public class MarcWriterTest extends MarcUtilsBaseTest { 
	
	boolean debug = true;
	
    
	@Test
	public void testWriter() {
	    List<String> myFnames = new ArrayList<String>();
	    myFnames.add(this.singleharrass);
	    
	    UUID randomUUID = UUID.randomUUID();
	    
	   // ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       // MarcWriter w = new MarcStreamWriter(byteArrayOutputStream, "UTF-8");        
        //AnselToUnicode conv = new AnselToUnicode();
        //w.setConverter(conv);
	    
		try {
		    for (String fname: myFnames) {
    			List<Record> records = getRecords(fname);
    			for (Record record: records) {
    			    //System.out.println("MARC RECORD: " + record.toString());
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    MarcJsonWriter jsonWriter =  new MarcJsonWriter(baos);
                    jsonWriter.setUnicodeNormalization(true);
                    jsonWriter.write(record);
                    jsonWriter.close();
                    
                    String jsonString = baos.toString();
                    JSONObject mRecord = new JSONObject(jsonString);
                    JSONObject content = new JSONObject();
                    content.put("content", mRecord);
                    //System.out.println("MARC TO JSON: " + mRecord.toString(3));
                    
                    //GET THE RAW MARC READY TO POST TO THE API
                    ByteArrayOutputStream rawBaos = new ByteArrayOutputStream();
                    MarcWriter writer = new MarcStreamWriter(rawBaos);
                    AnselToUnicode conv = new AnselToUnicode();
                    writer.setConverter(conv);
                    writer.write(record);
                    // TODO: is this a bug?...nothing was previously written to byteArrayOutputStream
                    JSONObject jsonWithRaw = new JSONObject();
                    jsonWithRaw.put("id", UUID.randomUUID().toString()); //
                    // I think this should be using rawBaos
                    
                    jsonWithRaw.put("content", rawBaos);
                    
                    //RAW RECORD
                    JSONObject rawRecordObject = new JSONObject();
                    rawRecordObject.put("id", UUID.randomUUID().toString());
                    rawRecordObject.put("content", jsonWithRaw.toString());
                    
                    JSONObject sourceRecordStorageObject = new JSONObject();
                    sourceRecordStorageObject.put("recordType", "MARC");
                    
                    //PARSED RECORD
                    
                    JSONObject parsedRecord = new JSONObject();
                    parsedRecord.put("id", UUID.randomUUID().toString());
                    parsedRecord.put("content", mRecord);
                    sourceRecordStorageObject.put("rawRecord", rawRecordObject);
                    sourceRecordStorageObject.put("parsedRecord", parsedRecord);
                    sourceRecordStorageObject.put("id",  UUID.randomUUID().toString());
                    
                    
                    
                    System.out.println("sourceRecordStorageObject: " + sourceRecordStorageObject.toString(3));
                    
    			}
		    }
		} catch (Exception e) {
			fail(e.getMessage());
		}
		 
	} 

}
