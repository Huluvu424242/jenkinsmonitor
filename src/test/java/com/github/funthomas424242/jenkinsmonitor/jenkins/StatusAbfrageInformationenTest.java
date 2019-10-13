package com.github.funthomas424242.jenkinsmonitor.jenkins;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class StatusAbfrageInformationenTest {

    @Test
    @DisplayName("Prüfe das alle Daten als Properties enthalten sind")
    public void containsAllProperties() {
        final StatusAbfrageInformationen statusAbfrageInformationen = assertDoesNotThrow(() -> {
            return new StatusAbfrageInformationen(
                new URL("http://localhost:8080/"),
                "Nutzername",
                "Passwort"

            );
        });
        assertNotNull(statusAbfrageInformationen.getStatusAbfrageUrl());
        assertEquals("Nutzername", statusAbfrageInformationen.getUserName());
        assertEquals("Passwort", statusAbfrageInformationen.getPassword());
        assertNotNull(statusAbfrageInformationen.getBasicAuthToken(statusAbfrageInformationen.getPassword()));
    }

    @Test
    @Disabled
    @DisplayName("Das BasicAuth Token wird korrekt mit base64 kodiert")
    public void getBase64CodedToken() {

        final StatusAbfrageInformationen statusAbfrageInformationen = assertDoesNotThrow(() -> {
            return new StatusAbfrageInformationen(
                new URL("http://localhost:8080/"),
                "Nutzername",
                "Passwort"
            );
        });
        try {
            final String expectedToken = Base64.getEncoder().encodeToString("Nutzername:Passwort".getBytes("utf-8"));
            assertNotNull(statusAbfrageInformationen.getBasicAuthToken(statusAbfrageInformationen.getPassword()));
        } catch (UnsupportedEncodingException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Das BasicAuth Token wird bei gültigem Passwort zurückgegeben")
    public void getTokenWithValidPassword() {

        final StatusAbfrageInformationen statusAbfrageInformationen = assertDoesNotThrow(() -> {
            return new StatusAbfrageInformationen(
                new URL("http://localhost:8080/"),
                "Nutzername",
                "Passwort"
            );
        });
        assertNotNull(statusAbfrageInformationen.getStatusAbfrageUrl());
        assertEquals("Nutzername", statusAbfrageInformationen.getUserName());
        assertEquals("Passwort", statusAbfrageInformationen.getPassword());
        assertNotNull(statusAbfrageInformationen.getBasicAuthToken(statusAbfrageInformationen.getPassword()));
    }

    @Test
    @Disabled
    @DisplayName("Als BasicAuth Token wird null bei ungültigem Passwort zurückgegeben")
    public void getNullWithInValidPassword() {

        final StatusAbfrageInformationen statusAbfrageInformationen = assertDoesNotThrow(() -> {
            return new StatusAbfrageInformationen(
                new URL("http://localhost:8080/"),
                "Nutzername",
                "Passwort"
            );
        });
        assertNull(statusAbfrageInformationen.getBasicAuthToken("blah blup falsch"));
    }

}
