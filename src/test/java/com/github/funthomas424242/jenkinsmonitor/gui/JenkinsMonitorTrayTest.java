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

import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockEmpty;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockOneJobFailed;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockOneJobSuccess;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockOneJobUnstable;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockValidTreeJobs;
import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationMockValidTwoJobs;
import com.github.funthomas424242.jenkinsmonitor.etc.NetworkHelper;
import com.github.funthomas424242.jenkinsmonitor.jenkins.AbstractJobBeschreibung;
import com.github.funthomas424242.jenkinsmonitor.jenkins.AbstractJobBeschreibungen;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JenkinsHttpClient;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobBeschreibungen;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatus;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatusBeschreibung;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.github.funthomas424242.jenkinsmonitor.gui.TrayImageTestHelper.isImageOfColor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JenkinsHttpClientMock extends JenkinsHttpClient {

    protected int jobNr = 0;
    protected final JobStatus[] jobStatus;

    public JenkinsHttpClientMock(JobStatus... jobStatus) {
        this.jobStatus = jobStatus;
    }

    @Override
    public void ladeJobsStatus(final AbstractJobBeschreibungen<JobStatusBeschreibung> jobStatusBeschreibungen, final JobBeschreibungen jobBeschreibungen) {
        final JobStatusBeschreibung jobStatusBeschreibung = new JobStatusBeschreibung("xxx", jobStatus[jobNr++], NetworkHelper.urlOf("http://localhost:8099/job/multibranchjob/job/master" + (jobNr + 1)), "#" + (jobNr + 1));
        jobStatusBeschreibungen.put(jobStatusBeschreibung.getPrimaryKey(), jobStatusBeschreibung);
    }
}

class JenkinsHttpClientMockAuto extends JenkinsHttpClient {

    protected int jobNr;
    protected final JobStatus[] jobStatus;

    public JenkinsHttpClientMockAuto(JobStatus... jobStatus) {
        this.jobStatus = jobStatus;
        this.jobNr = 0;
    }

    @Override
    public void ladeJobsStatus(final AbstractJobBeschreibungen<JobStatusBeschreibung> jobStatusBeschreibungen, final JobBeschreibungen jobBeschreibungen) {
        AbstractJobBeschreibung.sortedStreamOf(jobBeschreibungen)
                .forEach(jobBeschreibung -> {
                    final JobStatusBeschreibung jobStatusBeschreibung = new JobStatusBeschreibung("xxx", jobStatus[jobNr++], NetworkHelper.urlOf("http://localhost:8099/job/multibranchjob/job/master" + jobNr), "#" + jobNr);
                    jobStatusBeschreibungen.put(jobStatusBeschreibung.getPrimaryKey(), jobStatusBeschreibung);
                });
    }
}


@Tag("headfull")
class JenkinsMonitorTrayTest {

    protected static URL LOCALHOST_JOB_TEST_URL;

    protected JenkinsHttpClient jenkinsHttpClient;

    protected TestDrivenTimer clock;


    @BeforeAll
    protected static void setUpAll() throws MalformedURLException {
        LOCALHOST_JOB_TEST_URL = new URL("http://localhost:8099/job/test");
    }

    @BeforeEach
    protected void beforeTest() {
        jenkinsHttpClient = new JenkinsHttpClient();
        this.clock = new TestDrivenTimer();
    }

    protected TrayIcon getTrayIcon(final JenkinsMonitorTray jenkinsMonitorTray) {
        return jenkinsMonitorTray.getJobStatusDarstellungen().trayWrapper.getTrayIcon();
    }


