package se.di.ntl.changes

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler

object ChangesServer extends App {

  SLF4JBridgeHandler.removeHandlersForRootLogger()
  SLF4JBridgeHandler.install()

  lazy val system = ActorSystem("change-tracker")

  val log = LoggerFactory.getLogger(getClass)

  val serverDescription =
    s"""
       | Pending Change Server
       | environment: ${Application.environment}
       """.stripMargin

  log.info("Starting: " + serverDescription)

  val config = ConfigFactory.load(Application.environment + ".conf")
  val changesConfig = new DefaultChangesConfig(config)
  val actorSystem = ActorSystem("changes", config)

  sys.addShutdownHook {
    log.warn("Shutting down: " + serverDescription)
  }

}
