var client = require("./connection.js");
client.deleteByQuery({
        index: 'testcourses',
        body: {
           query: {
               match_all: { }
           }
        }
    }, function (error, response) {
        console.log(response);
    });

