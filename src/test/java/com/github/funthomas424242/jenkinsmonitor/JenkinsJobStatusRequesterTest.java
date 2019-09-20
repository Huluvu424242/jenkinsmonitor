package com.github.funthomas424242.jenkinsmonitor;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.net.MalformedURLException;
import java.net.URL;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static io.restassured.RestAssured.when;
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
    @DisplayName("Valid Request erzeugt gültigen Response")
    protected void validInstanz(){
        final JenkinsJobStatusRequester requester = new JenkinsJobStatusRequester();
        assumeTrue(requester!=null);
        assertNotNull(requester.getJobStatus(STATUS_URL_MULTIBRANCH_JOB1_RED));
    }

}
