package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.properties

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.{Author, Lesson, PaidCourse}
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor4}

import scala.util.Random

class CoursePriceSpec extends TestSuite with TableDrivenPropertyChecks:
  "The price of a course" `should` "always be 50, if the course has between 11 and 29 lessons" in new Fixture:
    forAll(courseDetails) { (t, a, ls, ts) =>
      whenever(ls.size >= 11 && ls.size <= 29) {
        PaidCourse(t, a, ls, ts).price shouldBe 50
      }
    }

  trait Fixture:
    val courseDetails: TableFor4[String, Author, Set[Lesson], Set[String]] = Table(
      ("Title", "Author", "Lessons", "Tags"),
      ("A WIP Course", Author("John", "Doe"), Set.empty, Set.empty[String]),
      ("Scala for Beginners", Author("John", "Doe"), Set.fill(11)(rndLesson), Set.empty[String]),
      ("Advanced Scala", Author("John", "Doe"), Set.fill(20)(rndLesson), Set.empty[String]),
      ("Get ready to your next Scala interview", Author("Mary", "Jane"), Set.fill(30)(rndLesson), Set("Scala")),
      ("Functional Kotlin", Author("Mary", "Jane"), Set.fill(60)(rndLesson), Set("Kotlin"))
    )

    private def rndLesson = Lesson(Random.alphanumeric.take(10).mkString)
