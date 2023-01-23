package com.github.funthomas424242.jenkinsmonitor.gui;

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

import com.github.funthomas424242.jenkinsmonitor.JenkinsMonitor;
import com.github.funthomas424242.jenkinsmonitor.config.Configuration;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationFluentGrammar;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationFluentGrammar.Created;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationFluentGrammar.Loaded;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockEmpty;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockNoExisting;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockOneJobFailed;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockOneJobSuccess;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockValidTwoJobs;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JenkinsAPIMock;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatus;
import com.github.tomakehurst.wiremock.WireMockServer;
import java.awt.*;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.github.funthomas424242.jenkinsmonitor.gui.TrayImageTestHelper.isImageOfColor;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Tag("headfull")
public class JenkinsMonitorTest {

    protected WireMockServer wireMockServer;

    @BeforeEach
    public void setUp() {
        wireMockServer = new WireMockServer(8099);
        wireMockServer.start();
        JenkinsAPIMock.definiereAnnahmen(wireMockServer);
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }


    @Test
    @DisplayName("JenkinsMonitor ist Hauptklasse und enthält eine main Methode.")
    protected void checkMainMethod() {
        final String[] dummyArgs = {"Hallo", "Du da"};
        assertDoesNotThrow(() -> {
            JenkinsMonitor.main(dummyArgs);
        });

    }

    @Test
    @DisplayName("JenkinsMonitor ist Hauptklasse und enthält eine main Methode die auch ohne Argumente aufgerufen werden kann.")
    protected void checkMainEmptyParaMethod() {
        assertDoesNotThrow(() -> {
            JenkinsMonitor.main(null);
        });

    }


    @Test
    @DisplayName("JenkinsMonitor besitzt eine Tray Instanz nach seiner Erzeugung")
    protected void checkTrayInstanz() {

        final JenkinsMonitor monitor = new JenkinsMonitor( ConfigurationMockNoExisting.getOrCreateInstance().reload());
        assumeTrue(monitor != null);
        assertNotNull(monitor.getMonitorTray());
    }

    @Test
    @DisplayName("Initiale Konfiguration enthält keine JobBeschribungen")
    protected void initialConfigWithEmptyJobs() {
        final JenkinsMonitor jenkinsMonitor = new JenkinsMonitor( ConfigurationMockNoExisting.getOrCreateInstance().reload());
        assumeTrue(jenkinsMonitor.getMonitorTray() != null);
        assumeTrue(jenkinsMonitor.getMonitorTray().configuration != null);
        assertEquals(0, jenkinsMonitor.getMonitorTray().configuration.getJobBeschreibungen().size());
    }

    @Test
    @DisplayName("Tray Konfiguration ist identisch zur der dem JenkinsMonitor übergebenen")
    protected void equalsConfigWithTrayAndMonitor() {
        final Loaded config =  ConfigurationMockValidTwoJobs.getOrCreateInstance().reload();
        final JenkinsMonitor jenkinsMonitor = new JenkinsMonitor(config);
        assertSame(config, jenkinsMonitor.getMonitorTray().configuration);
    }

    @Test
    @DisplayName("Nach Erzeugung besitzt der JenkinsMonitor ein Tray Icon")
    protected void afterInitTrayIconExists() {
        final Loaded config =  ConfigurationMockValidTwoJobs.getOrCreateInstance().reload();
        final JenkinsMonitor jenkinsMonitor = new JenkinsMonitor(config);
        assertNotNull(jenkinsMonitor.getMonitorTray().jobStatusDarstellungen.trayWrapper.getTrayIcon());
    }

    @Test
    @DisplayName("Eine leere Konfiguration erzeugt ein graues TrayIcon")
    protected void trayIconHasGrayImage() {
        final Loaded config =  ConfigurationMockEmpty.getOrCreateInstance().reload();
        final JenkinsMonitor jenkinsMonitor = new JenkinsMonitor(config);
        final TrayIcon icon = jenkinsMonitor.getMonitorTray().jobStatusDarstellungen.trayWrapper.getTrayIcon();
        final BufferedImage image = (BufferedImage) icon.getImage();
        assertNotNull(icon);
        isImageOfColor(image, JobStatus.OTHER.getColor());
    }

    @Test
    @DisplayName("Eine Konfiguration mit einem erfolgreichen Job erzeugt ein grünes TrayIcon")
    protected void trayIconHasGreenImage() {
        final Created config = ConfigurationMockOneJobSuccess.getOrCreateInstance();
        final JenkinsMonitor jenkinsMonitor = new JenkinsMonitor(config.reload());
        final TrayIcon icon = jenkinsMonitor.getMonitorTray().jobStatusDarstellungen.trayWrapper.getTrayIcon();
        assertNotNull(icon);
        final BufferedImage image = (BufferedImage) icon.getImage();
        assertNotNull(image);
        assertTrue(isImageOfColor(image, JobStatus.SUCCESS.getColor()));
    }

    @Test
    @DisplayName("Eine Konfiguration mit einem erfolgreichen Job erzeugt ein rotes TrayIcon")
    protected void trayIconHasRedImage() {
        final Created config =  ConfigurationMockOneJobFailed.getOrCreateInstance();
        final JenkinsMonitor jenkinsMonitor = new JenkinsMonitor(config.reload());
        final TrayIcon icon = jenkinsMonitor.getMonitorTray().jobStatusDarstellungen.trayWrapper.getTrayIcon();
        assertNotNull(icon);
        final BufferedImage image = (BufferedImage) icon.getImage();
        assertNotNull(image);
        assertTrue(isImageOfColor(image, JobStatus.FAILURE.getColor()));
    }
}
