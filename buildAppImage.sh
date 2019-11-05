#!/usr/bin/env sh

export ARCH=x86_64

# copy jar
mkdir -p AppDir/usr/share/jenkinsmonitor
cp -a ./target/jenkinsmonitor-*-jar-with-dependencies.jar AppDir/usr/share/jenkinsmonitor/jenkinsmonitor-jar-with-dependencies.jar

# read -p "Press [Enter]  ..."

# download jre 11
wget -c https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/download/jdk-11.0.3%2B7_openj9-0.14.3/OpenJDK11U-jdk_x64_linux_openj9_11.0.3_7_openj9-0.14.3.tar.gz
tar xf OpenJDK11U-jdk_x64_linux_openj9_11.0.3_7_openj9-0.14.3.tar.gz

# list deps modules
./jdk-11.0.3+7/bin/jdeps  --list-deps AppDir/usr/share/jenkinsmonitor/jenkinsmonitor-jar-with-dependencies.jar

# os specific binary image creating via jlink
./jdk-11.0.3+7/bin/jlink --no-header-files --no-man-pages --compress=2 --strip-debug --add-modules java.base,java.desktop,java.logging,java.management,java.naming,java.security.jgss,java.sql,java.xml --output usr

# copy binary
cp -Rf usr AppDir/

# download AppImageKit and give permissions und run it
wget -c https://github.com/AppImage/AppImageKit/releases/download/12/appimagetool-x86_64.AppImage
chmod +x appimagetool-x86_64.AppImage

# loesche altes Image und erstelle neu
rm Jenkins_Monitor*.AppImage
./appimagetool-x86_64.AppImage AppDir/

read -p "Press [Enter] to start remove waste files ..."
rm ./AppDir/.DirIcon
rm -rf ./AppDir/usr
rm -rf ./usr



