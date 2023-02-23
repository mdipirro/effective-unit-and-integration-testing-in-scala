package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.styles

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.Author
import org.scalatest.funsuite.AnyFunSuite

class AuthorFunSuite extends AnyFunSuite:
  test("The full name should comprise first name and last name") {
    assert(Author("Matteo", "Di Pirro").fullName == "Matteo Di Pirro")
  }

  test("The validation should reject empty first names") {
    assertThrows[IllegalArgumentException](Author("", "Jane"))
  }
