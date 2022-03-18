package edu.cornell.library.saml2demo.config;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;

import javax.servlet.http.HttpServletRequest;

import org.opensaml.security.x509.X509Support;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.saml2.core.Saml2X509Credential;
import org.springframework.security.saml2.provider.service.metadata.OpenSamlMetadataResolver;
import org.springframework.security.saml2.provider.service.registration.InMemoryRelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistration;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository; 
import org.springframework.stereotype.Component;

 
@Component
public class ConfigBeans  { 
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${spring.security.saml2.entity-id}") 
    private String entityId;
    // "http://www.okta.com/exk2i8uqo7lYfjwW45d7";
	 
	
	@Value("${spring.security.saml2.singlesignon.url}")
    private String ssoUrl;	
    //private String ssoUrl = "https://dev-11501804.okta.com/app/dev-11501804_saml2demo_1/exk2i8uqo7lYfjwW45d7/sso/saml";
	
	@Value("${spring.security.saml2.certificate}")
	private String samlCertificate;
	
	@Value("${spring.security.saml2.registration-id}")
    private String registrationId;
	
	private String audienceURI = "http://localhost:8080/saml2/service-provider-metadata/okta-saml";
	
    @Bean
    protected RelyingPartyRegistrationRepository relyingPartyRegistrations() throws Exception {
        File verificationKey = null;
        try {
            verificationKey = new ClassPathResource(this.samlCertificate).getFile(); 
        } catch (FileNotFoundException e) {
            System.err.println("Could not open file");
            
        }
        logger.debug("entityId: "+ entityId);
        logger.debug("ssoUrl: "+ ssoUrl);
        
        X509Certificate certificate = X509Support.decodeCertificate(verificationKey);
        Saml2X509Credential credential = Saml2X509Credential.verification(certificate);
        
        RelyingPartyRegistration registration = RelyingPartyRegistration
                .withRegistrationId(this.registrationId)
                .assertingPartyDetails(party -> party
                    .entityId(this.entityId)
                    .singleSignOnServiceLocation(this.ssoUrl)
                    .wantAuthnRequestsSigned(false)
                    .verificationX509Credentials(c -> c.add(credential))
                ).build();
        return new InMemoryRelyingPartyRegistrationRepository(registration);
    } 
}
