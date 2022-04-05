#!/bin/bash
set -eo pipefail
. ./awsenv.sh
FUNCTION=$(aws lambda list-functions --region us-east-1 --query 'Functions[?starts_with(FunctionName, `lambda-webapp-demo`) == `true`].FunctionName' --output json)
echo "$FUNCTION"

