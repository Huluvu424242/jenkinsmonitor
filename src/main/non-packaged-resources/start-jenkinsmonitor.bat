rem  startet den jenkinsmonitor
rem
rem Konfigurationsfile im Home des Nutzers
rem ~\jenkinsmonitor.properties
rem
rem Systemanforderungen: Java 11 muss installiert sein
rem
rem
start javaw.exe^
        -Dlogback.configurationFile=filelogging.xml
        -jar ${project.build.finalName}-jar-with-dependencies.jar
