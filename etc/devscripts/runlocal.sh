#!/bin/sh
"${JAVA_HOME}"/bin/java -Dlogback.configurationFile=filelogging.xml -jar ../../target/jenkinsmonitor-0.2.0-SNAPSHOT-jar-with-dependencies.jar

