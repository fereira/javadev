package edu.cornell.library.folioapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test; 

public class FolioInventoryServiceTest extends ApiBaseTest {

    @Test
    public void testGetInventoryByBarcode() {
        
        String arry[] = { "31924108807029", "31924080627106", "31924117718803", "31924114941911", "31924118759715" };
        String badBarcode[] = {"31924108808894"};
        String oneBarcode[] = {"31924081189056"};
        
        List<String> barcodes = (List) Arrays.asList(oneBarcode); 
        FolioInventoryService service = new FolioInventoryService();
       
        for (String barcode : barcodes) {
            try { 
                
                JSONObject jsonObject = service.getInventoryItem(barcode);
                System.out.println(jsonObject.toString(3));
                assertNotNull(jsonObject);
                  
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }
    }

}
