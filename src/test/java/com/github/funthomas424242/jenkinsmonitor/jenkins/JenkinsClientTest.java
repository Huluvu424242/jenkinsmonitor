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
import org.apache.http.client.HttpResponseException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class JenkinsClientTest {

    protected static URL JOB_URL_MULTIBRANCH_JOB1_RED;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_GREEN;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_YELLOW;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_GRAY_BUILDING;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_GRAY_UNKNOW;

//    protected static URL STATUS_URL_MULTIBRANCH_JOB1_RED;
//    protected static URL STATUS_URL_MULTIBRANCH_JOB1_GREEN;
//    protected static URL STATUS_URL_MULTIBRANCH_JOB1_YELLOW;
//    protected static URL STATUS_URL_MULTIBRANCH_JOB1_GRAY;

    protected WireMockServer wireMockServer;

    @BeforeAll
    protected static void setUpAll() throws MalformedURLException {
        JOB_URL_MULTIBRANCH_JOB1_RED = new URL(JenkinsAPIMock.JOB_URL_MULTIBRANCH_JOB1_RED);
        JOB_URL_MULTIBRANCH_JOB1_GREEN = new URL(JenkinsAPIMock.JOB_URL_MULTIBRANCH_JOB1_GREEN);
        JOB_URL_MULTIBRANCH_JOB1_YELLOW = new URL(JenkinsAPIMock.JOB_URL_MULTIBRANCH_JOB1_YELLOW);
        JOB_URL_MULTIBRANCH_JOB1_GRAY_BUILDING = new URL(JenkinsAPIMock.JOB_URL_MULTIBRANCH_JOB1_GRAY_BUILDING);
        JOB_URL_MULTIBRANCH_JOB1_GRAY_UNKNOW = new URL(JenkinsAPIMock.JOB_URL_MULTIBRANCH_JOB1_GRAY_UNKNOW);

//        STATUS_URL_MULTIBRANCH_JOB1_RED = new URL(JenkinsAPIMock.STATUS_URL_MULTIBRANCH_JOB1_RED);
//        STATUS_URL_MULTIBRANCH_JOB1_GREEN = new URL(JenkinsAPIMock.STATUS_URL_MULTIBRANCH_JOB1_GREEN);
//        STATUS_URL_MULTIBRANCH_JOB1_YELLOW = new URL(JenkinsAPIMock.STATUS_URL_MULTIBRANCH_JOB1_YELLOW);
//        STATUS_URL_MULTIBRANCH_JOB1_GRAY = new URL(JenkinsAPIMock.STATUS_URL_MULTIBRANCH_JOB1_GRAY);
    }

    @BeforeEach
    protected void setUp() {
        wireMockServer = new WireMockServer(8099);
        wireMockServer.start();
        JenkinsAPIMock.definiereAnnahmen(wireMockServer);
    }

    @AfterEach
    protected void tearDown() {
        wireMockServer.stop();
    }


    @Test
    @DisplayName("Fehlgeschlagener Multibranch Job erzeugt roten Status")
    protected void getStatusRed() {

        final JenkinsClient requester = new JenkinsClient();

        final JobStatusBeschreibung jobStatusBeschreibung = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_RED);
            return requester.getJobStatus(jobAbfragedaten, "#1");
        });
        assertNotNull(jobStatusBeschreibung);
        assertNotNull(jobStatusBeschreibung.getJobStatus());
        assertNotNull(jobStatusBeschreibung.getJobName());
        assertEquals(JobStatus.FAILURE.getColor(), jobStatusBeschreibung.getJobStatus().getColor());
    }


    @Test
    @DisplayName("Erfolgreicher Multibranch Job erzeugt grünen Status")
    protected void getStatusGreen() {
        final JenkinsClient requester = new JenkinsClient();

        final JobStatusBeschreibung jobStatusBeschreibung = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_GREEN);
            return requester.getJobStatus(jobAbfragedaten, "#1");
        });
        assertNotNull(jobStatusBeschreibung);
        assertNotNull(jobStatusBeschreibung.getJobStatus());
        assertNotNull(jobStatusBeschreibung.getJobName());
        assertEquals(JobStatus.SUCCESS.getColor(), jobStatusBeschreibung.getJobStatus().getColor());
    }

    @Test
    @DisplayName("Instabiler Multibranch Job erzeugt gelben Status")
    protected void getStatusYellow() {
        final JenkinsClient requester = new JenkinsClient();

        final JobStatusBeschreibung jobStatusBeschreibung = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_YELLOW);
            return requester.getJobStatus(jobAbfragedaten, "#1");
        });
        assertNotNull(jobStatusBeschreibung);
        assertNotNull(jobStatusBeschreibung.getJobStatus());
        assertNotNull(jobStatusBeschreibung.getJobName());
        assertEquals(JobStatus.UNSTABLE.getColor(), jobStatusBeschreibung.getJobStatus().getColor());
    }

    @Test
    @DisplayName("Unbekanter  Multibranch Job Status erzeugt grauen Status")
    protected void getStatusGray() {
        final JenkinsClient requester = new JenkinsClient();

        final JobStatusBeschreibung jobStatusBeschreibung = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_GRAY_UNKNOW);
            return requester.getJobStatus(jobAbfragedaten, "#1");
        });
        assertNotNull(jobStatusBeschreibung);
        assertNotNull(jobStatusBeschreibung.getJobStatus());
        assertNotNull(jobStatusBeschreibung.getJobName());
        assertEquals(JobStatus.OTHER.getColor(), jobStatusBeschreibung.getJobStatus().getColor());
    }


    @Test
    @DisplayName("Die Statusabfrage eines roten Build Jobs gibt ein valides JSON zurück")
    protected void getValidJsonRed() {
        final JenkinsClient requester = new JenkinsClient();
        final JSONObject json = assertDoesNotThrow(() ->
            requester.sendGetRequest(new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_RED))
        );
        assertNotNull(json);
        assertEquals("mypocketmod » master #2", json.get("fullDisplayName"));
        assertEquals("FAILURE", json.get("result"));
    }

    @Test
    @DisplayName("Die Statusabfrage eines grünen Build Jobs gibt ein valides JSON zurück")
    protected void getValidJsonGreen() {
        final JenkinsClient requester = new JenkinsClient();
        final JSONObject json = assertDoesNotThrow(() -> requester.sendGetRequest(new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_GREEN, null)));
        assertNotNull(json);
        assertEquals("mypocketmod \u00bb master #2", json.get("fullDisplayName"));
        assertEquals("SUCCESS", json.get("result"));
    }

    @Test
    @DisplayName("Die Statusabfrage eines gelben Build Jobs gibt ein valides JSON zurück")
    protected void getValidJsonYellow() {
        final JenkinsClient requester = new JenkinsClient();
        final JSONObject json = assertDoesNotThrow(() -> requester.sendGetRequest(new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_YELLOW, null)));
        assertNotNull(json);
        assertEquals("mypocketmod » master #2", json.get("fullDisplayName"));
        assertEquals("UNSTABLE", json.get("result"));
    }

    @Test
    @DisplayName("Die Statusabfrage eines unbekannten Build Jobs gibt KEIN valides JSON zurück")
    protected void getValidJsonGray() {
        final JenkinsClient requester = new JenkinsClient();
       HttpResponseException exception =  assertThrows(HttpResponseException.class, () -> {
            requester.sendGetRequest(new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_GRAY_UNKNOW, null));
            fail();
        });
       assertEquals(exception.getStatusCode(),404);
    }

