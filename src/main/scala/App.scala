package com.bryanjswift

import com.codahale.fig.Configuration
import org.mortbay.jetty.Server

object PortfolioApp {
  val Config = new Configuration("src/main/resources/config.json")
  def main(args:Array[String]) {
    val server = new Server(Config("http.port").as[Int]);
    //XmlConfiguration configuration = new XmlConfiguration(new File("myJetty.xml").toURL());
    // or use new XmlConfiguration(new FileInputStream("myJetty.xml"));
    //configuration.configure(server);
    server.start();
  }
}
