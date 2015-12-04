package net.fereira;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UnicodeCopyTest {

	public UnicodeCopyTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InputStream inStream = null;
		OutputStream outStream = null;
		boolean append = false;

		try {

			File afile = new File("/usr/local/src/javadev/data/ypard/ypard-users-550.json");
			File bfile = new File("/usr/local/src/javadev/data/ypard/out-ypard-users-550.json");

			inStream = new FileInputStream(afile);
			 
            UnicodeUtil.saveUTF8File(bfile, inStream, append);
			 
			inStream.close();
			 

			System.out.println("File is copied successful!");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
