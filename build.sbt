name := "scala-storm"

version := "1.0"

organization := "com.github.velvia"

resolvers += "Big Bee Consultants" at "http://repo.bigbeeconsultants.co.uk/repo"

resolvers += "clojars" at "http://clojars.org/repo/"

resolvers += "clojure-releases" at "http://build.clojure.org/releases"

libraryDependencies += "org.apache.storm" % "storm-core" % "0.9.2-incubating" % "provided" exclude("junit", "junit")

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

libraryDependencies ++= Seq(
  "uk.co.bigbeeconsultants" %% "bee-client" % "0.21.+",
  "org.slf4j" % "slf4j-api" % "1.7.+",
  "ch.qos.logback" % "logback-core"    % "1.0.+",
  "ch.qos.logback" % "logback-classic" % "1.0.+"
)