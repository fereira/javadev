package edu.cornell.library.folioapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.json.JSONObject;
import org.junit.jupiter.api.Test; 

public class FolioCompositeOrderServiceTest extends ApiBaseTest {

    @Test
    public void testGetBudget() { 
        String poNumber = "11948";
        FolioCompositeOrderService service = new FolioCompositeOrderService();
       
        //System.out.println("endpoint: "+ budgetEndpoint);
        try { 
            
            JSONObject jsonObject = service.getCompositeOrder(poNumber);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);   
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
