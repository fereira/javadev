package edu.cornell.library.saml2demo;

public class SamlAttributes {
    
    public final static String DISPLAYNAME = "urn:oid:2.16.840.1.113730.3.1.241";
    public final static String PRIMARY_AFFILIATION = "urn:oid:1.3.6.1.4.1.5923.1.1.1.5";
    public final static String CN = "urn:oid:2.5.4.3"; // CommonName
    public final static String GIVENNAME = "urn:oid:2.5.4.42";
    public final static String LASTNAME = "urn:oid:2.5.4.4";
    public final static String NETID = "urn:oid:0.9.2342.19200300.100.1.1";
    public final static String EMAIL = "urn:oid:0.9.2342.19200300.100.1.3";
    public final static String AFFILIATION = "urn:oid:1.3.6.1.4.1.5923.1.1.1.1";
    public final static String EDUPERSONDN = "urn:oid:1.3.6.1.4.1.5923.1.1.1.3";
    
    
    /**
     
    edupersonprimaryaffiliation                     urn:oid:1.3.6.1.4.1.5923.1.1.1.5        edupersonprimaryaffiliation
    cn(commonName)                                  urn:oid:2.5.4.3                         cn
    eduPersonPrincipalName (netid@cornell.edu)      urn:oid:1.3.6.1.4.1.5923.1.1.1.6        eduPersonPrincipalName
    givenName (first name)                          urn:oid:2.5.4.42                        givenName
    sn(last name)                                   urn:oid:2.5.4.4                         sn
    displayName                                     urn:oid:2.16.840.1.113730.3.1.241       displayName
    uid (netid)                                     urn:oid:0.9.2342.19200300.100.1.1       uid
    eduPersonOrgDN                                  urn:oid:1.3.6.1.4.1.5923.1.1.1.3        eduPersonOrgDN
    mail                                            urn:oid:0.9.2342.19200300.100.1.3       mail
    eduPersonAffiliation                            urn:oid:1.3.6.1.4.1.5923.1.1.1.1        eduPersonAffiliation
    eduPersonScopedAffiliation                      urn:oid:1.3.6.1.4.1.5923.1.1.1.9        eduPersonScopedAffiliation
    eduPersonEntitlement                            urn:oid:1.3.6.1.4.1.5923.1.1.1.7        eduPersonEntitlement
     
     */
    
    public SamlAttributes() {
        // TODO Auto-generated constructor stub
    }

}
