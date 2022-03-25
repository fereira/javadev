package edu.cornell.library.lucene;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

public class CreateIndex {

    private static final String INDEX_DIR = "/cul/src/javadev/LuceneDemo/testindex";

    public CreateIndex() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Creating lucent index at: "+ INDEX_DIR);
        IndexWriter writer = createWriter();
        List<Document> documents = new ArrayList<>();

        Document document1 = createDocument(1, "John", "Fereira");
        documents.add(document1);

        Document document2 = createDocument(2, "Another ", "Person");
        documents.add(document2);

        // Let's clean everything first
        writer.deleteAll();

        writer.addDocuments(documents);
        writer.commit();
        writer.close();
        
        System.out.println("Done");
    }

    private static IndexWriter createWriter() throws IOException {
        FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter writer = new IndexWriter(dir, config);
        return writer;
    }

    private static Document createDocument(Integer id, String firstName, String lastName) {
        Document document = new Document();
        document.add(new StringField("id", id.toString(), Field.Store.YES));
        document.add(new TextField("firstName", firstName, Field.Store.YES));
        document.add(new TextField("lastName", lastName, Field.Store.YES));
        return document;
    }

}
