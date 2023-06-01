package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.mutable

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.Course

class MutableEducative(var courses: Seq[Course]):
  def courseByName(courseTitle: String): Option[Course] = courses find (_.title == courseTitle)

  infix def `with`(newCourse: Course): Either[String, MutableEducative] =
    courses find (_.title.equalsIgnoreCase(newCourse.title)) match
      case Some(_) => Left(s"A course with title ${newCourse.title} already exists")
      case None =>
        courses = courses :+ newCourse
        Right(this)

  infix def without(courseTitle: String): MutableEducative =
    courses = courses filterNot (_.title.equalsIgnoreCase(courseTitle))
    this
