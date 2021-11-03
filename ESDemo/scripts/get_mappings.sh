export ESHOST=localhost
curl -X GET  "$ESHOST:9200/items/_mapping" | jq
