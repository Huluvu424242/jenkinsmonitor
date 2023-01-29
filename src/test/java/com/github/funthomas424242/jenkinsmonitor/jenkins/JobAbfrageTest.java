package com.github.funthomas424242.jenkinsmonitor.jenkins;

/*-
 * #%L
 * Jenkins Monitor
 * %%
 * Copyright (C) 2019 - 2020 PIUG
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

import com.cdancy.jenkins.rest.domain.job.BuildInfo;
import com.github.funthomas424242.jenkinsmonitor.gui.JobStatusBeschreibungen;
import com.github.tomakehurst.wiremock.WireMockServer;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class JobAbfrageTest {

    protected static URL JOB_URL_MULTIBRANCH_JOB1_RED;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_GREEN;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_YELLOW;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_GRAY_BUILDING;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_GRAY_UNKNOW;

    protected WireMockServer wireMockServer;

    protected JobStatusBeschreibungen jobStatusBeschreibungen;

    @BeforeAll
    protected static void setUp() throws MalformedURLException {
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
    protected void beforeTest() {
        wireMockServer = new WireMockServer(8099);
        wireMockServer.start();
        JenkinsAPIMock.definiereAnnahmen(wireMockServer);

        this.jobStatusBeschreibungen = new JobStatusBeschreibungen();
    }

    @AfterEach
    protected void afterTest() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("Fehlgeschlagener Multibranch Job erzeugt roten Status")
    void getStatusRed() {

        final JobStatusBeschreibung jobStatusBeschreibung = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_RED);
            final JobAbfrage requester = new JobAbfrage(jobAbfragedaten, "#1");
            return requester.getJobStatus();
        });
        assertNotNull(jobStatusBeschreibung);
        assertNotNull(jobStatusBeschreibung.getJobStatus());
        assertNotNull(jobStatusBeschreibung.getJobName());
        assertEquals(JobStatus.FAILURE.getColor(), jobStatusBeschreibung.getJobStatus().getColor());
    }

    @Test
    @DisplayName("Erfolgreicher Multibranch Job erzeugt grünen Status")
    void getStatusGreen() {
        final JobStatusBeschreibung jobStatusBeschreibung = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_GREEN);
            final JobAbfrage requester = new JobAbfrage(jobAbfragedaten, "#1");
            return requester.getJobStatus();
        });
        assertNotNull(jobStatusBeschreibung);
        assertNotNull(jobStatusBeschreibung.getJobStatus());
        assertNotNull(jobStatusBeschreibung.getJobName());
        assertEquals(JobStatus.SUCCESS.getColor(), jobStatusBeschreibung.getJobStatus().getColor());
    }

    @Test
    @DisplayName("Instabiler Multibranch Job erzeugt gelben Status")
    void getStatusYellow() {
        final JobStatusBeschreibung jobStatusBeschreibung = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_YELLOW);
            final JobAbfrage requester = new JobAbfrage(jobAbfragedaten, "#1");
            return requester.getJobStatus();
        });
        assertNotNull(jobStatusBeschreibung);
        assertNotNull(jobStatusBeschreibung.getJobStatus());
        assertNotNull(jobStatusBeschreibung.getJobName());
        assertEquals(JobStatus.UNSTABLE.getColor(), jobStatusBeschreibung.getJobStatus().getColor());
    }

    @Test
    @DisplayName("Unbekanter  Multibranch Job Status erzeugt grauen Status")
    void getStatusGray() {
        final JobStatusBeschreibung jobStatusBeschreibung = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_GRAY_UNKNOW);
            final JobAbfrage requester = new JobAbfrage(jobAbfragedaten, "#1");
            return requester.getJobStatus();
        });
        assertNotNull(jobStatusBeschreibung);
        assertNotNull(jobStatusBeschreibung.getJobStatus());
        assertNotNull(jobStatusBeschreibung.getJobName());
        assertEquals(JobStatus.OTHER.getColor(), jobStatusBeschreibung.getJobStatus().getColor());
    }


    @Test
    @DisplayName("Die Statusabfrage eines roten Build Jobs gibt ein valides JSON zurück")
    void getValidJsonRed() {
        final JSONObject json = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_RED);
            final JobAbfrage requester = new JobAbfrage(jobAbfragedaten, "#");
            return requester.sendGetRequest();
        });
        assertNotNull(json);
        assertEquals("mypocketmod » master #2", json.get("fullDisplayName"));
        assertEquals("FAILURE", json.get("result"));
    }

    @Test
    @DisplayName("Die Statusabfrage eines grünen Build Jobs gibt ein valides JSON zurück")
    void getValidJsonGreen() {
        final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_GREEN, null);
        final JobAbfrage requester = new JobAbfrage(jobAbfragedaten, "#1");
        final BuildInfo json = assertDoesNotThrow(() -> requester.sendGetRequest());
        assertNotNull(json);
        assertEquals("mypocketmod \u00bb master #2", json.fullDisplayName()); //get("fullDisplayName"));
        assertEquals("SUCCESS", json.result()); //get("result"));
    }

    @Test
    @DisplayName("Die Statusabfrage eines gelben Build Jobs gibt ein valides JSON zurück")
    void getValidJsonYellow() {
        final BuildInfo json = assertDoesNotThrow(() -> {
            final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_YELLOW, null);
            final JobAbfrage requester = new JobAbfrage(jobAbfragedaten, "#1");
            return requester.sendGetRequest();
        });
        assertNotNull(json);
        assertEquals("mypocketmod » master #2", json.fullDisplayName()); //get("fullDisplayName"));
        assertEquals("UNSTABLE", json.result()); //get("result"));
    }

    @Test
    @DisplayName("Die Statusabfrage eines unbekannten Build Jobs gibt KEIN valides JSON zurück")
    void getValidJsonGray() {
        final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(JOB_URL_MULTIBRANCH_JOB1_GRAY_UNKNOW, null);
        final JobAbfrage requester = new JobAbfrage(jobAbfragedaten, "#1");
        JobNotFoundException exception = assertThrows(JobNotFoundException.class, () -> {
            requester.sendGetRequest();
            fail();
        });
        assertNotNull(exception);
    }


}
