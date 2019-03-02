package net.gianlorenzo.aws.clients.facades

import scala.scalajs.js

@js.native
trait Error extends js.Object {
  val code: String = js.native
  val message: String = js.native
}

object Error {
  def apply(code: String, message: String): Error = js.Dynamic.literal("code" -> code, "message" -> message).asInstanceOf[Error]
}