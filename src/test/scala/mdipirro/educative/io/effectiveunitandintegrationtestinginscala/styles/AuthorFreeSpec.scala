package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.styles

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.Author
import org.scalatest.freespec.AnyFreeSpec

class AuthorFreeSpec extends AnyFreeSpec:
  "The full name of an an author" - {
    "when initialized" - {
      "should comprise first name and last name" in {
        assert(Author("Matteo", "Di Pirro").fullName == "Matteo Di Pirro")
      }
    }
  }

  "The validation" - {
    "should reject" - {
      "empty first names" in {
        assertThrows[IllegalArgumentException](Author("", "Jane"))
      }

      "empty last names" in {
        assertThrows[IllegalArgumentException](Author("Mary", "  "))
      }
    }
  }
