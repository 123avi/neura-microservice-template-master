enablePlugins(GitVersioning)
enablePlugins(GitBranchPrompt)
enablePlugins(BuildInfoPlugin)

val commonsInfraVersion = "1.2.3"
val logbackVersion = "1.2.3"
val scalaLogging = "3.7.1"
val akkaSlf4jVersion = "2.5.23"
val akkaHttpVersion = "10.1.8"
val akkaVersion = "2.5.23"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "$organization$",
      scalaVersion := "$scala_version$"
    )),
    name := "$name$"
  ).settings(dependencyCheckSuppressionFiles += file("suppress-checks.xml"))
  .settings(dependencyCheckFailBuildOnCVSS := 1)
  .settings(
    buildInfoOptions += BuildInfoOption.BuildTime,
    buildInfoOptions += BuildInfoOption.ToJson,
    buildInfoPackage := "$package$",
    buildInfoKeys := Seq[BuildInfoKey](name, version, "gitHash" -> git.gitHeadCommit.value.getOrElse("emptyRepository"))
  )

lazy val compileDependencies = {
  Seq(

    "com.typesafe.scala-logging" %% "scala-logging"% scalaLogging,
    "ch.qos.logback" % "logback-classic" % logbackVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaSlf4jVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,

    $if(RabbitMQ.truthy)$
        "com.neura" %% "common-infra-rabbitmq" % commonsInfraVersion,
    $endif$
    "de.heikoseeberger" %% "akka-http-json4s" % "1.20.1"
  )
}


libraryDependencies ++= compileDependencies

lazy val testDependencies = Seq(
  "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion ,
  "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     ,
  "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     ,
  "org.scalatest"     %% "scalatest"            % "3.0.5"         ,
  "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0"
).map(_ % Test)
libraryDependencies ++= testDependencies