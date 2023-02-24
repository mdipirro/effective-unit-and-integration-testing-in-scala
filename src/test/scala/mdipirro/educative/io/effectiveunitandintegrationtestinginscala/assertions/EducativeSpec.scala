package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.assertions

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.{Author, Educative, FreeCourse, Lesson, PaidCourse}

class EducativeSpec extends TestSuite:
  "A course fetched by name" `should` "exists and its price should be as expected" in {
    val educative = Educative(Seq(
      FreeCourse("Scala Basics", Author("Matteo", "Di Pirro"), Seq.empty[Lesson], Seq.empty[String]),
      PaidCourse("Advanced Scala", Author("Mary", "Jane"), Seq.fill(20)(Lesson("A lesson")), Seq.empty[String])
    ))

    val courseOpt = educative.courseByName("Advanced Scala")
    assert(
      courseOpt.isDefined &&
        courseOpt.get.isInstanceOf[PaidCourse] &&
        courseOpt.get.asInstanceOf[PaidCourse].price == 30
    )
  }

