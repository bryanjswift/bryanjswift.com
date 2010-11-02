package com.bryanjswift

import com.codahale.fig.Configuration
import org.mortbay.jetty.Server
import org.mortbay.jetty.webapp.WebAppContext

object PortfolioApp {
  private val ClassLoader = this.getClass.getClassLoader()
  def stream(path: String) = ClassLoader.getResourceAsStream(path)
  def absolutePath(filename: String) = ClassLoader.getResource(filename).getFile
  val Config = new Configuration(stream("config.json"))
  def main(args: Array[String]) {
    val server = new Server(Config("http.port").as[Int]);
    // create the context for the webapp
    val context = new WebAppContext()
    val webXml = absolutePath(Config("webapp.path").or("WEB-INF/web.xml"))
    context.setDescriptor(webXml)
    context.setContextPath(Config("webapp.context").or("/"))
    context.setParentLoaderPriority(true)
    // set the webapp context as the handler
    server.setHandler(context)
    // start the server
    server.start()
    server.join()
  }
}
