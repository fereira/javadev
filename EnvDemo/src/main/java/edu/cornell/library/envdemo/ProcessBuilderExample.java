package edu.cornell.library.envdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;

public class ProcessBuilderExample {

    public static void main(String[] args) {

        ProcessBuilder processBuilder = new ProcessBuilder();
        Map<String, String> envMap = processBuilder.environment();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
        LocalDateTime ldt = LocalDateTime.now();
        envMap.put("LAST_INDEX", ldt.format(dtf));
         
        processBuilder.command("/bin/bash", "-c", "echo $LAST_INDEX");

        try {

            Process process = processBuilder.start();
            System.out.println("Started process");
            // blocked :(
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                // System.out.println(line);
            } 
            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
            
            String lastIndex = System.getenv("LAST_INDEX");
            System.out.println("LAST_INDEX = "+ lastIndex); 
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
