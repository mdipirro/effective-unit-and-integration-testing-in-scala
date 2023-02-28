package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.matchers

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.{Author, Educative, FreeCourse, Lesson}
import org.scalatest.Inspectors

class EducativeSpec extends TestSuite with Inspectors with EducativeEitherMatchers:

  private val educative = Educative(Seq(
    FreeCourse("Scala for Beginners", Author("John", "Doe"), Set.empty[Lesson], Set.empty[String]),
    FreeCourse("Scala for Data Analysis", Author("Mary", "Jane"), Set.empty[Lesson], Set("Scala")),
    FreeCourse("Functional Kotlin", Author("Mary", "Jane"), Set.empty[Lesson], Set("kotlin"))
  ))

  "Educative" `should` "enforce uniqueness when adding a new course" in {
    educative `with` FreeCourse(
      title = "Scala for Beginners",
      author = Author("Lucas", "Manny"),
      lessons = Set(Lesson("What's Scala?")),
      tags = Set("Scala")
    ) should not(beDefined)
  }

  it `should` "add the new course if there's no course with the same title" in {
    val newCourse = FreeCourse(
      title = "Scala for Absolute Beginners",
      author = Author("Lucas", "Manny"),
      lessons = Set(Lesson("What's Scala?")),
      tags = Set("Scala")
    )

    def beDefinedAndHaveNCourses(n: Int) = beDefined and haveNCourses(n)

    educative `with` newCourse should beDefinedAndHaveNCourses(4)
  }

  it `should` "delete more than one course" in {
    val ed = educative without "scala for beginners" without "Scala for Data Analysis" without "functional Kotlin"

    ed.courses shouldBe empty
  }

  it `should` "not delete the course if there's no course with the same title" in {
    val ed = educative without "Scala for Absolute Beginners"

    ed.courses should not be empty
    ed.courses should contain theSameElementsAs educative.courses
  }

  it `should` "filter the courses by tag" in {
    val scalaCoursesTags = educative.filterByTag("Scala") map (_.tags)

    all(scalaCoursesTags) should contain ("Scala")
  }
