package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.styles

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v1.Author
import org.scalatest.funspec.AnyFunSpec

class AuthorFunSpec extends AnyFunSpec:
  describe("The full name of an an author") {
    describe("when initialized") {
      it("should comprise first name and last name") {
        assert(Author("Matteo", "Di Pirro").fullName == "Matteo Di Pirro")
      }
    }
  }

  describe("The validation") {
    it("should reject empty first names") {
      assertThrows[IllegalArgumentException](Author("", "Jane"))
    }

    it("should reject empty last names") {
      assertThrows[IllegalArgumentException](Author("Mary", "  "))
    }
  }
