package net.fereira.agroknow;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class FREMEProjectsClient {
	

	//public static void main(String[] args) {}
	static ClientResponse output;
	//static String output;
	
	public static ClientResponse postText(String input,String source_lang,String target_lang ){
		try {
			
			//Parameter definitions
		   // input="The article shows the need to develop preparations antioxidant actions and identify the preparative form, optimal for use in a stressful poultry industry. Because the impact of negative factors emergency force feeding a bird is greatly reduced, the body experiences a deficiency of biologically active substancesand their additional introduction food is ineffection, need to find other ways of introducing biological additives. As a means of antioxidant action is proposedthe use of marsh cinquefoil, which consists of flavonoids - hesperidin, luteolin-7-glycoside, robinin, vicenin,dihydroquercetin, hyperoside";
		    String informat= "text";
		    String outformat = "json-ld";
			source_lang= "en" ;
			target_lang = "en";
			String dataset= "cordis-fp7";
				 
			Client client = Client.create();
	 //http://api.freme-project.eu/0.3/e-entity/freme-ner/documents
			//?input=Giannis Stoitsis is a person.&informat=text&outformat=turtle&language=en&dataset=cordis-fp7
			WebResource webResource = client
			   .resource("http://api.freme-project.eu/0.6/e-entity/freme-ner/documents")
			   .queryParam("input", input)
			   .queryParam("informat", informat)
			   .queryParam("outformat", outformat)
			   .queryParam("language", source_lang)
			   .queryParam("dataset", dataset);
			
			//input=hello+world&informat=text&source-lang=en&target-lang=de&domain=TaaS-1000
		//	String input = null;// "{\"input\":\"hello world\",\"informat\":\"text\",\"source-lang\":\"en\",\"target-lang\":\"de\",\"domain\":\"TaaS-1000\"}";
			String dump = null;
			ClientResponse response = webResource
					.type("application/json+ld")
					.accept("application/json+ld")
          	        .post(ClientResponse.class,dump);
				
			////.accept("application/json+ld")
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}else{
				output = response;
			}
			/*
			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);
	 			*/		
		 ////// output = response;//.getEntity(String.class);
						
	/////	  Gson gson = new Gson();
			//ContextElements cntxEl = null;			
			//String json = null;			
	//////	  output = gson.toJson(output);
		  response.getClient().destroy();	
			
		  } catch (Exception e) {
	 
			e.printStackTrace();
	 
		  }
		return output;
	 
		
	}
	
	
	
	

} 