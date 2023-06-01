// Project layout based on https://github.com/DevInsideYou/scala3-seed.g8

import Dependencies._

ThisBuild / organization := "mdipirro.educative.io"
ThisBuild / scalaVersion := "3.2.2"

ThisBuild / scalacOptions ++=
  Seq(
    "-deprecation",
    "-explain",
    "-feature",
    "-language:implicitConversions",
    "-unchecked",
    "-Xfatal-warnings",
    "-Ykind-projector",
  ) ++ Seq("-rewrite", "-indent") ++ Seq("-source", "future-migration")

lazy val `effectiveunitandintegrationtestinginscala` =
  project
    .in(file("."))
    .configs(IntegrationTest)
    .settings(name := "EffectiveUnitAndIntegrationTestingInScala")
    .settings(commonSettings)
    .settings(integrationTestSettings)
    .settings(dependencies)

lazy val commonSettings = {
  lazy val commonScalacOptions = Seq(
    Compile / console / scalacOptions --= Seq(
      "-Wunused:_",
      "-Xfatal-warnings"
    ),
    Test / console / scalacOptions :=
      (Compile / console / scalacOptions).value,
    Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports")
  )

  lazy val otherCommonSettings = Seq(
    update / evictionWarningOptions := EvictionWarningOptions.empty
  )

  Seq(
    commonScalacOptions,
    otherCommonSettings,
  ).reduceLeft(_ ++ _)
}

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    software.amazon.awssdk.s3
  ),
  libraryDependencies ++= Seq(
    com.eed3si9n.expecty.expecty,
    org.scalatest.scalatest,
    org.scalatestplus.`scalacheck-1-17`,
    com.vladsch.flexmark.flexmark,
    com.vladsch.flexmark.`flexmark-profile-pegdown`,
    org.scalatestplus.`mockito-4-6`
  ).map(_ % Test),
  libraryDependencies ++= Seq(
    org.scalatest.scalatest,
    com.dimafeng.`testcontainers-scala-scalatest`
  ).map(_ % IntegrationTest)
)

lazy val integrationTestSettings = Defaults.itSettings ++ Seq(
  IntegrationTest / fork := true
)
