package edu.cornell.library.folioapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test; 

public class FolioSourceStorageServiceTest extends ApiBaseTest {

    @Test
    public void testGetAllSourceStorage() {   
        FolioSourceStorageService service = new FolioSourceStorageService(); 
   
        try { 
            
            JSONObject jsonObject = service.getAllSourceStorage();
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }         
    }
    
    @Test
    public void testGetSourceStorageById() {
        String vendorId = "5cf94601-26cf-17af-be30-34742fff402f";
        FolioSourceStorageService service = new FolioSourceStorageService(); 
   
        try { 
            
            JSONObject jsonObject = service.getSourceStorageById(vendorId);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }         
    }

}
