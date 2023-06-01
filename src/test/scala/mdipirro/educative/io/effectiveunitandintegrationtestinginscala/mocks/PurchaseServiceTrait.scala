package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.mocks

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v3.Course
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.service.purchase.traits.{PaymentService, PurchaseService}
import org.mockito.Mockito.when
import org.scalatest.EitherValues
import org.scalatestplus.mockito.MockitoSugar

import scala.language.unsafeNulls

trait PaymentServiceMock extends PaymentService:
  override def invoice(price: BigDecimal): Option[String] = Option.empty

class PurchaseServiceTrait extends TestSuite with MockitoSugar with EitherValues:

  private val purchaseService = new PurchaseService with PaymentServiceMock {}

  "The PurchaseService" `should` "return the correct course price" in {
    val course = mock[Course]
    when(course.price).thenReturn(BigDecimal(10))

    purchaseService.purchase(course).value shouldBe BigDecimal(10)
  }
