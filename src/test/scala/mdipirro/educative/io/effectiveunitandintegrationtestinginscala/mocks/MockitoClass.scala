package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.mocks

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v3.*
import org.mockito.ArgumentMatchers.{any, anyString}
import org.mockito.Mockito.*

import scala.language.unsafeNulls

class MockitoClass extends TestSuite:
  "Publishing a course with no lessons" `should` "leave the course-base as is" in new Fixture:
    educative.publishCourse("Scala for Dummies")

    verify(scalaForDummies, times(1)).title
    verify(scalaForDummies, times(1)).lessons
    verify(scalaForDummies, never).published
    verify(scalaForDummies, never).copy()

  "Publishing a course with some lessons" `should` "update the course-base" in new Fixture:
    educative.publishCourse("Advanced Scala")

    verify(advancedScala, times(2)).title
    verify(advancedScala, times(2)).lessons
    verify(advancedScala, times(1)).published
    verify(advancedScala, atLeastOnce()).copy()

  trait Fixture:
    val scalaForDummies: Course = spy(
      FreeCourse("Scala for Dummies", Author("Matteo", "Di Pirro"), Set.empty, Set.empty)
    )

    val advancedScala: Course = spy(
      FreeCourse("Advanced Scala", Author("Mary", "Jane"), Set(Lesson("Introduction")), Set.empty)
    )

    val educative: Educative = Educative(Seq(scalaForDummies, advancedScala))
