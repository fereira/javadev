#!/bin/bash
set -eo pipefail
. ./awsenv.sh
STACK=lambda-webapp-demo
ARTIFACT_BUCKET=$(cat bucket-name.txt)
TEMPLATE=sam.yaml
mvn package

aws cloudformation package --template-file $TEMPLATE --s3-bucket $ARTIFACT_BUCKET --output-template-file target/output-sam.yaml
aws cloudformation deploy --template-file target/output-sam.yaml --stack-name $STACK --capabilities CAPABILITY_IAM
