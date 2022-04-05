#!/bin/sh
. ./awsenv.sh
FILE=test.sh
ARTIFACT_BUCKET=$(cat bucket-name.txt)
set -eo pipefail
echo "uploading $FILE to s3://$ARTIFACT_BUCKET"
aws s3 cp $FILE  s3://$ARTIFACT_BUCKET
