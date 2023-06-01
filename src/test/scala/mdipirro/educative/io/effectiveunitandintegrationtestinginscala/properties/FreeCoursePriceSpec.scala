package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.properties

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v3.{Author, FreeCourse, Lesson}
import org.scalacheck.Prop.propBoolean
import org.scalacheck.{Arbitrary, Gen, Prop, Properties}

import scala.language.adhocExtensions
object FreeCoursePriceSpec extends Properties("A free course"):

  private val AuthorGenerator = (for {
    fn <- Gen.alphaNumStr.suchThat(_.nonEmpty)
    ln <- Gen.alphaNumStr.suchThat(_.nonEmpty)
  } yield (fn, ln)).map(Author.apply.tupled)

  private val FreeCourseGenerator = for {
    title <- Gen.alphaNumStr
    author <- AuthorGenerator
    ls <- Gen.listOf(Gen.alphaNumStr.map(Lesson.apply)).map(_.toSet)
  } yield FreeCourse(title, author, ls, Set.empty)

  given Arbitrary[FreeCourse] = Arbitrary(FreeCourseGenerator)

  property("Is always free, no matter the number of lessons") = Prop `forAll` { (course: FreeCourse) =>
      course.price == 0
  }
