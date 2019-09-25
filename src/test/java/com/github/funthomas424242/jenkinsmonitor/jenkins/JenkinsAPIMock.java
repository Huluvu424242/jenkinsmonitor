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
import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.funthomas424242.jenkinsmonitor.jenkins.JenkinsAPI.STATUS_PATH;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

public class JenkinsAPIMock {

    public static String JENKINS_ROOT = "http://localhost:8099";

    public static String JOBPATH_MULTIBRANCH_JOB1_RED = "/job/multibranchjobred/job/master";
    public static String STATUSPATH_MULTIBRANCH_JOB1_RED = JOBPATH_MULTIBRANCH_JOB1_RED + STATUS_PATH;
    public static String STATUS_URL_MULTIBRANCH_JOB1_RED = JENKINS_ROOT + STATUSPATH_MULTIBRANCH_JOB1_RED;
    public static String JOB_URL_MULTIBRANCH_JOB1_RED = JENKINS_ROOT + JOBPATH_MULTIBRANCH_JOB1_RED;
    public static String JOBPATH_MULTIBRANCH_JOB1_GREEN = "/job/multibranchjobgreen/job/master";
    public static String STATUSPATH_MULTIBRANCH_JOB1_GREEN = JOBPATH_MULTIBRANCH_JOB1_GREEN + STATUS_PATH;
    public static String STATUS_URL_MULTIBRANCH_JOB1_GREEN = JENKINS_ROOT + STATUSPATH_MULTIBRANCH_JOB1_GREEN;
    public static String JOB_URL_MULTIBRANCH_JOB1_GREEN = JENKINS_ROOT + JOBPATH_MULTIBRANCH_JOB1_GREEN;
    public static String JOBPATH_MULTIBRANCH_JOB1_YELLOW = "/job/multibranchjobyellow/job/master";
    public static String STATUSPATH_MULTIBRANCH_JOB1_YELLOW = JOBPATH_MULTIBRANCH_JOB1_YELLOW + STATUS_PATH;
    public static String STATUS_URL_MULTIBRANCH_JOB1_YELLOW = JENKINS_ROOT + STATUSPATH_MULTIBRANCH_JOB1_YELLOW;
    public static String JOB_URL_MULTIBRANCH_JOB1_YELLOW = JENKINS_ROOT + JOBPATH_MULTIBRANCH_JOB1_YELLOW;
    public static String JOBPATH_MULTIBRANCH_JOB1_GRAY = "/job/multibranchjobgray/job/master";
    public static String STATUSPATH_MULTIBRANCH_JOB1_GRAY = JOBPATH_MULTIBRANCH_JOB1_GRAY + STATUS_PATH;
    public static String STATUS_URL_MULTIBRANCH_JOB1_GRAY = JENKINS_ROOT + STATUSPATH_MULTIBRANCH_JOB1_GRAY;
    public static String JOB_URL_MULTIBRANCH_JOB1_GRAY = JENKINS_ROOT + JOBPATH_MULTIBRANCH_JOB1_GRAY;


    public static void definiereAnnahmen(WireMockServer jenkins) {
        jenkins.stubFor(get(WireMock.urlEqualTo(STATUSPATH_MULTIBRANCH_JOB1_RED))
            .willReturn(aResponse().withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBodyFile("json/multibranch-job1-red.json")));
        jenkins.stubFor(get(WireMock.urlEqualTo(STATUSPATH_MULTIBRANCH_JOB1_GREEN))
            .willReturn(aResponse().withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBodyFile("json/multibranch-job1-green.json")));
        jenkins.stubFor(get(WireMock.urlEqualTo(STATUSPATH_MULTIBRANCH_JOB1_YELLOW))
            .willReturn(aResponse().withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBodyFile("json/multibranch-job1-yellow.json")));
        jenkins.stubFor(get(WireMock.urlEqualTo(STATUSPATH_MULTIBRANCH_JOB1_GRAY))
            .willReturn(aResponse().withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBodyFile("json/multibranch-job1-gray.json")));
    }


}
