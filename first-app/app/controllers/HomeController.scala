package controllers

import javax.inject.Inject
import javax.inject.Singleton
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index() = Action{
    Ok("Hello World!")
  }

  def hello(name: String) = Action{
    Ok(s"Hello, $name!")
  }

  def addUser() = Action { implicit request =>
    val body = request.body

    body.asFormUrlEncoded match{
      //persist user information
      case Some(map) => Ok(s"The user of name `${map("name").head}` and age `${map("age").head}` has been created\n")
      case None => BadRequest("Unknow body format")
    }
  }
}