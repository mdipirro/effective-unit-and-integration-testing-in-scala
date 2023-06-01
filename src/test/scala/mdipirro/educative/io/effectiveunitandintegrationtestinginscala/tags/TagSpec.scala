package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.tags

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.{AssertionsTest, MatchersTest, TaggedTest, TestSuite}
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.Author

@TaggedTest
class TagSpec extends TestSuite:
  "The validation of an author" `should` "throw an exception if no first or last name is provided" `taggedAs` AssertionsTest in {
    val exc = intercept[IllegalArgumentException] {
      Author("", "")
    }

    assert(exc.getMessage.nn.contains("The first name must not be blank"))
  }

  it `should` "not throw any exceptions if both first and last names are given" `taggedAs` MatchersTest in {
    noException should be thrownBy Author("Matteo", "Di Pirro")
  }
