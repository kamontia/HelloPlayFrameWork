//import play.api._
//import play.api.Play.current
//import scala.concurrent.duration.DurationInt
//import play.api.libs.concurrent.Akka
//import play.api.libs.concurrent.Execution.Implicits._
//import akka.actor._
//class common extends GlobalSettings {
//
//  override def onStart(app: Application): Unit = {
//    Logger.info("Application has started for " + app.mode + "mode.")
//    app.mode.toString match {
//      case "Prod" => Logger.info("Prod mode.")
//      case "Dev" => Logger.info("Dev mode.")
//      case "Test" => Logger.info("Test mode.")
//      case _ => Logger.info("Unknown mode.")
//    }
//
//    Akka.system.scheduler.scheduleOnce(5 second) {
//      printtln("execute Once")
//    }
//    Akka.system.scheduler.schedule(0 seconds, 10 seconds) {
//      printtln("execute every 10 seconds")
//    }
//  }
//
//  override def onStop(app:Application): Unit ={
//    Logger.info("Application shutdown...")
//  }
//}
