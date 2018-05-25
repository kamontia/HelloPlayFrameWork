package controllers

import play.api.mvc.InjectedController

class TemplateController extends InjectedController {

  def show = Action {
    val list = List[String]("lemmon", "mikan", "nanao")
    Ok(views.html.show("Hello Scala Templates", list))
  }

}
