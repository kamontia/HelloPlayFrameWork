name := "HelloPlayFrameWork"

version := "1.0"

lazy val `helloplayframework` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq( ehcache, ws, specs2, evolutions % Test, guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.0" % Test,
  "com.typesafe.play" %% "play-slick" % "3.0.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.1",
  "com.h2database" % "h2" % "1.3.176"
)
unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

