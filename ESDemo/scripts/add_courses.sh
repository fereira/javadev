curl -X POST "localhost:9200/testcourses/_doc/?pretty" -H 'Content-Type: application/json' -d'
{
  "courseName": "Test Course 1",
  "courseNumber": "COURSE 001"
}
'

curl -X POST "localhost:9200/testcourses/_doc/?pretty" -H 'Content-Type: application/json' -d'
{
  "courseName": "Test Course 2",
  "courseNumber": "COURSE 002"
}
'

curl -X POST "localhost:9200/testcourses/_doc/?pretty" -H 'Content-Type: application/json' -d'
{
  "courseName": "Test Course 3",
  "courseNumber": "COURSE 003"
}
'

