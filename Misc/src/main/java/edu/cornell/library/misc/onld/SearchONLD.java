package edu.cornell.library.misc.onld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document; 
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.ConstantScoreQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Scorer;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class SearchONLD {
    private final String INDEX_DIRECTORY = "/usr/local/src/AgriVivo/agrivivo-harvester/onld/lucene";
	public SearchONLD() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SearchONLD app = new SearchONLD();
		System.out.println("Searching");
		try {
			app.searchIndex("FAO");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	public void searchIndex(String searchString) throws IOException, ParseException {
		String searchfield = "text";
		
		System.out.println("Searching for '" + searchString + "'"); 
		Path indexPath = Paths.get(INDEX_DIRECTORY);
		IndexReader reader = DirectoryReader.open(FSDirectory.open(indexPath));
		
	    IndexSearcher searcher = new IndexSearcher(reader);
	    Analyzer analyzer = new StandardAnalyzer();		 
		QueryParser parser = new QueryParser(searchfield, analyzer);
		
		Query query = parser.parse(searchString);
		int maxHits = 100;
		TopDocs topDocs = searcher.search(query, maxHits);
		ScoreDoc[] hits = topDocs.scoreDocs;
		System.out.println(hits.length + " total matching documents"); 
		for (int i = 0; i < hits.length; i++) {		 
		  int docId = hits[i].doc;
		  Document d = searcher.doc(docId);
		  List<IndexableField> fields = d.getFields();
		  for (IndexableField field: fields) {
			 System.out.println("field: "+ field.name() +" value: "+ d.get(field.name()));  
		  } 
		}

	}

}
