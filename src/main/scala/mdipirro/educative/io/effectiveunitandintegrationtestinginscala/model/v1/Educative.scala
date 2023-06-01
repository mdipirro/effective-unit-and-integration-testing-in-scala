package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v1

case class Author(firstName: String, lastName: String):
  require(!firstName.isBlank, "The first name must not be blank")
  require(!lastName.isBlank, "The last name must not be blank")

  val fullName: String = s"$firstName $lastName"

case class Lesson(title: String)

sealed trait Course:
  val title: String
  val author: Author
  val lessons: Seq[Lesson]
  val tags: Seq[String]

  lazy val isMini: Boolean = lessons.size < 20

case class FreeCourse(
                       override val title: String,
                       override val author: Author,
                       override val lessons: Seq[Lesson],
                       override val tags: Seq[String]
                     ) extends Course

case class PaidCourse(
                       override val title: String,
                       override val author: Author,
                       override val lessons: Seq[Lesson],
                       override val tags: Seq[String]
                     ) extends Course:
  val price: BigDecimal =
    val basePrice: BigDecimal = lessons.size match
      case n if n < 10 => 20
      case n if n < 30 => 50
      case n if n < 50 => 60 + 0.3 * n
      case n => 100 + 0.5 * n

    basePrice.min(300)

class Educative(val courses: Seq[Course]):
  def courseByName(courseTitle: String): Option[Course] = courses find (_.title == courseTitle)
