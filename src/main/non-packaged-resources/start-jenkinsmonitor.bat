@echo off
@rem  startet den jenkinsmonitor
@rem
@rem Konfigurationsfile im Home des Nutzers
@rem ~\jenkinsmonitor.properties
@rem
@rem Systemanforderungen: Java 11 muss installiert sein
@rem
@rem
@IF EXIST %userprofile%\jenkinsmonitor.properties (
    echo "%userprofile%\jenkinsmonitor.properties exists"
) else (
    copy jenkinsmonitor.properties.template %userprofile%\jenkinsmonitor.properties
)
start usr\bin\javaw.exe -jar usr\bin\jenkinsmonitor-jar-with-dependencies.jar
