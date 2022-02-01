package edu.cornell.library.misc.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException; 

public class XpathTest {
	
	private static DocumentBuilder parser;

	public XpathTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		XpathTest app = new XpathTest();
		app.run();
	}
	
	public void run() {
		try {
			String fileName = "/usr/local/src/teeal-builder/support/elsevier/main.xml";
			
			Document doc = getDocument(fileName);
			if (doc == null) {
				System.out.println("doc is null");
			}
			String path =  "/article"; 
			Node rootNode =  doc.getFirstChild();
			System.out.println("first child: "+rootNode.getNodeName());
			 
			XPath xPath =  XPathFactory.newInstance().newXPath();
			//read a string value
			String expression =  "/article/item-info/copyright"; 
			String str = xPath.compile(expression).evaluate(doc);
			System.out.println("result: "+ str);
			
			 
			
			//read a nodelist using xpath
			//expression = "/Employees/Employee";
			/*expression =  "/dataset/dataset-content/journal-item";
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			if (nodeList != null) {
				System.out.println("num nodes: "+ nodeList.getLength());
			} else {
				System.out.println("nodelist is null");
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
    /**
     * @return
     * @throws ParserConfigurationException
     */
	public static DocumentBuilder getDocumentBuilder()
            throws ParserConfigurationException {
        if (parser == null) {
            // JPT: Remove xerces use
            DocumentBuilderFactory dbf = DocumentBuilderFactory
                    .newInstance();
            //dbf.setNamespaceAware(true);
            //dbf.setValidating(false);
            dbf.setFeature("http://xml.org/sax/features/namespaces", false);
            dbf.setFeature("http://xml.org/sax/features/validation", false);
            dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            parser = dbf.newDocumentBuilder();
        }

        return parser;
    }
    
     

    /**
     * @param fileName
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public synchronized static Document getDocument(String fileName)
            throws IOException, SAXException, ParserConfigurationException {
        Document document = null;
        try {
            document = getDocumentBuilder().parse(new FileInputStream(fileName));
        } catch (SAXException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return document;
    }

}
