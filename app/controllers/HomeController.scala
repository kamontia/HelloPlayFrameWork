package controllers
import play.api._
import play.api.mvc._
import dao.CatDAO
import javax.inject._
import models.Cat
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.text
import play.api.libs.concurrent.Execution.Implicits.defaultContext
/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
//@Singleton
class HomeController @Inject()(catDao: CatDAO) extends Controller {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index = Action.async {
    catDao.all().map {
      cats => Ok(views.html.index(cats))
    }
  }

  def insertCat = Action.async { implicit request =>
    val cat: Cat = catForm.bindFromRequest.get
    catDao.insert(cat).map(_ => Redirect(routes.HomeController.index))
  }

  val catForm = Form(
    mapping(
      "name" -> text(),
      "color" -> text()
    )(Cat.apply)(Cat.unapply)
  )

}
