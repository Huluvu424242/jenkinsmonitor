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

import org.junit.jupiter.api.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.github.funthomas424242.jenkinsmonitor.TrayImage.isImageOfColor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("headfull")
public class JenkinsMonitorTrayTest {

    JenkinsMonitorTray jenkinsMonitorTray;

    @BeforeEach
    public void setUp() {
        jenkinsMonitorTray = new JenkinsMonitorTray();
        jenkinsMonitorTray.erzeugeDarstellung();
    }

    @AfterEach
    public void tearDown(){
        jenkinsMonitorTray=null;
    }

    @Test
    @DisplayName("Initial wird ein graues Icon angezeigt mit Tooltipp <<No jobs watching>>")
    public void shouldShowNoJobsWatching() throws AWTException {
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertEquals("No jobs watching", trayIcon.getToolTip());
        assertTrue(isImageOfColor((BufferedImage)  trayIcon.getImage(), JobStatus.OTHER.getColor()));
    }

    @Test
    @DisplayName("Bei einem erfolreichen Job soll das TrayIcon grün sein und der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master success>>")
    public void shouldShowOneSuccessJobWatching() {
        final JobBeschreibung[] jobBeschreibungen = new JobBeschreibung[1];
        jobBeschreibungen[0]=new JobBeschreibung("MultibrachnJob/master", JobStatus.SUCCESS,null);
        jenkinsMonitorTray.updateJobStatus(jobBeschreibungen);
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage)  trayIcon.getImage(), JobStatus.SUCCESS.getColor()));
//        assertEquals("MultibranchJob/master s/uccess", trayIcon.getToolTip());
    }

    @Test
    @DisplayName("Bei einem instabilen Job soll das TrayIcon gelb sein und der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master unstable>>")
    public void shouldShowOneInstabilJobWatching() {
        final JobBeschreibung[] jobBeschreibungen = new JobBeschreibung[1];
        jobBeschreibungen[0]=new JobBeschreibung("MultibranchJob/master", JobStatus.UNSTABLE,null);
        jenkinsMonitorTray.updateJobStatus(jobBeschreibungen);
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage)  trayIcon.getImage(), JobStatus.UNSTABLE.getColor()));
//        assertEquals("MultibranchJob/master s/uccess", trayIcon.getToolTip());
    }

    @Test
    @DisplayName("Bei einem instabilen Job soll das TrayIcon gelb sein und der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master unstable>>")
    public void shouldShowOneFailedJobWatching() {
        final JobBeschreibung[] jobBeschreibungen = new JobBeschreibung[1];
        jobBeschreibungen[0]=new JobBeschreibung("MultibranchJob/master", JobStatus.FAILURE,null);
        jenkinsMonitorTray.updateJobStatus(jobBeschreibungen);
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage)  trayIcon.getImage(), JobStatus.FAILURE.getColor()));
//        assertEquals("MultibranchJob/master s/uccess", trayIcon.getToolTip());
    }

    // TODO
//        assumeTrue(jenkinsMonitor.jobBeschreibungen != null);
//        assertEquals(0, jenkinsMonitor.jobBeschreibungen.length);
//
//        final Path validConfigfilePath = Paths.get(".", PATH_VALID_CONFIGURATION_FILE);
//        final File configFile = validConfigfilePath.toAbsolutePath().toFile();
//        jenkinsMonitor.configuration.reloadFromFile(configFile);
//        assumeTrue(jenkinsMonitor.configuration != null);
//        assumeTrue(jenkinsMonitor.jobBeschreibungen != null);
//        assertEquals(2, jenkinsMonitor.jobBeschreibungen.length);


}
