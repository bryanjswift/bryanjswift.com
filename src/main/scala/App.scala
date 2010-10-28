package com.bryanjswift

import com.codahale.fig.Configuration
import org.mortbay.jetty.Server
import org.mortbay.jetty.webapp.WebAppContext

object PortfolioApp {
  val Config = new Configuration("src/main/resources/config.json")
  def main(args:Array[String]) {
    val server = new Server(Config("http.port").as[Int]);
    // create the context for the webapp
    val webapp = Config("webapp.path").or("src/main/webapp")
    val context = new WebAppContext()
    context.setDescriptor(webapp + "/WEB-INF/web.xml")
    context.setResourceBase(webapp)
    context.setContextPath(Config("webapp.context").or("/"))
    context.setParentLoaderPriority(true)
    // set the webapp context as the handler
    server.setHandler(context)
    // start the server
    server.start()
    server.join()
  }
}
