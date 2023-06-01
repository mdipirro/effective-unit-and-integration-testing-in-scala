package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.matchers

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.Author

class AuthorSpec extends TestSuite:
  "The validation of an author" `should` "throw an exception if no first or last name is provided" in {
    val exc = the [IllegalArgumentException] thrownBy Author("", "")

    exc.getMessage.nn should include ("The first name must not be blank")
  }

  it `should` "not throw any exceptions if both first and last names are given" in {
    noException should be thrownBy Author("Matteo", "Di Pirro")
  }
