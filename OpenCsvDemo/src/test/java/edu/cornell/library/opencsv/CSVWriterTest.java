package edu.cornell.library.opencsv;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVParser;
import com.opencsv.ICSVWriter;

public class CSVWriterTest
{
   public static void main(String[] args) throws Exception
   {
      String csv = "/cul/src/javadev/data.tsv";
      FileWriter fwriter = new FileWriter(csv);
      Writer swriter = new StringWriter(); 
       
      ICSVWriter csvWriter = new CSVWriterBuilder(swriter)
    		    .withSeparator('\t')
    		    .withQuoteChar(ICSVParser.NULL_CHARACTER)
    		    .withEscapeChar(ICSVParser.DEFAULT_ESCAPE_CHARACTER)
    		    .withLineEnd(ICSVWriter.DEFAULT_LINE_END)
    		    .build(); 
      //Create record
      String [] record = "4,David,Miller,Australia,30".split(",");
      //Write the record to file
      csvWriter.writeNext(record);
        
      //close the writer
      csvWriter.close();
      System.out.println(swriter.toString());
      System.out.println("Done.");
   }
}


