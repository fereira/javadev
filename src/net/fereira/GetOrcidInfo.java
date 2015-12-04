package net.fereira;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.fereira.service.HttpService;



public class GetOrcidInfo {

	public GetOrcidInfo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetOrcidInfo app = new GetOrcidInfo();
		System.out.println("Running...");
		app.run();
        System.out.println("Done.");
	}
	
	public void run() {
		//String contentType = "application/vnd.crossref-api-message+json";
		String contentType = "";
		String firstname = "Fabrizio";
		String lastname = "Celli";
		String fullname = getFullName(firstname, lastname);
		String url = "http://api.freme-project.eu/0.2/e-entity/freme-ner/documents?input="+fullname+"&informat=text&outformat=json-ld&language=en&dataset=orcid";
		HttpService httpService = new HttpService();
		try {
			System.out.println();
			System.out.println(url);
			String results = httpService.getString(url, contentType);
			
			System.out.println();
			printJson(results);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getFullName(String firstname, String lastname) {
		String fullname = new String();;
		try {
			fullname = URLEncoder.encode(firstname + " " + lastname, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		return fullname;
	}
	
	public void printJson(String str) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		try {
			Object json = mapper.readValue(str, Object.class);
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
			 
		} catch (JsonGenerationException e) { 
			e.printStackTrace();
			System.out.println(str);
		} catch (JsonMappingException e) { 
			e.printStackTrace();
			System.out.println(str);
		} catch (IOException e) { 
			e.printStackTrace();
			System.out.println(str);
		} 
	}

}
