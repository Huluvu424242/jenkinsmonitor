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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class JobAbfragedaten {

    transient protected final Logger LOGGER = LoggerFactory.getLogger(JobAbfragedaten.class);

    protected final URL jenkinsJobUrl;
    protected final BasicAuthDaten authDaten;


    public JobAbfragedaten(final URL jenkinsJobUrl) {
        this(jenkinsJobUrl, (BasicAuthDaten) null);
    }

    public JobAbfragedaten(final URL jenkinsJobUrl, final BasicAuthDaten authDaten) {
        if (jenkinsJobUrl == null) {
            throw new IllegalArgumentException("job url kann nicht leer sein");
        }
        this.jenkinsJobUrl = jenkinsJobUrl;
        this.authDaten = authDaten;
    }

    public String getBasicAuthToken() {
        if (authDaten == null) {
            return null;
        } else {
            return authDaten.getBasicAuthToken(authDaten.password);
        }
    }

    public URL getStatusAbfrageUrl() {
        try {
            return new URL(jenkinsJobUrl.toExternalForm() + JenkinsAPI.STATUS_PATH);
        } catch (MalformedURLException e) {
            LOGGER.error("Generierter Statusabfrage URL ist ungültig", e);
        }
        return null;
    }

    public URL getJenkinsJobUrl() {
        return jenkinsJobUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobAbfragedaten that = (JobAbfragedaten) o;
        return jenkinsJobUrl.equals(that.jenkinsJobUrl) &&
            Objects.equals(authDaten, that.authDaten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jenkinsJobUrl, authDaten);
    }


}
