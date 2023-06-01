package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.mocks

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v3.*
import org.mockito.ArgumentMatchers.{any, anyString, eq as mockitoEq}
import org.mockito.Mockito.*
import org.scalatest.OptionValues
import org.scalatestplus.mockito.MockitoSugar

import scala.language.unsafeNulls

class MockitoTrait extends TestSuite with MockitoSugar:
  "Publishing a course with no lessons" `should` "leave the course-base as is" in new Fixture:
    educative.publishCourse("Scala for Dummies")

    verify(scalaForDummies, times(1)).title
    verify(scalaForDummies, times(1)).lessons
    verify(scalaForDummies, never).published
    verify(scalaForDummies, never).copy()

  "Publishing a course with some lessons" `should` "update the course-base" in new Fixture:
    educative.publishCourse("Advanced Scala")

    verify(advancedScala, times(1)).title
    verify(advancedScala, times(1)).lessons
    verify(advancedScala, times(1)).published
    verify(advancedScala, atLeastOnce()).copy()

  trait Fixture:
    val scalaForDummies: Course =
      val courseMock = mock[Course]
      when(courseMock.title).thenReturn("Scala for Dummies")
      when(courseMock.lessons).thenReturn(Set.empty)
      when(courseMock.published).thenReturn(false)
      courseMock

    val advancedScala: Course =
      val courseMock = mock[Course]
      when(courseMock.title).thenReturn("Advanced Scala")
      when(courseMock.lessons).thenReturn(Set(Lesson("Introduction")))
      when(courseMock.published).thenReturn(false)
      when(courseMock.copy(published = true)).thenReturn(courseMock)
      courseMock

    val educative: Educative = Educative(Seq(scalaForDummies, advancedScala))
