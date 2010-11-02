import sbt._

class PortfolioProject(info: ProjectInfo) extends DefaultWebProject(info) with assembly.AssemblyBuilder {
  // repository locations (for Jersey)
  val javaNet = "Java.net Repository for Maven" at "http://download.java.net/maven/2/"
  val codaRepo = "Coda Hale's Repository" at "http://repo.codahale.com/"

  // Fig - for configuration
  val fig = "com.codahale" %% "fig" % "1.0.6" from "http://github.com/downloads/bryanjswift/fig/fig_2.8.0-1.0.6.jar"
  // Jersey
  val jersey = "com.sun.jersey" % "jersey-server" % "1.4"
  // Velocity
  val velocity = "org.apache.velocity" % "velocity" % "1.6.4"
  val simpleVelocity = "bryanjswift" % "simple-velocity" % "0.3.1" from "http://github.com/downloads/bryanjswift/simple-velocity/simple-velocity_2.8.0-0.3.1.jar"
  // Jetty
  val jetty6 = "org.mortbay.jetty" % "jetty" % "6.1.25"
  // MongoDB
  val liftMongo = "net.liftweb" %% "lift-mongodb" % "2.1"
  val jodaTime = "joda-time" % "joda-time" % "1.6.2"

  val jettyTest = "org.mortbay.jetty" % "jetty" % "6.1.25" % "test->default"
  // ScalaTest
  val scalaTest = "org.scalatest" % "scalatest" % "1.2" % "test->default"

  // override looking for jars in ./lib
  override def dependencyPath = sourceDirectoryName / mainDirectoryName / "lib"
  // override path to managed dependency cache
  override def managedDependencyPath = "project" / "lib_managed"
  // include WEB-INF files on the resources classpath
  override def mainResources = super.mainResources +++ webappResources.filter(_.relativePath.startsWith("WEB-INF"))
}
