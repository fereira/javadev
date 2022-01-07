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
public class LookupFundCodesTest extends LookupUtilBaseTest {
    
    private boolean debug = true;
    private CompositeConfiguration config = new CompositeConfiguration();
    PrintStream stdout = System.out;
    String fundCode = "AMAZON";
 
	@Test
    public void testLookupFundCodes() {
	    String MAXLIMIT = "20"; 
	    Map<String,String> map = new HashMap<String, String>();
	    //String endpoint = this.getBaseOkapEndpoint()+"finance/funds?limit=3000";	    
	    String endpoint = this.getBaseOkapEndpoint()+"finance/funds?query=((fundStatus='Active'))&limit=1000&offset=0";
	    try {
	       
	        map = this.getUtil().getFundCodes(endpoint, this.getToken());            
	           
            if (debug) {
                System.setOut(new PrintStream(new FileOutputStream("/cul/src/SampleProject/fundCodes.json")));
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
