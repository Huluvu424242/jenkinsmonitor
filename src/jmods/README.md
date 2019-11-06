```shell
# Herstellung der jmod Dateien für auto module
#
# Gezeigt am Beispiel von commons-lang
#
jdeps --generate-open-module ./jdeps ~/.m2/repository/commons-lang/commons-lang/2.6/commons-lang-2.6.jar
jdeps --generate-module-info ./jdeps ~/.m2/repository/commons-lang/commons-lang/2.6/commons-lang-2.6.jar
unzip  ~/.m2/repository/commons-lang/commons-lang/2.6/commons-lang-2.6.jar -d ./jdeps/commons.lang/classes
javac -d ./jdeps/commons.lang/classes ./jdeps/commons.lang/module-info.java
jmod create --class-path ./jdeps/commons.lang/classes src/jmods/commons-lang-2.6.jmod
```
