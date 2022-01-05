#!/bin/bash
BUILD_DIR=/cul/src/javadev/LuceneDemo
export CLASSPATH=`mvn -f ../pom.xml -q exec:exec -Dexec.executable=echo -Dexec.args="%classpath edu.cornell.library.lucene.LuceneSearch" `
# echo $CLASSPATH
java $OPTS -classpath $CLASSPATH edu.cornell.library.lucene.LuceneSearch $*
