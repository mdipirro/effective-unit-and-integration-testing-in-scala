package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.assertions

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v1.{Author, Course, Lesson, PaidCourse}

import scala.util.Random

class CourseSpec extends TestSuite:
  "The price of the course" `should` "be 20 if the course has less than 10 lessons" in {
    val c = PaidCourse(
      title = "Effective Unit and Integration testing in Scala",
      author = Author("Matteo", "Di Pirro"),
      lessons = Seq.empty[Lesson],
      tags = Seq.empty[String]
    )

    assertResult(
      expected = 20,
      clue = s"The expected price of a course with less than 10 lessons is 20, ${c.price} found"
    ) {
      c.price
    }
  }

  it `should` "never be grater than 300" in {
    val rndLessons = Random.nextInt(100) + 400

    val c = PaidCourse(
      title = "Effective Unit and Integration testing in Scala",
      author = Author("Matteo", "Di Pirro"),
      lessons = Seq.fill(rndLessons)(Lesson("A sample lesson")),
      tags = Seq.empty[String]
    )

    assert(c.price <= 300, s"The expected maximum price of a course should be 300, ${c.price} found")
  }
