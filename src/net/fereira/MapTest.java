package net.fereira;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import net.fereira.util.Normalize;

public class MapTest {

	public MapTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		MapTest app = new MapTest();
		app.run();
	}

	public void run() {
		
		Map<String, String> map = new HashMap<String, String>();
		 
		for (int i=0; i < 1000; i++) {
			map.put(" "+i+" ", " "+i+" ");
		}
		 
		System.out.println("size: "+ map.size()); 
	}

	 

}
