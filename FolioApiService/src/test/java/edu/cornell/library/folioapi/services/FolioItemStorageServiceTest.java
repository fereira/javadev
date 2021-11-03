package edu.cornell.library.folioapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test; 

public class FolioItemStorageServiceTest extends ApiBaseTest {

    @Test
    public void testGetItemStorageyById() {
        
        String itemId = "ae685959-7f36-4f5c-a83c-c747b4c4ed41";  
        FolioItemStorageService service = new FolioItemStorageService();
       
   
        try { 
            
            JSONObject jsonObject = service.getItemStorage(itemId);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }

}
