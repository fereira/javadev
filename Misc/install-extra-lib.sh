#!/bin/sh
mvn -U install:install-file -Dfile=./extralib/orcid-java-client-0.12.2.jar -DgroupId=edu.cornell.library.misc -DartifactId=orcid-java-client -Dversion=0.12.2 -Dpackaging=jar -DgeneratePom=true

mvn -U install:install-file -Dfile=./extralib/DLESETools.jar -DgroupId=edu.cornell.library.misc -DartifactId=DLESETools -Dversion=0.1.0 -Dpackaging=jar -DgeneratePom=true

# mvn -U install:install-file -Dfile=./extralib/org.restlet.jar -DgroupId=edu.cornell.library.misc -DartifactId=org.restlet -Dversion=2.3.0 -Dpackaging=jar -DgeneratePom=true
