package com.bryanjswift.web

import com.bryanjswift.PortfolioApp
import com.handinteractive.mobile.UAgentInfo
import velocity.VelocityView

class IndexServlet extends Servlet {
  override def doGet(http:HttpHelper) {
    val context = Map[String, Any]("UserAgentInfo" -> new UAgentInfo(http.request))
    val template = PortfolioApp.Config("templates." + http.uri + "." + http.format).as[String]
    val view = new VelocityView(template)
    view.render(context, http.response)
  }
}