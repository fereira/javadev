package edu.cornell.library.misc.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils; 
/**
 * @author jaf30
 *
 */
public class Normalize { 
	
	public static String fixEncoding(String latin1) throws Exception {
		try {
			byte[] bytes = latin1.getBytes("ISO-8859-1");
			if(!validUTF8(bytes))
				return latin1;
			return new String(bytes, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			// Impossible, throw unchecked
			throw new IllegalStateException("No Latin1 or UTF-8: " + e.getMessage());
		}
		
	}
	
	public static boolean validUTF8(byte[] input) {
		int i = 0;
		// Check for BOM
		if(input.length >= 3 && (input[0] & 0xFF) == 0xEF
			&& (input[1] & 0xFF) == 0xBB & (input[2] & 0xFF) == 0xBF) {
			i = 3;
		}
		
		int end;
		for(int j = input.length; i < j; ++i) {
			int octet = input[i];
			if((octet & 0x80) == 0) {
				continue; // ASCII
			}
			
			// Check for UTF-8 leading byte
			if((octet & 0xE0) == 0xC0) {
				end = i + 1;
			} else if((octet & 0xF0) == 0xE0) {
				end = i + 2;
			} else if((octet & 0xF8) == 0xF0) {
				end = i + 3;
			} else {
				// Java only supports BMP so 3 is max
				return false;
			}
			
			while(i < end) {
				i++;
				octet = input[i];
				if((octet & 0xC0) != 0x80) {
					// Not a valid trailing byte
					return false;
				}
			}
		}
		return true;
	}
	
	public String fixUnicode(String input) {
		StringBuilder b = new StringBuilder();

	    for (char c : input.toCharArray()) {
	        if (c >= 128)
	            b.append("\\u").append(String.format("%04X", (int) c));
	        else
	            b.append(c);
	    }

	    return b.toString();
	}
	 
	
	

}
