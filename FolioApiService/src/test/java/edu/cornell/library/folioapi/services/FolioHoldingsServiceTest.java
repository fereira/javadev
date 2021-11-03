package edu.cornell.library.folioapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.json.JSONObject;
import org.junit.jupiter.api.Test; 

public class FolioHoldingsServiceTest extends ApiBaseTest {

    @Test
    public void testGetHoldings() { 
        String instanceId = "820432a1-2000-49c2-af1a-fd0a39ebf36d";  
        FolioHoldingsService service = new FolioHoldingsService();
       
        //System.out.println("endpoint: "+ HoldingsEndpoint);
        try { 
            
            JSONObject jsonObject = service.getHoldings(instanceId);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
