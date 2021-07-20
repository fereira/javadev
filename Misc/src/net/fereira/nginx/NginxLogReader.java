package net.fereira.nginx;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class NginxLogReader {

	public NginxLogReader() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		NginxLogReader app = new NginxLogReader();
        app.run();
	}
	
	public void run() {
		Path path = Paths.get("/cul/src/NginxLogReader/data/access.log");
		//System.out.println("Begin...");
	    try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))){
	 
	       
	      String currentLine = null;
	      // '$remote_addr - $remote_user [$time_local] "$request" '
          // '$status $body_bytes_sent "$http_referer" '
          // '"$http_user_agent" "$http_x_forwarded_for"';
	      // 
	      List<String> allList = new ArrayList();
	      while ((currentLine = reader.readLine()) != null) { //while there is content on the current line
	    	
	        //System.out.println(currentLine);
	        String lastPart = StringUtils.substringAfter(currentLine, "]");
	        System.out.println(lastPart);
	        String[] tokens = StringUtils.split(lastPart, '"');
	        //System.out.println(tokens.length +" tokens found");
	        String ips = tokens[7];
	        if (StringUtils.contains(ips, ",")) {
	    	   String[] list = StringUtils.split(ips, ",");
	    	   for (int i=0; i < list.length; i++) {
	    		   
	    		   String ip = list[i].trim();
	    		   if (! StringUtils.equals(ip, "-")) {
	    			   System.out.print(ip);
	    		   } 
	    	   }
	        }  else {	        	 
	    		   if (! StringUtils.equals(ips.trim(), "-")) {
	    			   System.out.print(ips.trim());
	    		   }  
	        }
	        
	        //Map<String, Long> counter = allList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	        //System.out.println(counter);
	        
	      }
	    } catch(IOException ex){
	      ex.printStackTrace(); //handle an exception here
	    }
	    //System.out.println("Done");
	}

}
