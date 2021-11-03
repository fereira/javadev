# curl -XDELETE 'localhost:9200/testcourses'
curl -X PUT "localhost:9200/testcourses?pretty" -H 'Content-Type: application/json' -d'
{
  "mappings": {
    "properties": {
      "id": { "type": "long" },
      "semester": { "type": "keyword" },
      "courseName": { "type": "text" },
      "courseNumber": { "type": "keyword" },
      "displayCourseNumber": { "type": "keyword" },
      "instructor": { "type": "keyword" },
      "department": { "type": "keyword" },
      "pickupLocation": { "type": "keyword" },
      "registrarCourseId": { "type": "keyword" },
      "numItems": { "type": "long" }
    }
  }
}'
