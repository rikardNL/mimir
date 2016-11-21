package se.di.ntl.changes

import java.time.Instant

object Application {
  val environment: String = Option(System.getProperty("env")).getOrElse("local")
  val startTime: Instant = Instant.now()
}
