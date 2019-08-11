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

scalacOptions ++= Seq(
  "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",                // Specify character encoding used by source files.
  "-explaintypes",                     // Explain type errors in more detail.
  "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
  "-Xfuture",                          // Turn on future language features.
  "-Xlint:doc-detached",               // A Scaladoc comment appears to be detached from its element.
  "-Xlint:infer-any",                  // Warn when a type argument is inferred to be `Any`.
  "-Xlint:private-shadow",             // A private field (or class parameter) shadows a superclass field.
  "-Xlint:stars-align",                // Pattern sequence wildcard must align with sequence component.
  "-Xlint:type-parameter-shadow",      // A local type parameter shadows a type already in scope.
  "-Xlint:unsound-match",              // Pattern match may not be typesafe.
  "-Ywarn-dead-code",                  // Warn when dead code is identified.
  "-Ywarn-inaccessible",               // Warn about inaccessible types in method signatures.
  "-Ywarn-infer-any",                  // Warn when a type argument is inferred to be `Any`.
  "-Ywarn-nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Ywarn-nullary-unit",               // Warn when nullary methods return Unit.
  "-Ywarn-unused:locals",              // Warn if a local definition is unused.
  "-Ywarn-unused:params",              // Warn if a value parameter is unused.
  "-Ywarn-unused:patvars",             // Warn if a variable bound in a pattern is unused.
  "-Ywarn-unused:privates",            // Warn if a private member is unused.
  "-Ywarn-value-discard"               // Warn when non-Unit expression results are unused.
)