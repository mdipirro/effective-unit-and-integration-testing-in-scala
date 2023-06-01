package mdipirro.educative.io
package effectiveunitandintegrationtestinginscala

import org.scalatest.flatspec.{AnyFlatSpec, AsyncFlatSpec}
import org.scalatest.matchers.should.Matchers

trait TestSuite extends AnyFlatSpec with Matchers

trait AsyncTestSuite extends AsyncFlatSpec with Matchers