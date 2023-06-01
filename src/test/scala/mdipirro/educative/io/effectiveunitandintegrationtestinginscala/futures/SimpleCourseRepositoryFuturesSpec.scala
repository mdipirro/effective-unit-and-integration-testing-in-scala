package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.futures

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.repository.SimpleCourseRepository
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.{Await, ExecutionContext}
import scala.language.postfixOps
import concurrent.duration.DurationInt

class SimpleCourseRepositoryFuturesSpec extends TestSuite with ScalaFutures:

  given ec: ExecutionContext = ExecutionContext.global

  "A dummy CourseRepository" `should` "return no courses" in new Fixture:
    whenReady(repo.getAll) {
      _ shouldBe empty
    }


  trait Fixture:
    val repo = new SimpleCourseRepository()
