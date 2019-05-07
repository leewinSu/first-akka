import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.example.AkkademyDb
import com.example.AkkademyDbObject.SetRequest
import org.scalatest.{FunSpecLike, Matchers}

/**
  * @author suyao on 2019/5/8.
  * @version 1.0.0
  */
class AkkademyDbSpec extends FunSpecLike with Matchers {
  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5)

  describe("akkademyDb") {
    describe("give SetRequest") {
      it("should place key/value into map") {
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("key1", "value1")
        actorRef ! SetRequest("key2", "value2")

        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key1") should equal(Some("value1"))
        akkademyDb.map.get("key2") should equal(Some("value2"))
      }
    }
  }
}
