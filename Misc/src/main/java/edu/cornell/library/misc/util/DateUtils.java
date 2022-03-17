package edu.cornell.library.misc.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.routines.DateValidator;
import org.joda.time.format.DateTimeFormat;

public class DateUtils {

    public DateUtils() {
        // TODO Auto-generated constructor stub
    }
    
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public static final Long MILLISECONDSINDAY = 86400L;
    public static final Long MILLISECONDSINYEAR = MILLISECONDSINDAY * 365;

    public static String getToday(String fmt) {
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern(fmt);
       LocalDate localDate = LocalDate.now();
       return dtf.format(localDate);
    }

    public static String getCurrentYear() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }


    /**
     * Get Current Jave Date
     *
     * returns a date in following format: Tue Apr 01 08:21:57 EST 2003
     */
    public static java.util.Date getCurrentJavaDate() {
       java.util.Date currentDate = new java.util.Date();
       return currentDate;
    }

    /**
     * get Current SQL date
     *
     * returns a date in following format: 2003-04-01
     */
    public static java.sql.Date getCurrentSQLDate() {
       java.util.Date currentDate = new java.util.Date();
       java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
       return sqlDate;
    }

    /**
     * get current SQL Timestamp
     *
     * returns a date in following format: 2003-04-01 08:21:57.556
     */
    public static java.sql.Timestamp getCurrentSQLTimestamp() {
       java.sql.Timestamp sqlTime = new java.sql.Timestamp(System.currentTimeMillis());
       return sqlTime;
    }

   

    /**
     * returns a formatted date/time as a string
     * @param a format string
     * @param a tz (timezone) string
     * @return the current date in the specified format
     */
    public static String getFormattedDate(String outFmt, String tz) {
       String s = null;
       Calendar today = Calendar.getInstance();
       SimpleDateFormat dateFormatGmt = new SimpleDateFormat(outFmt);
       dateFormatGmt.setTimeZone(TimeZone.getTimeZone(tz));
       s = dateFormatGmt.format(today.getTime());
       return s;
    }

    /**
     * given a String date in a described format (i.e. YYYY-mm-dd) convert
     * it to a String date in a new format (i.e. MM-dd-YYYY)
     * 
     * This is the SimpleDateFormat implementation...See convertDateFormat of preferred impl 
     *
     * @param s input date string
     * @param formatin a format string
     * @param formatout a format string
     * @return formated date string
     */
    public static String convertStringDate(String s, String formatIn, String formatOut) {

       try {
         if (s.equals("now")) {
           return (new SimpleDateFormat(formatOut).format(new java.util.Date()));
         } else {
           return (new SimpleDateFormat(formatOut)).format(
                  (new SimpleDateFormat(formatIn)).parse(
                    s, new ParsePosition(0)));
         }
       } catch( Exception e ) {
         System.err.println("Could not convert datestring: "+ s +" from "+ formatIn +" to "+ formatOut);
         return "";
       }

    }
    
    /**
     * given a String date in a described format (i.e. YYYY-mm-dd) convert
     * it to a String date in a new format (i.e. MM-dd-YYYY)
     * 
     * This is the preferred Impl
     *
     * @param input input date string
     * @param inFmt a format string
     * @param outFmt a format string
     * @return formated date string
     */
    public static String convertDateFormat(String input, String inFmt, String outFmt) {
        // String input = "2022-03-14 10:42:00 ";
        DateTimeFormatter oldPattern = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss");
        DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("E, MMM dd, yyyy");
        LocalDateTime datetime = LocalDateTime.parse(input.trim(), oldPattern);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(datetime, ZoneId.of("UTC"));
        return datetime.format(newPattern);
    }

    /**
     * returns a Date given a formatted string
     */
    public static java.util.Date setDate(String ds, String fmt) {
       SimpleDateFormat formatter = new SimpleDateFormat(fmt);
       ParsePosition pos = new ParsePosition(0);
       java.util.Date newDate = formatter.parse(ds, pos);
       return newDate;
    }

     
     

    /**
     * returns a SQL Time given a formatted string
     */
    public static java.sql.Time getSQLTimeFromString(String s) {
       return java.sql.Time.valueOf(s);
    }

    /*
     * return  a formatted version of a java.util.date
     */
    public static String getFormattedDate(java.util.Date date, String fmt) {
       String formattedDate = new String();
       formattedDate = new SimpleDateFormat(fmt).format(new java.util.Date(date.getTime()));
       return formattedDate;
    }

    /*
     * return  a formatted version of a java.sqlDate
     */
    public static String getFormattedSQLDate(java.sql.Date sqldate, String fmt) {
       String formattedDate = new String();
       formattedDate = new SimpleDateFormat(fmt).format(new java.util.Date(sqldate.getTime()));
       return formattedDate;
    }


    /**
     * @param currentDate
     * @return
     */
    public static java.sql.Date getNextSQLDate(java.sql.Date currentDate) {
       Calendar cal = Calendar.getInstance();
       cal.setTime(currentDate);
       cal.add(Calendar.DATE, 1);
       java.util.Date tomorrow = cal.getTime();
       return new java.sql.Date(tomorrow.getTime());
    }

    /**
    * @param start
    * @param end
    * @return elapsed time
    */
    public static String getElapsedTime(String start, String end) {
       long startlong = Long.parseLong(start, 10);
       long endlong = Long.parseLong(end, 10);
       long elapsed = endlong - startlong;
       return Long.toString(elapsed, 10);
    }

    /**
     * returns a SQL Timestamp given a formatted string
     */
    public static java.sql.Timestamp convertSQLTimestamp(String s, String formatIn, String formatOut) {
       String s2 = convertStringDate(s, formatIn, formatOut);
       //System.out.println("s2: "+s2);
       return java.sql.Timestamp.valueOf(s2+".0");
    }

    /**
     * validates a date string.  Uses lenient validation so that
     * the datestring can be fixed if the day or month is a single digit
     * @param d
     * @return
     */
    public static boolean validateDateString(String d, String fmt, boolean lenient) {
       DateValidator validator = DateValidator.getInstance();
       return validator.isValid(d, fmt);
    }

    /**
     * pads month and day fields with a leading 0
     * @param d
     * @return
     */
    public static String fixDateString(String d) {
       String[] parts = StringUtils.split(d, "-");
       if (parts[1].length()  == 1) {
          parts[1] = "0" + parts[1];
       }
       if (parts[2].length()  == 1) {
          parts[2] = "0" + parts[2];
       }
       return parts[0] + "-" + parts[1] + "-" + parts[2];

    } 
    
 
}
