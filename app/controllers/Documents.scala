package controllers

import javax.inject.Inject
import scala.concurrent.Future
import scala.concurrent.duration._

import play.api.libs.ws._
import play.api.mvc._

class Documents @Inject() (ws: WSClient) extends Controller {
  implicit val context = play.api.libs.concurrent.Execution.Implicits.defaultContext

  val request: WSRequest = ws.url("http://localhost:8080/documents")

  def index = {
    val getDocumentRequest = request.withHeaders("Accept" -> "application/json")
      .withRequestTimeout(10000.millis)

    def wsAction = Action.async {
      getDocumentRequest.get().map {
        response => Ok(response.body)
      }
    }
    wsAction
  }

  def create = TODO

}
