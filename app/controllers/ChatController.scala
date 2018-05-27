package controllers
import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import com.gargoylesoftware.htmlunit.javascript.host.html.Enumerator
import javax.inject.Inject
import play.api.mvc.{Action, Controller, WebSocket}

case class Join(nickName: String)

case class Leave(nickName: String)

case class Broadcast(nickName: String)

class ChatController @Inject() (system:ActorSystem) extends Controller {
  implicit val timeout = Timeout(1.seconds)
  val room = system.actorOf(Props[ChatRoom])

  def showRoom(nickName: String) = Action { implicit request =>
    Ok(views.html.chat(nickName))
  }

  def chatSocket(nickName: String) = WebSocket.async{ request =>
    val channelsFuture = room ? Join(nickName)
    channelsFuture.mapTo[(Iteratee[String, _], Enumerator[String])]
  }


}

class ChatRoom extends Actor {
  var users = Set[String]()
  val (enumerator, channel) = Concurrent.broadcast[String]

  def receive = {
    case Join(nickName) => {
      if (!users.contains(nickName)) {
        val iteratee = Iteratee.foreach[String] { message =>
          self ! Broadcaset("%s: %s" format(nickName, message))
        }.mapDone { _ =>
          self ! Leave(nickName)
        }
        users += nickName

        channel.push("User %s has joined the room, now %s users" format(nickName, users.size))
        sender ! (iteratee, enumerator)
      } else {
        val enumerator = Enumerator("Nickname %s is already in use." format nickName)
        val iteratee = Iteratee.ignore
        sender ! (iteratee, enumerator)
      }
    }
    case Leave(nickName) => {
      users-=nickName
      channel.push("User %s has left the room, %s users left" format(nickName,users.size))
    }

    case Broadcast(msg:String)=> channel.push(msg)
  }
}