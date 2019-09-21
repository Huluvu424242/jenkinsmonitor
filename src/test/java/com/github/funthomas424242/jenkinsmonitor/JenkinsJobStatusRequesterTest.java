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

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


public class JenkinsJobStatusRequesterTest {


    protected static URL JOB_URL_MULTIBRANCH_JOB1_RED;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_GREEN;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_YELLOW;
    protected static URL JOB_URL_MULTIBRANCH_JOB1_GRAY;

    WireMockServer wireMockServer;

    @BeforeAll
    static void setUp() throws MalformedURLException {
        JOB_URL_MULTIBRANCH_JOB1_RED = new URL(JenkinsAPIMock.JOB_URL_MULTIBRANCH_JOB1_RED);
        JOB_URL_MULTIBRANCH_JOB1_GREEN = new URL(JenkinsAPIMock.JOB_URL_MULTIBRANCH_JOB1_GREEN);
        JOB_URL_MULTIBRANCH_JOB1_YELLOW = new URL(JenkinsAPIMock.JOB_URL_MULTIBRANCH_JOB1_YELLOW);
        JOB_URL_MULTIBRANCH_JOB1_GRAY = new URL(JenkinsAPIMock.JOB_URL_MULTIBRANCH_JOB1_GRAY);
    }

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(8099);
        wireMockServer.start();
        JenkinsAPIMock.definiereAnnahmen(wireMockServer);
    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }


    @Test
    @DisplayName("Fehlgeschlagener Multibranch Job erzeugt roten Status")
    protected void getStatusRed() {

        final JenkinsJobStatusRequester requester = new JenkinsJobStatusRequester();
        assumeTrue(requester != null);
        final JobBeschreibung jobBeschreibung = assertDoesNotThrow(() -> {
            return requester.getJobStatus(JOB_URL_MULTIBRANCH_JOB1_RED);
        });
        assertNotNull(jobBeschreibung);
        assertNotNull(jobBeschreibung.getJobStatus());
        assertNotNull(jobBeschreibung.getJobName());
        assertEquals(JobStatus.FAILED.getColor(), jobBeschreibung.getJobStatus().getColor());
    }

    @Test
    @Disabled
    @DisplayName("Erfolgreicher Multibranch Job erzeugt grünen Status")
    protected void getStatusGreen() {
        final JenkinsJobStatusRequester requester = new JenkinsJobStatusRequester();
        assumeTrue(requester != null);
        final JobBeschreibung jobBeschreibung = assertDoesNotThrow(() -> {
            return requester.getJobStatus(JOB_URL_MULTIBRANCH_JOB1_GREEN);
        });
        assertNotNull(jobBeschreibung);
        assertNotNull(jobBeschreibung.getJobStatus());
        assertNotNull(jobBeschreibung.getJobName());
        assertEquals(JobStatus.SUCCESS.getColor(), jobBeschreibung.getJobStatus().getColor());
    }

}
