package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.matchers

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.{Author, Lesson, PaidCourse}

class CourseSpec extends TestSuite:
  "The price of the course" `should` "be 20 if the course has less than 10 lessons" in {
    val c = PaidCourse(
      title = "Effective Unit and Integration testing in Scala",
      author = Author("Matteo", "Di Pirro"),
      lessons = Seq.empty[Lesson],
      tags = Seq.empty[String]
    )

    c should have(
      Symbol("title") ("Effective Unit and Integration testing in Scala"),
      Symbol("author") (Author("Matteo", "Di Pirro")),
      Symbol("price") (20)
    )
  }
