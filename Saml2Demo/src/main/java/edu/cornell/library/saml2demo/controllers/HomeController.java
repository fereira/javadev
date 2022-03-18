package edu.cornell.library.saml2demo.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.cornell.library.saml2demo.SamlAttributes;
 
@Controller
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@RequestMapping("/")
	public String index() {
		return "home";
	}

	@RequestMapping("/samlAttributes")
	public String hello(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
	    	     
	    // printAttributes(principal);
	    
		// model.addAttribute("name", principal.getName());
		model.addAttribute("displayName", principal.getFirstAttribute(SamlAttributes.DISPLAYNAME));
		model.addAttribute("givenName", principal.getFirstAttribute(SamlAttributes.GIVENNAME));
		model.addAttribute("lastName", principal.getFirstAttribute(SamlAttributes.LASTNAME));
		model.addAttribute("commonName", principal.getFirstAttribute(SamlAttributes.CN));
		model.addAttribute("netid", principal.getFirstAttribute(SamlAttributes.NETID));
		model.addAttribute("email", principal.getFirstAttribute(SamlAttributes.EMAIL));
		model.addAttribute("affiliation", principal.getFirstAttribute(SamlAttributes.AFFILIATION));
		model.addAttribute("primaryAffiliation", principal.getFirstAttribute(SamlAttributes.PRIMARY_AFFILIATION));
		return "hello";
	}
	
	protected void printAttributes(Saml2AuthenticatedPrincipal principal) {
	    Map<String, List<Object>> attrMap = principal.getAttributes();
        Iterator iter = attrMap.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            List<Object> objs = attrMap.get(key);
            System.out.println("attr: "+ key);
            for (Object obj: objs) {
                System.out.println("   val: "+ obj);
            }
            System.out.println();
        }     
	}

}

