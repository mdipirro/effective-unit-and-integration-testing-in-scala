package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.repository

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v2.Course

import scala.concurrent.{ExecutionContext, Future}

trait CourseRepository:
  def getAll: Future[Seq[Course]]

class SimpleCourseRepository(using ExecutionContext):

  def getAll: Future[Seq[Course]] = Future { Seq.empty }
