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
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class JenkinsClientTest {

    protected WireMockServer wireMockServer;

    protected JobStatusBeschreibungen jobStatusBeschreibungen;


    @BeforeAll
    protected static void setUpAll() throws MalformedURLException {

    }

    @BeforeEach
    protected void setUp() {
        wireMockServer = new WireMockServer(8099);
        wireMockServer.start();
        JenkinsAPIMock.definiereAnnahmen(wireMockServer);

        this.jobStatusBeschreibungen = new JobStatusBeschreibungen();
    }

    @AfterEach
    protected void tearDown() {
        wireMockServer.stop();
    }


    @Test
    @DisplayName("ladeStatus beim Auftreten einer IO Exception wird diese geloggt")
    void checkLadeStatusWithException() {

        final JenkinsClient requester = new JenkinsClient() {
//            @Override
//            protected JobStatusBeschreibung getJobStatus(final JobAbfragedaten statusAbfrageInformationen, String jobId) {
//                return new JobStatusBeschreibung("test", JobStatus.OTHER, statusAbfrageInformationen.getJenkinsJobUrl(), jobId);
//            }
        };

        final JobBeschreibungen jobBeschreibungen = new JobBeschreibungen();
        final JobBeschreibung jobBeschreibung = new JobBeschreibung(null, new JobAbfragedaten(NetworkHelper.urlOf("http://test.org")));
        jobBeschreibungen.put(jobBeschreibung.getPrimaryKey(), jobBeschreibung);

        assertDoesNotThrow(() -> requester.ladeJobsStatus(jobStatusBeschreibungen, jobBeschreibungen));
        assertEquals(1, jobStatusBeschreibungen.size());
    }


    @Test
    @DisplayName("prüfe ladeJobStatus für einen Job mit rotem Build")
    @Disabled("refactoring")
    void checkLadeOneJobStatusFailure() {

        final JenkinsClient requester = new JenkinsClient() {
//            @Override
//            protected JobStatusBeschreibung getJobStatus(final JobAbfragedaten statusAbfrageInformationen, final String jobId) {
//                return new JobStatusBeschreibung("hallo", JobStatus.FAILURE, statusAbfrageInformationen.getJenkinsJobUrl(), jobId);
//            }
        };
        final JobBeschreibungen jobBeschreibungen = new JobBeschreibungen();
        final JobBeschreibung jobBeschreibung = new JobBeschreibung(null, new JobAbfragedaten(NetworkHelper.urlOf("http://test.org")));
        jobBeschreibungen.put(jobBeschreibung.getPrimaryKey(), jobBeschreibung);
        final JobStatusBeschreibungen jobStatusBeschreibungen = new JobStatusBeschreibungen();

        requester.ladeJobsStatus(jobStatusBeschreibungen, jobBeschreibungen);

        assertEquals(1, jobStatusBeschreibungen.size());
        final JobStatusBeschreibung jobStatusBeschreibung = jobStatusBeschreibungen.get("null#http://test.org");
        assertEquals("hallo", jobStatusBeschreibung.getJobName());
        assertEquals(JobStatus.FAILURE, jobStatusBeschreibung.getJobStatus());
        assertEquals("http://test.org", jobStatusBeschreibung.getJobUrl().toExternalForm());
        assertEquals("null#http://test.org", jobStatusBeschreibung.getPrimaryKey());
    }

    @Test
    @DisplayName("prüfe ladeJobStatutus für zwei Jobs einer rot und einer gelb")
    @Disabled("parallel")
    void checkLadeTwoJobStatusSUCCESS_UNSTABLE() {

        final JenkinsClient requester = new JenkinsClient() {
//            int counter = 0;
//
//            @Override
//            protected JobStatusBeschreibung getJobStatus(final JobAbfragedaten statusAbfrageInformationen, final String jobId) {
//                if (counter == 0) {
//                    counter++;
//                    return new JobStatusBeschreibung("hallo", JobStatus.FAILURE, statusAbfrageInformationen.getJenkinsJobUrl(), jobId);
//                } else {
//                    counter++;
//                    return new JobStatusBeschreibung("hallo", JobStatus.SUCCESS, statusAbfrageInformationen.getJenkinsJobUrl(), jobId);
//                }
//            }
        };

        final JobBeschreibungen jobBeschreibungen = new JobBeschreibungen();
        final JobBeschreibung jobBeschreibung1 = new JobBeschreibung("#2", new JobAbfragedaten(NetworkHelper.urlOf("http://test1.org")));
        jobBeschreibungen.put(jobBeschreibung1.getPrimaryKey(), jobBeschreibung1);
        final JobBeschreibung jobBeschreibung2 = new JobBeschreibung("#1", new JobAbfragedaten(NetworkHelper.urlOf("http://test.org")));
        jobBeschreibungen.put(jobBeschreibung2.getPrimaryKey(), jobBeschreibung2);

        final JobStatusBeschreibungen jobStatusBeschreibungen = new JobStatusBeschreibungen();

        requester.ladeJobsStatus(jobStatusBeschreibungen, jobBeschreibungen);

        assertEquals(2, jobStatusBeschreibungen.size());
        final JobStatusBeschreibung jobStatusBeschreibung0 = jobStatusBeschreibungen.get("#1#http://test.org");/**/
        final JobStatusBeschreibung jobStatusBeschreibung1 = jobStatusBeschreibungen.get("#2#http://test1.org");/**/

        assertEquals("hallo", jobStatusBeschreibung0.getJobName());
        assertEquals(JobStatus.FAILURE, jobStatusBeschreibung0.getJobStatus());
        assertEquals("http://test.org", jobStatusBeschreibung0.getJobUrl().toExternalForm());

        assertEquals("hallo", jobStatusBeschreibung1.getJobName());
        assertEquals(JobStatus.SUCCESS, jobStatusBeschreibung1.getJobStatus());
        assertEquals("http://test1.org", jobStatusBeschreibung1.getJobUrl().toExternalForm());
    }

}
