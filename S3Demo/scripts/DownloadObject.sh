#!/bin/bash
BUILD_DIR=/cul/src/javadev/S3Demo
export CLASSPATH=`mvn -f ../pom.xml -q exec:exec -Dexec.executable=echo -Dexec.args="%classpath edu.cornell.library.s3demo.DownloadObject" `
# echo $CLASSPATH
java $OPTS -classpath $CLASSPATH edu.cornell.library.s3demo.DownloadObject $*
