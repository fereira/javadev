package net.fereira.util;

import java.security.CodeSource;
import java.text.MessageFormat;

public class MiscUtils {

	public MiscUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getJaxpImplementationInfo(String componentName, Class componentClass) {
	    CodeSource source = componentClass.getProtectionDomain().getCodeSource();
	    return MessageFormat.format(
	            "{0} implementation: {1} loaded from: {2}",
	            componentName,
	            componentClass.getName(),
	            source == null ? "Java Runtime" : source.getLocation());
	}

}
