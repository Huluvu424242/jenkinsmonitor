package com.github.funthomas424242.jenkinsmonitor.config;

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

import com.github.funthomas424242.jenkinsmonitor.jenkins.JenkinsJobBeschreibung;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class ConfigurationTest {

    protected static final String USER_HOME = "user.home";
    protected static final String Configuration_CONFIGURATIONFILENAME = "jenkinsmonitor.properties";
    protected static final int DEFAULT_POLLPERIOD = 5;

    protected static Logger LOG = LoggerFactory.getLogger(ConfigurationTest.class);

    Configuration notexistingConfigurationfile;
    Configuration emptyConfigurationfile;
    Configuration validConfigurationfile;

    @BeforeAll
    static void setUpAll() {
        final Path defaultConfigfilePath = Paths.get(System.getProperty(USER_HOME) + File.separator + Configuration_CONFIGURATIONFILENAME);
        final File defaultConfigfile = defaultConfigfilePath.toFile();
        if (defaultConfigfile != null && defaultConfigfile.exists()) {
            final long timeStamp = new Date().getTime();
            final File newFile = new File(defaultConfigfilePath.toAbsolutePath().toString() + "-old-" + timeStamp);
            final boolean ok = defaultConfigfile.renameTo(newFile);
            LOG.debug("### Bereinige Testumgebung: Configfile renamed from " + defaultConfigfile.getAbsolutePath().toString() + " zu "
                + newFile.getAbsolutePath().toString());
        }
    }

    @BeforeEach
    void setUp() {
        notexistingConfigurationfile = new ConfigurationMockNoExisting();
        emptyConfigurationfile = new ConfigurationMockEmpty();
        validConfigurationfile = new ConfigurationMockValidTwoJobs();
    }

    @Test
    @DisplayName("Nach Lesen der Pollperiod ist die Configuration initialisiert")
    void initAfterGetPollOK() {
        final Configuration configuration = new ConfigurationMockEmpty();
        assumeFalse(configuration.isInitialisiert);
        configuration.getPollPeriodInSecond();
        assertTrue(configuration.isInitialisiert);
    }

    @Test
    @DisplayName("Nach Lesen der JobBeschreibungen ist die Configuration initialisiert")
    void initAfterGetJobBeschreibungenOK() {
        final Configuration configuration = new ConfigurationMockEmpty();
        assumeFalse(configuration.isInitialisiert);
        configuration.getJobBeschreibungen();
        assertTrue(configuration.isInitialisiert);
    }


    @Test
    @DisplayName("Die Default Konfiguration wird aus ~/jenkinsmonitor.properties geladen")
    void useDefaultConfigfile() {
        assertDoesNotThrow(() -> {
            final File defaultConfigurationsfile = Configuration.getDefaultConfigurationsfile();
            final Configuration configuration = new Configuration(defaultConfigurationsfile);
            assumeTrue(configuration != null);
            final File file = configuration.getConfigurationfile();

            final String configurationsFilePath = file.getAbsolutePath().toString();
            final String expectedPath = System.getProperty(USER_HOME) + File.separator + Configuration_CONFIGURATIONFILENAME;
            assertEquals(expectedPath, configurationsFilePath);
        });
    }


    @Test
    @DisplayName("Prüfe Default Konfiguration wenn kein Configfile existiert")
    void validDefaultsWhenNotExistingConfigfile() {
        final int pollPeriodInSecond = this.notexistingConfigurationfile.getPollPeriodInSecond();
        assertEquals(DEFAULT_POLLPERIOD, pollPeriodInSecond);

        final JenkinsJobBeschreibung[] jenkinsJobBeschreibungen = this.notexistingConfigurationfile.getJobBeschreibungen();
        assertNotNull(jenkinsJobBeschreibungen);
        assertEquals(0, jenkinsJobBeschreibungen.length);
    }

    @Test
    @DisplayName("Prüfe Default Konfiguration wenn das Configfile leer ist")
    void validDefaultsWithEmptyConfigfile() {
        final int pollPeriodInSecond = emptyConfigurationfile.getPollPeriodInSecond();
        assertEquals(DEFAULT_POLLPERIOD, pollPeriodInSecond);

        final JenkinsJobBeschreibung[] jenkinsJobBeschreibungen = this.emptyConfigurationfile.getJobBeschreibungen();
        assertNotNull(jenkinsJobBeschreibungen);
        assertEquals(0, jenkinsJobBeschreibungen.length);
    }

    @Test
    @DisplayName("Prüfe auf die im Konfigfile hinterlegten Werte")
    void useDefaultPollPeriod() {
        final int pollPeriodInSecond = validConfigurationfile.getPollPeriodInSecond();
        assertEquals(6, pollPeriodInSecond);

        final JenkinsJobBeschreibung[] jenkinsJobBeschreibungen = this.validConfigurationfile.getJobBeschreibungen();
        assertNotNull(jenkinsJobBeschreibungen);
        assertEquals(2, jenkinsJobBeschreibungen.length);
    }

    @Test
    @DisplayName("Prüfe auf gleiche Werte bei reload aus Configfile")
    void reloadCurrentConfiguration() {
        final int pollPeriodInSecond1 = validConfigurationfile.getPollPeriodInSecond();
        final JenkinsJobBeschreibung[] jenkinsJobBeschreibungen1 = this.validConfigurationfile.getJobBeschreibungen();
        assumeTrue(pollPeriodInSecond1 == 6);
        assumeTrue(jenkinsJobBeschreibungen1 != null);
        assumeTrue(jenkinsJobBeschreibungen1.length == 2);
        validConfigurationfile.reload();
        final int pollPeriodInSecond2 = validConfigurationfile.getPollPeriodInSecond();
        final JenkinsJobBeschreibung[] jenkinsJobBeschreibungen2 = this.validConfigurationfile.getJobBeschreibungen();
        assumeTrue(pollPeriodInSecond2 == 6);
        assumeTrue(jenkinsJobBeschreibungen2 != null);
        assumeTrue(jenkinsJobBeschreibungen2.length == 2);
        //
        assertEquals(pollPeriodInSecond1, pollPeriodInSecond2);
        assertArrayEquals(jenkinsJobBeschreibungen1, jenkinsJobBeschreibungen2);
    }

    @Test
    @DisplayName("Prüfe auf neue Werte bei reload aus anderem Configfile")
    void reloadOtherConfiguration() {
        final File emptyConfigFile = new ConfigurationMockEmpty().getConfigurationfile();
        final Configuration tmpConfiguration = new Configuration(emptyConfigFile);
        assumeTrue(tmpConfiguration != null);
        final JenkinsJobBeschreibung[] jenkinsJobBeschreibungenenLeer = tmpConfiguration.getJobBeschreibungen();
        assumeTrue(jenkinsJobBeschreibungenenLeer != null);
        assumeTrue(jenkinsJobBeschreibungenenLeer.length == 0);

        final File configFile = new ConfigurationMockValidTwoJobs().getConfigurationfile();
        tmpConfiguration.reloadFromFile(configFile);
        final JenkinsJobBeschreibung[] jenkinsJobBeschreibungenGefuellt = tmpConfiguration.getJobBeschreibungen();
        assertNotNull(jenkinsJobBeschreibungenGefuellt);
        assertEquals(2, jenkinsJobBeschreibungenGefuellt.length);
    }


}
