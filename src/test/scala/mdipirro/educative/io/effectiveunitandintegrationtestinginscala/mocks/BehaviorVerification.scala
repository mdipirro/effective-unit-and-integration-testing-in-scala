package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.mocks

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v3.mockable.*
import org.scalatest.OptionValues

class BehaviorVerification extends TestSuite with OptionValues:
  "Publishing a course with no lessons" `should` "leave the course-base as is" in new Fixture:
    val newEducative = educative.publishCourse("Scala for Dummies")

    val double = newEducative.courseByName("Scala for Dummies").value.asInstanceOf[CourseDouble]
    double.titleInvoked shouldBe true
    double.lessonsInvoked shouldBe true
    double.publishedInvoked shouldBe false
    double.copyInvoked shouldBe false

  "Publishing a course with some lessons" `should` "update the course-base" in new Fixture:
    val newEducative = educative.publishCourse("Advanced Scala")

    val double = newEducative.courseByName("Advanced Scala").value.asInstanceOf[CourseDouble]
    double.titleInvoked shouldBe true
    double.lessonsInvoked shouldBe true
    double.publishedInvoked shouldBe true
    double.copyInvoked shouldBe true

  class CourseDouble(
      t: String,
      ls: Set[Lesson],
      pub: Boolean
    ) extends Course:
    var titleInvoked = false
    var lessonsInvoked = false
    var publishedInvoked = false
    var copyInvoked = false

    override def title: String =
      titleInvoked = true
      t

    override def lessons: Set[Lesson] =
      lessonsInvoked = true
      ls

    override def published: Boolean =
      publishedInvoked = true
      pub

    def copy(
              title: String,
              author: Author,
              lessons: Set[Lesson],
              tags: Set[String],
              published: Boolean
            ): CourseDouble =
      val newCourse = CourseDouble(t, ls, published)

      newCourse.titleInvoked = titleInvoked
      newCourse.lessonsInvoked = lessonsInvoked
      newCourse.publishedInvoked = publishedInvoked
      newCourse.copyInvoked = true

      newCourse

    override val author: Author = Author("Not", "Used")
    override val tags: Set[String] = Set.empty
    override val price: BigDecimal = 0

  trait Fixture:
    val educative: Educative = Educative(
      Seq(
        CourseDouble("Scala for Dummies", Set.empty, false),
        CourseDouble("Advanced Scala", Set(Lesson("Introduction")), false)
      )
    )
