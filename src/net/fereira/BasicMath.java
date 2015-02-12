package net.fereira;

public class BasicMath {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float A = 14446;
		float B = 50;
		float C = 10;
		float D = 90;
		float E = 50;
		float F = 29;
		float G = 1937;
		float H = 177;
		
		float X =(A + (G * (D / (B - (C / 2))) + (B + E) + (H * F)) - 611) / 1000;
		float Y =(A * (B / C) - ((D - E) * F) - (G * (C - 2)) + H - ((E * C) - 100 + H)) / 1000;
		System.out.format("XXXXX = %5.3f \n", X);
		System.out.format("YYYYY = %5.3f \n", Y);


	}

} 