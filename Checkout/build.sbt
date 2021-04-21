name := "Checkout"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.7"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % "test"

lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.+"
lazy val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.13.+"