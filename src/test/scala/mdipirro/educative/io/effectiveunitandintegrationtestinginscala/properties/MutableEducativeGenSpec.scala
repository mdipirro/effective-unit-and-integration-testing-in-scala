package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.properties

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.mutable.MutableEducative
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.{Author, Course, FreeCourse, Lesson}
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import org.scalacheck.{Gen, Prop}
import org.scalacheck.commands.Commands
import org.scalatestplus.scalacheck.Checkers

object MutableEducativeGenSpec extends Commands:
  override type State = Seq[Course]
  override type Sut = MutableEducative

  override def canCreateNewSut(
      newState: State,
      initSuts: Traversable[State],
      runningSuts: Traversable[Sut],
    ): Boolean = initSuts.isEmpty && runningSuts.isEmpty

  override def newSut(state: State): Sut = MutableEducative(state)

  override def destroySut(sut: Sut): Unit = ()

  override def initialPreCondition(state: State): Boolean = state.isEmpty

  override def genInitialState: Gen[State] = Gen.const(Seq.empty)

  override def genCommand(state: State): Gen[Command] =
    val authorGenerator = (for {
      fn <- Gen.alphaNumStr.suchThat(_.nonEmpty)
      ln <- Gen.alphaNumStr.suchThat(_.nonEmpty)
    } yield (fn, ln)).map(Author.apply.tupled)

    val freeCourseGenerator = for {
      title <- Gen.alphaNumStr
      author <- authorGenerator
      ls <- Gen.listOf(Gen.alphaNumStr.map(Lesson.apply)).map(_.toSet)
    } yield FreeCourse(title, author, ls, Set.empty)

    val createGenerator = freeCourseGenerator.map(Create.apply)

    val deleteGenerator = Gen.alphaNumStr.map(Delete.apply)

    Gen.oneOf(createGenerator, deleteGenerator, Gen.const(Check))

  /*
  COMMANDS
  */
  final case class Create(course: Course) extends UnitCommand:

    override def run(sut: Sut): Unit = sut.`with`(course)

    override def nextState(state: State): State =
      if (!state.exists(_.title.equalsIgnoreCase(course.title))) state.appended(course) else state

    override def preCondition(state: State): Boolean = true

    override def postCondition(state: State, success: Boolean): Prop = success

  final case class Delete(title: String) extends UnitCommand:

    override def run(sut: Sut): Unit = sut.without(title)

    override def nextState(state: State): State = state filterNot (_.title.equalsIgnoreCase(title))

    override def preCondition(state: State): Boolean = true

    override def postCondition(state: State, success: Boolean): Prop = success

  case object Check extends SuccessCommand:

    override type Result = Seq[Course]

    override def run(sut: Sut): Result = sut.courses

    override def nextState(state: State): State = state

    override def preCondition(state: State): Boolean = true

    override def postCondition(state: State, result: Result): Prop = state == result

class MutableEducativeGenSpec extends TestSuite with Checkers:
  "Adding and deleting courses from the platform" `should` "work correctly" in {
    check(MutableEducativeGenSpec.property())
  }
