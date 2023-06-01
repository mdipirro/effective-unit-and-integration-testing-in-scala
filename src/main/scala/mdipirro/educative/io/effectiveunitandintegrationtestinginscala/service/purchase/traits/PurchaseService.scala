package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.service.purchase.traits

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v3.Course

trait PurchaseService:
  this: PaymentService =>
    def purchase(course: Course): Either[String, BigDecimal] =
      val price = course.price
      invoice(price).toLeft(price)

/*
PaymentService can be instantiated with:
val purchaseService = new PurchaseService with PaymentServiceImpl {}
*/
