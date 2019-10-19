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

import com.github.funthomas424242.jenkinsmonitor.jenkins.BasicAuthDaten;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobBeschreibung;
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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class ConfigurationTest {

    protected static final String USER_HOME = "user.home";
    protected static final String Configuration_CONFIGURATIONFILENAME = "jenkinsmonitor.properties";
    protected static final int DEFAULT_POLLPERIOD = 5;

    protected static Logger LOG = LoggerFactory.getLogger(ConfigurationTest.class);

    protected Configuration notexistingConfigurationfile;
    protected Configuration emptyConfigurationfile;
    protected Configuration validConfigurationfile;

    @BeforeAll
    protected static void setUpAll() {
        final Path defaultConfigfilePath = Paths.get(System.getProperty(USER_HOME) + File.separator + Configuration_CONFIGURATIONFILENAME);
        final File defaultConfigfile = defaultConfigfilePath.toFile();
        if (defaultConfigfile != null && defaultConfigfile.exists()) {
            final long timeStamp = new Date().getTime();
            final File newFile = new File(defaultConfigfilePath.toAbsolutePath().toString() + "-old-" + timeStamp);
            defaultConfigfile.renameTo(newFile);
            LOG.debug("### Bereinige Testumgebung: Configfile renamed from " + defaultConfigfile.getAbsolutePath().toString() + " zu "
                + newFile.getAbsolutePath().toString());
        }
    }

    @BeforeEach
    protected void setUp() {
        notexistingConfigurationfile = new ConfigurationMockNoExisting();
        emptyConfigurationfile = new ConfigurationMockEmpty();
        validConfigurationfile = new ConfigurationMockValidTwoJobs();
    }

    @Test
    @DisplayName("Nach Lesen der Pollperiod ist die Configuration initialisiert")
    protected void initAfterGetPollOK() {
        final Configuration configuration = new ConfigurationMockEmpty();
        assumeFalse(configuration.isInitialisiert);
        configuration.getPollPeriodInSecond();
        assertTrue(configuration.isInitialisiert);
    }

    @Test
    @DisplayName("Nach Lesen der JobBeschreibungen ist die Configuration initialisiert")
    protected void initAfterGetJobBeschreibungenOK() {
        final Configuration configuration = new ConfigurationMockEmpty();
        assumeFalse(configuration.isInitialisiert);
        configuration.getJobBeschreibungen();
        assertTrue(configuration.isInitialisiert);
    }


    @Test
    @DisplayName("Die Default Konfiguration wird aus ~/jenkinsmonitor.properties geladen")
    protected void useDefaultConfigfile() {
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
    protected void validDefaultsWhenNotExistingConfigfile() {
        final long pollPeriodInSecond = this.notexistingConfigurationfile.getPollPeriodInSecond();
        assertEquals(DEFAULT_POLLPERIOD, pollPeriodInSecond);

        final JobBeschreibung[] jobBeschreibungen = this.notexistingConfigurationfile.getJobBeschreibungen();
        assertNotNull(jobBeschreibungen);
        assertEquals(0, jobBeschreibungen.length);
    }

    @Test
    @DisplayName("Prüfe Default Konfiguration wenn das Configfile leer ist")
    protected void validDefaultsWithEmptyConfigfile() {
        final long pollPeriodInSecond = emptyConfigurationfile.getPollPeriodInSecond();
        assertEquals(DEFAULT_POLLPERIOD, pollPeriodInSecond);

        final JobBeschreibung[] jobBeschreibungen = this.emptyConfigurationfile.getJobBeschreibungen();
        assertNotNull(jobBeschreibungen);
        assertEquals(0, jobBeschreibungen.length);
    }

    @Test
    @DisplayName("Prüfe auf die im Konfigfile hinterlegten Werte")
    protected void useDefaultPollPeriod() {
        final long pollPeriodInSecond = validConfigurationfile.getPollPeriodInSecond();
        assertEquals(6, pollPeriodInSecond);

        final JobBeschreibung[] jobBeschreibungen = this.validConfigurationfile.getJobBeschreibungen();
        assertNotNull(jobBeschreibungen);
        assertEquals(2, jobBeschreibungen.length);
    }

    @Test
    @DisplayName("Prüfe auf die im Konfigfile hinterlegten Werte")
    protected void useUserNameFromConfigfile() {
        final JobBeschreibung[] jobBeschreibungen = validConfigurationfile.getJobBeschreibungen();
        assertNotNull(jobBeschreibungen);
        assertEquals(2, jobBeschreibungen.length);
        assertNotNull(jobBeschreibungen[0].getJobAbfragedaten());
        assertEquals(
            new BasicAuthDaten("admin", "streng geheim").getBasicAuthToken("streng geheim"),
            jobBeschreibungen[0].getJobAbfragedaten().getBasicAuthToken());
    }

    @Test
    @DisplayName("Prüfe auf gleiche Werte bei reload aus Configfile")
    protected void reloadCurrentConfiguration() {
        final long pollPeriodInSecond1 = validConfigurationfile.getPollPeriodInSecond();
        final JobBeschreibung[] jobBeschreibungen1 = this.validConfigurationfile.getJobBeschreibungen();
        assumeTrue(pollPeriodInSecond1 == 6);
        assumeTrue(jobBeschreibungen1 != null);
        assumeTrue(jobBeschreibungen1.length == 2);
        validConfigurationfile.reload();
        final long pollPeriodInSecond2 = validConfigurationfile.getPollPeriodInSecond();
        final JobBeschreibung[] jobBeschreibungen2 = this.validConfigurationfile.getJobBeschreibungen();
        assumeTrue(pollPeriodInSecond2 == 6);
        assumeTrue(jobBeschreibungen2 != null);
        assumeTrue(jobBeschreibungen2.length == 2);
        //
        assertEquals(pollPeriodInSecond1, pollPeriodInSecond2);
        assertArrayEquals(jobBeschreibungen1, jobBeschreibungen2);
    }

    @Test
    @DisplayName("Prüfe auf neue Werte bei reload aus anderem Configfile")
    protected void reloadOtherConfiguration() {
        final File emptyConfigFile = new ConfigurationMockEmpty().getConfigurationfile();
        final Configuration tmpConfiguration = new Configuration(emptyConfigFile);
        assumeTrue(tmpConfiguration != null);
        final JobBeschreibung[] jobBeschreibungenenLeer = tmpConfiguration.getJobBeschreibungen();
        assumeTrue(jobBeschreibungenenLeer != null);
        assumeTrue(jobBeschreibungenenLeer.length == 0);

        final File configFile = new ConfigurationMockValidTwoJobs().getConfigurationfile();
        tmpConfiguration.reloadFromFile(configFile);
        final JobBeschreibung[] jobBeschreibungenGefuellt = tmpConfiguration.getJobBeschreibungen();
        assertNotNull(jobBeschreibungenGefuellt);
        assertEquals(2, jobBeschreibungenGefuellt.length);
    }


}
