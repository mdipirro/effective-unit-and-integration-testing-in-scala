package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.styles

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v1.Author
import org.scalatest.featurespec.AnyFeatureSpec

class AuthorFeatureSpec extends AnyFeatureSpec:
  Feature("The full name of an author") {
    Scenario("The author requests their full name") {
      assert(Author("Matteo", "Di Pirro").fullName == "Matteo Di Pirro")
    }

    Scenario("The author must have both first name and last name") {
      assertThrows[IllegalArgumentException](Author("", "Jane"))
      assertThrows[IllegalArgumentException](Author("Mary", "  "))
    }
  }

