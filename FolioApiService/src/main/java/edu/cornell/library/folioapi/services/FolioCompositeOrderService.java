package edu.cornell.library.folioapi.services; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

 
public class FolioCompositeOrderService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    
    public FolioCompositeOrderService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getCompositeOrder(String poNumber ) {
        String endpoint = getBaseOkapEndpoint() + "orders/composite-orders?limit=30&offset=0&query=((poNumber=" + poNumber + "))";
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getCompositeOrderByUUID(String orderUUID ) {
        String endpoint = getBaseOkapEndpoint() + "orders/composite-orders/"+ orderUUID;
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
