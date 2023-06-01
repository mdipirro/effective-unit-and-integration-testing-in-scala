package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.futures

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.repository.SimpleCourseRepository
import org.scalatest.concurrent.{Eventually, ScalaFutures}
import org.scalatest.time.{Second, Seconds, Span}

import scala.concurrent.ExecutionContext

class SimpleCourseRepositoryEventualSpec extends TestSuite with Eventually with ScalaFutures:
  given ec: ExecutionContext = ExecutionContext.global

  given pc: PatienceConfig = PatienceConfig(timeout = scaled(Span(5, Seconds)), interval = scaled(Span(1, Second)))

  "A dummy CourseRepository" `should` "return no courses" in new Fixture:
    eventually {
      whenReady(repo.getAll) { _ should not be (empty) }
    }

  trait Fixture:
    val repo = new SimpleCourseRepository()
