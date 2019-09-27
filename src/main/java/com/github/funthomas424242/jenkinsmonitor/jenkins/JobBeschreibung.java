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

    protected String jobId;

    protected final URL jobUrl;


    public JobBeschreibung(final URL jobUrl) {
        this(null, jobUrl);
    }

    public JobBeschreibung(final String jobId, final URL jobUrl) {
        if (jobUrl == null) throw new IllegalArgumentException("URL darf nicht null sein.");
        this.jobId = jobId;
        this.jobUrl = jobUrl;
    }

    public URL getJobUrl() {
        return this.jobUrl;
    }

    public String getJobId() {
        return this.jobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobBeschreibung that = (JobBeschreibung) o;
        return jobUrl.equals(that.jobUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobUrl);
    }
}
