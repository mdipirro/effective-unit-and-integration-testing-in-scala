package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.properties

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.{Author, Course, FreeCourse}
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.mutable.MutableEducative
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.properties.MutableEducativeSpec.Action
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.repository.SimpleCourseRepository
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor2}

object MutableEducativeSpec:
    enum Action:
      case Create(course: Course)
      case Delete(title: String)

class MutableEducativeSpec extends TestSuite with TableDrivenPropertyChecks:

  "Adding and deleting the same course from an empty platform" `should` "leave the platform empty" in {
    val f = new Fixture {}
    import f.*

    forAll(actions) { (action, expectedCourses) =>
      action match
        case Action.Create(course) => educative `with` course
        case Action.Delete(title) => educative without title

      educative.courses shouldBe expectedCourses
    }
  }

  trait Fixture:
    import MutableEducativeSpec.Action
    import MutableEducativeSpec.Action.*

    val educative: MutableEducative = MutableEducative(Seq.empty)

    val actions: TableFor2[Action, Seq[FreeCourse]] = Table(
      ("Action", "Expected Courses"),
      (
        Create(FreeCourse("A WIP Course", Author("John", "Doe"), Set.empty, Set.empty[String])),
        Seq(FreeCourse("A WIP Course", Author("John", "Doe"), Set.empty, Set.empty[String]))
      ),
      (Delete("A WIP Course"), Seq.empty)
    )
