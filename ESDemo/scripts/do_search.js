
var client = require("./connection.js");

client.search({  
  index: 'testcourse',
  type: '_doc',
  body: {
    query: {
      match: { "courseName": "course" }
    },
 }
},function (error, response,status) {
    if (error){
      console.log("search error: "+error)
    }
    else {
      console.log("--- Response ---");
      console.log(response);
      console.log("--- Hits ---");
      response.hits.hits.forEach(function(hit){
        console.log(hit);
      })
    }
});
