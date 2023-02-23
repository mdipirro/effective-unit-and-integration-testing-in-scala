package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.styles

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.Author
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.propspec.AnyPropSpec

import scala.language.postfixOps

class AuthorPropSpec extends AnyPropSpec with TableDrivenPropertyChecks with Matchers:
  property("The full name should never be empty") {
    val authors = Table(
      "Author",
      Author("Matteo", "Di Pirro"),
      Author("Mary", "Jane")
    )

    forAll(authors) { author =>
      author.fullName should not be empty
    }
  }

  property("The validation should reject empty first names and last names") {
    val names = Table(
      "Author",
      ("", "Di Pirro"),
      ("Mary", "")
    )

    forAll(names) { case (firstName, lastName) =>
      an [IllegalArgumentException] should be thrownBy Author(firstName, lastName)
    }
  }
