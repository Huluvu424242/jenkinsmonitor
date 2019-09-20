package com.github.funthomas424242.jenkinsmonitor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class JenkinsJobStatusRequesterTest {


    protected static URL urlMultibranchJob1;

    @BeforeAll
    static void setUp() throws MalformedURLException {
        urlMultibranchJob1 = new URL("http://stachel:8090/job/multibranchjob1/job/master");
    }

    @Test
    @DisplayName("Valid Request erzeugt gültigen Response")
    protected void validInstanz(){
        final JenkinsJobStatusRequester requester = new JenkinsJobStatusRequester();
        assumeTrue(requester!=null);
        assertNotNull(requester.getJobStatus(urlMultibranchJob1));
    }

}
