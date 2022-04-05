#!/bin/bash
. ./awsenv.sh
STACK=lambda-webapp-demo
aws cloudformation describe-stacks --stack-name $STACK
