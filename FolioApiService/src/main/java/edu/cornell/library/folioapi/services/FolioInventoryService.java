package edu.cornell.library.folioapi.services; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

 
public class FolioInventoryService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    
    public FolioInventoryService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getInventoryItem(String barcode) {
        String endpoint = getBaseOkapEndpoint() + "inventory/items?query=(barcode=="  + barcode + ")";
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
