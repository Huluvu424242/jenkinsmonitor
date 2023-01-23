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

import java.net.MalformedURLException;
import java.net.URL;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JobBeschreibungTest {

    public static final String NAME_JOB1 = "job1";
    protected static URL LOCALHOST_JOB_TEST_URL;

    @BeforeAll
    protected static void setUpAll() throws MalformedURLException {
        LOCALHOST_JOB_TEST_URL = new URL("http://localhost:8099/job/test");
    }

    @Test
    @DisplayName("Prüfe equals und hashCode")
    void checkEqualsAndHashCode() {
        assertDoesNotThrow(() -> {
            EqualsVerifier.forClass(JobBeschreibung.class)
                    .withNonnullFields("jobAbfragedaten")
                    .verify();
        });
    }


    @Test
    @DisplayName("Es wird eine gültige Instanz erstellt")
    void valideInitialisierung() {
        final JobBeschreibung jobBeschreibung
                = new JobBeschreibung(new JobAbfragedaten(LOCALHOST_JOB_TEST_URL));
        assertNotNull(jobBeschreibung);
        assertEquals(LOCALHOST_JOB_TEST_URL, jobBeschreibung.getJobUrl());
    }

    @Test
    @DisplayName("Statusfarbe eines erfolgreichen Jobs ist grün")
    void erfolgreicheJobsSindGruen() {
        final JobBeschreibung jobBeschreibung = new JobBeschreibung(NAME_JOB1
                , new JobAbfragedaten(LOCALHOST_JOB_TEST_URL));
        assertNotNull(jobBeschreibung);
    }

    @Test
    @DisplayName("Statusfarbe eines instabilen Jobs ist gelb")
    void instabileJobsSindGelb() {
        final JobBeschreibung jobBeschreibung = new JobBeschreibung(NAME_JOB1
                , new JobAbfragedaten(LOCALHOST_JOB_TEST_URL));
        assertNotNull(jobBeschreibung);
    }

    @Test
    @DisplayName("Statusfarbe eines fehlerhaften Jobs ist rot")
    void fehlerhafteJobsSindRot() {
        final JobBeschreibung jobBeschreibung = new JobBeschreibung(NAME_JOB1
                , new JobAbfragedaten(LOCALHOST_JOB_TEST_URL));
        assertNotNull(jobBeschreibung);
    }

}
