name := "s3lambda"

version := "0.1"

scalaVersion := "2.12.8"

enablePlugins(ScalaJSPlugin)

scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }

