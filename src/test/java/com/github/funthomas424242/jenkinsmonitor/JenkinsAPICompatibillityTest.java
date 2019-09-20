package com.github.funthomas424242.jenkinsmonitor;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Tag("compatibillity")
public class JenkinsAPICompatibillityTest {


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
    @DisplayName("Kein echter Test nur GemockterClient testet gemockten Server -> API Compatibillity Test")
    public void testStatusCodePositive() {
            when().
            get(STATUS_URL_MULTIBRANCH_JOB1_RED).
            then().
            statusCode(200).
            body("fullDisplayName", Matchers.equalTo("mypocketmod » master #2"),
                "result", Matchers.equalTo("FAILURE"));

    }



}
