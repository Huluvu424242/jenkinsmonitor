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

import java.net.URL;
import java.util.Objects;

public final class JobBeschreibung {

    protected final String jobId;

    protected final AbfrageDaten abfrageDaten;


    public JobBeschreibung(final AbfrageDaten abfrageDaten) {
        this(null, abfrageDaten);
    }

    public JobBeschreibung(final String jobId, final AbfrageDaten abfrageDaten) {
        if (abfrageDaten == null || abfrageDaten.getJenkinsJobUrl() == null) throw new IllegalArgumentException("URL darf nicht null sein.");
        this.jobId = jobId;
        this.abfrageDaten = abfrageDaten;
    }

    public URL getJobUrl() {
        return this.abfrageDaten.getJenkinsJobUrl();
    }

    public String getJobId() {
        return this.jobId;
    }


    public AbfrageDaten getAbfrageDaten() {
        return this.abfrageDaten;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobBeschreibung that = (JobBeschreibung) o;
        return abfrageDaten.equals(that.abfrageDaten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abfrageDaten);
    }
}