//    @Test
//    @DisplayName("ladeStatus beim Auftreten einer IO Exception wird diese geloggt")
//    void checkLadeStatusWithException() {
//
//        final JenkinsClient requester = new JenkinsClient() {
//            @Override
//            protected JobStatusBeschreibung getJobStatus(final JobAbfragedaten statusAbfrageInformationen, String jobId) {
//                return new JobStatusBeschreibung("test", JobStatus.OTHER, statusAbfrageInformationen.getJenkinsJobUrl(), jobId);
//            }
//        };
//
//        final Map<String, JobBeschreibung> jobBeschreibungen = new HashMap<>();
//        final JobBeschreibung jobBeschreibung = new JobBeschreibung(null, new JobAbfragedaten(NetworkHelper.urlOf("http://test.org")));
//        jobBeschreibungen.put(jobBeschreibung.getPrimaryKey(), jobBeschreibung);
//        final Map<String, JobStatusBeschreibung> jobStatusBeschreibungen = new HashMap<>();
//
//        assertDoesNotThrow(() -> requester.ladeJobsStatus(jobStatusBeschreibungen, jobBeschreibungen));
//        assertEquals(1, jobStatusBeschreibungen.size());
//    }


//    @Test
//    @DisplayName("prüfe ladeJobStatus für einen Job mit rotem Build")
//    void checkLadeOneJobStatusFailure() {
//
//        final JenkinsClient requester = new JenkinsClient() {
//            @Override
//            protected JobStatusBeschreibung getJobStatus(final JobAbfragedaten statusAbfrageInformationen, final String jobId) {
//                return new JobStatusBeschreibung("hallo", JobStatus.FAILURE, statusAbfrageInformationen.getJenkinsJobUrl(), jobId);
//            }
//        };
//        final Map<String, JobBeschreibung> jobBeschreibungen = new HashMap<>();
//        final JobBeschreibung jobBeschreibung = new JobBeschreibung(null, new JobAbfragedaten(NetworkHelper.urlOf("http://test.org")));
//        jobBeschreibungen.put(jobBeschreibung.getPrimaryKey(), jobBeschreibung);
//        final Map<String, JobStatusBeschreibung> jobStatusBeschreibungen = new HashMap<>();
//
//        requester.ladeJobsStatus(jobStatusBeschreibungen, jobBeschreibungen);
//
//        assertEquals(1, jobStatusBeschreibungen.size());
//        final JobStatusBeschreibung jobStatusBeschreibung = jobStatusBeschreibungen.get("null#http://test.org");
//        assertEquals("hallo", jobStatusBeschreibung.getJobName());
//        assertEquals(JobStatus.FAILURE, jobStatusBeschreibung.getJobStatus());
//        assertEquals("http://test.org", jobStatusBeschreibung.getJobUrl().toExternalForm());
//        assertEquals("null#http://test.org", jobStatusBeschreibung.getPrimaryKey());
//    }

