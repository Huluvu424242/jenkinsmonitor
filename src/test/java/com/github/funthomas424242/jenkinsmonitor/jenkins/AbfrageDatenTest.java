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

import static org.junit.jupiter.api.Assertions.*;

class AbfrageDatenTest {

    @Test
    @DisplayName("Prüfe das alle Daten als Properties enthalten sind")
    public void containsAllProperties() {
        final AbfrageDaten abfrageDaten = assertDoesNotThrow(() -> {
            return new AbfrageDaten(
                new URL("http://localhost:8080/"),
                "Nutzername",
                "Passwort"

            );
        });
        assertNotNull(abfrageDaten.getStatusAbfrageUrl());
        assertEquals("Nutzername", abfrageDaten.getUserName());
        assertEquals("Passwort", abfrageDaten.getPassword());
        assertNotNull(abfrageDaten.getBasicAuthToken(abfrageDaten.getPassword()));
    }

    @Test
    @Disabled
    @DisplayName("Das BasicAuth Token wird korrekt mit base64 kodiert")
    public void getBase64CodedToken() {

        final AbfrageDaten abfrageDaten = assertDoesNotThrow(() -> {
            return new AbfrageDaten(
                new URL("http://localhost:8080/"),
                "Nutzername",
                "Passwort"
            );
        });
        try {
            final String expectedToken = Base64.getEncoder().encodeToString("Nutzername:Passwort".getBytes("utf-8"));
            assertNotNull(abfrageDaten.getBasicAuthToken(abfrageDaten.getPassword()));
        } catch (UnsupportedEncodingException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Das BasicAuth Token wird bei gültigem Passwort zurückgegeben")
    public void getTokenWithValidPassword() {

        final AbfrageDaten abfrageDaten = assertDoesNotThrow(() -> {
            return new AbfrageDaten(
                new URL("http://localhost:8080/"),
                "Nutzername",
                "Passwort"
            );
        });
        assertNotNull(abfrageDaten.getStatusAbfrageUrl());
        assertEquals("Nutzername", abfrageDaten.getUserName());
        assertEquals("Passwort", abfrageDaten.getPassword());
        assertNotNull(abfrageDaten.getBasicAuthToken(abfrageDaten.getPassword()));
    }

    @Test
    @DisplayName("Als BasicAuth Token wird null bei ungültigem Passwort zurückgegeben")
    public void getNullWithInValidPassword() {

        final AbfrageDaten abfrageDaten = assertDoesNotThrow(() -> {
            return new AbfrageDaten(
                new URL("http://localhost:8080/"),
                "Nutzername",
                "Passwort"
            );
        });
        assertNull(abfrageDaten.getBasicAuthToken("blah blup falsch"));
    }

}
