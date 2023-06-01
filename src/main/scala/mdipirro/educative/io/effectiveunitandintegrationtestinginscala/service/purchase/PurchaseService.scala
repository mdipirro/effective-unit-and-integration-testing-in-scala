package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.service.purchase

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v3.Course
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.service.purchase.PaymentService

class PurchaseService:
  def purchase(course: Course): Either[String, BigDecimal] =
    val price = course.price
    PaymentService.invoice(price).toLeft(price)

