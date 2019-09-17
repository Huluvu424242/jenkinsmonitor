package com.github.funthomas424242.jenkinsmonitor;

/*-
 * #%L
 * jenkinsmonitor Example
 * %%
 * Copyright (C) 2018 - 2019 PIUG
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


class JenkinsMonitorTest {

    protected static final String USER_HOME = "user.home";
    protected static final String JENKINSMONITOR_CONFIGURATIONFILENAME = "jenkinsmonitor.properties";
    protected static final int DEFAULT_POLLPERIOD = 5;

    JenkinsMonitor withoutConfigJenkinsMonitor;
    JenkinsMonitor emptyConfigJenkinsMonitor;
    JenkinsMonitor validConfigJenkinsMonitor;

    @BeforeEach
    void setUpTestfall() {
        final Path withoutConfigfilePath = Paths.get(".", "src/test/resources/xxx_configuration.properties");
        final File withoutConfigFile = withoutConfigfilePath.toAbsolutePath().toFile();
        withoutConfigJenkinsMonitor = new JenkinsMonitor(withoutConfigFile);


        final Path emptyConfigfilePath = Paths.get(".", "src/test/resources/empty_configuration.properties");
        final File emptyConfigFile = emptyConfigfilePath.toAbsolutePath().toFile();
        emptyConfigJenkinsMonitor = new JenkinsMonitor(emptyConfigFile);

        final Path validConfigfilePath = Paths.get(".", "src/test/resources/valid_configuration.properties");
        final File configFile = validConfigfilePath.toAbsolutePath().toFile();
        validConfigJenkinsMonitor = new JenkinsMonitor(configFile);
    }


    @Test
    void usedSpecifiedConfigfile() {
        final JenkinsMonitor jenkinsMonitor = new JenkinsMonitor();

        final File file = jenkinsMonitor.getConfigurationfile();

        final String propertyFilePath = file.getAbsolutePath().toString();
        final String expectedPath = System.getProperty(USER_HOME) + File.separator + JENKINSMONITOR_CONFIGURATIONFILENAME;
        assertEquals(expectedPath, propertyFilePath);
    }

    @Test
    @DisplayName("Prüfe Default Konfiguration wenn kein Configfile existiert")
    void validDefaultsWhenNotExistingConfigfile() {
        final int pollPeriod = this.withoutConfigJenkinsMonitor.getPollPeriod();
        assertEquals(DEFAULT_POLLPERIOD, pollPeriod);
    }

    @Test
    void validDefaultsWithEmptyConfigfile() {
        final int pollPeriod = emptyConfigJenkinsMonitor.getPollPeriod();
        assertEquals(DEFAULT_POLLPERIOD, pollPeriod);
    }

    @Test
    void useSpecifiedPollPeriod() {
        final int pollPeriod = validConfigJenkinsMonitor.getPollPeriod();
        assertEquals(6, pollPeriod);
    }


}
