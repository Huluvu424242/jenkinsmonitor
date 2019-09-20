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


    protected static URL STATUS_URL_MULTIBRANCH_JOB1;

    WireMockServer wireMockServer;

    @BeforeAll
    static void setUp() throws MalformedURLException {
        STATUS_URL_MULTIBRANCH_JOB1 = new URL("http://localhost:8099/job/multibranchjobred/job/master/lastBuild/api/json");
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
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/job/multibranchjobred/job/master/lastBuild/api/json"))
            .willReturn(WireMock.aResponse().withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBodyFile("json/multibranch-job1-red.json")));
    }

    @Test
    @Tag("compatibillity")
    @DisplayName("Kein echter Test nur GemockterClient testet gemockten Server -> API Compatibillity Test")
    public void testStatusCodePositive() {
            when().
            get(STATUS_URL_MULTIBRANCH_JOB1).
            then().
            statusCode(200).
            body("fullDisplayName", Matchers.equalTo("mypocketmod » master #2"),
                "result", Matchers.equalTo("FAILURE"));

    }

    @Test
    @DisplayName("Valid Request erzeugt gültigen Response")
    protected void validInstanz(){
        final JenkinsJobStatusRequester requester = new JenkinsJobStatusRequester();
        assumeTrue(requester!=null);
        assertNotNull(requester.getJobStatus(STATUS_URL_MULTIBRANCH_JOB1));
    }

}
