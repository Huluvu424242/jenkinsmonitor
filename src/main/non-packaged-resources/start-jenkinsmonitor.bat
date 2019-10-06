# startet den jenkinsmonitor
#
# Konfigurationsfile im Home des Nutzers
# ~\jenkinsmonitor.properties
#
# Systemanforderungen: Java 11 muss installiert sein
#
#
start javaw -jar ${project.build.finalName}-jar-with-dependencies.jar
