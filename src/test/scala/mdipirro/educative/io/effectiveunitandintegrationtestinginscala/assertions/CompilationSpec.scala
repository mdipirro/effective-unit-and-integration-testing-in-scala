package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.assertions

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite

class CompilationSpec extends TestSuite:
  "A test class" `should` "not compile if the right matchers are not imported" in {
    assertDoesNotCompile(
      """import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
        |import md`ipirro.e`ducative.io.effectiveunitandintegrationtestinginscala.model.v1.{Author, FreeCourse, Lesson, PaidCourse}
        |
        |class CourseSpec extends TestSuite:
        |  "A course with less than 20 lessons" `should` "be mini" in {
        |    val c = FreeCourse("A mini course", Author("John", "Doe"), Seq.fill(19)(Lesson("A lesson")), Seq.empty[String])
        |
        |    c shouldBe mini
        |  }
        |""".stripMargin)
  }

  it `should` "compile if the right matchers are imported" in {
    assertCompiles(
      """import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
        |import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v1.{Author, FreeCourse, Lesson, PaidCourse}
        |import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.matchers.MiniCourseMatcher
        |
        |class CourseSpec extends TestSuite with MiniCourseMatcher:
        |  "A course with less than 20 lessons" `should` "be mini" in {
        |    val c = FreeCourse("A mini course", Author("John", "Doe"), Seq.fill(19)(Lesson("A lesson")), Seq.empty[String])
        |
        |    c shouldBe mini
        |  }
        |""".stripMargin)
  }

  "A course" `should` "not type-check if an Int is used as a first name for an Author" in {
    assertTypeError(
      """val a = mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.Author(1, "Jane")"""
    )
  }
