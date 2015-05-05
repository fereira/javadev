set FUSEKI_HOME=/usr/local/src/jena-fuseki-1.1.1-vivo
java -cp %FUSEKI_HOME%/fuseki-server.jar tdb.tdbloader --tdb=config-onld-text.ttl ONLD.rdf
