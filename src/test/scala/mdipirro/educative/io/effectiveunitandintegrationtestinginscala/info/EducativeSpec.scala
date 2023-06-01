package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.info

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.{Author, Educative, FreeCourse, Lesson}
import org.scalatest.{GivenWhenThen, Ignore}

class EducativeSpec extends TestSuite with GivenWhenThen:
  private val educative = Educative(Seq(
    FreeCourse("Scala for Beginners", Author("John", "Doe"), Set.empty[Lesson], Set.empty[String]),
    FreeCourse("Scala for Data Analysis", Author("Mary", "Jane"), Set.empty[Lesson], Set("Scala")),
    FreeCourse("Functional Kotlin", Author("Mary", "Jane"), Set.empty[Lesson], Set("kotlin"))
  ))

  markup(
    """**Educative specification**
      |`Educative` models an e-learning platform where many courses of different topics are available. The main features, at the time of writing, are as follows:
      |
      |* Get a course by title
      |* Add a course
      |* Delete a course
      |* Filter the courses by tag
      |
      |""".stripMargin)

  Given("An Educative platform")

  "Educative" `should` "delete more than one course" in {
    When("Deleting all courses")
    val ed = educative without "scala for beginners" without "Scala for Data Analysis" without "functional Kotlin"

    Then("It should be empty")
    ed.courses shouldBe empty
  }

  it `should` "not delete the course if there's no course with the same title" in {
    When("Attempting to delete a course that's not in it")
    val ed = educative without "Scala for Absolute Beginners"

    Then("It should contain other courses")
    ed.courses should not be empty
    And("No course should have been deleted at all")
    ed.courses should contain theSameElementsAs educative.courses
  }
