package net.gianlorenzo.aws.clients.facades.events

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
trait S3Record extends js.Object {
  val bucket: Bucket = js.native
  @JSName("object")
  val objectValue: Key = js.native
}
