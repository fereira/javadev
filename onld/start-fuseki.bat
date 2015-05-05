set FUSEKI_HOME=/usr/local/src/jena-fuseki-1.1.1-vivo
REM java -Xmx1024M -jar %FUSEKI_HOME%/fuseki-server.jar --mem /ds
java -Xmx1024M -jar %FUSEKI_HOME%/fuseki-server.jar --conf=config-onld-text.ttl

