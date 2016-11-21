package se.di.ntl.changes

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.flywaydb.core.Flyway
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler

object ChangesServer extends App {

  SLF4JBridgeHandler.removeHandlersForRootLogger()
  SLF4JBridgeHandler.install()

  val config = ConfigFactory.load(Application.environment + ".conf")
  val changesConfig = new DefaultChangesConfig(config)

  lazy val system = ActorSystem("changes", config)

  val log = LoggerFactory.getLogger(getClass)

  log.info("Applying database migrations...")
  val flyway = new Flyway
  flyway.setDataSource(changesConfig.dbUrl, changesConfig.dbUser, changesConfig.dbPassword)
  val migrations = flyway.migrate()
  log.info(s"Done migrating db. Applied $migrations migrations.")

  val serverDescription =
    s"""
       | Pending Change Server
       | environment: ${Application.environment}
       """.stripMargin

  log.info("Starting: " + serverDescription)

  sys.addShutdownHook {
    log.warn("Shutting down: " + serverDescription)
  }
}
