val Scala212 = "2.12.18"
val Scala213 = "2.13.11"
val Scala3 = "3.3.0"


ThisBuild / scalaVersion := Scala212
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.pairwiseltd"
ThisBuild / organizationName := "Pairwise Software Ltd."

lazy val root = (project in file("."))
  .settings(
    name := "casecheckgen",
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.17.0"
  )

//coverageFailOnMinimum := true
//coverageMinimumStmtTotal := 100
//coverageMinimumBranchTotal := 100

crossScalaVersions := Seq(Scala3, Scala212, Scala213)



// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
