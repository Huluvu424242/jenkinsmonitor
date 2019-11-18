@echo off
set ARCH=x86_64
set CUR_DIR=%~dp0
set BUNDLE_DIR=target\AppDir\
set TOOLS_DIR=target\tools\
set RESOURCE_DIR=src\appimage-resources\
set JDK_BINFOLDER=%JAVA_HOME%\bin\

@rem  clear folders
del /S /Q "%BUNDLE_DIR%"*.*
rd /S /Q "%TOOLS_DIR%usr"

@rem  list deps modules
"%JDK_BINFOLDER%jdeps"  --list-deps target\jenkinsmonitor-*-jar-with-dependencies.jar

@rem  spezielles jre bauen
"%JDK_BINFOLDER%jlink" --no-header-files --no-man-pages --compress=2 --strip-debug --add-modules java.base,java.desktop,java.logging,java.management,java.naming,java.security.jgss,java.sql,java.xml --output "%TOOLS_DIR%usr"

@rem  copy binary
mkdir "%BUNDLE_DIR%usr"
xcopy /Y /E  "%TOOLS_DIR%usr" "%BUNDLE_DIR%usr"

 @rem  copy resources at final place
copy  target\jenkinsmonitor-*-jar-with-dependencies.jar %BUNDLE_DIR%usr\bin\jenkinsmonitor-jar-with-dependencies.jar
xcopy /Y /E  "%RESOURCE_DIR%"*.* "%BUNDLE_DIR%"

  @rem  create sym links
  cd "%BUNDLE_DIR%"
  @rem ln -s ./usr/bin/jenkinsmonitor.wrapper ./AppRun
  @rem ln -s ./usr/share/metainfo/com.github.funthomas424242.jenkinsmonitor.metainfo.xml ./usr/share/metainfo/jenkinsmonitor.appdata.xml
  @rem ln -s ./usr/share/applications/jenkinsmonitor.desktop ./jenkinsmonitor.desktop
  @rem ln -s ./usr/share/icons/theme/320x235/1984EmmanuelGoldstein.png ./usr/share/icons/theme/256x256/1984EmmanuelGoldstein.png
  @rem ln -s ./usr/share/icons/theme/320x235/1984EmmanuelGoldstein.png ./1984EmmanuelGoldstein.png
  cd "%CUR_DIR%"

  @rem  download AppImageKit and give permissions und run it
  @rem wget -P %TOOLS_DIR%  -c https://github.com/AppImage/AppImageKit/releases/download/12/appimagetool-x86_64.AppImage
  @rem chmod +x %TOOLS_DIR%appimagetool-x86_64.AppImage
  @rem wget -P %TOOLS_DIR%  https://github.com/linuxdeploy/linuxdeploy/releases/download/continuous/linuxdeploy-x86_64.AppImage
  @rem chmod +x %TOOLS_DIR%linuxdeploy-x86_64.AppImage


  @rem read -p "Press [Enter] Resourcenprüfung jetzt möglich ..."

  @rem  loesche altes Image und erstelle neu
  @rem del /S /Q "%CUR_DIR%target\Jenkins_Monitor*.AppImage"
  @rem %TOOLS_DIR%appimagetool-x86_64.AppImage %BUNDLE_DIR%
  @rem %TOOLS_DIR%/linuxdeploy-x86_64.AppImage --appdir %BUNDLE_DIR% -e src/appimage-resources/launch.sh -i src/appimage-resources/1984EmmanuelGoldstein.png -d src/appimage-resources/myapp.desktop  --output appimage
  @rem mv Jenkins_Monitor*.AppImage %CUR_DIR%target

  @rem  Bereinigung
  del /S /Q "%BUNDLE_DIR%.DirIcon"


@rem read -p "Press [Enter] to start @remove waste files ..."
@rem del /S /Q "%TOOLS_DIR%linuxdeploy*.AppImage"



