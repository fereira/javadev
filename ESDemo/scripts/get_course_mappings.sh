export ESHOST=localhost
curl -X GET  "$ESHOST:9200/courses/_mapping" | jq
