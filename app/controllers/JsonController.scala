package controllers

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

class JsonController extends Controller {
  def simpleJson = Action{
    val result = Map("status"->"success")
    val json= Json.toJson(result)
    Ok(json)
  }

}
