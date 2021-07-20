package net.fereira;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
		Set<Map<String, String>> solSet = getNewSolSet();
		
		Map<String, String> map = new HashMap<String, String>();
		 
		for (int i=0; i < 1000; i++) {
			map.put(" "+i+" ", " "+i+" ");
		}
		for (int i=0; i <10 ; i++) {
		   solSet.add(map);
		}
		 
		System.out.println("set size: "+ solSet.size()); 
		System.out.println("set hashcode: "+ solSet.hashCode());
	}
	
	private static Set<Map<String, String>> getNewSolSet() {
		return new TreeSet<Map<String, String>>(new Comparator<Map<String, String>>() {
			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				String o1key = o1.get("sInput") + o1.get("sVivo");
				String o2key = o2.get("sInput") + o2.get("sVivo");
				return o1key.compareTo(o2key);
			}
		});
	}

	 

}
