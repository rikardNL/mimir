package se.di.ntl.changes

import com.typesafe.config.Config

import scala.util.Try

trait ChangesConfig {
  def httpPort: Int

  def sPlusHost: String
  def sPlusPath: String
  def sPlusProtocol: String

  def editorId: String

  def apiHost: String
  def apiPort: Int
  def apiProtocol: String
}

class DefaultChangesConfig(c: Config) extends ChangesConfig {
  override val httpPort: Int = c.getInt("pending-changes.http.port")

  override val sPlusHost: String = c.getString("service-plus.host")
  override val sPlusPath: String = c.getString("service-plus.path")
  override val sPlusProtocol: String = c.getString("service-plus.protocol")

  override val editorId: String = c.getString("editor.userId")

  override val apiHost: String = c.getString("api.host")
  override val apiPort: Int = Try(c.getInt("api.port")).getOrElse(80)
  override val apiProtocol: String = c.getString("api.protocol")
}
