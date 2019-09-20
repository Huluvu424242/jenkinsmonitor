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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


public class JenkinsJobStatusRequesterTest {


    protected static URL STATUS_URL_MULTIBRANCH_JOB1_RED;

    WireMockServer wireMockServer;

    @BeforeAll
    static void setUp() throws MalformedURLException {
        STATUS_URL_MULTIBRANCH_JOB1_RED = new URL(JenkinsAPIMock.STATUS_URL_MULTIBRANCH_JOB1_RED);
    }

    @BeforeEach
    public void setup () {
        wireMockServer = new WireMockServer(8099);
        wireMockServer.start();
        JenkinsAPIMock.definiereAnnahmen(wireMockServer);
    }

    @AfterEach
    public void teardown () {
        wireMockServer.stop();
    }



    @Test
    @DisplayName("Fehlgeschlagener Multibranch Job erzeugt roten Status")
    protected void validInstanz(){
        final JenkinsJobStatusRequester requester = new JenkinsJobStatusRequester();
        assumeTrue(requester!=null);
        final JobBeschreibung jobBeschreibung = requester.getJobStatus(STATUS_URL_MULTIBRANCH_JOB1_RED);
        assertNotNull(jobBeschreibung);
        assertNotNull(jobBeschreibung.getJobStatus());
        assertNotNull(jobBeschreibung.getJobName());
        assertEquals(JobStatus.FAILED.getColor(),jobBeschreibung.getJobStatus().getColor());
    }

}
