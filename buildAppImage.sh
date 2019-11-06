#!/usr/bin/env sh

export ARCH=x86_64
export CUR_DIR=${PWD}/
export APP_DIR=target/AppDir/
export BUILD_DIR=target/appbuild/
export RESOURCE_DIR=src/appimage-resources/
export JDK_ARCHIVE_NAME=OpenJDK11U-jdk_x64_linux_openj9_11.0.3_7_openj9-0.14.3.tar.gz

# download jre 11
wget -P ${BUILD_DIR} -c https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/download/jdk-11.0.3%2B7_openj9-0.14.3/${JDK_ARCHIVE_NAME}
tar  -C ${BUILD_DIR} -xf ${BUILD_DIR}${JDK_ARCHIVE_NAME}

# list deps modules
${BUILD_DIR}jdk-11.0.3+7/bin/jdeps  --list-deps ./target/jenkinsmonitor-*-jar-with-dependencies.jar

# spezielles jre bauen
${BUILD_DIR}jdk-11.0.3+7/bin/jlink --no-header-files --no-man-pages --compress=2 --strip-debug --add-modules java.base,java.desktop,java.logging,java.management,java.naming,java.security.jgss,java.sql,java.xml --output ${BUILD_DIR}usr

# copy binary
mkdir ${APP_DIR}
cp -Rf ${BUILD_DIR}usr ${APP_DIR}

# download AppImageKit and give permissions und run it
wget -P ${BUILD_DIR}  -c https://github.com/AppImage/AppImageKit/releases/download/12/appimagetool-x86_64.AppImage
chmod +x ${BUILD_DIR}appimagetool-x86_64.AppImage
#wget -P ${BUILD_DIR}  https://github.com/linuxdeploy/linuxdeploy/releases/download/continuous/linuxdeploy-x86_64.AppImage
#chmod +x ${BUILD_DIR}linuxdeploy-x86_64.AppImage


# copy resources at final place and create sym links
cp -a ./target/jenkinsmonitor-*-jar-with-dependencies.jar ${APP_DIR}usr/bin/jenkinsmonitor-jar-with-dependencies.jar
cp -Rf ${RESOURCE_DIR}* ${APP_DIR}
cd "${APP_DIR}"
ln -s ./usr/bin/jenkinsmonitor.wrapper ./AppRun
ln -s ./usr/share/metainfo/com.github.funthomas424242.jenkinsmonitor.metainfo.xml ./usr/share/metainfo/jenkinsmonitor.appdata.xml
ln -s ./usr/share/applications/jenkinsmonitor.desktop ./jenkinsmonitor.desktop
ln -s ./usr/share/icons/theme/320x235/1984EmmanuelGoldstein.png ./usr/share/icons/theme/256x256/1984EmmanuelGoldstein.png
ln -s ./usr/share/icons/theme/320x235/1984EmmanuelGoldstein.png ./1984EmmanuelGoldstein.png
cd "${CUR_DIR}"

#read -p "Press [Enter] Resourcenprüfung jetzt möglich ..."

# loesche altes Image und erstelle neu
rm Jenkins_Monitor*.AppImage
${BUILD_DIR}appimagetool-x86_64.AppImage ${APP_DIR}
#${BUILD_DIR}/linuxdeploy-x86_64.AppImage --appdir ${APP_DIR} -e src/appimage-resources/launch.sh -i src/appimage-resources/1984EmmanuelGoldstein.png -d src/appimage-resources/myapp.desktop  --output appimage

#read -p "Press [Enter] to start remove waste files ..."
#rm ${BUILD_DIR}linuxdeploy*.AppImage
rm -rf ${APP_DIR}*
rm -rf ${APP_DIR}.DirIcon
rm -rf ${BUILD_DIR}usr



