package edu.cornell.library.misc.marmotta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.marmotta.client.ClientConfiguration;
import org.apache.marmotta.client.clients.ImportClient; 
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.Rio;

import edu.cornell.library.misc.solr.SolrSearchTest;

public class MarmottaImport {
	
	private String marmottaHost = "http://jaf30-dev.library.cornell.edu";
	private String context = "http://example.org/context";
	public MarmottaImport() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		MarmottaImport app = new MarmottaImport();
		app.run();

	}
	
	public void run() {
		String path = "/usr/local/src/ONLD/ONLD.rdf";
		
		ClientConfiguration configuration = new ClientConfiguration(marmottaHost);
		configuration.setMarmottaContext(context);
		ImportClient importClient = new ImportClient(configuration);
		InputStream is;
		try {
			is = new FileInputStream(new File(path));		
		    Optional<RDFFormat> format = Rio.getParserFormatForFileName(path);
		    //importClient.uploadDataset(is, format.getDefaultMIMEType());
		    importClient.uploadDataset(is, format.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
