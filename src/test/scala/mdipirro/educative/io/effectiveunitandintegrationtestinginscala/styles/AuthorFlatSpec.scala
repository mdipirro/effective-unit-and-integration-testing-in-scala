package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.styles

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v1.Author
import org.scalatest.flatspec.AnyFlatSpec

class AuthorFlatSpec extends AnyFlatSpec:
  "The full name" `should` "comprise first name and last name" in {
    assert(Author("Matteo", "Di Pirro").fullName == "Matteo Di Pirro")
  }

  behavior of "The validation"

  it `must` "reject empty first names" in {
    assertThrows[IllegalArgumentException](Author("", "Jane"))
  }

  it `must` "reject empty last names" in {
    assertThrows[IllegalArgumentException](Author("Mary", "  "))
  }
