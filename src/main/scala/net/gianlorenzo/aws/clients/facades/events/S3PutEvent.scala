package net.gianlorenzo.aws.clients.facades.events

import scala.scalajs.js

@js.native
trait S3PutEvent extends js.Object {
  val Records: js.Array[S3Notification] = js.native
}
