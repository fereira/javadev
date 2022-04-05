#!/bin/bash
set -eo pipefail
. ./awsenv.sh
STACK=lambda-webapp-demo
JARFILE=LambdaWebApp-1.0.0-SNAPSHOT.jar

FUNCTION=$(aws lambda list-functions --region us-east-1 --query 'Functions[?starts_with(FunctionName, `lambda-webapp-demo`) == `true`].FunctionName' --output text)
echo "Function: $FUNCTION"
aws lambda update-function-code --function-name $FUNCTION  --zip-file fileb://target/$JARFILE

