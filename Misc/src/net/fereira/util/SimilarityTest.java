package net.fereira.util;

import java.util.Locale;

import org.apache.commons.text.similarity.FuzzyScore;

public class SimilarityTest {

	public SimilarityTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimilarityTest app = new SimilarityTest();
        app.run();
	}
	
	public void run() {
	   CharSequence cs1 = "INFORMATION";
	   CharSequence cs2 = "information";
	   FuzzyScore fs = new FuzzyScore(Locale.ENGLISH);
	   int result = fs.fuzzyScore(cs1, cs1) ;
	   System.out.println("cs1 length: "+ cs1.length());
	   System.out.println("score: "+result);
	}

}
