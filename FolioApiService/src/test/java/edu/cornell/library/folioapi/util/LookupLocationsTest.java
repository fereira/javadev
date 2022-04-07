package edu.cornell.library.folioapi.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.apache.commons.configuration.CompositeConfiguration;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled; 

/**
 * Unit test for simple App.
 */
public class LookupLocationsTest extends LookupUtilBaseTest {
    
    private boolean debug = true;
    private CompositeConfiguration config = new CompositeConfiguration();
    PrintStream stdout = System.out;
 
	@Test
    public void testLookupMaterialTypes() {
	     
	    Map<String,String> map = new HashMap<String, String>();
	    String endpoint = this.getBaseOkapEndpoint()+"locations?query(isActive=true)&limit=" + MAXLIMIT;
	    
	    
	    try {       
	         
	        map = this.getUtil().getLocations(endpoint, this.getToken());
	        if (debug) {
                System.setOut(new PrintStream(new FileOutputStream("/cul/src/javadev/FolioApiService/output/locations.json")));
                System.out.println("map size: " + map.size());
                Iterator iter = map.keySet().iterator();
                while (iter.hasNext()) {
                    String key= (String) iter.next();
                    String val = map.get(key);
                    System.out.println("key: "+ key);
                    System.out.println("val: "+ val);
                    System.out.println();
                }   
                System.setOut(stdout);
            } else {                 
                assertNotNull(map); 
            } 
            
        } catch (IOException e) {
            fail(e.getMessage());
        } catch (InterruptedException e) {
            fail(e.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            //fileOut.close();
        }
    }
	
	
}
