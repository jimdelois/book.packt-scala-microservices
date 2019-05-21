package controllers

import javax.inject.Inject
import javax.inject.Singleton
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index() = Action{
    Ok("Hello World!")
  }

  // As per Scala, Action {...} is syntactic sugar for "Action.apply{...}"
  def hello(name: String) = Action {
    Ok(s"Hello, $name!")
      .withHeaders("Server" -> "Play")
      .withCookies(Cookie("CookieName", scala.util.Random.nextInt().toString))
  }


  def addUser() = Action { implicit request =>
    val body = request.body

    // Could be:
    // application/x-www-form-urlencoded => request.body.asFormUrlEncoded
    // text/plain => request.body.asText
    // application/xml => request.body.asXML
    // application/json => request.body.asJson

    body.asFormUrlEncoded match{
      //persist user information
      case Some(map) => Ok(s"The user of name `${map("name").head}` and age `${map("age").head}` has been created\n")
      case None => BadRequest("Unknow body format")
    }
  }

  // Some examples of other responses...
  def sqrt(num:String) = Action {
    scala.util.Try(num.toInt) match {
      case scala.util.Success(ans) if ans >= 0 => {
        val html: play.twirl.api.Html = views.html.sqrt(math.sqrt(ans))
        Ok(html)
      }
      case scala.util.Success(ans) => BadRequest(s"The input ($num) must be greater than zero")
      case scala.util.Failure(ex) => InternalServerError(s"Could not extract the contents from $num")
    }
  }
}