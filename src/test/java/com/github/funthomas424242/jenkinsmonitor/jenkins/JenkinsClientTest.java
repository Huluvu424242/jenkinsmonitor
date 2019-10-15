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
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


public class JenkinsClientTest {

    protected static URL JOB_URL_MULTIBRANCH_JOB1_RED;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_GREEN;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_YELLOW;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_GRAY;

//    protected static URL STATUS_URL_MULTIBRANCH_JOB1_RED;
//    protected static URL STATUS_URL_MULTIBRANCH_JOB1_GREEN;
//    protected static URL STATUS_URL_MULTIBRANCH_JOB1_YELLOW;
//    protected static URL STATUS_URL_MULTIBRANCH_JOB1_GRAY;

    protected WireMockServer wireMockServer;

    @BeforeAll
    protected static void setUpAll() throws MalformedURLException, URISyntaxException {
        JOB_URL_MULTIBRANCH_JOB1_RED = new URL(JenkinsAPIMock.JOB_URL_MULTIBRANCH_JOB1_RED);
        JOB_URL_MULTIBRANCH_JOB1_GREEN = new URL(JenkinsAPIMock.JOB_URL_MULTIBRANCH_JOB1_GREEN);
        JOB_URL_MULTIBRANCH_JOB1_YELLOW = new URL(JenkinsAPIMock.JOB_URL_MULTIBRANCH_JOB1_YELLOW);
        JOB_URL_MULTIBRANCH_JOB1_GRAY = new URL(JenkinsAPIMock.JOB_URL_MULTIBRANCH_JOB1_GRAY);

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
        assumeTrue(requester != null);
        final JobStatusBeschreibung jobStatusBeschreibung = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_RED);
            return requester.getJobStatus(jobAbfragedaten);
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
        assumeTrue(requester != null);
        final JobStatusBeschreibung jobStatusBeschreibung = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_GREEN);
            return requester.getJobStatus(jobAbfragedaten);
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
        assumeTrue(requester != null);
        final JobStatusBeschreibung jobStatusBeschreibung = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_YELLOW);
            return requester.getJobStatus(jobAbfragedaten);
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
        assumeTrue(requester != null);
        final JobStatusBeschreibung jobStatusBeschreibung = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_GRAY);
            return requester.getJobStatus(jobAbfragedaten);
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
        final JSONObject json = assertDoesNotThrow(() -> {
            return requester.sendGetRequest(new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_RED));
        });
        assertNotNull(json);
        assertEquals("mypocketmod » master #2", json.get("fullDisplayName"));
        assertEquals("FAILURE", json.get("result"));
    }

    @Test
    @DisplayName("Die Statusabfrage eines grünen Build Jobs gibt ein valides JSON zurück")
    protected void getValidJsonGreen() {
        final JenkinsClient requester = new JenkinsClient();
        final JSONObject json = assertDoesNotThrow(() -> {
            return requester.sendGetRequest(new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_GREEN, null));
        });
        assertNotNull(json);
        assertEquals("mypocketmod » master #2", json.get("fullDisplayName"));
        assertEquals("SUCCESS", json.get("result"));
    }

    @Test
    @DisplayName("Die Statusabfrage eines gelben Build Jobs gibt ein valides JSON zurück")
    protected void getValidJsonYellow() {
        final JenkinsClient requester = new JenkinsClient();
        final JSONObject json = assertDoesNotThrow(() -> {
            return requester.sendGetRequest(new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_YELLOW, null));
        });
        assertNotNull(json);
        assertEquals("mypocketmod » master #2", json.get("fullDisplayName"));
        assertEquals("UNSTABLE", json.get("result"));
    }

    @Test
    @DisplayName("Die Statusabfrage eines grauen Build Jobs gibt KEIN valides JSON zurück")
    protected void getValidJsonGray() {
        final JenkinsClient requester = new JenkinsClient();
        final JSONObject json = assertDoesNotThrow(() -> {
            return requester.sendGetRequest(new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_GRAY, null));
        });
        assertNotNull(json);
        assertTrue(json.isEmpty());
    }

    @Test
    @DisplayName("ladeStatus beim Auftreten einer IO Exception wird diese geloggt")
    void checkLadeStatusWithException() {

        final JenkinsClient requester = new JenkinsClient() {
            @Override
            protected JobStatusBeschreibung getJobStatus(final JobAbfragedaten statusAbfrageInformationen) throws IOException {
                throw new IOException();
            }
        };

        final JobBeschreibung[] jobBeschreibungen = new JobBeschreibung[1];
        jobBeschreibungen[0] = new JobBeschreibung(null, new JobAbfragedaten(NetworkHelper.urlOf("http://test.org")));

        final JobStatusBeschreibung[] jobStatusBeschreibungen = assertDoesNotThrow(() -> {
            final JobStatusBeschreibung[] statusBeschreibungen = requester.ladeJobsStatus(jobBeschreibungen);
            assumeTrue(statusBeschreibungen != null);
            return statusBeschreibungen;
        });
    }


    @Test
    @DisplayName("prüfe ladeJobStatutus für einen Job mit rotem Build")
    void checkLadeOneJobStatusFailure() {

        final JenkinsClient requester = new JenkinsClient() {
            @Override
            protected JobStatusBeschreibung getJobStatus(final JobAbfragedaten statusAbfrageInformationen) throws IOException {
                return new JobStatusBeschreibung("hallo", JobStatus.FAILURE, statusAbfrageInformationen.getJenkinsJobUrl());
            }
        };
        final JobBeschreibung[] jobBeschreibungen = new JobBeschreibung[1];
        jobBeschreibungen[0] = new JobBeschreibung(null, new JobAbfragedaten(NetworkHelper.urlOf("http://test.org")));

        final JobStatusBeschreibung[] jobStatusBeschreibungen = requester.ladeJobsStatus(jobBeschreibungen);
        assumeTrue(jobBeschreibungen != null);
        assertEquals("hallo", jobStatusBeschreibungen[0].getJobName());
        assertEquals(JobStatus.FAILURE, jobStatusBeschreibungen[0].getJobStatus());
        assertEquals("http://test.org", jobStatusBeschreibungen[0].getJobUrl().toExternalForm());
    }

    @Test
    @DisplayName("prüfe ladeJobStatutus für zwei Jobs einer rot und einer gelb")
    void checkLadeTwoJobStatusSUCCESS_UNSTABLE() {

        final JenkinsClient requester = new JenkinsClient() {
            int counter = 0;

            @Override
            protected JobStatusBeschreibung getJobStatus(final JobAbfragedaten statusAbfrageInformationen) throws IOException {
                if (counter == 0) {
                    counter++;
                    return new JobStatusBeschreibung("hallo", JobStatus.FAILURE, statusAbfrageInformationen.getJenkinsJobUrl());
                } else {
                    counter++;
                    return new JobStatusBeschreibung("hallo", JobStatus.SUCCESS, statusAbfrageInformationen.getJenkinsJobUrl());
                }
            }
        };

        final JobBeschreibung[] jobBeschreibungen = new JobBeschreibung[2];
        jobBeschreibungen[0] = new JobBeschreibung("the first job", new JobAbfragedaten(NetworkHelper.urlOf("http://test.org")));
        jobBeschreibungen[1] = new JobBeschreibung("idname", new JobAbfragedaten(NetworkHelper.urlOf("http://test1.org")));

        /**/
        {
            final JobStatusBeschreibung[] jobStatusBeschreibungen = requester.ladeJobsStatus(jobBeschreibungen);
            assumeTrue(jobBeschreibungen != null);
            assertEquals("hallo", jobStatusBeschreibungen[0].getJobName());
            assertEquals(JobStatus.FAILURE, jobStatusBeschreibungen[0].getJobStatus());
            assertEquals("http://test.org", jobStatusBeschreibungen[0].getJobUrl().toExternalForm());
        }
        /**/
        {
            final JobStatusBeschreibung[] jobStatusBeschreibungen = requester.ladeJobsStatus(jobBeschreibungen);
            assumeTrue(jobBeschreibungen != null);
            assertEquals("hallo", jobStatusBeschreibungen[1].getJobName());
            assertEquals(JobStatus.SUCCESS, jobStatusBeschreibungen[1].getJobStatus());
            assertEquals("http://test1.org", jobStatusBeschreibungen[1].getJobUrl().toExternalForm());
        }
    }

}
