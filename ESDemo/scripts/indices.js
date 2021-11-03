
var client = require("./connection.js");

const indices = function indices() {
  return client.cat.indices({v: true})
  .then(console.log)
  .catch(err => console.error(`Error connecting to the es client: ${err}`));
};

indices();
