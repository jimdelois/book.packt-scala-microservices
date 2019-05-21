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


}