package edu.cornell.library.folioapi.services; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

 
public class FolioBudgetService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    public FolioBudgetService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getBudget(String fundCode, String fiscalYearCode ) {
        String budgetEndpoint = getBaseOkapEndpoint() + "finance/budgets?query=(name=="  + fundCode + "-" + fiscalYearCode + ")";
        try { 
            String budgetResponse = getApiService().callApiGet(budgetEndpoint, getToken());
            JSONObject budgetsObject = new JSONObject(budgetResponse);
            return budgetsObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}
