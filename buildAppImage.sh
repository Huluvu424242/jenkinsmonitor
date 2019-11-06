#!/usr/bin/env sh

export ARCH=x86_64

# download jre 11
wget -c https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/download/jdk-11.0.3%2B7_openj9-0.14.3/OpenJDK11U-jdk_x64_linux_openj9_11.0.3_7_openj9-0.14.3.tar.gz
tar xf OpenJDK11U-jdk_x64_linux_openj9_11.0.3_7_openj9-0.14.3.tar.gz

# list deps modules
./jdk-11.0.3+7/bin/jdeps  --list-deps ./target/jenkinsmonitor-*-jar-with-dependencies.jar

# spezielles jre bauen
./jdk-11.0.3+7/bin/jlink --no-header-files --no-man-pages --compress=2 --strip-debug --add-modules java.base,java.desktop,java.logging,java.management,java.naming,java.security.jgss,java.sql,java.xml --output usr

# copy binary
cp -Rf usr AppDir/

#read -p "Press [Enter] jre gebaut ..."

# download AppImageKit and give permissions und run it
wget -c https://github.com/AppImage/AppImageKit/releases/download/12/appimagetool-x86_64.AppImage
chmod +x appimagetool-x86_64.AppImage
wget https://github.com/linuxdeploy/linuxdeploy/releases/download/continuous/linuxdeploy-x86_64.AppImage
chmod +x linuxdeploy-x86_64.AppImage

#echo "Starte Workarounds"
# worarounds for missing deps
#cp -a ./usr/lib/j9vm/libjvm.so AppDir/usr/lib/libjvm.so
#cp -a ./usr/lib/compressedrefs/libj9thr29.so AppDir/usr/lib/libj9thr29.so
#read -p "Press [Enter] Workarounds erledigt ..."

# copy resources at final place
cp -a ./target/jenkinsmonitor-*-jar-with-dependencies.jar AppDir/usr/bin/jenkinsmonitor-jar-with-dependencies.jar
cp -Rf appimg-resources/* AppDir/
ln -s AppDir/AppRun AppDir/usr/bin/jenkinsmonitor.wrapper


# loesche altes Image und erstelle neu
rm Jenkins_Monitor*.AppImage
./appimagetool-x86_64.AppImage AppDir/
#./linuxdeploy-x86_64.AppImage --appdir AppDir --output appimage
#./linuxdeploy-x86_64.AppImage --appdir AppDir -e target/jenkinsmonitor-0.0.2-SNAPSHOT-jar-with-dependencies.jar -i appimg-resources/1984EmmanuelGoldstein.png -d appimg-resources/myapp.desktop  --output appimage
#./linuxdeploy-x86_64.AppImage --appdir AppDir -e appimg-resources/launch.sh -i appimg-resources/1984EmmanuelGoldstein.png -d appimg-resources/myapp.desktop  --output appimage

#read -p "Press [Enter] to start remove waste files ..."
rm ./linuxdeploy*.AppImage
rm -rf ./AppDir/*
rm -rf ./AppDir/.DirIcon
rm -rf ./usr



