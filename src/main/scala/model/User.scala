package com.bryanjswift.model

import org.joda.time.DateTime

sealed case class User(
    id:Option[String], name:Option[String], username:String
  , password:String, created:DateTime, modified:DateTime
)

object User {
  def apply(username:String, password:String):User = {
    val now = new DateTime
    User(None, None, username, password, now, now)
  }
}
