
var client = require("./connection.js");
client.deleteByQuery({
        index: 'courses',
        body: {
           query: {
               match: { semester: 'Spring 2020' }
           }
        }
    }, function (error, response) {
        console.log(response);
    });

