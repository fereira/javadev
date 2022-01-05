package edu.cornell.library.lucene;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVParser;
import com.opencsv.ICSVWriter;

public class LuceneSearch {
	private static final String INDEX_DIR = "/cul/data/skosmos/lucene/nalt"; 

	public static void main(String[] args) throws Exception {
		File indexDir = new File(INDEX_DIR);
    	FSDirectory index = FSDirectory.open(indexDir.toPath());
    	DirectoryReader reader = DirectoryReader.open(index);
    	
        IndexSearcher searcher = new IndexSearcher(reader); 

		// Search by lang to get all terms
        int numDocs = searcher.count(new MatchAllDocsQuery( ));

		System.out.println("Total Docs :: " + numDocs);
				
		TopDocs topDocs = searchByLabel("Aba*", searcher, numDocs);
		System.out.println("Total Hits: "+ topDocs.totalHits);
		// create a writer for csv output
		
		 
	       
	    System.out.println("Done.");
	    
	}
	private static TopDocs allDocs(IndexSearcher searcher, int maxHits) throws Exception {
		QueryParser qp = new QueryParser("pref", new StandardAnalyzer());
		Query query = new MatchAllDocsQuery( );
		TopDocs hits = searcher.search(query, maxHits);
		return hits;
	} 
	
	private static TopDocs searchByLang(String lang, IndexSearcher searcher, int maxHits) throws Exception {
		QueryParser qp = new QueryParser("lang", new StandardAnalyzer());
		Query labelQuery = qp.parse(lang);
		TopDocs hits = searcher.search(labelQuery, maxHits);
		return hits;
	}
	
	private static TopDocs searchByLabel(String label, IndexSearcher searcher, int maxHits) throws Exception {
		QueryParser qp = new QueryParser("pref", new StandardAnalyzer());
		Query labelQuery = qp.parse(label);
		TopDocs hits = searcher.search(labelQuery, maxHits);
		return hits;
	} 
}
