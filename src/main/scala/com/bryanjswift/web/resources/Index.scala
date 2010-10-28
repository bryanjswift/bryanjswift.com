package com.bryanjswift.web.resources

import com.sun.jersey.spi.resource.Singleton
import javax.ws.rs.{GET,Produces,Path,PathParam}
import javax.ws.rs.core.MediaType.{APPLICATION_XML,TEXT_PLAIN,TEXT_XML,TEXT_HTML}
import velocity.VelocityView

@Singleton
@Path("/index")
class IndexResource {
  @GET
  @Produces(Array(TEXT_HTML))
  def message:String = {
    val xml = <h1>Hello Bryan, From Jersey</h1>
    xml.toString
  }
  @GET
  @Path("/velocity")
  @Produces(Array(TEXT_HTML))
  def velocity:String = {
    val context = Map[String,Any]()
    val view = new VelocityView("templates/index.vm")
    view.merge(context)
  }
}
