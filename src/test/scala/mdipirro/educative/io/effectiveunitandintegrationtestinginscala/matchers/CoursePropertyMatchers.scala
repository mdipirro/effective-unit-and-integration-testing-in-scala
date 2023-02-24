package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.matchers

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.{Author, Course, PaidCourse}
import org.scalatest.matchers.{HavePropertyMatchResult, HavePropertyMatcher}

trait CoursePropertyMatchers:
  def title(expectedValue: String): HavePropertyMatcher[Course, String] =
    new HavePropertyMatcher[Course, String]:
      def apply(course: Course) =
        HavePropertyMatchResult(
          matches = course.title == expectedValue,
          propertyName = "title",
          expectedValue = expectedValue,
          actualValue = course.title
        )

  def author(expectedValue: Author): HavePropertyMatcher[Course, Author] =
    (course: Course) => HavePropertyMatchResult(
      matches = course.author == expectedValue,
      propertyName = "author",
      expectedValue = expectedValue,
      actualValue = course.author
    )

trait PaidCoursePropertyMatchers extends CoursePropertyMatchers:
  def price(expectedValue: BigDecimal): HavePropertyMatcher[PaidCourse, BigDecimal] =
    (course: PaidCourse) => HavePropertyMatchResult(
      matches = course.price == expectedValue,
      propertyName = "price",
      expectedValue = expectedValue,
      actualValue = course.price
    )
