#!/bin/bash
set -eo pipefail
. ./awsenv.sh
STACK=lambda-webapp-demo
FUNCTION=$(aws cloudformation describe-stack-resource --stack-name $STACK --logical-resource-id function --query 'StackResourceDetail.PhysicalResourceId' --output text)
BUCKET_NAME=$(aws cloudformation describe-stack-resource --stack-name $STACK --logical-resource-id bucket --query 'StackResourceDetail.PhysicalResourceId' --output text)

if [ ! -f event.json ]; then
  cp event.json.template event.json
  sed -i'' -e "s/BUCKET_NAME/$BUCKET_NAME/" event.json

fi

echo "Invoking $FUNCTION 3 times"
for i in `seq 1 3`;
  aws lambda invoke --function-name $FUNCTION --payload file://event.json out.json
  cat out.json
  echo ""
  sleep 2
done
