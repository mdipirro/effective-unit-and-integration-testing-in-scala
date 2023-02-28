package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.styles

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v1.Author
import org.scalatest.wordspec.AnyWordSpec

class AuthorWordSpec extends AnyWordSpec:
  "The full name of an an author" when {
    "initialized" should {
      "comprise first name and last name" in {
        assert(Author("Matteo", "Di Pirro").fullName == "Matteo Di Pirro")
      }
    }
  }

  private val reject = afterWord("reject")

  "The validation" when {
    "performed" `must` reject {
      "empty first names" in {
        assertThrows[IllegalArgumentException](Author("", "Jane"))
      }

      "empty last names" in {
        assertThrows[IllegalArgumentException](Author("Mary", "  "))
      }
    }
  }
