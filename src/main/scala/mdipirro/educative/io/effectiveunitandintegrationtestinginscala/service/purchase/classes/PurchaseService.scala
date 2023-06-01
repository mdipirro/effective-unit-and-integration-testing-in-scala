package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.service.purchase.classes

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v3.Course

class PurchaseService(paymentService: PaymentService):
  def purchase(course: Course): Either[String, BigDecimal] =
    val price = course.price
    paymentService.invoice(price).toLeft(price)

