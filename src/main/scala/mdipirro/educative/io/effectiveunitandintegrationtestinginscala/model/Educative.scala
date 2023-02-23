package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model

case class Author(firstName: String, lastName: String)

case class Lesson(title: String)

case class Course(title: String, author: Author, price: BigDecimal, lessons: Seq[Lesson], tags: Seq[String])

case class Educative(courses: Seq[Course])
