package se.di.ntl.changes.claim.person

import java.time.Instant
import java.util.UUID

import scalaz._
import Scalaz._
import doobie.imports._
import se.di.ntl.changes.claim.person.Writer.Claim

import scalaz.concurrent.Task
import se.di.ntl.changes.common.DoobieSupport._

import scalaz.stream.Process.Await

class Writer(transactor: Transactor[Task]) {

  def test = {
    val scalazFuture = dsql"select id, user_id, entity_id, new_value, operation, status, timestamp from Claim"
      .query[Claim]
      .list
      .transact(transactor)
      .get
  }


}

object Writer {

  case class Claim(id: UUID,
                   userId: String,
                   entityId: String,
                   newValue: Array[Byte],
                   operation: String,
                   status: String,
                   timestamp: Instant)
}