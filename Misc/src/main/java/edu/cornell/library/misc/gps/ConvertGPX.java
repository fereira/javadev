package edu.cornell.library.misc.gps;

import java.io.File;

public class ConvertGPX {

	public ConvertGPX() {
		
		
	}
	
	public static void main(String[] args) {
		ConvertGPX app = new ConvertGPX();
		if (args.length != 1) {
			System.err.println("You must provide a gpx file name");
			System.exit(-1);
		}
		app.run(args[0]);
	}
	
	public  void run(String filename) {
		
		GpxTransformer transformer = new GpxTransformer();
		String gpxXml = new String();
		try {
			gpxXml = transformer.transform(new File(filename));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(gpxXml);
		
		
	}

}
