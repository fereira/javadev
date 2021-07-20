package net.fereira.agroknow;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class DatasetClient
{
	

	 
	static ClientResponse output;
	 
	
	public static ClientResponse getText(){
		try {  
			Client client = Client.create();
	 
			WebResource webResource = client.resource("http://api.freme-project.eu/current/e-entity/freme-ner/datasets");
			 
		 
			ClientResponse response = webResource
					.type("application/json+ld")
					.accept("application/json+ld")
          	        .get(ClientResponse.class); 
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			} else { 
				output = response;
			} 
		  response.getClient().destroy();	
			
		  } catch (Exception e) {
	 
			e.printStackTrace();
	 
		  }
		return output;
	 
		
	}
	
	
	
	

} 