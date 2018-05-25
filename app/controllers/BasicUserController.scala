package controllers
import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import javax.inject.Inject
import models.UserData
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.mvc.{AbstractController, Action, ControllerComponents, InjectedController}

class BasicUserController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc) with play.api.i18n.I18nSupport{
  import play.api.data.Form
  import play.api.data.Forms._

  val userForm = Form(
    mapping(
      "name" -> text,
      "age" -> number
    )(UserData.apply)(UserData.unapply)
  )
  val userFormConstraints2 = Form(
    mapping(
      "name" -> nonEmptyText,
      "age" -> number(min = 0, max = 100)
    )(UserData.apply)(UserData.unapply)
  )

  val userFormConstraints = Form(
    mapping(
      "name" -> text.verifying(nonEmpty),
      "age" -> number.verifying(min(0), max(100))
    )(UserData.apply)(UserData.unapply)
  )

  def index = Action { implicit request =>
    Ok(views.html.basicuser.user(userForm))
  }

  def post()=TODO

}
