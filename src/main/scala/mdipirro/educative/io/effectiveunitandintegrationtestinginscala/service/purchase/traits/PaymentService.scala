package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.service.purchase.traits

trait PaymentService:
  def invoice(price: BigDecimal): Option[String]

trait PaymentServiceImpl extends PaymentService:
  override def invoice(price: BigDecimal): Option[String] =
    // Make a call to the actual payment service and return an error message if something goes wrong
    ???
