package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.matchers

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v1.{Author, FreeCourse, Lesson, PaidCourse}

class CourseSpec extends TestSuite with PaidCoursePropertyMatchers with MiniCourseMatcher:
  "The price of the course" `should` "be 20 if the course has less than 10 lessons" in {
    val c = PaidCourse(
      title = "Effective Unit and Integration testing in Scala",
      author = Author("Matteo", "Di Pirro"),
      lessons = Seq.empty[Lesson],
      tags = Seq.empty[String]
    )

    c should have(
      title ("Effective Unit and Integration testing in Scala"),
      author (Author("Matteo", "Di Pirro")),
      price (20)
    )
  }

  "A course with less than 20 lessons" `should` "be mini" in {
    val c = FreeCourse("A mini course", Author("John", "Doe"), Seq.fill(19)(Lesson("A lesson")), Seq.empty[String])

    c shouldBe mini
  }
  
  
