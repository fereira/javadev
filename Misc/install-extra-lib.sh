# DLESETools.jar
# orcid-java-client-0.12.2.jar
# org.restlet.jar
# virt_jena2.jar
# virtjdbc4.jar
mvn -U install:install-file -Dfile=./extralib/orcid-java-client-0.12.2.jar -DgroupId=edu.cornell.library.misc -DartifactId=orcid-java-client -Dversion=0.12.2 -Dpackaging=jar -DgeneratePom=true

mvn -U install:install-file -Dfile=./extralib/DLESETools.jar -DgroupId=edu.cornell.library.misc -DartifactId=DLESETools -Dversion=0.1.0 -Dpackaging=jar -DgeneratePom=true
