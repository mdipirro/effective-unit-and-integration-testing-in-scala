import sbt._

object Dependencies {
  object software {
    object amazon {
      object awssdk {
        val s3 = "software.amazon.awssdk" % "s3" % "2.20.52"
      }
    }
  }

  object com {
    object eed3si9n {
      object expecty {
        val expecty =
          "com.eed3si9n.expecty" %% "expecty" % "0.16.0"
      }
    }

    object dimafeng {
      val `testcontainers-scala-scalatest` = "com.dimafeng" %% "testcontainers-scala-scalatest" % "0.40.15"
    }

    object vladsch {
      object flexmark {
        private val version = "0.64.0"

        val flexmark = "com.vladsch.flexmark" % "flexmark" % version
        val `flexmark-profile-pegdown` = "com.vladsch.flexmark" % "flexmark-profile-pegdown" % version
      }
    }
  }

  object org {
    object scalatest {
      val scalatest =
        "org.scalatest" %% "scalatest" % "3.2.15"
    }

    object scalatestplus {
      val `scalacheck-1-17` =
        "org.scalatestplus" %% "scalacheck-1-17" % "3.2.15.0"

      val `mockito-4-6` = "org.scalatestplus" %% "mockito-4-6" % "3.2.15.0"
    }
  }
}
