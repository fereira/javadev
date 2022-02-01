package edu.cornell.library.misc;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTest {

	public DateFormatTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String strStartDate = "2014-10-02T08:00:00Z";
		String strEndDate = "2014-10-03";
		Date from = null;
		Date until = null;
		 
		
		if (strStartDate.endsWith("Z")) {
		  from = setDate(strStartDate, "yyyy-MM-dd'T'HH:mm:ss'Z'");
		} else {
		   from = setDate(strStartDate, "yyyy-MM-dd");
	    }
		if (strEndDate.endsWith("Z")) {
			until = setDate(strEndDate, "yyyy-MM-dd'T'HH:mm:ss'Z'");
		} else {
			until = setDate(strEndDate, "yyyy-MM-dd");	
		}// TODO Auto-generated method stub
		System.out.println(from.toString());
		System.out.println(until.toString());
	}
	
	public static java.util.Date setDate(String s, String fmt) {
	      SimpleDateFormat formatter = new SimpleDateFormat(fmt);
	      
	      java.util.Date newDate = null;
		  try {
			  newDate = formatter.parse(s);
			  System.out.println("formatted: "+ formatter.format(newDate));
		  } catch(ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
	      
	      return newDate;
	}

}
