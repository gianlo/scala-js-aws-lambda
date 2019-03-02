package net.gianlorenzo.aws.clients.facades.s3

import scala.scalajs.js

@js.native
trait GetObjectRequest extends js.Object {
  val Key: String = js.native
  val Bucket: String = js.native
}

object GetObjectRequest {
  def apply(bucket: String, key: String): GetObjectRequest = js.Dynamic.literal("Bucket" -> bucket, "Key" -> key).asInstanceOf[GetObjectRequest]
}