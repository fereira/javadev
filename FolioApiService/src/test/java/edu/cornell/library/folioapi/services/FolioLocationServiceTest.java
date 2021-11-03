package edu.cornell.library.folioapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test; 

public class FolioLocationServiceTest extends ApiBaseTest {

    @Disabled
    public void testGetLocations() {   
        FolioLocationService service = new FolioLocationService(); 
   
        try { 
            
            JSONObject jsonObject = service.getLocations();
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Test
    public void testGetLocationById() {   
        FolioLocationService service = new FolioLocationService(); 
        String locationId = "4778f0d5-e04b-44b1-8819-138d0f88991c";
        try { 
            
            JSONObject jsonObject = service.getLocationById(locationId);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }

}
