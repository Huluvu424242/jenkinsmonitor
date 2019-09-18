package com.github.funthomas424242.jenkinsmonitor;

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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class JobStatusTest {

    public static final String NAME_JOB1 = "job1";
    protected static URL LOCALHOST_JOB_TEST_URL;

    @BeforeAll
    static void  setUp() throws MalformedURLException {
        new URL("http://localhost:8090/job/test");
    }

    @Test
    @DisplayName("Es wird eine gültige Instanz erstellt")
    void valideInitialisierung() {
        final JobStatus jobStatus = new JobStatus(null
            , null
            , null);
        assertNotNull(jobStatus);
//        assertNull(jobStatus.get)
        assertEquals(Color.gray,jobStatus.getStatusColor());
    }

    @Test
    @DisplayName("Statusfarbe eines erfolgreichen Jobs ist grün")
    void erfolgreicheJobsSindGruen() {
        final JobStatus jobStatus = new JobStatus(NAME_JOB1
            , JobStatus.Status.SUCCESS
            , LOCALHOST_JOB_TEST_URL);
        assertNotNull(jobStatus);
        assertEquals(Color.green,jobStatus.getStatusColor());
    }

    @Test
    @DisplayName("Statusfarbe eines instabilen Jobs ist gelb")
    void instabileJobsSindGelb() {
        final JobStatus jobStatus = new JobStatus(NAME_JOB1
            , JobStatus.Status.INSTABIL
            , LOCALHOST_JOB_TEST_URL);
        assertNotNull(jobStatus);
        assertEquals(Color.yellow,jobStatus.getStatusColor());
    }

    @Test
    @DisplayName("Statusfarbe eines fehlerhaften Jobs ist rot")
    void fehlerhafteJobsSindRot() {
        final JobStatus jobStatus = new JobStatus(NAME_JOB1
            , JobStatus.Status.FAILED
            , LOCALHOST_JOB_TEST_URL);
        assertNotNull(jobStatus);
        assertEquals(Color.red,jobStatus.getStatusColor());
    }
}
