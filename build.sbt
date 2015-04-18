name := "sigarrestfulinterface"

scalaVersion := "2.11.4"

scalacOptions += "-target:jvm-1.7"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.2"

libraryDependencies += "io.spray" %%  "spray-json" % "1.3.1"

libraryDependencies += "it.unibo.ing" %% "utils" % "1.0" withSources() intransitive()