package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.styles

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v1.Author
import org.scalatest.refspec.RefSpec

class AuthorRefSpec extends RefSpec:
  object `The full name of an author`:
    def `should comprise first name and last name` =
      assert(Author("Matteo", "Di Pirro").fullName == "Matteo Di Pirro")

  object `The validation`:
    object `should reject`:
      def `empty first names` =
        assertThrows[IllegalArgumentException](Author("", "Jane"))

      def `empty last names` =
        assertThrows[IllegalArgumentException](Author("Mary", ""))
