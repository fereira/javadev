#!/bin/bash
BUILD_DIR=/cul/src/javadev/EnvDemo
export CLASSPATH=`mvn -f ../pom.xml -q exec:exec -Dexec.executable=echo -Dexec.args="%classpath" `
java $OPTS -classpath $CLASSPATH edu.cornell.library.envdemo.ProcessBuilderExample $*
