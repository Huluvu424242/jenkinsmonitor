#!/bin/bash
# Herstellung der jmod Dateien für auto module
#
# Gezeigt am Beispiel von commons-lang
#
export GROUP_ID="commons-lang"
export ARTIFACT_ID="commons-lang"
export ARTIFACT_ID_DOT="commons.lang"
export VERSION="2.6"
# bereinige jdeps
rm -rf ./jdeps/*
# generiere Module Definition
jdeps --generate-open-module ./jdeps ~/.m2/repository/${GROUP_ID}/${ARTIFACT_ID}/${VERSION}/${ARTIFACT_ID}-${VERSION}.jar
jdeps --generate-module-info ./jdeps ~/.m2/repository/${GROUP_ID}/${ARTIFACT_ID}/${VERSION}/${ARTIFACT_ID}-${VERSION}.jar
# compiliere module-info
unzip  ~/.m2/repository/${GROUP_ID}/${ARTIFACT_ID}/${VERSION}/${ARTIFACT_ID}-${VERSION}.jar -d ./jdeps/${ARTIFACT_ID_DOT}/classes
javac -d ./jdeps/commons.lang/classes ./jdeps/${ARTIFACT_ID_DOT}/module-info.java
# erzeuge jmod module
jmod create --class-path ./jdeps/${ARTIFACT_ID_DOT}/classes src/jmods/${ARTIFACT_ID}-${VERSION}.jmod

