AWS Lambda in scala.js
----------------------

This is an example of a `node.js` lambda written in `scala.js`.
It uses the `node.js` `aws-sdk` to interact with `S3`.


Using it
--------
This was an experiment, hence my set-up is pretty 'manual'.
- create a `node.js` lambda in the aws console
- build the javascript artifact by running `sbt ";clean;compile;fastOptJS"`
- cut and paste code from `/src/scala/s3lambda/target/scala-2.12/s3lambda-fastopt.js` into the lambda console text editor
- in aws console: hit the save button in the editor and at the lambda level
- test it with an S3 put event (you can generate one for testing in the aws lambda console)



Learning points
---------------

- The `node.js` lambda `Error` type is different from the `aws-sdk` clients `Error` type.
- Use the `callback` parameter from the lambda handler to return either a result or an error
- Can model `aws-sdk` types in facades in a minimal fashion for the needed functionality 