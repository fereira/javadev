package edu.cornell.library.misc;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import edu.cornell.library.misc.util.Normalize;

public class StringTest {

	public StringTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		StringTest app = new StringTest();
		app.run();
	}

	public void run() {
		try {
			String str = FileUtils.readFileToString(new File("/usr/local/src/javadev/data/text-orig.txt"));
			System.out.println(str);
			System.out.println();

			//byte[] newbytes = UnicodeUtil2.convert(str.getBytes(), "utf-8");
			//System.out.println(new String(newbytes));
			String orig = "foo&#039;foo&#039;";
			System.out.println("orig: "+orig+ " decoded: "+ decodeString(orig));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String decodeString(String URL) {

		String urlString = "";
		try {
			urlString = URLDecoder.decode(URL, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block

		}

		return urlString;

	}

}
