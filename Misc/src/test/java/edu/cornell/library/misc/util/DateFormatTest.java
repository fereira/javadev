package edu.cornell.library.misc.util; 

import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test; 

 
public class DateFormatTest {

    public DateFormatTest() {
        // TODO Auto-generated constructor stub
    }
    
    @Test
    public void testGetToday() {
        System.out.println("testGetToday"); 
        String fmt = "E, MMM dd, yyyy";
        String output = DateUtils.getToday(fmt); 
        System.out.println("output: "+ output);
        System.out.println();
    }
    
    @Test
    public void testGetCurrentYear() {
        System.out.println("testGetCurrentYear");
        String output = DateUtils.getCurrentYear(); 
        System.out.println("output: "+ output);
        System.out.println();
    }
    
    @Test
    public void testGetCurrentJavaDate() {
        System.out.println("testGetCurrentJavaDate");
        Date today = DateUtils.getCurrentJavaDate(); 
        System.out.println("output: "+ today.toString());
        System.out.println();
    }
    
    @Test
    public void testGetCurrentSQLDate() {
        System.out.println("testGetCurrentSQLDate");
        java.sql.Date today = DateUtils.getCurrentSQLDate(); 
        System.out.println("output: "+ today.toString());
        System.out.println();
    }
    
    @Test
    public void testGetCurrentSQLTimestamp() {
        System.out.println("testGetCurrentSQLTimestamp");
        java.sql.Timestamp today = DateUtils.getCurrentSQLTimestamp(); 
        System.out.println("output: "+ today.toString());
        System.out.println();
    }
    
    @Test
    public void testGetFormattedDate() {
        System.out.println("testGetFormattedDate");
        String fmt = "E, dd MMM yyyy HH:mm:ss";
        String tz = "America/New_York";
        String today = DateUtils.getFormattedDate(fmt, tz); 
        System.out.println("output: "+ today.toString());
        System.out.println();
    }
    
     

    @Disabled
    public void testConvertStringDate() {
        System.out.println("testConvertStringDate");
        String input = "Tue, 08 Mar 2022 00:00:00"; 
        String inFmt = "E, dd MMM yyyy HH:mm:ss";
        String outFmt = "E, MMM dd, yyyy";
        
        String output = DateUtils.convertStringDate(input, inFmt, outFmt);
        System.out.println("input: "+ input);
        System.out.println("output: "+ output);
        System.out.println();
    }
    
    @Test
    public void testSetDate() {
        System.out.println("testSetDate");
        String ds = "Mar 14, 2002";
        String fmt = "MMM dd, yyyy";
        Date date = DateUtils.setDate(ds, fmt); 
        System.out.println("output: "+ date);
        System.out.println();
    }
    
    @Disabled
    public void testConvertDateFormat() {
        System.out.println("testConvertDateFormat");
        String input = "Tue, 08 Mar 2022 00:00:00"; 
        String inFmt = "E, dd MMM yyyy HH:mm:ss";
        String outFmt = "E, MMM dd, yyyy";
        
        String output = DateUtils.convertDateFormat(input, inFmt, outFmt);
        System.out.println("input: "+ input);
        System.out.println("output: "+ output);
        System.out.println();
        
    }

}
