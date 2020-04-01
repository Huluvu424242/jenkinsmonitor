#!/bin/sh
${JAVA_HOME}/bin/java -Dlogback.configurationFile=filelogging.xml -jar target/jenkinsmonitor-0.0.2-SNAPSHOT-jar-with-dependencies.jar

