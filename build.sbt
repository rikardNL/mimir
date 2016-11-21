name := "techlist-mimir"

version := "0.1.0"

scalaVersion := "2.12.0"

val akkaVersion = "2.4.12"
val akkaHttpVersion = "10.0.0-RC2"
val slf4jVersion = "1.7.12"
val jacksonVersion = "2.8.5"
val json4sVersion = "3.5.0"
val doobieVersion = "0.3.1-M2"

resolvers += Resolver.bintrayRepo("hseeberger", "maven")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %%  "akka-actor"            % akkaVersion,
  "com.typesafe.akka" %%  "akka-http"             % akkaHttpVersion,
  "com.typesafe.akka" %%  "akka-testkit"          % akkaVersion       % "test",
  "com.typesafe.akka" %%  "akka-http-testkit"     % akkaHttpVersion   % "test",

  "de.heikoseeberger" %%  "akka-http-circe"       % "1.11.0-M3",

  "org.slf4j"         %   "slf4j-api"             % slf4jVersion,
  "org.slf4j"         %   "jcl-over-slf4j"        % slf4jVersion,
  "org.slf4j"         %   "jul-to-slf4j"          % slf4jVersion,

  "org.tpolecat"      %%  "doobie-core"           % doobieVersion,
  "org.tpolecat"      %%  "doobie-postgres"       % doobieVersion,

  "org.flywaydb"      %   "flyway-core"           %   "4.0"
)