//    @Test
//    @DisplayName("prüfe ladeJobStatutus für zwei Jobs einer rot und einer gelb")
//    @Disabled("parallel")
//    void checkLadeTwoJobStatusSUCCESS_UNSTABLE() {
//
//        final JenkinsClient requester = new JenkinsClient() {
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
//        };
//
//        final Map<String, JobBeschreibung> jobBeschreibungen = new HashMap<>();
//        final JobBeschreibung jobBeschreibung1 = new JobBeschreibung("#2", new JobAbfragedaten(NetworkHelper.urlOf("http://test1.org")));
//        jobBeschreibungen.put(jobBeschreibung1.getPrimaryKey(), jobBeschreibung1);
//        final JobBeschreibung jobBeschreibung2 = new JobBeschreibung("#1", new JobAbfragedaten(NetworkHelper.urlOf("http://test.org")));
//        jobBeschreibungen.put(jobBeschreibung2.getPrimaryKey(), jobBeschreibung2);
//
//        final Map<String, JobStatusBeschreibung> jobStatusBeschreibungen = new HashMap<>();
//
//        requester.ladeJobsStatus(jobStatusBeschreibungen, jobBeschreibungen);
//
//        assertEquals(2, jobStatusBeschreibungen.size());
//        final JobStatusBeschreibung jobStatusBeschreibung0 = jobStatusBeschreibungen.get("#1#http://test.org");/**/
//        final JobStatusBeschreibung jobStatusBeschreibung1 = jobStatusBeschreibungen.get("#2#http://test1.org");/**/
//
//        assertEquals("hallo", jobStatusBeschreibung0.getJobName());
//        assertEquals(JobStatus.FAILURE, jobStatusBeschreibung0.getJobStatus());
//        assertEquals("http://test.org", jobStatusBeschreibung0.getJobUrl().toExternalForm());
//
//        assertEquals("hallo", jobStatusBeschreibung1.getJobName());
//        assertEquals(JobStatus.SUCCESS, jobStatusBeschreibung1.getJobStatus());
//        assertEquals("http://test1.org", jobStatusBeschreibung1.getJobUrl().toExternalForm());
//    }

}
