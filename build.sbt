name := "scala-storm"

version := "1.0"

organization := "com.github.velvia"

resolvers += "clojars" at "http://clojars.org/repo/"

resolvers += "clojure-releases" at "http://build.clojure.org/releases"

libraryDependencies += "org.apache.storm" % "storm-core" % "0.9.2-incubating" % "provided" exclude("junit", "junit")

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"