package edu.cornell.library.misc.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.Test;

public class DateFormatTest {

    public DateFormatTest() {
        // TODO Auto-generated constructor stub
    }
    
     

    @Test
    public void testConvertDateFormat() {
        String input = "Tue, 08 Mar 2022 00:00:00"; 
        String inFmt = "E, dd MMM yyyy HH:mm:ss";
        String outFmt = "E, MMM dd, yyyy";
        
        String output = DateUtils.convertDateFormat(input, inFmt, outFmt);
        System.out.println("input: "+ input);
        System.out.println("output: "+ output);
        
    }

}
