package edu.cornell.library.marcutils.util;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;

public class MarcUtilsMatchYearTest extends MarcUtilsBaseTest { 
	
	boolean debug = true;
	String dateStringArray[] = {"2020", "[2019]", "2018.10", "2020.", "©1937", "dicembre 2020.", null};
	List<String> pubDates = Arrays.asList(dateStringArray);

	@Test
	public void testGetRush() {
		try {
		    for (String pubDate: pubDates) {
    			 System.out.println(pubDate +": " + marcUtils.matchYear(pubDate));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}		 
	} 

}
