package net.fereira.agroknow;

import com.sun.jersey.api.client.ClientResponse;

public class ClientTest {

	public ClientTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String input="Giannis Stoitsis is a person";
		String source_lang = "en";
		String target_lang = "en";
		ClientResponse response = FREMEProjectsClient.postText(input, source_lang, target_lang);
        System.out.println("status: "+ response.getStatus());
	}

}
