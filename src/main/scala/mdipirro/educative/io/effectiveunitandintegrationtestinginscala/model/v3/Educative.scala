package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v3

case class Author(firstName: String, lastName: String):
  require(!firstName.isBlank, "The first name must not be blank")
  require(!lastName.isBlank, "The last name must not be blank")

  val fullName: String = s"$firstName $lastName"

case class Lesson(title: String)

sealed trait Course:

  val title: String
  val author: Author
  val lessons: Set[Lesson]
  val tags: Set[String]

  val price: BigDecimal

  val published: Boolean

  require(lessons.nonEmpty || !published, "A course can be published only if it has at least one lesson")

  def copy(
            title: String = title,
            author: Author = author,
            lessons: Set[Lesson] = lessons,
            tags: Set[String] = tags,
            published: Boolean = published
          ): Course

case class FreeCourse(
                       override val title: String,
                       override val author: Author,
                       override val lessons: Set[Lesson],
                       override val tags: Set[String],
                       override val published: Boolean = false
                     ) extends Course:
  override val price: BigDecimal = 0

final case class PaidCourse(
                       override val title: String,
                       override val author: Author,
                       override val lessons: Set[Lesson],
                       override val tags: Set[String],
                       override val published: Boolean = false
                     ) extends Course:
  override val price: BigDecimal =
    val basePrice: BigDecimal = lessons.size match
      case n if n < 10 => 20
      case n if n < 30 => 50
      case n if n < 50 => 60 + 0.3 * n
      case n => 100 + 0.5 * n

    basePrice.min(300)

class Educative(val courses: Seq[Course]):
  def courseByName(courseTitle: String): Option[Course] = courses find (_.title == courseTitle)

  infix def `with`(newCourse: Course): Either[String, Educative] =
    courses find (_.title.equalsIgnoreCase(newCourse.title)) match
      case Some(_) => Left(s"A course with title ${newCourse.title} already exists")
      case None => Right(Educative(courses :+ newCourse))

  infix def without(courseTitle: String): Educative = Educative(courses filterNot (_.title.equalsIgnoreCase(courseTitle)))

  def filterByTag(tag: String): Seq[Course] = courses filter (_.tags.exists(_.equalsIgnoreCase(tag)))

  lazy val coursesByAuthor: Map[Author, Seq[String]] =
    courses.groupBy(_.author).view.mapValues(cs => cs.map(_.title)).toMap

  def publishCourse(title: String): Educative = Educative(courses map { c =>
    if c.title == title && c.lessons.nonEmpty && !c.published then c.copy(published = true)
    else c
  })
