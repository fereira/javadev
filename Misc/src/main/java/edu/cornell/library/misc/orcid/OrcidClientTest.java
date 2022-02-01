package edu.cornell.library.misc.orcid;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXParser;
import org.jbibtex.BibTeXString;
import org.jbibtex.Key;
import org.jbibtex.StringValue;
import org.jbibtex.TokenMgrException;
import org.jbibtex.Value;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.cornell.library.misc.bo.BibtexCitation;
import edu.cornell.library.misc.bo.OrcidPojo;
import uk.bl.odin.orcid.client.OrcidPublicClient;
import uk.bl.odin.orcid.client.constants.OrcidExternalIdentifierType;
import uk.bl.odin.orcid.schema.messages.onepointtwo.Citation;
import uk.bl.odin.orcid.schema.messages.onepointtwo.CitationType;
import uk.bl.odin.orcid.schema.messages.onepointtwo.ExternalIdentifiers;
import uk.bl.odin.orcid.schema.messages.onepointtwo.JournalTitle;
import uk.bl.odin.orcid.schema.messages.onepointtwo.OrcidActivities;
import uk.bl.odin.orcid.schema.messages.onepointtwo.OrcidProfile;
import uk.bl.odin.orcid.schema.messages.onepointtwo.OrcidWork;
import uk.bl.odin.orcid.schema.messages.onepointtwo.OrcidWorks;
import uk.bl.odin.orcid.schema.messages.onepointtwo.PublicationDate;
import uk.bl.odin.orcid.schema.messages.onepointtwo.WorkExternalIdentifier;
import uk.bl.odin.orcid.schema.messages.onepointtwo.WorkExternalIdentifiers;
import uk.bl.odin.orcid.schema.messages.onepointtwo.WorkTitle;
import uk.bl.odin.orcid.schema.messages.onepointtwo.Year;

public class OrcidClientTest {

	public OrcidClientTest() {
		
	}

	public static void main(String[] args) {
		OrcidClientTest app = new OrcidClientTest();
		app.run();
	}
	
	protected void run() {
	   String orcid = "0000-0003-3860-4304";
	   //create a client
       OrcidPublicClient client = null;
       OrcidProfile pro = null;
       
       
	   try {
		  client = new OrcidPublicClient();
		  pro = client.getOrcidProfile(orcid);
	   } catch (JAXBException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	   } catch (IOException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	   }
       OrcidActivities activities = pro.getOrcidActivities();
       OrcidWorks orcidWorks = activities.getOrcidWorks();
        
       List<OrcidWork> workList = orcidWorks.getOrcidWork();
       List<OrcidPojo> orcidList = new ArrayList<OrcidPojo>();
       for (OrcidWork work: workList) {
    	   OrcidPojo pojo = new OrcidPojo();
    	   pojo.setDocType(work.getWorkType()); // work type
    	   WorkTitle workTitle = work.getWorkTitle();
    	   pojo.setTitle(workTitle.getTitle()); // title
    	   
    	   JournalTitle journalTitle = work.getJournalTitle();
    	   if (journalTitle != null) pojo.setJournalTitle( journalTitle.getContent());
    	   PublicationDate pubDate = work.getPublicationDate();
    	   if (pubDate != null) {
    		   Year year = pubDate.getYear();
    		   if (year != null) pojo.setPubYear(year.getValue());
    	   }
    	   
    	   WorkExternalIdentifiers externalIdentifiers = work.getWorkExternalIdentifiers();
    	   if (externalIdentifiers != null) {
    	       List<WorkExternalIdentifier> identifierList = externalIdentifiers.getWorkExternalIdentifier();
	    	   for (WorkExternalIdentifier identifier: identifierList) {
	    		    
	    		   String idType = identifier.getWorkExternalIdentifierType();
	    		   String idStr = identifier.getWorkExternalIdentifierId();
	    		   if (idType.equalsIgnoreCase("doi")) {
	    			   pojo.setDoi(idStr);   
	    		   } else if (idType.equalsIgnoreCase("issn")) {
	    			  pojo.setIssn(idStr);
	    		   } else if (idType.equalsIgnoreCase("isbn")) {
		    		  pojo.setIsbn(idStr);
		    	   }
	    	   }
    	   }
    	   Citation citation = work.getWorkCitation();
    	   if (citation != null) {
    		   CitationType citationType = citation.getWorkCitationType();
    		   if (citationType != null) pojo.setCitationType(citationType.name());
    		   if (citationType.name().equalsIgnoreCase("BIBTEX")) {
    		      BibtexCitation bibCite = parseBibtex(citation.getCitation());
    		      if (bibCite != null) {
    		    	  // printBusinessObject(bibCite);
    		    	  if (StringUtils.isNotEmpty(pojo.getTitle()) && bibCite.getTitle() != null) {
    		    		  pojo.setTitle(bibCite.getTitle());
    		    	  }
    		    	  if (pojo.getPubYear() == null && bibCite.getYear() != null) {
    		    		  pojo.setPubYear(bibCite.getYear());
    		    	  }
    		    	  if ( pojo.getVolume() == null && bibCite.getVolume() != null) {
    		    		  pojo.setVolume(bibCite.getVolume());
    		    	  }
    		    	  if ( pojo.getIssue() == null  && bibCite.getNumber() != null) {
    		    		  pojo.setIssue(bibCite.getNumber());
    		    	  }
    		    	  if ( pojo.getPages() == null && bibCite.getPages() != null) {
    		    		  pojo.setPages(bibCite.getPages());
    		    	  }
    		    	  if ( pojo.getJournalTitle() == null  && bibCite.getJournalTitle() != null) {
    		    		  pojo.setJournalTitle(bibCite.getJournalTitle());
    		    	  }
    		    	  if ( pojo.getBookTitle() == null && bibCite.getBookTitle() != null) {
    		    		  pojo.setBookTitle(bibCite.getBookTitle());
    		    	  }
    		    	  if ( pojo.getAuthorString() == null && bibCite.getAuthor() != null) {
    		    		  pojo.setAuthorString(bibCite.getAuthor());
    		    	  } 
    		      }
    		   } else {
    		      pojo.setCitationString(citation.getCitation());
    		   }
    		   
    		   
    	   }
    	   
    	   
    	   orcidList.add(pojo);
       }
       
       for (OrcidPojo pub: orcidList) {
    	  printBusinessObject(pub);
       }
       
       

       
	}
	
