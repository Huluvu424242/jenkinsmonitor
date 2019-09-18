package com.github.funthomas424242.jenkinsmonitor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JobStatusTest {

    public static final String NAME_JOB1 = "job1";
    protected static URL LOCALHOST_JOB_TEST_URL;

    @BeforeAll
    void setUp() throws MalformedURLException {
        new URL("http://localhost:8090/job/test");
    }

    @Test
    @DisplayName("Es wird eine gültige Instanz erstellt")
    void valideInitialisierung() {
        final JobStatus jobStatus = new JobStatus(NAME_JOB1
            , JobStatus.Status.SUCCESS
            , LOCALHOST_JOB_TEST_URL);
        assertNotNull(jobStatus);
        assertEquals(Color.green,jobStatus.getStatusColor());
    }

}
