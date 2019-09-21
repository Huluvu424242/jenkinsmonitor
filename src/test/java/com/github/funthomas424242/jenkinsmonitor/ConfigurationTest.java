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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class ConfigurationTest {

    protected static final String USER_HOME = "user.home";
    protected static final String Configuration_CONFIGURATIONFILENAME = "jenkinsmonitor.properties";
    protected static final int DEFAULT_POLLPERIOD = 5;
    public static final String PATH_VALID_CONFIGURATION_FILE = "src/test/resources/valid_configuration.properties";
    public static final String PATH_EMPTY_CONFIGURATION_FILE = "src/test/resources/empty_configuration.properties";
    public static final String PATH_NOTEXISTING_CONFIGURATION_FILE = "src/test/resources/xxx_configuration.properties";

    Configuration notexistingConfigurationfile;
    Configuration emptyConfigurationfile;
    Configuration validConfigurationfile;

    @BeforeEach
    void init() {
        final Path withoutConfigfilePath = Paths.get(".", PATH_NOTEXISTING_CONFIGURATION_FILE);
        final File withoutConfigFile = withoutConfigfilePath.toAbsolutePath().toFile();
        notexistingConfigurationfile = new Configuration(withoutConfigFile);


        final Path emptyConfigfilePath = Paths.get(".", PATH_EMPTY_CONFIGURATION_FILE);
        final File emptyConfigFile = emptyConfigfilePath.toAbsolutePath().toFile();
        emptyConfigurationfile = new Configuration(emptyConfigFile);

        final Path validConfigfilePath = Paths.get(".", PATH_VALID_CONFIGURATION_FILE);
        final File configFile = validConfigfilePath.toAbsolutePath().toFile();
        validConfigurationfile = new Configuration(configFile);
    }


    @Test
    @DisplayName("Die Default Konfiguration wird aus ~/jenkinsmonitor.properties geladen")
    void useDefaultConfigfile() {
        final Configuration configuration = new Configuration();
        assumeTrue(configuration != null);
        final File file = configuration.getConfigurationfile();

        final String configurationsFilePath = file.getAbsolutePath().toString();
        final String expectedPath = System.getProperty(USER_HOME) + File.separator + Configuration_CONFIGURATIONFILENAME;
        assertEquals(expectedPath, configurationsFilePath);
    }

    @Test
    @DisplayName("Prüfe Default Konfiguration wenn kein Configfile existiert")
    void validDefaultsWhenNotExistingConfigfile() {
        final int pollPeriodInSecond = this.notexistingConfigurationfile.getPollPeriodInSecond();
        assertEquals(DEFAULT_POLLPERIOD, pollPeriodInSecond);

        final JobBeschreibung[] jobBeschreibungen = this.notexistingConfigurationfile.getJobBeschreibungen();
        assertNotNull(jobBeschreibungen);
        assertEquals(0, jobBeschreibungen.length);
    }

    @Test
    @DisplayName("Prüfe Default Konfiguration wenn das Configfile leer ist")
    void validDefaultsWithEmptyConfigfile() {
        final int pollPeriodInSecond = emptyConfigurationfile.getPollPeriodInSecond();
        assertEquals(DEFAULT_POLLPERIOD, pollPeriodInSecond);

        final JobBeschreibung[] jobBeschreibungen = this.emptyConfigurationfile.getJobBeschreibungen();
        assertNotNull(jobBeschreibungen);
        assertEquals(0, jobBeschreibungen.length);
    }

    @Test
    @DisplayName("Prüfe auf die im Konfigfile hinterlegten Werte")
    void useDefaultPollPeriod() {
        final int pollPeriodInSecond = validConfigurationfile.getPollPeriodInSecond();
        assertEquals(6, pollPeriodInSecond);

        final JobBeschreibung[] jobBeschreibungen = this.validConfigurationfile.getJobBeschreibungen();
        assertNotNull(jobBeschreibungen);
        assertEquals(2, jobBeschreibungen.length);
    }


    @Test
    @DisplayName("Prüfe auf die im Konfigfile hinterlegten Werte")
    void reloadCurrentConfiguration() {
        final Path emptyConfigfilePath = Paths.get(".", PATH_EMPTY_CONFIGURATION_FILE);
        final File emptyConfigFile = emptyConfigfilePath.toAbsolutePath().toFile();
        final Configuration tmpConfigurationfile = new Configuration(emptyConfigFile);
        assumeTrue(tmpConfigurationfile != null);
        final JobBeschreibung[] jobBeschreibungenenLeer = tmpConfigurationfile.getJobBeschreibungen();
        assumeTrue(jobBeschreibungenenLeer != null);
        assumeTrue(jobBeschreibungenenLeer.length==0);

        final Path validConfigfilePath = Paths.get(".", PATH_VALID_CONFIGURATION_FILE);
        final File configFile = validConfigfilePath.toAbsolutePath().toFile();
        tmpConfigurationfile.reload(configFile);
        final JobBeschreibung[] jobBeschreibungenGefuellt = tmpConfigurationfile.getJobBeschreibungen();
        assertNotNull(jobBeschreibungenGefuellt);
        assertEquals(2, jobBeschreibungenGefuellt.length);
    }


}
