name := """18655-Fall2016-Team7"""

version := "1.0-SNAPSHOT"
routesGenerator := StaticRoutesGenerator

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  cache,
  javaWs,
  javaJdbc,
  evolutions,
  "mysql" % "mysql-connector-java" % "5.1.40"
)
