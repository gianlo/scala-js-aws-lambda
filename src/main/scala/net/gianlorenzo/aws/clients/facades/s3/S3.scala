package net.gianlorenzo.aws.clients.facades.s3

import net.gianlorenzo.aws.clients.facades.Error

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("aws-sdk", "S3")
class S3() extends js.Object {
  def getObject(request: GetObjectRequest, callback: js.Function2[Error, GetObjectResponse, Unit]): Unit = js.native
}
