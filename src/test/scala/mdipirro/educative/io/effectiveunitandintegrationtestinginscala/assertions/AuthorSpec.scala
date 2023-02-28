package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.assertions

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.Author

class AuthorSpec extends TestSuite:
  "The validation of an author" `should` "throw an exception if no first or last name is provided" in {
    val exc = intercept[IllegalArgumentException]{ Author("", "") }

    assert(exc.getMessage.nn.contains("The first name must not be blank"))
  }
