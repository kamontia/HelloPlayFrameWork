package controllers

import play.api.mvc._


class SessionController extends InjectedController {

  def setSession = Action { implicit request =>
    Ok("save session.").withSession("date" -> new java.util.Date().toString())
  }

  def getSession = Action { implicit request =>
    request.session.get("date").map { a =>
      Ok("save session page access time:" + a)
    }.getOrElse {
      Ok("you have never access in save session page")
    }
  }

  def getFlash=Action{ implicit request=>
    Ok{
      request.flash.get("msg").getOrElse("no msg")
    }
  }

  def setFlash = Action{
    Redirect("/getFlash").flashing("msg" ->"saveFlash")
  }
}