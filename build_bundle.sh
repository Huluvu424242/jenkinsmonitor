#!/bin/bash
jlink --module-path "output;$JAVA_HOME/jmods" --add-modules com.github.funthomas424242.jenkinsmonitor --output laufZeitImage
