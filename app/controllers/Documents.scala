package controllers

import javax.inject.Inject

import models.Document
import play.api.libs.ws._
import play.api.mvc._

import scala.concurrent.duration._

class Documents @Inject()(ws: WSClient) extends Controller {
  implicit val context = play.api.libs.concurrent.Execution.Implicits.defaultContext

  val request: WSRequest = ws.url("http://localhost:8080/documents")

  def index = {
    val getDocumentRequest = request.withHeaders("Accept" -> "application/json")
      .withRequestTimeout(10000.millis)

    Action.async {
      getDocumentRequest.get().map {
        response => Ok(views.html.documents.list((response.json \ "documents").as[Seq[Document]]))
      }
    }
  }

  def create = TODO

}
