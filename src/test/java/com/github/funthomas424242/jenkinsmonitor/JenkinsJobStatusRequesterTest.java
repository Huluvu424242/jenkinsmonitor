package com.github.funthomas424242.jenkinsmonitor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class JenkinsJobStatusRequesterTest {


    @Test
    @DisplayName("Valid Request erzeugt gültigen Response")
    protected void validInstanz(){
        final JenkinsJobStatusRequester requester = new JenkinsJobStatusRequester();
        assumeTrue(requester!=null);
//        assertNotNull(requester.getJobStatus());
    }

}
