libraryDependencies += "org.apache.spark" %% "spark-core" % "3.2.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.1"

organization in ThisBuild := "tv.lazygoat"
version in ThisBuild := "1.0"

assemblyMergeStrategy in assembly := {   
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

compileOrder := CompileOrder.JavaThenScala
