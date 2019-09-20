package com.github.funthomas424242.jenkinsmonitor;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class JenkinsAPIMock {


    public static String STATUS_PATH_MULTIBRANCH_JOB1 = "/job/multibranchjobred/job/master/lastBuild/api/json";


    public static void definiereAnnahmen(WireMockServer jenkins) {
        jenkins.stubFor(WireMock.get(WireMock.urlEqualTo(STATUS_PATH_MULTIBRANCH_JOB1))
            .willReturn(WireMock.aResponse().withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBodyFile("json/multibranch-job1-red.json")));
    }


}
