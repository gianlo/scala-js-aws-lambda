package net.gianlorenzo.aws.clients

import net.gianlorenzo.aws.clients.facades.s3.{GetObjectRequest, S3}

import scala.concurrent.{Future, Promise}

class ScalaS3(s3Client: S3) {
  def getContent(bucket: String, key: String): Future[String] = {
    val result = Promise[String]()
    println(s"Calling client with bucket '$bucket' and key '$key'")
    s3Client.getObject(GetObjectRequest(bucket, key), (err, resp) => {
      println(s"Client processing response err '$err' resp '$resp'")
      val didItError = Option(err)
      if (didItError.isEmpty) {
        result.success(resp.Body.toString)
      } else {
        result.failure(new Exception(s"${err.message}"))
      }
    })
    println(s"Client processing request")
    result.future
  }
}
