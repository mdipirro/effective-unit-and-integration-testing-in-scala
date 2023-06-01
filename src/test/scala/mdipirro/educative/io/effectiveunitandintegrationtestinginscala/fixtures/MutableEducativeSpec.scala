package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.fixtures

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.{Author, FreeCourse, Lesson}
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.mutable.MutableEducative
import org.scalatest.OneInstancePerTest

class MutableEducativeSpec extends TestSuite:
  it `should` "delete more than one course" in new Fixture:
    val ed = educative without "scala for beginners" without "Scala for Data Analysis" without "functional Kotlin"

    ed.courses shouldBe empty

  it `should` "not delete the course if there's no course with the same title" in new Fixture:
    val ed = educative without "Scala for Absolute Beginners"

    ed.courses should not be empty
    ed.courses should contain theSameElementsAs educative.courses

  trait Fixture:
    val educative: MutableEducative = MutableEducative(Seq(
      FreeCourse("Scala for Beginners", Author("John", "Doe"), Set.empty[Lesson], Set.empty[String]),
      FreeCourse("Scala for Data Analysis", Author("Mary", "Jane"), Set.empty[Lesson], Set("Scala")),
      FreeCourse("Functional Kotlin", Author("Mary", "Jane"), Set.empty[Lesson], Set("kotlin"))
    ))

  override def withFixture(t: NoArgTest) =
    super.withFixture(t)
