#!/bin/bash

BUILD_DIR=/cul/src/javadev/Misc
export CLASSPATH=`mvn -f ../pom.xml -q exec:exec -Dexec.executable=echo -Dexec.args="%classpath"`
# echo $CLASSPATH
java -classpath $CLASSPATH edu.cornell.library.misc.gps.GPX2Text $*
