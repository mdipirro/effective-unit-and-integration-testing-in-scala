package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.assertions

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.{Author, Educative, FreeCourse, Lesson, PaidCourse}

class EducativeSpec extends TestSuite:
  "A course fetched by name" `should` "exists and its price should be as expected" in {
    val educative = Educative(Seq(
      FreeCourse("Scala Basics", Author("Matteo", "Di Pirro"), Seq.empty[Lesson], Seq.empty[String]),
      PaidCourse("Advanced Scala", Author("Mary", "Jane"), Seq.fill(20)(Lesson("A lesson")), Seq.empty[String])
    ))

    educative.courseByName("Advanced Scala") match
      case Some(course) => course match
        case _: FreeCourse => fail("The course is a free one")
        case p: PaidCourse if p.price != 30 => fail(s"The price of the course is ${p.price}, not 30")
        case _ => succeed
      case None => fail("No course with the expected name found")
  }

