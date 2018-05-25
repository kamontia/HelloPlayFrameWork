package controllers

import javax.inject.Inject
import models.User
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

class UserController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc) {
  /* Definition Forms */
  val userForm = Form(
    mapping("name" -> text, "email" -> text)(User.apply)(User.unapply)
  )

  /* Function initial view */
  def entryInit = Action {
    val filledForm = userForm.fill(User("user name", "email address"))
    Ok(views.html.user.entry(filledForm))
  }

  /* Function entry user information view */
  def entrySubmit = Action { implicit request =>
    val user = userForm.bindFromRequest.get
    println(user)
    Ok(views.html.user.entrySubmit())

  }

}