	protected BibtexCitation parseBibtex(String cite) {
		BibtexCitation bibCite = new BibtexCitation(); 
		try {
			StringReader reader = new StringReader(cite);
			BibTeXParser bibtexParser = new BibTeXParser();
			 
			BibTeXDatabase database = bibtexParser.parse(reader); 
			 
			Collection<BibTeXEntry> entries = (database.getEntries()).values();
			Value value = null;
			 
			for (BibTeXEntry entry : entries) {
			   value = entry.getField(BibTeXEntry.KEY_TITLE);
			   if (value != null) bibCite.setTitle(value.toUserString());
			   
			   value = entry.getField(BibTeXEntry.KEY_JOURNAL);
			   if (value != null) bibCite.setJournalTitle(value.toUserString());
			   
			   value = entry.getField(BibTeXEntry.KEY_BOOKTITLE);
			   if (value != null) bibCite.setBookTitle(value.toUserString());
			   
			   value = entry.getField(BibTeXEntry.KEY_YEAR);
			   if (value != null) bibCite.setYear(value.toUserString()); 
			   
			   value = entry.getField(BibTeXEntry.KEY_VOLUME);
			   if (value != null) bibCite.setVolume(value.toUserString());
			   
			   value = entry.getField(BibTeXEntry.KEY_NUMBER);
			   if (value != null) bibCite.setNumber(value.toUserString());
			   
			   value = entry.getField(BibTeXEntry.KEY_PAGES);
			   if (value != null) bibCite.setPages(value.toUserString());
			   
			   value = entry.getField(BibTeXEntry.KEY_AUTHOR);
			   if (value != null) bibCite.setAuthor(value.toUserString());
			   
			}
		}   catch (NullPointerException | TokenMgrException | org.jbibtex.ParseException ex) {
			ex.printStackTrace();
			System.err.println(cite);	
		}
		//printBusinessObject(bibCite);
		return bibCite;
		
	}  
	
	/**
	 * @param o
	 */
	public static void printBusinessObject(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			try {
				field.setAccessible(true);
				System.out.println(field.getName() + ": " + field.get(o));
			} catch (IllegalAccessException e) {
				System.err.println("Illegal access exception");
			} catch (NullPointerException e) {
				System.err.println("Nullpointer Exception");
			}
		}
		System.out.println();
	}

}
