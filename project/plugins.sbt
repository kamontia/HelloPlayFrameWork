logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.14")
addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.0-M15")