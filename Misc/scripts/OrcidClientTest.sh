#!/bin/bash
CLASSES=../build
LIB=../lib
OPTS=
CLASSPATH=$(JARS=("$LIB"/*.jar); IFS=:; echo "${JARS[*]}")
CLASSPATH=$CLASSES:$CLASSPATH
java $OPTS -classpath $CLASSPATH net.fereira.orcid.OrcidClientTest
