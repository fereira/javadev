#!/bin/bash

BUILD_DIR=/cul/src/javadev
export CLASSPATH=$BUILD_DIR/bin/:$BUILD_DIR/lib/*
# echo $CLASSPATH
java -classpath $CLASSPATH edu.cornell.library.misc.gps.Waypoints2Xml $*
