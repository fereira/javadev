package net.fereira.agroknow;

import com.sun.jersey.api.client.ClientResponse;

public class ClientTest {

	public ClientTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String input="John Fereira, United States";
		//String input="Cornell";
		String source_lang = "en";
		String target_lang = "en";
		String dataset = "orcid";
		ClientResponse response = EntityClient.postText(input, source_lang, target_lang, dataset);
		//ClientResponse response = DatasetClient.postText(input, source_lang, target_lang);
        System.out.println("status: "+ response.getStatus());
        String output = response.getEntity(String.class);
        System.out.println(output);
	}

}
