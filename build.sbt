val Scala212 = "2.12.18"
val Scala213 = "2.13.11"
//val Scala3 = "3.3.0"


ThisBuild / scalaVersion := Scala213
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.pairwiseltd"
ThisBuild / organizationName := "Pairwise Software Ltd."

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



// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
