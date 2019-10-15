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
import com.github.funthomas424242.jenkinsmonitor.jenkins.JenkinsClient;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobAbfragedaten;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobBeschreibung;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatus;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatusBeschreibung;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.github.funthomas424242.jenkinsmonitor.gui.TrayImage.isImageOfColor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JenkinsClientMock extends JenkinsClient {

    protected int jobNr = 0;
    protected final JobStatus[] jobStatus;

    public JenkinsClientMock(JobStatus... jobStatus) {
        this.jobStatus = jobStatus;
    }

    @Override
    protected JobStatusBeschreibung getJobStatus(final JobAbfragedaten jobAbfragedaten) throws IOException {
        return new JobStatusBeschreibung("xxx", jobStatus[jobNr++], jobAbfragedaten.getJenkinsJobUrl());
    }
}


@Tag("headfull")
public class JenkinsMonitorTrayTest {

    protected static URL LOCALHOST_JOB_TEST_URL;


    @BeforeAll
    protected static void setUpAll() throws MalformedURLException {
        LOCALHOST_JOB_TEST_URL = new URL("http://localhost:8099/job/test");
    }

    @Test
    @DisplayName("Initial wird ein graues Icon angezeigt mit Tooltipp <<No jobs watching>>")
    public void shouldShowNoJobsWatching() throws AWTException {
        final JenkinsMonitorTray jenkinsMonitorTray = new JenkinsMonitorTray(new JenkinsClientMock(JobStatus.OTHER), new ConfigurationMockEmpty());
        jenkinsMonitorTray.updateJobStatus();
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertEquals("Keine Jobs überwachend", trayIcon.getToolTip());
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage(), JobStatus.OTHER.getColor()));
    }

    @Test
    @DisplayName("Bei einem erfolreichen Job soll das TrayIcon grün sein und der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master success>>")
    public void shouldShowOneSuccessJobWatching() {
        final JenkinsMonitorTray jenkinsMonitorTray = new JenkinsMonitorTray(new JenkinsClientMock(JobStatus.SUCCESS), new ConfigurationMockEmpty());
        jenkinsMonitorTray.updateJobStatus();
        final JobBeschreibung[] jobBeschreibungen = new JobBeschreibung[1];
        jobBeschreibungen[0] = new JobBeschreibung(new JobAbfragedaten(LOCALHOST_JOB_TEST_URL));
        jenkinsMonitorTray.updateJobStatus(jobBeschreibungen);
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage(), JobStatus.SUCCESS.getColor()));
//        assertEquals("MultibranchJob/master s/uccess", trayIcon.getToolTip());
    }

    @Test
    @DisplayName("Bei einem instabilen Job soll das TrayIcon gelb sein und der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master unstable>>")
    public void shouldShowOneInstabilJobWatching() {
        final JenkinsMonitorTray jenkinsMonitorTray = new JenkinsMonitorTray(new JenkinsClientMock(JobStatus.UNSTABLE), new ConfigurationMockEmpty());
        jenkinsMonitorTray.updateJobStatus();
        final JobBeschreibung[] jobBeschreibungen = new JobBeschreibung[1];
        jobBeschreibungen[0] = new JobBeschreibung(new JobAbfragedaten(LOCALHOST_JOB_TEST_URL));
        jenkinsMonitorTray.updateJobStatus(jobBeschreibungen);
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage(), JobStatus.UNSTABLE.getColor()));
//        assertEquals("MultibranchJob/master s/uccess", trayIcon.getToolTip());
    }

    @Test
    @DisplayName("Bei einem fehlschlagenden Job soll das TrayIcon rot sein und der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master failed>>")
    public void shouldShowOneFailedJobWatching() {
        final JenkinsMonitorTray jenkinsMonitorTray = new JenkinsMonitorTray(new JenkinsClientMock(JobStatus.FAILURE), new ConfigurationMockEmpty());
        jenkinsMonitorTray.updateJobStatus();
        final JobBeschreibung[] jobBeschreibungen = new JobBeschreibung[1];
        jobBeschreibungen[0] = new JobBeschreibung(new JobAbfragedaten(LOCALHOST_JOB_TEST_URL));
        jenkinsMonitorTray.updateJobStatus(jobBeschreibungen);
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage(), JobStatus.FAILURE.getColor()));
//        assertEquals("MultibranchJob/master s/uccess", trayIcon.getToolTip());
    }

    @Test
    @DisplayName("Bei 2 Jobs - einer grün, einer rot erscheinen die Status im Tooltipp.")
    protected void showStatusAsToolstippsIfJobPresent() {
        final Configuration configJobs = new ConfigurationMockValidTwoJobs();
        final JenkinsMonitorTray tray = new JenkinsMonitorTray(new JenkinsClientMock(JobStatus.SUCCESS, JobStatus.FAILURE), configJobs);
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
        final JenkinsMonitorTray tray = new JenkinsMonitorTray(new JenkinsClientMock(JobStatus.SUCCESS, JobStatus.FAILURE, JobStatus.UNSTABLE), configJobs);
        tray.updateJobStatus();
        final TrayIcon trayIcon = tray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage()
            , JobStatus.SUCCESS.getColor()
            , JobStatus.FAILURE.getColor()
            , JobStatus.UNSTABLE.getColor())
        );
    }

    @Test
    @DisplayName("Status aktualisiert sich nach Ablauf der Zeitperiode durch Jobs update")
    public void updateJobsAfterTimePeriod() {

        ManualTimer clock = new ManualTimer();


        final Configuration configJobs = new ConfigurationMockValidTreeJobs();
        final JenkinsMonitorTray tray = new JenkinsMonitorTray(clock, new JenkinsClientMock(JobStatus.SUCCESS, JobStatus.FAILURE, JobStatus.UNSTABLE, JobStatus.SUCCESS, JobStatus.SUCCESS, JobStatus.SUCCESS), configJobs);
        tray.updateJobStatus();
        final TrayIcon trayIcon = tray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage()
            , JobStatus.SUCCESS.getColor()
            , JobStatus.FAILURE.getColor()
            , JobStatus.UNSTABLE.getColor())
        );

        clock.elapseTime();

        final TrayIcon trayIcon1 = tray.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage) trayIcon1.getImage()
            , JobStatus.SUCCESS.getColor()
            , JobStatus.SUCCESS.getColor()
            , JobStatus.SUCCESS.getColor())
        );

    }
}
