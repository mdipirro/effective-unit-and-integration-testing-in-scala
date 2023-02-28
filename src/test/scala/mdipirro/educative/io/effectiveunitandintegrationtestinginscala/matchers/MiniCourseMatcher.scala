package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.matchers

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v1.Course
import org.scalatest.matchers.{BeMatcher, MatchResult}

trait MiniCourseMatcher:
  def mini: BeMatcher[Course] =
    (course: Course) => MatchResult(
      matches = course.isMini,
      rawFailureMessage = "The course is not a mini one",
      rawNegatedFailureMessage = "The course is a mini one",
      args = IndexedSeq.empty
    )
