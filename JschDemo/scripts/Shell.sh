#!/bin/bash
export CLASSPATH=`mvn -f ../pom.xml -q exec:exec -Dexec.executable=echo -Dexec.args="%classpath" `
java $OPTS -classpath $CLASSPATH edu.cornell.library.jcraft.Shell $*
