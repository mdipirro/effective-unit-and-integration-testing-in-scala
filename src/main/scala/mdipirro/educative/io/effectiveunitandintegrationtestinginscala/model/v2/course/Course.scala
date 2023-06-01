package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.course

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.{ Author, Lesson }

sealed trait Course:
  val title: String
  val author: Author
  val lessons: Set[Lesson]
  val tags: Set[String]

  val price: BigDecimal

case class FreeCourse private (
    override val title: String,
    override val author: Author,
    override val lessons: Set[Lesson],
    override val tags: Set[String],
    override val price: BigDecimal,
  ) extends Course

object FreeCourse:
  def apply(
      title: String,
      author: Author,
      lessons: Set[Lesson],
      tags: Set[String],
    ): FreeCourse = FreeCourse(title, author, lessons, tags, BigDecimal(0))

case class PaidCourse(
    override val title: String,
    override val author: Author,
    override val lessons: Set[Lesson],
    override val tags: Set[String],
    override val price: BigDecimal,
  ) extends Course

object PaidCourse:
  def apply(
      title: String,
      author: Author,
      lessons: Set[Lesson],
      tags: Set[String],
    ): PaidCourse = PaidCourse(title, author, lessons, tags, computePrice(lessons.size))

  private def computePrice(lessonsNumber: Int) =
    val basePrice: BigDecimal = lessonsNumber match
      case n if n < 10 => 20
      case n if n < 30 => 50
      case n if n < 50 => 60 + 0.3 * n
      case n => 100 + 0.5 * n

    basePrice.min(300)
