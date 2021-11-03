package edu.cornell.library.folioapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test; 

public class FolioNotesServiceTest extends ApiBaseTest {

    @Test
    public void testGetNotes() {   
        FolioNotesService service = new FolioNotesService(); 
   
        try { 
            
            JSONObject jsonObject = service.getNotes();
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }
    
    @Test
    public void testGetNotesById() {
        String noteUUID = "479060c3-deed-4d9d-a60c-6967c44e388e";
        FolioNotesService service = new FolioNotesService(); 
   
        try { 
            
            JSONObject jsonObject = service.getNotesById(noteUUID);
            System.out.println(jsonObject.toString(3));
            assertNotNull(jsonObject);
              
        } catch (Exception e) {
            fail(e.getMessage());
        }
         
    }

}
