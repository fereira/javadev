package edu.cornell.library.folioapi.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled; 

/**
 * Unit test for simple App.
 */
public class LookupVendorInfoTest extends LookupUtilBaseTest {
    
    private boolean debug = true;
    PrintStream stdout = System.out;
	@Test
    public void testVendorInfoTest() {
	    Map<String,String> map = new HashMap<String, String>();
	    String endpoint = this.getBaseOkapEndpoint()+"orders/composite-orders?limit=300";
	    System.out.println(endpoint);
	    try {
	           
            String response = this.getUtil().getResponse(endpoint, this.getToken());
            JSONObject jsonObject = new JSONObject(response);
           
            if (debug) {
                System.setOut(new PrintStream(new FileOutputStream("/cul/src/SampleProject/output.json")));
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
