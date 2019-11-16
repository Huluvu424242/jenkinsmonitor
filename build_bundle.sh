#!/bin/bash
jlink --module-path "output;$JAVA_HOME/jmods" --add-modules com.github.funthomas424242.jenkinsmonitor --output laufZeitImage

#java --module-path com.mydeveloperplanet.jpmshello/target/jpmshello-1.0-SNAPSHOT.jar;com.mydeveloperplanet.jpmshi/target/jpmshi-1.0-SNAPSHOT.jar --module com.mydeveloperplanet.jpmshello/com.mydeveloperplanet.jpmshello.HelloModules
