package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.futures

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.AsyncTestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.repository.SimpleCourseRepository
import org.scalatest.Assertion

import scala.concurrent.Future

class SimpleCourseRepositoryAsyncSpec extends AsyncTestSuite:
  "A dummy CourseRepository" `should` "return no courses" in {
    val f = new Fixture {}
    import f.repo

    repo.getAll map {
      _ shouldBe empty
    }
  }

  trait Fixture:
    val repo = new SimpleCourseRepository()
