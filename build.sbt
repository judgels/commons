import de.johoop.testngplugin.TestNGPlugin
import de.johoop.jacoco4sbt.JacocoPlugin.jacoco

lazy val judgelscommons = (project in file("."))
    .settings(
        name := "commons",
        version := IO.read(file("version.properties")).trim,
        scalaVersion := "2.11.7",
        libraryDependencies ++= Seq(
            "com.google.code.gson" % "gson" % "2.3.1",
            "com.google.guava" % "guava" % "18.0",
            "com.puppycrawl.tools" % "checkstyle" % "6.8.1",
            "commons-io" % "commons-io" % "2.4",
            "org.apache.httpcomponents" % "httpclient" % "4.5",
            "org.apache.commons" % "commons-lang3" % "3.3.2",
            "org.powermock" % "powermock-api-mockito" % "1.6.2",
            "org.powermock" % "powermock-module-testng" % "1.6.2",
            "org.eclipse.jgit" % "org.eclipse.jgit" % "3.7.0.201502260915-r",
            "com.amazonaws" % "aws-java-sdk" % "1.9.28.1" exclude("joda-time", "joda-time")
        ),
        resolvers += Resolver.sbtPluginRepo("releases")
    )
    .settings(TestNGPlugin.testNGSettings: _*)
    .settings(
        TestNGPlugin.testNGSuites := Seq("src/test/resources/testng.xml")
    )
    .settings(jacoco.settings: _*)
    .settings(
        parallelExecution in jacoco.Config := false
    )
    .settings(
        publishArtifact in (Compile, packageDoc) := false,
        publishArtifact in packageDoc := false,
        sources in (Compile,doc) := Seq.empty
    )


fork in run := true
