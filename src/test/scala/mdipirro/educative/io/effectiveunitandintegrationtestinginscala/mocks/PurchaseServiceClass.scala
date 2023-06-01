package mdipirro.educative.io.effectiveunitandintegrationtestinginscala.mocks

import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.TestSuite
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.model.v3.Course
import mdipirro.educative.io.effectiveunitandintegrationtestinginscala.service.purchase.classes.{PaymentService, PurchaseService}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.scalatest.EitherValues
import org.scalatestplus.mockito.MockitoSugar

import scala.language.unsafeNulls

class PurchaseServiceClass extends TestSuite with MockitoSugar with EitherValues:

  "The PurchaseService" `should` "return the correct course price" in {
    val paymentService = mock[PaymentService]
    when(paymentService.invoice(any[BigDecimal]())).thenReturn(Option.empty)

    val course = mock[Course]
    when(course.price).thenReturn(BigDecimal(10))

    PurchaseService(paymentService).purchase(course).value shouldBe BigDecimal(10)
  }
