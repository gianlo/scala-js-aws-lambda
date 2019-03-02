package net.gianlorenzo.aws

import net.gianlorenzo.aws.clients.ScalaS3
import net.gianlorenzo.aws.clients.facades.events.S3PutEvent
import net.gianlorenzo.aws.clients.facades.s3.S3
import net.gianlorenzo.aws.model.TextFileInfo

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.util.{Failure, Success}


object Lambda {

  @JSExportTopLevel("handler")
  def handler(event: S3PutEvent, context: js.Object, callback: js.Function2[String, Object, Unit]): Unit = {

    val s3Client = new ScalaS3(new S3())
    val s3Record = event.Records.head.s3
    val bucket = s3Record.bucket.name
    val key = s3Record.objectValue.key
    println(s"Processing '$key' from bucket '$bucket'")
    val content = s3Client.getContent(bucket, key)
    content.andThen {
      case Success(txt) => {
        println(s"There are ${txt.count(_ == '\n')} lines in this txt.")
        callback(null, TextFileInfo(key, txt.count(_ == '\n')))
      }
      case Failure(err) => {
        println(s"Future failed with '${err.getMessage}'")
        callback(err.getMessage, null)
      }

    }
  }
}
