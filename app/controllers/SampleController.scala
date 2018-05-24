package controllers

import play.api.libs.json.Json.DefaultValues
import play.api.mvc._


class SampleController extends InjectedController {

  def sample1 = Action {
        Ok(views.html.index("Sample Controller#sample1"))
//    Redirect("sample2")
  }


  def sample2 = Action {
//    Ok(views.html.index("Sample Controller#sample2"))
    Redirect(routes.SampleController.sample1)
  }

  def sample3(id: Long) = Action {
    Ok(views.html.index("id:" + id))

  }

  def sample4(path: String) = Action {
    Ok(views.html.index("path:" + path))
  }

  def sample5(fixedValued: String) = Action {
    Ok(views.html.index("fiexedValued:" + fixedValued))
  }

  def sample6(defaultValues: Int) = Action {
    Ok(views.html.index("defaultValues:" + defaultValues))
  }

  def sample7(optValue: Option[String]) = Action {
    val res = optValue match {
      case Some(opt) => opt
      case None => "nothing"
    }


    Ok(views.html.index("optValue:" + res))
  }

}
