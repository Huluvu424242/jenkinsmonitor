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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Objects;

public class JenkinsZugangsdaten {

    transient protected final Logger LOGGER = LoggerFactory.getLogger(JenkinsZugangsdaten.class);

    protected final URL jenkinsJobUrl;
    protected final String userName;
    protected final String password;

    public JenkinsZugangsdaten(final URL jenkinsJobUrl) {
        this(jenkinsJobUrl, null, null);
    }

    public JenkinsZugangsdaten(final URL jenkinsJobUrl, final String userName, String password) {
        this.jenkinsJobUrl = jenkinsJobUrl;
        this.userName = userName;
        this.password = password;
    }

    public URL getStatusAbfrageUrl() {
        try {
            return new URL(jenkinsJobUrl.toExternalForm() + JenkinsAPI.STATUS_PATH);
        } catch (MalformedURLException e) {
            LOGGER.error("Generierter Statusabfrage URL ist ungültig", e);
        }
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
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

    public URL getJenkinsJobUrl() {
        return jenkinsJobUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JenkinsZugangsdaten that = (JenkinsZugangsdaten) o;
        return jenkinsJobUrl.equals(that.jenkinsJobUrl) &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jenkinsJobUrl, userName, password);
    }
}
