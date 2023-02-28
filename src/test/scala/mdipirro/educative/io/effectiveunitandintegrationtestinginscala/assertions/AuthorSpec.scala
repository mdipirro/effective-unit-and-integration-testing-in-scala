package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.assertions

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.Author

import scala.util.{Failure, Success, Try}

class AuthorSpec extends TestSuite:
  "The validation of an author" `should` "throw an exception if no first or last name is provided" in {
    Try(Author("", "")) match
      case Failure(_) => succeed
      case Success(_) => fail("No exception was thrown")
  }
