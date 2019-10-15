package com.github.funthomas424242.jenkinsmonitor.etc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class BasicAuthDaten {

    transient protected final Logger LOGGER = LoggerFactory.getLogger(BasicAuthDaten.class);

    protected String userName;

    protected String password;

    public BasicAuthDaten(final String userName, final String password) {
        if (userName == null || password == null) {
            throw new IllegalArgumentException("Nutzername und Passwort dürfen nicht leer sein");
        }
        this.userName = userName;
        this.password = password;
    }

    public String getBasicAuthToken(String password) {
        String encodedToken = null;
        try {
            if (password != null && password.equals(this.password)) {
                encodedToken = Base64.getEncoder().encodeToString(String.format("%s:%s", this.userName, password).getBytes("utf-8"));
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Nichtunterstütztes Encoding utf-8 für das Verschlüsseln der Zugangsdaten angefordert");
        }
        return encodedToken;
    }

}
