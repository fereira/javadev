package net.fereira.agrovoc;

 

import java.io.IOException;

import net.fereira.util.IterableAdaptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.semanticweb.skos.SKOSCreationException;
import org.semanticweb.skos.SKOSDataset;
import org.semanticweb.skos.SKOSInputSource;
import org.semanticweb.skosapibinding.SKOSManager;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory; 
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.tdb.TDBFactory; 


public class AgrovocTool {
   
   /** Logger for this class and subclasses */
   protected final Log logger = LogFactory.getLog(getClass()); 
   private final String TDBPREFIX = "/usr/local/src/AgriVivo/harvesters/";
   private String harvester = "egfar_orgs";
   private final String TDBDIR = TDBPREFIX + "tdb/agrovoc";   
   
   private Model jenaModel;
   private Dataset dataset;
   
   /**
    * default constructor
    */
   public AgrovocTool() { 
       
   } 
   /**
    * @return the jenaModel
    */
   public Model getJenaModel() {
      return jenaModel;
   } 

   /**
    * @param jenaModel the jenaModel to set
    */
   public void setJenaModel(Model jenaModel) {
      this.jenaModel = jenaModel;
   }




   /**
    * @return the dataset
    */
   public Dataset getDataset() {
      return dataset;
   }




   /**
    * @param dataset the dataset to set
    */
   public void setDataset(Dataset dataset) {
      this.dataset = dataset;
   }




   /**
    * @param args
    */
   public static void main(String[] args) {
     AgrovocTool app = new AgrovocTool();    
     app.run();
   }
   

   /**
    * 
    */
   public void run() {
	  SKOSDataset dataset;
	  try {
		SKOSManager manager = new SKOSManager();
		SKOSInputSource inputSource = null;
		dataset = manager.loadDataset(inputSource);
	  } catch (SKOSCreationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	  }
      //System.out.println("Getting model");
      setDataset(TDBFactory.createDataset(TDBDIR));
      //setJenaModel(dataset.getDefaultModel());
      
      System.out.println("Model size: "+ jenaModel.size());      
     
      String nsPrefix = "http://vivo.example.org/harvest/"+ this.harvester +"/webpage/";
      
      String prefixes = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
    				  "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> ";
      
      String query = prefixes + "SELECT ?sub ?pred ?obj \n" 
          + "WHERE {\n"
          + " ?sub ?pred ?obj .\n"
          + " FILTER(regex(str(?sub), '^"+ nsPrefix +"') ) \n"
          + "}  ";
      System.out.println(query);
      ResultSet resultSet = null;
      try {
         resultSet = executeSelectQuery(query, true);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
       
      for (QuerySolution solution : IterableAdaptor.adapt(resultSet)) {
    	 System.out.println(solution.toString());
         //String item = solution.getResource("item").getURI(); 
         //String itemLabel = solution.getLiteral("itemLabel").toString();         
         //System.out.println(item + " : " + itemLabel);
         
      }
      
       
      jenaModel.close();
      
      
   }
   
   public ResultSet executeSelectQuery(String queryString, boolean datasetMode) throws IOException {
      QueryExecution qexec = buildQueryExec(queryString, datasetMode);
      ResultSet rs = qexec.execSelect();       
      return rs;
   }
   
   private QueryExecution buildQueryExec(String queryString, boolean datasetMode) throws IOException {
      QueryExecution qe;
      if(datasetMode) {
         qe = QueryExecutionFactory.create(QueryFactory.create(queryString, Syntax.syntaxARQ), getDataset());
      } else {
         qe = QueryExecutionFactory.create(QueryFactory.create(queryString, Syntax.syntaxARQ), getJenaModel());
      }
      return qe;
   }
}
