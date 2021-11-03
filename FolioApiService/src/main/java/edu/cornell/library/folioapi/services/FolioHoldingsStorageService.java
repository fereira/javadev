package edu.cornell.library.folioapi.services; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

 
public class FolioHoldingsStorageService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    
    public FolioHoldingsStorageService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getHoldingsStorage(int limit) {
        String endpoint = getBaseOkapEndpoint() + "holdings-storage/holdings?limit="+ String.valueOf(limit);
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
