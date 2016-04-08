package models

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._

case class Document(title: String, documentType: String, tags: Set[String], content: String)

object Document {
  implicit val documentReads: Reads[Document] = (
    (JsPath \ "title").read[String] and
      (JsPath \ "documentType").read[String] and
      (JsPath \ "tags").read[Set[String]] and
      (JsPath \ "content").read[String]
    ) (Document.apply _)
}
