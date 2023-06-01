package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.properties

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v3.{Author, FreeCourse, Lesson, PaidCourse}
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.properties.FreeCoursePriceSpec.property
import org.scalacheck.{Arbitrary, Gen, Prop}
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class CoursePriceSuiteSpec extends TestSuite with ScalaCheckPropertyChecks:
  private val LessonGenerator = for {
    l <- Gen.alphaNumStr.map(Lesson.apply)
  } yield l

  private val AuthorGenerator = (for {
    fn <- Gen.alphaNumStr.suchThat(_.nonEmpty)
    ln <- Gen.alphaNumStr.suchThat(_.nonEmpty)
  } yield (fn, ln)).map(Author.apply.tupled)

  private val FreeCourseGenerator = for {
    title <- Gen.alphaNumStr
    author <- AuthorGenerator
    ls <- Gen.listOf(LessonGenerator).map(_.toSet)
  } yield FreeCourse(title, author, ls, Set.empty)

  private val CourseGenerator = for {
    fc <- FreeCourseGenerator
    numberOfLessons <- Gen.chooseNum(0, 40)
    ls <- Gen.listOfN(numberOfLessons, LessonGenerator).map(_.toSet).suchThat(ls => ls.size >= 11 && ls.size <= 29)
  } yield PaidCourse(fc.title, fc.author, ls, fc.tags)

  "A free course" `should` "always be free, no matter the number of lessons" in {
    forAll(FreeCourseGenerator -> "Free course") { fc =>
      fc.price shouldBe 0
    }
  }

  "The price of a course" `should` "always be 50, if the course has between 11 and 29 lessons" in {
    forAll(CourseGenerator -> "The course") { pc =>
      pc.price shouldBe 50
    }
  }

