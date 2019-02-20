package com.ovoenergy.payments

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport, JSName}


object Lambda {

  @JSExportTopLevel("handler")
  def handler(event: S3PutEvent, context: js.Object): Unit = {

    val s3Client = new S3()
    val s3Record = event.Records.head.s3
    val bucket = s3Record.bucket.name
    val key = s3Record.objectValue.key
    println(s"Processing '$key' from bucket '$bucket'")
    val content = getContent(bucket, key)(s3Client)
    content.foreach(txt => println(s"There are ${txt.count(_ == '\n')} lines in this txt."))
  }

  private def getContent(bucket: String, key: String)(s3Client: S3): Future[String] = {
    val result = Promise[String]()
    s3Client.getObject(GetObjectRequest(bucket, key), (err, resp) => {
      val didItError = Option(err)
      if (didItError.isEmpty){
        result.success(resp.Body.toString)
      } else {
        result.failure(new Exception(s"${err.message}"))
      }
    })
    result.future
  }
}

@js.native
@JSImport("aws-sdk", "S3")
class S3() extends js.Object{
  def getObject(request: GetObjectRequest, callback: js.Function2[Error, GetObjectResponse, Unit]): Unit = js.native
}

@js.native
trait GetObjectRequest extends js.Object
{
  val Key: String = js.native
  val Bucket: String = js.native
}

object GetObjectRequest{
  def apply(bucket: String, key: String): GetObjectRequest = js.Dynamic.literal("Bucket" -> bucket, "Key" -> key).asInstanceOf[GetObjectRequest]
}

@js.native
trait GetObjectResponse extends js.Object {
  def Body: js.Array[Byte] = js.native
}

@js.native
trait Error extends js.Object {
  val code: String = js.native
  val message: String = js.native
}

@js.native
trait S3PutEvent extends js.Object {
  val Records: js.Array[S3Notification] = js.native
}

@js.native
trait S3Notification extends js.Object{
  val s3: S3Record = js.native
}

@js.native
trait S3Record extends js.Object {
  val bucket: Bucket = js.native
  @JSName("object")
  val objectValue: Key = js.native
}

@js.native
trait Bucket extends js.Object{
  val name: String = js.native
}

@js.native
trait Key extends js.Object{
  val key: String = js.native
}
