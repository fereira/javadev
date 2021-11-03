package edu.cornell.library.folioapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test; 

public class FolioPermsetServiceTest extends ApiBaseTest {

    @Test
    public void testGetAllPermset() {   
        FolioPermsetService service = new FolioPermsetService(); 
   
        try { 
            
            JSONObject jsonObject = service.getAllPermset();
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Test
    public void testGetPermsetByUUID() {
        String vendorId = "5cf94601-26cf-17af-be30-34742fff402f";
        FolioPermsetService service = new FolioPermsetService(); 
   
        try { 
            
            JSONObject jsonObject = service.getPermsetByUUID(vendorId);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Test
    public void testGetPermset() {
        String vendorCode = "AMAZON";
        FolioPermsetService service = new FolioPermsetService(); 
   
        try { 
            
            JSONObject jsonObject = service.getPermset(vendorCode);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }

}
