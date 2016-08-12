package net.fereira.summarize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper; 

public class SummarizeTest {

	public SummarizeTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		SummarizeTest app = new SummarizeTest();
		app.run();
	}
	
	public void run() {
		File file = new File("/teeal/documents/agra0001/2011/FINAL_report_-_PAP.pdf");
		PDDocument doc = null;
		String txt = new String();
		try {
			doc = PDDocument.load(file);
			PDFTextStripper stripper = new PDFTextStripper();
			 
			txt = stripper.getText(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				doc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		System.out.println(txt);
		
			
	}


}
