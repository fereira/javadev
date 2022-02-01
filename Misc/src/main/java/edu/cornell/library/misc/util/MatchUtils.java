package edu.cornell.library.misc.util;

import java.util.ArrayList;
import java.util.List;

public class MatchUtils {

	public MatchUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public Double fuzzyMatch(String s1, String s2) {
		return dice(bigram(s1), bigram(s2));
	}
	
	public int charMatcher(String s1, String s2) throws Exception {
		int maxlen = Math.min( s1.length(),  s2.length());
		int match = 0;
		for (int i=0; i < maxlen; i++) {
			 
			if (s1.charAt(i) == s2.charAt(i)) {
				match = match + 1;
			}
		}
		return match;
	}
	
	public List<char[]> bigram(String input) {
		ArrayList<char[]> bigram = new ArrayList<char[]>();
		for (int i = 0; i < input.length() - 1; i++) {
			char[] chars = new char[2];
			chars[0] = input.charAt(i);
			chars[1] = input.charAt(i + 1);
			bigram.add(chars);
		}
		return bigram;
	}
	
	public double dice(List<char[]> bigram1, List<char[]> bigram2) {
		List<char[]> copy = new ArrayList<char[]>(bigram2);
		int matches = 0;
		for (int i = bigram1.size(); --i >= 0;) {
			char[] bigram = bigram1.get(i);
			for (int j = copy.size(); --j >= 0;) {
				char[] toMatch = copy.get(j);
				if (bigram[0] == toMatch[0] && bigram[1] == toMatch[1]) {
					copy.remove(j);
					matches += 2;
					break;
				}
			}
		}
		return (double) matches / (bigram1.size() + bigram2.size());
	} 
}
