package com.github.funthomas424242.jenkinsmonitor;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigurationMockOneJobSuccess extends Configuration {
    public static final String PATH_ONEJOB_SUCCESS_CONFIGURATION_FILE = "src/test/resources/onejob-success-configuration.properties";


    public ConfigurationMockOneJobSuccess(){
        super(getConfigFile());
    }

    protected static File getConfigFile() {
        final Path validConfigfilePath = Paths.get(".", PATH_ONEJOB_SUCCESS_CONFIGURATION_FILE);
        return validConfigfilePath.toAbsolutePath().toFile();
    }
}
