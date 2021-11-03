curl -XDELETE 'localhost:9200/testitems'
curl -X PUT "localhost:9200/testitems?pretty" -H 'Content-Type: application/json' -d'
{
    "mappings": {
      "properties": {
        "articleTitle": { "type": "text"},
        "author": { "type": "text"},
        "barcode": { "type": "keyword"},
        "callnumber": { "type": "text"},
        "courseId": { "type": "long"},
        "documentType": { "type": "text"},
        "dueDate": { "type": "text"}, 
        "edition": { "type": "text"},
        "editor": { "type": "text"},
        "id": { "type": "long"},
        "issue": { "type": "text"},
        "isxn": { "type": "text"},
        "itemFormat": { "type": "text"},
        "itemType": { "type": "text"},
        "journalMonth": { "type": "text"},
        "journalYear": { "type": "text"},
        "link": { "type": "text"},
        "location": { "type": "text"},
        "pages": { "type": "text"},
        "pubDate": { "type": "text"},
        "pubPlace": { "type": "text"},
        "publisher": { "type": "text"},
        "status": { "type": "text"},
        "title": { "type": "text"},
        "volume": { "type": "text"}
      }
    }
}'
