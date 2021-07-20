package net.fereira.agroknow;

import com.sun.jersey.api.client.ClientResponse;

public class DatasetClientTest {

	public DatasetClientTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		 
		ClientResponse response = DatasetClient.getText();
        System.out.println("status: "+ response.getStatus());
        String output = response.getEntity(String.class);
        System.out.println(output);
	}

}
