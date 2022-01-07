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
public class LookupOrdersTest extends LookupUtilBaseTest {
    
    private boolean debug = true;
    private CompositeConfiguration config = new CompositeConfiguration();
    PrintStream stdout = System.out;
 
	@Test
    public void testLookupOrders() {
	     
	    Map<String,String> map = new HashMap<String, String>();
	    String endpoint = this.getBaseOkapEndpoint()+"orders/composite-orders?limit=10000";
	    //String endpoint = this.getBaseOkapEndpoint()+"orders/composite-orders";
	    
	    try {
	       
	        String response = this.getUtil().getResponse(endpoint, this.getToken());
	        JSONObject jsonObject = new JSONObject(response);
	        JSONArray locArray = jsonObject.getJSONArray("purchaseOrders");
            System.out.println("len: " + locArray.length());
            if (debug) {
                System.setOut(new PrintStream(new FileOutputStream("/cul/src/SampleProject/composite-orders.json")));
                System.out.println(endpoint);
                System.out.println(jsonObject.toString(3));
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
