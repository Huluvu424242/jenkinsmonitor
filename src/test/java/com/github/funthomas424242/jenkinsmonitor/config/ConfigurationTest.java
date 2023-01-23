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

import com.github.funthomas424242.jenkinsmonitor.etc.JavaSystemWrapper;
import com.github.funthomas424242.jenkinsmonitor.jenkins.BasicAuthDaten;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobBeschreibungen;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.funthomas424242.jenkinsmonitor.config.ConfigurationFluentGrammar.Created;
import static com.github.funthomas424242.jenkinsmonitor.config.ConfigurationFluentGrammar.Loaded;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class ConfigurationTest {

    protected static final String USER_HOME = "user.home";
    protected static final String Configuration_CONFIGURATIONFILENAME = "jenkinsmonitor.properties";
    protected static final int DEFAULT_POLLPERIOD = 5;

    protected static Logger LOG = LoggerFactory.getLogger(ConfigurationTest.class);

    protected Created notexistingConfigurationfile;
    protected Created emptyConfigurationfile;
    protected Created validConfigurationfile;

    @BeforeAll
    protected static void setUpAll() {
        final Path defaultConfigfilePath = Paths.get(System.getProperty(USER_HOME) + File.separator + Configuration_CONFIGURATIONFILENAME);
        final File defaultConfigfile = defaultConfigfilePath.toFile();
        if (defaultConfigfile.exists()) {
            final long timeStamp = new Date().getTime();
            final File newFile = new File(defaultConfigfilePath.toAbsolutePath().toString() + "-old-" + timeStamp);
            defaultConfigfile.renameTo(newFile);
            LOG.debug("### Bereinige Testumgebung: Configfile renamed from " + defaultConfigfile.getAbsolutePath().toString() + " zu "
                    + newFile.getAbsolutePath().toString());
        }
    }

    @BeforeEach
    protected void setUp() {
        notexistingConfigurationfile = ConfigurationMockNoExisting.getOrCreateInstance();
        emptyConfigurationfile = ConfigurationMockEmpty.getOrCreateInstance();
        validConfigurationfile = ConfigurationMockValidTwoJobs.getOrCreateInstance();
    }

    @AfterEach
    protected void tearDown() {
        Configuration.setJavaSysteMock(null);
    }

    @Test
    @DisplayName("Nach Lesen der Pollperiod ist die Configuration initialisiert")
    protected void initAfterGetPollOK() {
        final Created config = ConfigurationMockEmpty.getOrCreateInstance();
        assumeFalse(config.isInitialisiert());
        final Loaded configuration = config.reload();
        assumeTrue(config.isInitialisiert());
        final long pollPeriod = configuration.getPollPeriodInSecond();
        // TODO
        assertTrue(configuration.isInitialisiert());
    }

    @Test
    @DisplayName("Nach Lesen der JobBeschreibungen ist die Configuration initialisiert")
    protected void initAfterGetJobBeschreibungenOK() {
        final Created config = ConfigurationMockEmpty.getOrCreateInstance();
        assumeFalse(config.isInitialisiert());
        final Loaded configuration = config.reload();
        assumeTrue(configuration.isInitialisiert());
        final JobBeschreibungen jobBeschreibungen = configuration.getJobBeschreibungen();
        // TODO
        assertTrue(configuration.isInitialisiert());
    }


    @Test
    @DisplayName("Die Default Konfiguration wird aus ~/jenkinsmonitor.properties geladen")
    protected void useDefaultConfigfile() {
        final String tmpDir = new File("src/test/resources/").getAbsolutePath().toString();
        System.out.println("TMP DIR: " + tmpDir);
        final JavaSystemWrapper.JavaSystemMock mock = new JavaSystemWrapper.JavaSystemMock() {

            @Override
            public String getProperty(String name) {
                if ("user.home".equals(name)) {
                    return tmpDir;
                } else {
                    return null;
                }
            }
        };

        assertDoesNotThrow(() -> {
            Configuration.setJavaSysteMock(mock);
            final File defaultConfigurationsfile = Configuration.getDefaultConfigurationsfile();
            final Created configuration = Configuration.getOrCreateInstance(defaultConfigurationsfile);
            assumeTrue(configuration != null);
            final File file = configuration.getConfigurationfile();

            final String configurationsFilePath = file.getAbsolutePath().toString();
            final String expectedPath = tmpDir + File.separator + Configuration_CONFIGURATIONFILENAME;
            assertEquals(expectedPath, configurationsFilePath);
        });
    }

    @Test
    @DisplayName("Die Default Konfiguration wird aus ${HOMESHARE}/jenkinsmonitor.properties geladen")
    protected void useSharedDefaultConfigfile() throws NoSuchFieldException {

        final String tmpDir = new File("src/test/resources/").getAbsolutePath().toString();
        System.out.println("TMP DIR: " + tmpDir);
        final JavaSystemWrapper.JavaSystemMock mock = new JavaSystemWrapper.JavaSystemMock() {

            @Override
            public String getenv(String name) {
                if ("HOMESHARE".equals(name)) {
                    return tmpDir;
                } else {
                    return null;
                }
            }
        };

        assertDoesNotThrow(() -> {
            Configuration.setJavaSysteMock(mock);
            final File defaultConfigurationsfile = Configuration.getDefaultConfigurationsfile();
            final Created configuration = Configuration.getOrCreateInstance(defaultConfigurationsfile);
            assumeTrue(configuration != null);
            final File file = configuration.getConfigurationfile();

            final String configurationsFilePath = file.getAbsolutePath().toString();
            final String expectedPath = tmpDir + File.separator + Configuration_CONFIGURATIONFILENAME;
            assertEquals(expectedPath, configurationsFilePath);
        });
    }


    @Test
    @DisplayName("Prüfe Default Konfiguration wenn kein Configfile existiert")
    protected void validDefaultsWhenNotExistingConfigfile() {
        final long pollPeriodInSecond = this.notexistingConfigurationfile.reload().getPollPeriodInSecond();
        assertEquals(DEFAULT_POLLPERIOD, pollPeriodInSecond);

        final JobBeschreibungen jobBeschreibungen = this.notexistingConfigurationfile.reload().getJobBeschreibungen();
        assertNotNull(jobBeschreibungen);
        assertEquals(0, jobBeschreibungen.size());
    }

    @Test
    @DisplayName("Prüfe Default Konfiguration wenn das Configfile leer ist")
    protected void validDefaultsWithEmptyConfigfile() {
        final long pollPeriodInSecond = emptyConfigurationfile.reload().getPollPeriodInSecond();
        assertEquals(DEFAULT_POLLPERIOD, pollPeriodInSecond);

        final JobBeschreibungen jobBeschreibungen = this.emptyConfigurationfile.reload().getJobBeschreibungen();
        assertNotNull(jobBeschreibungen);
        assertEquals(0, jobBeschreibungen.size());
    }

    @Test
    @DisplayName("Prüfe auf die im Konfigfile hinterlegten Werte")
    protected void useDefaultPollPeriod() {
        final long pollPeriodInSecond = validConfigurationfile.reload().getPollPeriodInSecond();
        assertEquals(6, pollPeriodInSecond);

        final JobBeschreibungen jobBeschreibungen = this.validConfigurationfile.reload().getJobBeschreibungen();
        assertNotNull(jobBeschreibungen);
        assertEquals(2, jobBeschreibungen.size());
    }

    @Test
    @DisplayName("Prüfe auf die im Konfigfile hinterlegten Werte")
    protected void useUserNameFromConfigfile() {
        final JobBeschreibungen jobBeschreibungen = validConfigurationfile.reload().getJobBeschreibungen();
        assertNotNull(jobBeschreibungen);
        assertEquals(2, jobBeschreibungen.size());
        assertNotNull(jobBeschreibungen.get("multibranch1#http://localhost:8099/job/multibranchjobred/job/master").getJobAbfragedaten());
        assertEquals(
                new BasicAuthDaten("admin", "streng geheim").getBasicAuthToken("streng geheim"),
                jobBeschreibungen.get("multibranch1#http://localhost:8099/job/multibranchjobred/job/master").getJobAbfragedaten().getBasicAuthToken());
    }

    @Test
    @DisplayName("Prüfe auf gleiche Werte bei reload aus Configfile")
    protected void reloadCurrentConfiguration() {
        final long pollPeriodInSecond1 = validConfigurationfile.reload().getPollPeriodInSecond();
        final JobBeschreibungen jobBeschreibungen1 = this.validConfigurationfile.reload().getJobBeschreibungen();
        assumeTrue(pollPeriodInSecond1 == 6);
        assumeTrue(jobBeschreibungen1 != null);
        assumeTrue(jobBeschreibungen1.size() == 2);
        validConfigurationfile.reload();
        final long pollPeriodInSecond2 = validConfigurationfile.reload().getPollPeriodInSecond();
        final JobBeschreibungen jobBeschreibungen2 = this.validConfigurationfile.reload().getJobBeschreibungen();
        assumeTrue(pollPeriodInSecond2 == 6);
        assumeTrue(jobBeschreibungen2 != null);
        assumeTrue(jobBeschreibungen2.size() == 2);
        //
        assertEquals(pollPeriodInSecond1, pollPeriodInSecond2);
        assertEquals(jobBeschreibungen1, jobBeschreibungen2);
    }

    @Test
    @DisplayName("Prüfe auf neue Werte bei reload aus anderem Configfile")
    protected void reloadOtherConfiguration() {
        final File emptyConfigFile = ConfigurationMockEmpty.getOrCreateInstance().getConfigurationfile();
        final Loaded tmpConfiguration = Configuration.getOrCreateInstance(emptyConfigFile).reload();
        assumeTrue(tmpConfiguration != null);
        final JobBeschreibungen jobBeschreibungenenLeer = tmpConfiguration.getJobBeschreibungen();
        assumeTrue(jobBeschreibungenenLeer != null);
        assumeTrue(jobBeschreibungenenLeer.size() == 0);

        final File configFile = ConfigurationMockValidTwoJobs.getOrCreateInstance().getConfigurationfile();
        tmpConfiguration.reloadFromFile(configFile);
        final JobBeschreibungen jobBeschreibungenGefuellt = tmpConfiguration.getJobBeschreibungen();
        assertNotNull(jobBeschreibungenGefuellt);
        assertEquals(2, jobBeschreibungenGefuellt.size());
    }


}
