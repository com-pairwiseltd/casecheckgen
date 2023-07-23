val Scala212 = "2.12.18"
val Scala213 = "2.13.11"
//val Scala3 = "3.3.0"


ThisBuild / scalaVersion := Scala213
ThisBuild / organization := "com.pairwiseltd"
ThisBuild / organizationName := "Pairwise Software Ltd."
ThisBuild / version := "0.1.1-SNAPSHOT"
ThisBuild / versionScheme := Some("semver-spec")

lazy val root = (project in file("."))
  .settings(
    name := "casecheckgen",
    libraryDependencies ++= Seq("org.scalacheck" %% "scalacheck" % "1.17.0",
      "org.scala-lang" % "scala-reflect" % "2.13.11",
      "org.scalatest" %% "scalatest" % "3.2.14" % Test,
      "org.scalatestplus" %% "scalacheck-1-17" % "3.2.16.0" % Test
    )
  )

coverageFailOnMinimum := true
coverageMinimumStmtTotal := 100
coverageMinimumBranchTotal := 100

import com.github.sbt.findbugs.FindbugsPlugin.autoImport._

findbugsExcludeFilters := Some(xml.XML.loadFile("findbugs-exclude.xml"))

crossScalaVersions := Seq(Scala212, Scala213)

sonatypeBundleDirectory := (ThisBuild / baseDirectory).value / target.value.getName / "sonatype-staging-2" / (ThisBuild / version).value
publishTo := sonatypePublishToBundle.value
ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"
ThisBuild / sonatypeRepository := "https://s01.oss.sonatype.org/service/local"
