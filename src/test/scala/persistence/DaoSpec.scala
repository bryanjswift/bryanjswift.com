package com.bryanjswift.persistence

import com.bryanjswift.model.User
import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

class DaoSpec extends WordSpec with ShouldMatchers {
  "Dao" should {
    "save User to database" in {
      val user = User("bryanjswift", "password")
      assert(!user.id.isDefined)
      val saved = Dao.save(user)
      assert(saved.id.isDefined)
    }
  }
}
