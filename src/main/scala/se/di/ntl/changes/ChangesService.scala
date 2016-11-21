package se.di.ntl.changes

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._

class ChangesService {

  val routes = claimPerson ~ suggestPerson

  def claimPerson = pathPrefix("claim") {
    pathPrefix("person") {
      post {
        complete("CREATE A NEW CLAIM")
      } ~
      path(Segment) { claimId =>
        get {
          complete("GET CLAIM BY ID")
        } ~
        (put & path("accept")) {
          complete("ACCEPT CLAIM BY ID")
        } ~
        (put & path("reject")) {
          complete("REJECT CLAIM BY ID")
        }
      }
    }
  }

  def suggestPerson = pathPrefix("person") {
    pathPrefix("new") {
      pathEndOrSingleSlash {
        get {
          complete("LIST ALL SUGGEST NEW PERSON")
        } ~
        post {
          complete("CREATE SUGGEST NEW PERSON")
        }
      } ~
      pathPrefix(Segment) { suggestId =>
        path("accept") {
          complete("ACCEPT WITH A USER ID")
        } ~
        path("accept" / Segment) { personId =>
          complete("ACCEPT WITH A PERSON ID")
        } ~
        pathPrefix("context") {
          get {
            complete("GET CONTEXT FOR A SUGGESTION ID")
          } ~
          post {
            complete("ADD NEW CONTEXT TO SUGGESTION")
          } ~
          (put & path(Segment / "status")) { contextId =>
            complete("CHANGE STATUS OF CONTEXT ID")
          }
        }
      }
    } ~
    pathPrefix("update") {
      (get & parameter('userId) & parameter('onlyPending.?)) {
        case (userId, onlyPending) =>
          complete("GET ALL PENDING FOR USER ID")
      } ~
      post {
        complete("CREATE A NEW SUGGESTED UPDATE")
      } ~
      pathPrefix(Segment) { updateId =>
        get {
          complete("GET SUGGESTED UPDATE WITH ID")
        } ~
        (put & path("reject")) {
          complete("REJECT AN UPDATE")
        } ~
        (put & path("accept")) {
          complete("ACCEPT AN UPDATE")
        }
      }
    }
  }
}
