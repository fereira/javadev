package edu.cornell.library.folioapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.json.JSONObject;
import org.junit.jupiter.api.Test; 

public class FolioBudgetServiceTest extends ApiBaseTest {

    @Test
    public void testGetBudget() { 
        String fundCode = "p6793"; 
        String fiscalYearCode = "FY2021";
        FolioBudgetService service = new FolioBudgetService();
       
        //System.out.println("endpoint: "+ budgetEndpoint);
        try { 
            
            JSONObject budgetsObject = service.getBudget(fundCode, fiscalYearCode);
            System.out.println(budgetsObject.toString(3));
            assertNotNull(budgetsObject);
            int totalRecords = (Integer) budgetsObject.get("totalRecords");
            assertNotNull(totalRecords);
            assertEquals(totalRecords, 1);
            //System.out.println(budgetsObject.toString(3));     
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
