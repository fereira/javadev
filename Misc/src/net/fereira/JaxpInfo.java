package net.fereira;

import java.security.CodeSource;
import java.text.MessageFormat;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactory;
import org.apache.xalan.processor.TransformerFactoryImpl; 
import javax.xml.xpath.XPathFactory;

public class JaxpInfo {

	public JaxpInfo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OutputJaxpImplementationInfo();
		 
		
	}
	
	private static void OutputJaxpImplementationInfo() {
		System.setProperty("javax.xml.transform.TransformerFactory","org.apache.xalan.processor.TransformerFactoryImpl");
	    System.out.println(getJaxpImplementationInfo("DocumentBuilderFactory", DocumentBuilderFactory.newInstance().getClass()));
	    System.out.println(getJaxpImplementationInfo("XPathFactory", XPathFactory.newInstance().getClass()));
	    System.out.println(getJaxpImplementationInfo("TransformerFactory", TransformerFactory.newInstance().getClass()));
	    System.out.println(getJaxpImplementationInfo("SAXParserFactory", SAXParserFactory.newInstance().getClass()));
	}

	private static String getJaxpImplementationInfo(String componentName, Class componentClass) {
	    CodeSource source = componentClass.getProtectionDomain().getCodeSource();
	    return MessageFormat.format(
	            "{0} implementation: {1} loaded from: {2}",
	            componentName,
	            componentClass.getName(),
	            source == null ? "Java Runtime" : source.getLocation());
	}

}
