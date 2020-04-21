package com.github.funthomas424242.jenkinsmonitor.jenkins;

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

import com.github.funthomas424242.jenkinsmonitor.etc.NetworkHelper;
import com.github.funthomas424242.jenkinsmonitor.gui.JobStatusBeschreibungen;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class JenkinsClientTest {

    protected WireMockServer wireMockServer;

    protected JobStatusBeschreibungen jobStatusBeschreibungen;
    protected JenkinsClient jenkinsClient;


    @BeforeAll
    protected static void setUpAll() throws MalformedURLException {

    }

    @BeforeEach
    protected void setUp() {
        wireMockServer = new WireMockServer(8099);
        wireMockServer.start();
        JenkinsAPIMock.definiereAnnahmen(wireMockServer);

        this.jobStatusBeschreibungen = new JobStatusBeschreibungen();
        this.jenkinsClient = new JenkinsClient();
    }

    @AfterEach
    protected void tearDown() {
        wireMockServer.stop();
    }


    @Test
    @DisplayName("ladeStatus beim Auftreten einer IO Exception wird diese geloggt")
    void checkLadeStatusWithException() {
        // TODO Logging Mock erstellen und auswerten

        final JobBeschreibungen jobBeschreibungen = new JobBeschreibungen();
        final JobBeschreibung jobBeschreibung = new JobBeschreibung(null, new JobAbfragedaten(NetworkHelper.urlOf("http://localhost:8099/job/multibranchjobred/job/master")));
        jobBeschreibungen.put(jobBeschreibung.getPrimaryKey(), jobBeschreibung);

        assertDoesNotThrow(() -> {
            jenkinsClient.ladeJobsStatus(jobStatusBeschreibungen, jobBeschreibungen);
        });
        assertEquals(1, jobStatusBeschreibungen.size());
    }


    @Test
    @DisplayName("prüfe ladeJobStatus für einen Job mit rotem Build")
    void checkLadeOneJobStatusFailure() {
        final JobBeschreibungen jobBeschreibungen = new JobBeschreibungen();
        final JobBeschreibung jobBeschreibung = new JobBeschreibung(null, new JobAbfragedaten(NetworkHelper.urlOf("http://localhost:8099/job/multibranchjobred/job/master")));
        jobBeschreibungen.put(jobBeschreibung.getPrimaryKey(), jobBeschreibung);

        jenkinsClient.ladeJobsStatus(jobStatusBeschreibungen, jobBeschreibungen);

        assertEquals(1, jobStatusBeschreibungen.size());
        final JobStatusBeschreibung jobStatusBeschreibung = jobStatusBeschreibungen.get("null#http://localhost:8099/job/multibranchjobred/job/master");
        assertEquals("mypocketmod » master #2", jobStatusBeschreibung.getJobName());
        assertEquals(JobStatus.FAILURE, jobStatusBeschreibung.getJobStatus());
        assertEquals("http://localhost:8099/job/multibranchjobred/job/master", jobStatusBeschreibung.getJobUrl().toExternalForm());
        assertEquals("null#http://localhost:8099/job/multibranchjobred/job/master", jobStatusBeschreibung.getPrimaryKey());
    }

    @Test
    @DisplayName("prüfe ladeJobStatutus für zwei Jobs einer rot und einer gelb")
    void checkLadeTwoJobStatusSUCCESS_UNSTABLE() {

        final JobBeschreibungen jobBeschreibungen = new JobBeschreibungen();
        final JobBeschreibung jobBeschreibung1 = new JobBeschreibung("#2", new JobAbfragedaten(NetworkHelper.urlOf("http://localhost:8099/job/multibranchjobred/job/master")));
        jobBeschreibungen.put(jobBeschreibung1.getPrimaryKey(), jobBeschreibung1);
        final JobBeschreibung jobBeschreibung2 = new JobBeschreibung("#1", new JobAbfragedaten(NetworkHelper.urlOf("http://localhost:8099/job/multibranchjobgreen/job/master")));
        jobBeschreibungen.put(jobBeschreibung2.getPrimaryKey(), jobBeschreibung2);

        final JobStatusBeschreibungen jobStatusBeschreibungen = new JobStatusBeschreibungen();

        jenkinsClient.ladeJobsStatus(jobStatusBeschreibungen, jobBeschreibungen);

        assertEquals(2, jobStatusBeschreibungen.size());
        final JobStatusBeschreibung jobStatusBeschreibung0 = jobStatusBeschreibungen.get("#1#http://localhost:8099/job/multibranchjobgreen/job/master");/**/
        final JobStatusBeschreibung jobStatusBeschreibung1 = jobStatusBeschreibungen.get("#2#http://localhost:8099/job/multibranchjobred/job/master");/**/

        assertEquals("mypocketmod » master #2", jobStatusBeschreibung0.getJobName());
        assertEquals(JobStatus.SUCCESS, jobStatusBeschreibung0.getJobStatus());
        assertEquals("http://localhost:8099/job/multibranchjobgreen/job/master", jobStatusBeschreibung0.getJobUrl().toExternalForm());

        assertEquals("mypocketmod » master #2", jobStatusBeschreibung1.getJobName());
        assertEquals(JobStatus.FAILURE, jobStatusBeschreibung1.getJobStatus());
        assertEquals("http://localhost:8099/job/multibranchjobred/job/master", jobStatusBeschreibung1.getJobUrl().toExternalForm());
    }

}
