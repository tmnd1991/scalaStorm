name := "scala-storm"

version := "2.0"

scalaVersion := "2.11.2"

organization := "com.github.velvia"

resolvers += "clojars" at "http://clojars.org/repo/"

resolvers += "clojure-releases" at "http://build.clojure.org/releases"

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.2"

libraryDependencies += "org.apache.storm" % "storm-core" % "0.9.2-incubating" % "provided" exclude("junit", "junit") withSources()

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"