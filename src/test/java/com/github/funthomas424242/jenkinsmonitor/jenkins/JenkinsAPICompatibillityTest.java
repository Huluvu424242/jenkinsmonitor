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

import com.github.tomakehurst.wiremock.WireMockServer;
import java.net.MalformedURLException;
import java.net.URL;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Tag("compatibillity")
class JenkinsAPICompatibillityTest {


    protected static URL STATUS_URL_MULTIBRANCH_JOB1_RED;
    protected static URL STATUS_URL_MULTIBRANCH_JOB1_GREEN;
    protected static URL STATUS_URL_MULTIBRANCH_JOB1_YELLOW;
    protected static URL STATUS_URL_MULTIBRANCH_JOB1_GRAY_BUILDING;
    protected static URL STATUS_URL_MULTIBRANCH_JOB1_GRAY_UNKNOW;

    protected WireMockServer wireMockServer;

    @BeforeAll
    protected static void setUpAll() throws MalformedURLException {
        STATUS_URL_MULTIBRANCH_JOB1_RED = new URL(JenkinsAPIMock.STATUS_URL_MULTIBRANCH_JOB1_RED);
        STATUS_URL_MULTIBRANCH_JOB1_GREEN = new URL(JenkinsAPIMock.STATUS_URL_MULTIBRANCH_JOB1_GREEN);
        STATUS_URL_MULTIBRANCH_JOB1_YELLOW = new URL(JenkinsAPIMock.STATUS_URL_MULTIBRANCH_JOB1_YELLOW);
        STATUS_URL_MULTIBRANCH_JOB1_GRAY_BUILDING = new URL(JenkinsAPIMock.STATUS_URL_MULTIBRANCH_JOB1_GRAY_BUILDING);
        STATUS_URL_MULTIBRANCH_JOB1_GRAY_UNKNOW = new URL(JenkinsAPIMock.STATUS_URL_MULTIBRANCH_JOB1_GRAY_UNKNOW);
    }

    @BeforeEach
    protected void setUp() {
        wireMockServer = new WireMockServer(8099);
        wireMockServer.start();
        JenkinsAPIMock.definiereAnnahmen(wireMockServer);
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }


    @Test
    @DisplayName("Jenkins API Compatibillity: Statusabfrage roter Multibranch Job")
    void statusMultibranchJobRot() {
        assertDoesNotThrow(() -> {
            when().
                    get(STATUS_URL_MULTIBRANCH_JOB1_RED).
                    then().
                    statusCode(200).
                    body("fullDisplayName", Matchers.equalTo("mypocketmod » master #2"),
                            "result", Matchers.equalTo("FAILURE"));
        });
    }

    @Test
    @DisplayName("Jenkins API Compatibillity: Statusabfrage grüner Multibranch Job")
    void statusMultibranchJobGruen() {
        assertDoesNotThrow(() -> {
            when().
                    get(STATUS_URL_MULTIBRANCH_JOB1_GREEN).
                    then().
                    statusCode(200).
                    body("fullDisplayName", Matchers.equalTo("mypocketmod » master #2"),
                            "result", Matchers.equalTo("SUCCESS"));
        });
    }

    @Test
    @DisplayName("Jenkins API Compatibillity: Statusabfrage gelber Multibranch Job")
    void statusMultibranchJobGelb() {
        assertDoesNotThrow(() -> {
            when().
                    get(STATUS_URL_MULTIBRANCH_JOB1_YELLOW).
                    then().
                    statusCode(200).
                    body("fullDisplayName", Matchers.equalTo("mypocketmod » master #2"),
                            "result", Matchers.equalTo("UNSTABLE"));
        });

    }

    @Test
    @DisplayName("Jenkins API Compatibillity: Statusabfrage im Build befindlicher Multibranch Job")
    void statusMultibranchJobGrauBuildend() {
        assertDoesNotThrow(() -> {
            when().
                    get(STATUS_URL_MULTIBRANCH_JOB1_GRAY_BUILDING).
                    then().
                    statusCode(200).
                    body("building", Matchers.equalTo(true));
        });
    }

    @Test
    @DisplayName("Jenkins API Compatibillity: Statusabfrage unbekannter Multibranch Job")
    void statusMultibranchJobGrauUnbekannt() {
        assertDoesNotThrow(() -> {
            when().
                    get(STATUS_URL_MULTIBRANCH_JOB1_GRAY_UNKNOW).
                    then().
                    statusCode(404);
        });
    }


}
