#!/bin/bash

BUILD_DIR=/cul/src/javadev/Misc
export CLASSPATH=`mvn -f ../pom.xml -q exec:exec -Dexec.executable=echo -Dexec.args="%classpath"`
java -classpath $CLASSPATH edu.cornell.library.misc.gps.Waypoints2Xml $*
