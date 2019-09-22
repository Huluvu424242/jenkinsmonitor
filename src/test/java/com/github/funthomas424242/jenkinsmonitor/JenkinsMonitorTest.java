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

import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Tag("headfull")
class JenkinsMonitorTest {

    @Test
    @DisplayName("JenkinsMonitor ist Hauptklasse und enthält eine main Methode.")
    protected void checkMainMethod() {
        final String[] dummyArgs = {"Hallo", "Du da"};
        Assertions.assertDoesNotThrow(() -> {
            JenkinsMonitor.main(dummyArgs);
        });

    }

    @Test
    @DisplayName("JenkinsMonitor ist Hauptklasse und enthält eine main Methode die auch ohne Argumente aufgerufen werden kann.")
    protected void checkMainEmptyParaMethod() {
        Assertions.assertDoesNotThrow(() -> {
            JenkinsMonitor.main(null);
        });

    }


    @Test
    @DisplayName("JenkinsMonitor besitzt eine Tray Instanz nach seiner Erzeugung")
    protected void checkTrayInstanz() {
        final JenkinsMonitor monitor = new JenkinsMonitor();
        assumeTrue(monitor!=null);
        assertNotNull(monitor.monitorTray);
    }

    @Test
    @DisplayName("Initiale Konfiguration enthält keine JobBeschribungen")
    protected void initialConfigWithEmptyJobs() {
        final JenkinsMonitor jenkinsMonitor = new JenkinsMonitor(new ConfigurationMockNoExisting());
        assumeTrue(jenkinsMonitor.monitorTray != null);
        assumeTrue(jenkinsMonitor.monitorTray.getConfiguration() != null);
        assertEquals(0, jenkinsMonitor.monitorTray.getConfiguration().getJobBeschreibungen().length);
    }

    @Test
    @DisplayName("Tray Konfiguration ist identisch zur der dem JenkinsMonitor übergebenen")
    protected void equalsConfigWithTrayAndMonitor() {
        final Configuration config = new ConfigurationMockValidTwoJobs();
        final JenkinsMonitor jenkinsMonitor = new JenkinsMonitor(config);
        assertSame(config, jenkinsMonitor.monitorTray.getConfiguration());
    }

    @Test
    @DisplayName("Nach Erzeugung besitzt der JenkinsMonitor ein Tray Icon")
    protected void afterInitTrayIconExists() {
        final Configuration config = new ConfigurationMockValidTwoJobs();
        final JenkinsMonitor jenkinsMonitor = new JenkinsMonitor(config);
        assertNotNull(jenkinsMonitor.monitorTray.getTrayIcon());
    }

}
