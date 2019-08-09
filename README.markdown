# A [Giter8][g8] template for Scala Microservices
## Field of application
A blueprint for a scala micro service that processes http requests and that can be deployed in k8s.
## Usage
+ g8 https://github.com/123avi/neura-microservice-template-master.git
* sbt dependencyCheck
* adjust versions
* remove unnecessary libs 
* sbt run

## Included plugins 
+ sbt-updates https://github.com/rtimush/sbt-updates
> It's always ideal to have your dependencies updated to the latest release. To automate that, sbt-updates can check maven repositories for dependency updates on your project dependencies (there is also a limited support for Ivy repositories hosted on BinTray).
+ wartremover - http://www.wartremover.org/
> In addition to the standard library warnings, sbt-wartremover adds more validations during the compile stage to enforce better coding conventions such as usage of null and asInstanceOf or non-final case classes, for instance.
+ sbt-coverage https://github.com/scoverage/sbt-scoverage
> sbt-scoverage is a plugin for SBT that integrates the scoverage code coverage library.
+ sbt-buildinfo https://github.com/sbt/sbt-buildinfo
> sbt-buildinfo generates Scala source from your build definitions.
  
+ sbt-dependency-graph https://github.com/jrudolph/sbt-dependency-graph
+