package com.github.funthomas424242.jenkinsmonitor;

/*-
 * #%L
 * Jenkins Monitor
 * %%
 * Copyright (C) 2019 PIUG
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigurationTest {

    protected static final String USER_HOME = "user.home";
    protected static final String Configuration_CONFIGURATIONFILENAME = "jenkinsmonitor.properties";
    protected static final int DEFAULT_POLLPERIOD = 5;

    Configuration withoutConfigConfiguration;
    Configuration emptyConfigConfiguration;
    Configuration validConfigConfiguration;

    @BeforeEach
    void setUpTestfall() {
        final Path withoutConfigfilePath = Paths.get(".", "src/test/resources/xxx_configuration.properties");
        final File withoutConfigFile = withoutConfigfilePath.toAbsolutePath().toFile();
        withoutConfigConfiguration = new Configuration(withoutConfigFile);


        final Path emptyConfigfilePath = Paths.get(".", "src/test/resources/empty_configuration.properties");
        final File emptyConfigFile = emptyConfigfilePath.toAbsolutePath().toFile();
        emptyConfigConfiguration = new Configuration(emptyConfigFile);

        final Path validConfigfilePath = Paths.get(".", "src/test/resources/valid_configuration.properties");
        final File configFile = validConfigfilePath.toAbsolutePath().toFile();
        validConfigConfiguration = new Configuration(configFile);
    }


    @Test
    void usedSpecifiedConfigfile() {
        final Configuration Configuration = new Configuration();

        final File file = Configuration.getConfigurationfile();

        final String propertyFilePath = file.getAbsolutePath().toString();
        final String expectedPath = System.getProperty(USER_HOME) + File.separator + Configuration_CONFIGURATIONFILENAME;
        assertEquals(expectedPath, propertyFilePath);
    }

    @Test
    @DisplayName("Prüfe Default Konfiguration wenn kein Configfile existiert")
    void validDefaultsWhenNotExistingConfigfile() {
        final int pollPeriod = this.withoutConfigConfiguration.getPollPeriod();
        assertEquals(DEFAULT_POLLPERIOD, pollPeriod);
    }

    @Test
    void validDefaultsWithEmptyConfigfile() {
        final int pollPeriod = emptyConfigConfiguration.getPollPeriod();
        assertEquals(DEFAULT_POLLPERIOD, pollPeriod);
    }

    @Test
    void useSpecifiedPollPeriod() {
        final int pollPeriod = validConfigConfiguration.getPollPeriod();
        assertEquals(6, pollPeriod);
    }


}
