package com.github.funthomas424242.jenkinsmonitor.jenkins;

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

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class JobStatusBeschreibungTest {

    protected static final String NAME_JOB1 = "job1";
    protected static URL LOCALHOST_JOB_TEST_URL;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        new URL("http://localhost:8099/job/test");
        // TODO prüfen - tut nix
        // LOCALHOST_JOB_TEST_URL = new URL("http://localhost:8099/job/test");
    }

    @Test
    @DisplayName("Prüfe equals und hashCode")
    protected void checkEqualsAndHashCode() {
        assertDoesNotThrow(() -> {
            EqualsVerifier.forClass(JobStatusBeschreibung.class).verify();
        });
    }


    @Test
    @DisplayName("Es wird eine gültige Instanz erstellt")
    public void valideInitialisierung() {
        final JobStatusBeschreibung JobStatusBeschreibung = new JobStatusBeschreibung(null
            , null
            , null, "#1");
        assertNotNull(JobStatusBeschreibung);
        assertNull(JobStatusBeschreibung.getJobStatus());
        assertNull(JobStatusBeschreibung.getJobUrl());
        assertEquals(JobStatus.OTHER.getColor(), JobStatusBeschreibung.getStatusColor());
    }

    @Test
    @DisplayName("Statusfarbe eines erfolgreichen Jobs ist grün")
    public void erfolgreicheJobsSindGruen() {
        final JobStatusBeschreibung JobStatusBeschreibung = new JobStatusBeschreibung(NAME_JOB1
            , JobStatus.SUCCESS
            , LOCALHOST_JOB_TEST_URL, "#1");
        assertNotNull(JobStatusBeschreibung);
        assertEquals(JobStatus.SUCCESS.getColor(), JobStatusBeschreibung.getStatusColor());
    }

    @Test
    @DisplayName("Statusfarbe eines instabilen Jobs ist gelb")
    public void instabileJobsSindGelb() {
        final JobStatusBeschreibung JobStatusBeschreibung = new JobStatusBeschreibung(NAME_JOB1
            , JobStatus.UNSTABLE
            , LOCALHOST_JOB_TEST_URL, "#1");
        assertNotNull(JobStatusBeschreibung);
        assertEquals(JobStatus.UNSTABLE.getColor(), JobStatusBeschreibung.getStatusColor());
    }

    @Test
    @DisplayName("Statusfarbe eines fehlerhaften Jobs ist rot")
    public void fehlerhafteJobsSindRot() {
        final JobStatusBeschreibung JobStatusBeschreibung = new JobStatusBeschreibung(NAME_JOB1
            , JobStatus.FAILURE
            , LOCALHOST_JOB_TEST_URL, "#1");
        assertNotNull(JobStatusBeschreibung);
        assertEquals(JobStatus.FAILURE.getColor(), JobStatusBeschreibung.getStatusColor());
    }

}
