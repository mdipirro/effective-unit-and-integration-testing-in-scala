package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.matchers

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.Educative
import org.scalatest.matchers.{MatchResult, Matcher}

trait EducativeEitherMatchers:
  def haveNCourses(n: Int): Matcher[Either[String, Educative]] =
    (ee: Either[String, Educative]) => MatchResult(
      matches = ee.fold(_ => n == 0, _.courses.size == n),
      rawFailureMessage = s"$ee did not contain $n courses",
      rawNegatedFailureMessage = s"$ee did not contain $n courses",
      IndexedSeq.empty
    )

  def beDefined: Matcher[Either[String, Educative]] =
    (ee: Either[String, Educative]) => MatchResult(
      matches = ee.isRight,
      rawFailureMessage = s"$ee does not contain a valid Educative instance",
      rawNegatedFailureMessage = s"$ee contains a valid Educative instance",
      IndexedSeq.empty
    )

object EducativeEitherMatchers extends EducativeEitherMatchers
