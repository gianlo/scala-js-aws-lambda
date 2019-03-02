package net.gianlorenzo.aws.model

import scala.scalajs.js

@js.native
trait TextFileInfo extends js.Object {
  val fileName: String = js.native
  val numberOfLines: Int = js.native
}

object TextFileInfo {
  def apply(fileName: String, numberOfLines: Int): TextFileInfo = js.Dynamic.literal("fileName" -> fileName, "numberOfLines" -> numberOfLines).asInstanceOf[TextFileInfo]
}