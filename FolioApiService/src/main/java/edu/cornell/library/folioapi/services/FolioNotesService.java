package edu.cornell.library.folioapi.services; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

 
public class FolioNotesService extends FolioBaseService {
    protected final Log logger = LogFactory.getLog(getClass());
    
    public FolioNotesService() {
        // TODO Auto-generated constructor stub
    }
    
    public JSONObject getNotes() {
        String endpoint = getBaseOkapEndpoint() + "order/notes";
        try { 
            String response = getApiService().callApiGet(endpoint, getToken());
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    
    public JSONObject getNotesById(String noteId) {
        String endpoint = getBaseOkapEndpoint() + "notes/"+ noteId;
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
