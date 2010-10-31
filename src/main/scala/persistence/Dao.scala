package com.bryanjswift.persistence

import com.bryanjswift.model.User
import com.mongodb.BasicDBObject
import net.liftweb.mongodb.{DefaultMongoIdentifier, MongoAddress, MongoDB, MongoHost}
import org.bson.types.ObjectId

object Dao {
  MongoDB.defineDb(DefaultMongoIdentifier, MongoAddress(MongoHost("localhost", 27017), "bryanjswift-portfolio"))
  def save(user:User) = {
    MongoDB.use(db => {
      val coll = db.getCollection("users")
      // save the doc to the db
      if (user.id.isDefined) {
        coll.update(new BasicDBObject("_id", new ObjectId(user.id.get)), user)
      } else {
        coll.save(user)
      }
      user.copy(id = Some(coll.findOne(new BasicDBObject("username", user.username)).get("_id").toString))
    })
  }
  private implicit def userToDoc(user:User):BasicDBObject = {
    val doc = new BasicDBObject("username", user.username)
    if (user.name.isDefined) { doc.put("name", user.name.get) }
    doc.append("password", user.password)
    doc.append("created", user.created.toGregorianCalendar.getTimeInMillis)
    doc.append("modified", user.modified.toGregorianCalendar.getTimeInMillis)
  }
}
