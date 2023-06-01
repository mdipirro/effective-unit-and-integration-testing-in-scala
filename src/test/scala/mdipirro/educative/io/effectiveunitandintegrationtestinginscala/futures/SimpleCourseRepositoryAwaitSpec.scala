package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.futures

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.repository.SimpleCourseRepository

import scala.concurrent.{Await, ExecutionContext}
import scala.language.postfixOps
import concurrent.duration.DurationInt

class SimpleCourseRepositoryAwaitSpec extends TestSuite:

  given ec: ExecutionContext = ExecutionContext.global

  "A dummy CourseRepository" `should` "return no courses" in new Fixture:
    val courses = Await.result(repo.getAll, 2 seconds)

    courses shouldBe empty

  trait Fixture:
    val repo = new SimpleCourseRepository()
