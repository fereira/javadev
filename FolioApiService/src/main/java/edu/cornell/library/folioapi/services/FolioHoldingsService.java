package edu.cornell.library.folioapi.services; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

 
public class FolioHoldingsService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    
    public FolioHoldingsService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getHoldings(String instanceId) {
        String endpoint = getBaseOkapEndpoint() + "holdings-storage/holdings?query=(instanceId==" + instanceId + ")";
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}
