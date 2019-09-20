package com.github.funthomas424242.jenkinsmonitor;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;

import java.net.MalformedURLException;
import java.net.URL;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


public class JenkinsJobStatusRequesterTest {


    protected static URL URL_MULTIBRANCH_JOB1;

    WireMockServer wireMockServer;

    @BeforeAll
    static void setUp() throws MalformedURLException {
        URL_MULTIBRANCH_JOB1 = new URL("http://stachel:8099/job/multibranchjob1/job/master");
    }

    @BeforeEach
    public void setup () {
        wireMockServer = new WireMockServer(8099);
        wireMockServer.start();
        setupStub();
    }

    @AfterEach
    public void teardown () {
        wireMockServer.stop();
    }

    public void setupStub() {
        wireMockServer.stubFor(get(urlEqualTo("/job/mypocketmod/job/master/lastBuild/api/json"))
            .willReturn(aResponse().withHeader("Content-Type", "text/plain")
                .withStatus(200)
                .withBodyFile("json/multibranch-job1-red.json")));
    }

    @Test
    public void testStatusCodePositive() {
        given().
            when().
            get("http://localhost:8099/job/mypocketmod/job/master/lastBuild/api/json").
            then().
            assertThat().statusCode(200);
    }

    @Test
    @DisplayName("Valid Request erzeugt gültigen Response")
    protected void validInstanz(){
        final JenkinsJobStatusRequester requester = new JenkinsJobStatusRequester();
        assumeTrue(requester!=null);
        assertNotNull(requester.getJobStatus(URL_MULTIBRANCH_JOB1));
    }

}
