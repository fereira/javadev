var client = require("./connection.js");

client.count({index: 'testcourses'})
  .then(function(resp) {
    console.log("Successful query!");
    console.log(JSON.stringify(resp, null, 4));
  }, function(err) {
    console.trace(err.message);
  });
