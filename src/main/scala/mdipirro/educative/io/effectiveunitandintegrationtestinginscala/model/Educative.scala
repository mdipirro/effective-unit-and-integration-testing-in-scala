package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model

case class Author(firstName: String, lastName: String):
  require(!firstName.isBlank, "The first name must not be blank")
  require(!lastName.isBlank, "The last name must not be blank")

  val fullName: String = s"$firstName $lastName"

case class Lesson(title: String)

case class Course(title: String, author: Author, price: BigDecimal, lessons: Seq[Lesson], tags: Seq[String])

case class Educative(courses: Seq[Course])
