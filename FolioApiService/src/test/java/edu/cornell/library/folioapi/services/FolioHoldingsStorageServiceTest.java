package edu.cornell.library.folioapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.json.JSONObject;
import org.junit.jupiter.api.Test; 

public class FolioHoldingsStorageServiceTest extends ApiBaseTest {

    @Test
    public void testGetHoldingsStorage() {  
        FolioHoldingsStorageService service = new FolioHoldingsStorageService();
       
        //System.out.println("endpoint: "+ HoldingsEndpoint);
        try { 
            
            JSONObject jsonObject = service.getHoldingsStorage(100);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
