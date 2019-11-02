#!/bin/bash
# Herstellung der jmod Dateien für auto module
#
# Gezeigt am Beispiel von commons-lang
#
export GROUP_ID="org/json"
export ARTIFACT_ID="json"
export MODULE_ID="org.json"
export VERSION="20190722"
# bereinige jdeps
rm -rf ./jdeps/*
rm ./${MODULE_ID}-${VERSION}.jmod
# generiere Module Definition
jdeps --generate-open-module ./jdeps ~/.m2/repository/${GROUP_ID}/${ARTIFACT_ID}/${VERSION}/${ARTIFACT_ID}-${VERSION}.jar
jdeps --generate-module-info ./jdeps ~/.m2/repository/${GROUP_ID}/${ARTIFACT_ID}/${VERSION}/${ARTIFACT_ID}-${VERSION}.jar
# compiliere module-info
unzip  ~/.m2/repository/${GROUP_ID}/${ARTIFACT_ID}/${VERSION}/${ARTIFACT_ID}-${VERSION}.jar -d ./jdeps/${MODULE_ID}/classes
javac -d ./jdeps/${MODULE_ID}/classes ./jdeps/${MODULE_ID}/module-info.java
# erzeuge jmod module
jmod create --class-path ./jdeps/${MODULE_ID}/classes ./${MODULE_ID}-${VERSION}.jmod

