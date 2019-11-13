#!/bin/bash

#!/usr/bin/env sh

export ARCH=x86_64
export CUR_DIR=${PWD}/
export MOD_DIR=target/module_dir/
export TOOL_DIR=target/tools/
export JDK_ARCHIVE_NAME=OpenJDK11U-jdk_x64_linux_openj9_11.0.3_7_openj9-0.14.3.tar.gz


# download jre 11
wget -P ${TOOL_DIR} -c https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/download/jdk-11.0.3%2B7_openj9-0.14.3/${JDK_ARCHIVE_NAME}
tar  -C ${TOOL_DIR} -xf ${TOOL_DIR}${JDK_ARCHIVE_NAME}

# list deps modules
${TOOL_DIR}jdk-11.0.3+7/bin/jdeps  --list-deps ${CUR_DIR}target/jenkinsmonitor-*-jar-with-dependencies.jar



#${TOOL_DIR}jdk-11.0.3+7/bin/jdeps  --add-modules java.base,java.desktop,java.logging,java.management,java.naming,java.security.jgss,java.sql,java.xml --generate-open-module ${MOD_DIR} ${CUR_DIR}target/jenkinsmonitor-*-jar-with-dependencies.jar
#${TOOL_DIR}jdk-11.0.3+7/bin/jdeps  --add-modules java.base,java.desktop,java.logging,java.management,java.naming,java.security.jgss,java.sql,java.xml --generate-module-info ${MOD_DIR} ${CUR_DIR}target/jenkinsmonitor-*-jar-with-dependencies.jar

# spezielles jre bauen
rm -rf ${MOD_DIR}jenkinsmonitor
mkdir -p ${MOD_DIR}
${TOOL_DIR}jdk-11.0.3+7/bin/jlink --add-modules java.base,java.desktop,java.logging,java.management,java.naming,java.security.jgss,java.sql,java.xml --no-header-files --no-man-pages --compress=2 --strip-debug  --output ${MOD_DIR}jenkinsmonitor



#jlink --module-path "output;$JAVA_HOME/jmods" --add-modules com.github.funthomas424242.jenkinsmonitor --output laufZeitImage

#java --module-path com.mydeveloperplanet.jpmshello/target/jpmshello-1.0-SNAPSHOT.jar;com.mydeveloperplanet.jpmshi/target/jpmshi-1.0-SNAPSHOT.jar --module com.mydeveloperplanet.jpmshello/com.mydeveloperplanet.jpmshello.HelloModules
