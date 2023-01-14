ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "realEstate"
  )

libraryDependencies += "dev.zio"       %% "zio-prelude" % "1.0.0-RC16"
libraryDependencies += "org.scalatest" %% "scalatest"   % "3.2.15" % Test