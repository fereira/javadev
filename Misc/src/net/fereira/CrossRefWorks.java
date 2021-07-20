package net.fereira;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.fereira.service.HttpService;



public class CrossRefWorks {

	public CrossRefWorks() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CrossRefWorks app = new CrossRefWorks();
		System.out.println("Running...");
		app.run();
        System.out.println("Done.");
	}
	
	public void run() {
		//String contentType = "application/vnd.crossref-api-message+json";
		String contentType = "";
		String firstname = "Valeria";
		String lastname = "Pesce";
		String fullname = getFullName(firstname, lastname);
		String url = "http://api.crossref.org/works?query="+ fullname +"&rows=20";
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
			fullname = URLEncoder.encode(lastname.toLowerCase() + " " + firstname.toLowerCase(), "UTF-8");
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
