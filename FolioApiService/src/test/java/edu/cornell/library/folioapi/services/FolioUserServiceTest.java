package edu.cornell.library.folioapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test; 

public class FolioUserServiceTest extends ApiBaseTest {

 
    
    @Test
    public void testGetUserByName() {
        String name = "app_order-import";
        FolioUserService service = new FolioUserService(); 
   
        try { 
            
            JSONObject jsonObject = service.getUserByName(name);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }         
    }

}
