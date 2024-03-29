#!/bin/bash
set -eo pipefail
. ./awsenv.sh

ARTIFACT_BUCKET=$(cat bucket-name.txt)
TEMPLATE=template.yml
mvn package
aws cloudformation package --template-file $TEMPLATE --s3-bucket $ARTIFACT_BUCKET --output-template-file out.yml
aws cloudformation deploy --template-file out.yml --stack-name aws-lambda-demo-app --capabilities CAPABILITY_NAMED_IAM
