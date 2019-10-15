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

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class JobAbfragedatenTest {

    @Test
    @DisplayName("Prüfe das alle Daten als Properties enthalten sind")
    public void containsAllProperties() {
        final JobAbfragedaten jobAbfragedaten = assertDoesNotThrow(() -> {
            return new JobAbfragedaten(
                new URL("http://localhost:8080/"), new BasicAuthDaten(
                "Nutzername",
                "Passwort")

            );
        });
        assertNotNull(jobAbfragedaten.getStatusAbfrageUrl());
        assertEquals(new BasicAuthDaten("Nutzername", "Passwort"), jobAbfragedaten.authDaten);
        assertNotNull(jobAbfragedaten.getBasicAuthToken());
    }

    @Test
    @Disabled
    @DisplayName("Das BasicAuth Token wird korrekt mit base64 kodiert")
    public void getBase64CodedToken() {

        final JobAbfragedaten jobAbfragedaten = assertDoesNotThrow(() -> {
            return new JobAbfragedaten(
                new URL("http://localhost:8080/"), new BasicAuthDaten(
                "Nutzername",
                "Passwort")
            );
        });
        try {
            final String expectedToken = Base64.getEncoder().encodeToString("Nutzername:Passwort".getBytes("utf-8"));
            assertEquals(expectedToken, jobAbfragedaten.getBasicAuthToken());
        } catch (UnsupportedEncodingException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Das BasicAuth Token wird bei gültigem Passwort zurückgegeben")
    public void getTokenWithValidPassword() {

        final JobAbfragedaten jobAbfragedaten = assertDoesNotThrow(() -> {
            return new JobAbfragedaten(
                new URL("http://localhost:8080/"), new BasicAuthDaten(
                "Nutzername",
                "Passwort")
            );
        });
        assertNotNull(jobAbfragedaten.getStatusAbfrageUrl());
        assertEquals(new BasicAuthDaten("Nutzername", "Passwort"), jobAbfragedaten.authDaten);
        assertNotNull(jobAbfragedaten.getBasicAuthToken());
    }


    // TODO later
//    @Test
//    @DisplayName("Als BasicAuth Token wird null bei ungültigem Passwort zurückgegeben")
//    public void getNullWithInValidPassword() {
//
//        final JobAbfragedaten jobAbfragedaten = assertDoesNotThrow(() -> {
//            return new JobAbfragedaten(
//                new URL("http://localhost:8080/"), new BasicAuthDaten(
//                "Nutzername",
//                "Passwort")
//            );
//        });
//        assertNull(jobAbfragedaten.getBasicAuthToken("blah blup falsch"));
//    }

}