    @Test
    @DisplayName("Bei leerer Configuration wird ein graues Icon angezeigt mit Tooltipp <<No jobs watching>>")
    void shouldShowNoJobsWatching() {
        final JenkinsMonitorTray jenkinsMonitorTray = new JenkinsMonitorTray(jenkinsHttpClient, ConfigurationMockEmpty.getOrCreateInstance().reload());
        jenkinsMonitorTray.updateJobStatus();
        final TrayIcon trayIcon = getTrayIcon(jenkinsMonitorTray);
        assertEquals("Keine Jobs überwachend", trayIcon.getToolTip());
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage(), JobStatus.OTHER.getColor()));
    }

    @Test
    @DisplayName("Bei einem erfolreichen Job soll das TrayIcon grün sein und der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master success>>")
    void shouldShowOneSuccessJobWatching() {
        final JenkinsMonitorTray jenkinsMonitorTray = new JenkinsMonitorTray(clock, new JenkinsHttpClientMock(JobStatus.SUCCESS), ConfigurationMockOneJobSuccess.getOrCreateInstance().reload());

        jenkinsMonitorTray.updateJobStatus();

        final TrayIcon trayIcon = getTrayIcon(jenkinsMonitorTray);
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage(), JobStatus.SUCCESS.getColor()));
    }

    @Test
    @DisplayName("Bei einem instabilen Job soll das TrayIcon gelb sein und der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master unstable>>")
    void shouldShowOneInstabilJobWatching() {
        final JenkinsMonitorTray jenkinsMonitorTray = new JenkinsMonitorTray(clock, new JenkinsHttpClientMock(JobStatus.UNSTABLE), ConfigurationMockOneJobUnstable.getOrCreateInstance().reload());

        jenkinsMonitorTray.updateJobStatus();

        final TrayIcon trayIcon = getTrayIcon(jenkinsMonitorTray);
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage(), JobStatus.UNSTABLE.getColor()));
    }

    @Test
    @DisplayName("Bei einem fehlschlagenden Job soll das TrayIcon rot sein und der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master failed>>")
    void shouldShowOneFailedJobWatching() {
        final JenkinsMonitorTray jenkinsMonitorTray = new JenkinsMonitorTray(clock, new JenkinsHttpClientMock(JobStatus.FAILURE), ConfigurationMockOneJobFailed.getOrCreateInstance().reload());

        jenkinsMonitorTray.updateJobStatus();

        final TrayIcon trayIcon = jenkinsMonitorTray.getJobStatusDarstellungen().trayWrapper.getTrayIcon();
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage(), JobStatus.FAILURE.getColor()));
    }

    @Test
    @DisplayName("Bei 2 Jobs - einer grün, einer rot erscheinen die Status im Tooltipp.")
    void showStatusAsToolstippsIfJobPresent() {
        final JenkinsMonitorTray jenkinsMonitorTray = new JenkinsMonitorTray(clock, new JenkinsHttpClientMockAuto(JobStatus.SUCCESS, JobStatus.FAILURE), ConfigurationMockValidTwoJobs.getOrCreateInstance().reload());

        jenkinsMonitorTray.updateJobStatus();

        final TrayIcon trayIcon = getTrayIcon(jenkinsMonitorTray);
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage()
                , JobStatus.SUCCESS.getColor()
                , JobStatus.FAILURE.getColor()));
    }

    @Test
    @DisplayName("Bei 3 Jobs - einer grün, einer rot, einer gelb  erscheinen die Status im Tooltipp.")
    void showStatusAsToolstippsIfJobsPresent() {
        final JenkinsMonitorTray jenkinsMonitorTray = new JenkinsMonitorTray(clock, new JenkinsHttpClientMockAuto(JobStatus.SUCCESS, JobStatus.FAILURE, JobStatus.UNSTABLE), ConfigurationMockValidTreeJobs.getOrCreateInstance().reload());

        jenkinsMonitorTray.updateJobStatus();

        final TrayIcon trayIcon = getTrayIcon(jenkinsMonitorTray);
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage()
                , JobStatus.SUCCESS.getColor()
                , JobStatus.FAILURE.getColor()
                , JobStatus.UNSTABLE.getColor())
        );
    }

    @Test
    @DisplayName("Status aktualisiert sich nach Ablauf der Zeitperiode durch Jobs update")
//    @Disabled("wackelt")
    void updateJobsAfterTimePeriod() {

        final JenkinsMonitorTray jenkinsMonitorTray = new JenkinsMonitorTray(clock, new JenkinsHttpClientMockAuto(JobStatus.SUCCESS, JobStatus.FAILURE, JobStatus.UNSTABLE, JobStatus.SUCCESS, JobStatus.SUCCESS, JobStatus.SUCCESS), ConfigurationMockValidTreeJobs.getOrCreateInstance().reload());

        jenkinsMonitorTray.updateJobStatus();

        final TrayIcon trayIcon = getTrayIcon(jenkinsMonitorTray);
        assertTrue(isImageOfColor((BufferedImage) trayIcon.getImage()
                , JobStatus.SUCCESS.getColor()
                , JobStatus.FAILURE.getColor()
                , JobStatus.UNSTABLE.getColor())
        );

        clock.elapseTime();

        final TrayIcon trayIcon1 = getTrayIcon(jenkinsMonitorTray);
        assertTrue(isImageOfColor((BufferedImage) trayIcon1.getImage()
                , JobStatus.SUCCESS.getColor()
                , JobStatus.SUCCESS.getColor()
                , JobStatus.SUCCESS.getColor())
        );

    }
}
