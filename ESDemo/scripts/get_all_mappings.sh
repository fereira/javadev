export ESHOST=localhost
curl -X GET  "$ESHOST:9200/,/_mapping" | jq
