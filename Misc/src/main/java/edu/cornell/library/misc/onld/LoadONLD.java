package edu.cornell.library.misc.onld;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;

public class LoadONLD {

	public LoadONLD() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LoadONLD app = new LoadONLD();
		System.out.println("Loading ONLD.rdf into tdb");
		String directory = "/usr/local/src/javadev/tdbs/ONLD";
		Dataset dataset = TDBFactory.createDataset(directory);

		Model tdb = dataset.getDefaultModel();

		// read the input file
		String source = "/usr/local/src/javadev/data/ONLD.rdf";
		FileManager.get().readModel(tdb, source );

		dataset.commit();// INCLUDE THIS STAMEMENT

		tdb.close();
		dataset.close();
		System.out.println("Done.");

	}

}
