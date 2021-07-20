#!/bin/bash
export FUSEKI_HOME=/usr/local/src/jena-fuseki-1.1.1-vivo
java -cp $FUSEKI_HOME/fuseki-server.jar jena.textindexer --desc=config-onld-text.ttl
