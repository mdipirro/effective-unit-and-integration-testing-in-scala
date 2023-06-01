package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.mocks

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v3.{Author, Educative, FreeCourse, Lesson, PaidCourse}
import org.scalatest.OptionValues

class StateVerification extends TestSuite with OptionValues:
  "Publishing a course with no lessons" `should` "leave the course-base as is" in new Fixture:
    educative
      .publishCourse("Scala for Dummies")
      .courseByName("Scala for Dummies").value.published shouldBe false

  "Publishing a course with some lessons" `should` "update the course-base" in new Fixture:
    educative
      .publishCourse("Advanced Scala")
      .courseByName("Advanced Scala").value.published shouldBe true

  trait Fixture:
    val educative: Educative = Educative(
      Seq(
        FreeCourse("Scala for Dummies", Author("Mary", "Jane"), Set.empty, Set.empty),
        PaidCourse("Advanced Scala", Author("Mary", "Jane"), Set(Lesson("Introduction")), Set.empty)
      )
    )
