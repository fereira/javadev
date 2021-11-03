package edu.cornell.library.folioapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test; 

public class FolioOrganizationServiceTest extends ApiBaseTest {

    @Test
    public void testGetVendors() {   
        FolioOrganizationService service = new FolioOrganizationService(); 
   
        try { 
            
            JSONObject jsonObject = service.getVendors();
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Test
    public void testGetOrganizationById() {
        String vendorId = "5cf94601-26cf-17af-be30-34742fff402f";
        FolioOrganizationService service = new FolioOrganizationService(); 
   
        try { 
            
            JSONObject jsonObject = service.getOrganizationById(vendorId);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Test
    public void testGetVendorByCode() {
        String vendorCode = "AMAZON";
        FolioOrganizationService service = new FolioOrganizationService(); 
   
        try { 
            
            JSONObject jsonObject = service.getVendorByCode(vendorCode);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }

}
