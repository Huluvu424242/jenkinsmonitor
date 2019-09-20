package com.github.funthomas424242.jenkinsmonitor;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class JenkinsAPIMock {


    public static String STATUSPATH_MULTIBRANCH_JOB1_RED = "/job/multibranchjobred/job/master/lastBuild/api/json";
    public static String STATUSPATH_MULTIBRANCH_JOB1_GREEN = "/job/multibranchjobgreen/job/master/lastBuild/api/json";
    public static String STATUSPATH_MULTIBRANCH_JOB1_YELLOW = "/job/multibranchjobyellow/job/master/lastBuild/api/json";
    public static String STATUSPATH_MULTIBRANCH_JOB1_GRAY = "/job/multibranchjobgray/job/master/lastBuild/api/json";


    public static void definiereAnnahmen(WireMockServer jenkins) {
        jenkins.stubFor(WireMock.get(WireMock.urlEqualTo(STATUSPATH_MULTIBRANCH_JOB1_RED))
            .willReturn(WireMock.aResponse().withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBodyFile("json/multibranch-job1-red.json")));
        jenkins.stubFor(WireMock.get(WireMock.urlEqualTo(STATUSPATH_MULTIBRANCH_JOB1_GREEN))
            .willReturn(WireMock.aResponse().withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBodyFile("json/multibranch-job1-green.json")));
        jenkins.stubFor(WireMock.get(WireMock.urlEqualTo(STATUSPATH_MULTIBRANCH_JOB1_YELLOW))
            .willReturn(WireMock.aResponse().withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBodyFile("json/multibranch-job1-yellow.json")));
        jenkins.stubFor(WireMock.get(WireMock.urlEqualTo(STATUSPATH_MULTIBRANCH_JOB1_GRAY))
            .willReturn(WireMock.aResponse().withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBodyFile("json/multibranch-job1-gray.json")));
    }


}
