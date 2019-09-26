package com.github.funthomas424242.jenkinsmonitor.gui;

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

import com.github.funthomas424242.jenkinsmonitor.config.Configuration;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockEmpty;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockValidTreeJobs;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockValidTwoJobs;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JenkinsJobBeschreibung;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JenkinsJobStatusBeschreibung;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JenkinsJobStatusRequester;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatus;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.github.funthomas424242.jenkinsmonitor.gui.TrayImage.isImageOfColor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RequesterMock extends JenkinsJobStatusRequester {

    protected int jobNr = 0;
    protected final JobStatus[] jobStatus;

    public RequesterMock(JobStatus... jobStatus) {
        this.jobStatus = jobStatus;
    }

    @Override
    protected JenkinsJobStatusBeschreibung getJobStatus(final URL jenkinsJobURL) throws IOException {
        return new JenkinsJobStatusBeschreibung("xxx", jobStatus[jobNr++], jenkinsJobURL);
    }
}


@Tag("headfull")
public class JenkinsMonitorTrayTest {

    protected static URL LOCALHOST_JOB_TEST_URL;

    JenkinsMonitorTray jenkinsMonitorTray;

    @BeforeAll
    static void setUpAll() throws MalformedURLException {
        LOCALHOST_JOB_TEST_URL = new URL("http://localhost:8099/job/test");
    }

    @BeforeEach
    public void setUp() {
        jenkinsMonitorTray = new JenkinsMonitorTray(new ConfigurationMockEmpty());
        jenkinsMonitorTray.requester = new RequesterMock(JobStatus.OTHER);
        jenkinsMonitorTray.updateJobStatus();
    }

    @AfterEach
    public void tearDown() {
        jenkinsMonitorTray = null;
    }

    @Test
    @DisplayName("Initial wird ein graues Icon angezeigt mit Tooltipp <<No jobs watching>>")
    public void shouldShowNoJobsWatching() throws AWTException {
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertEquals("No jobs watching", trayIcon.getToolTip());
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage(), JobStatus.OTHER.getColor()));
    }

    @Test
    @DisplayName("Bei einem erfolreichen Job soll das TrayIcon grün sein und der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master success>>")
    public void shouldShowOneSuccessJobWatching() {
        final JenkinsJobBeschreibung[] jenkinsJobBeschreibungen = new JenkinsJobBeschreibung[1];
        jenkinsJobBeschreibungen[0] = new JenkinsJobBeschreibung(LOCALHOST_JOB_TEST_URL);
        jenkinsMonitorTray.requester = new RequesterMock(JobStatus.SUCCESS);
        jenkinsMonitorTray.updateJobStatus(jenkinsJobBeschreibungen);
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage(), JobStatus.SUCCESS.getColor()));
//        assertEquals("MultibranchJob/master s/uccess", trayIcon.getToolTip());
    }

    @Test
    @DisplayName("Bei einem instabilen Job soll das TrayIcon gelb sein und der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master unstable>>")
    public void shouldShowOneInstabilJobWatching() {
        final JenkinsJobBeschreibung[] jenkinsJobBeschreibungen = new JenkinsJobBeschreibung[1];
        jenkinsJobBeschreibungen[0] = new JenkinsJobBeschreibung(LOCALHOST_JOB_TEST_URL);
        jenkinsMonitorTray.requester = new RequesterMock(JobStatus.UNSTABLE);
        jenkinsMonitorTray.updateJobStatus(jenkinsJobBeschreibungen);
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage(), JobStatus.UNSTABLE.getColor()));
//        assertEquals("MultibranchJob/master s/uccess", trayIcon.getToolTip());
    }

    @Test
    @DisplayName("Bei einem fehlschlagenden Job soll das TrayIcon rot sein und der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master failed>>")
    public void shouldShowOneFailedJobWatching() {
        final JenkinsJobBeschreibung[] jenkinsJobBeschreibungen = new JenkinsJobBeschreibung[1];
        jenkinsJobBeschreibungen[0] = new JenkinsJobBeschreibung(LOCALHOST_JOB_TEST_URL);
        jenkinsMonitorTray.requester = new RequesterMock(JobStatus.FAILURE);
        jenkinsMonitorTray.updateJobStatus(jenkinsJobBeschreibungen);
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage(), JobStatus.FAILURE.getColor()));
//        assertEquals("MultibranchJob/master s/uccess", trayIcon.getToolTip());
    }

    @Test
    @DisplayName("Bei 2 Jobs - einer grün, einer rot erscheinen die Status im Tooltipp.")
    protected void showStatusAsToolstippsIfJobPresent() {
        final Configuration configJobs = new ConfigurationMockValidTwoJobs();
        final JenkinsMonitorTray tray = new JenkinsMonitorTray(configJobs);
        tray.requester = new RequesterMock(JobStatus.SUCCESS, JobStatus.FAILURE);
        tray.updateJobStatus();
        final TrayIcon trayIcon = tray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage()
            , JobStatus.SUCCESS.getColor()
            , JobStatus.FAILURE.getColor()));
    }

    @Test
    @DisplayName("Bei 3 Jobs - einer grün, einer rot, einer gelb  erscheinen die Status im Tooltipp.")
    protected void showStatusAsToolstippsIfJobsPresent() {
        final Configuration configJobs = new ConfigurationMockValidTreeJobs();
        final JenkinsMonitorTray tray = new JenkinsMonitorTray(configJobs);
        tray.requester = new RequesterMock(JobStatus.SUCCESS, JobStatus.FAILURE, JobStatus.UNSTABLE);
        tray.updateJobStatus();
        final TrayIcon trayIcon = tray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage()
            , JobStatus.SUCCESS.getColor()
            , JobStatus.FAILURE.getColor()
            , JobStatus.UNSTABLE.getColor())
        );
    }
}
