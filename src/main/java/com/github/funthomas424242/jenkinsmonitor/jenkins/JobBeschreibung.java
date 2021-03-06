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
import org.jetbrains.annotations.NotNull;

public final class JobBeschreibung implements AbstractJobBeschreibung {

    protected final String jobOrderId;

    @NotNull
    protected final JobAbfragedaten jobAbfragedaten;


    public JobBeschreibung(final JobAbfragedaten jobAbfragedaten) {
        this(null, jobAbfragedaten);
    }

    public JobBeschreibung(final String jobOrderId, final JobAbfragedaten jobAbfragedaten) {
        if (jobAbfragedaten == null || jobAbfragedaten.getJenkinsJobUrl() == null)
            throw new IllegalArgumentException("URL darf nicht null sein.");
        this.jobOrderId = jobOrderId;
        this.jobAbfragedaten = jobAbfragedaten;
    }

    @Override
    public URL getJobUrl() {
        return this.jobAbfragedaten.getJenkinsJobUrl();
    }

    @Override
    public String getJobOrderId() {
        return this.jobOrderId;
    }


    public JobAbfragedaten getJobAbfragedaten() {
        return this.jobAbfragedaten;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobBeschreibung that = (JobBeschreibung) o;
        return Objects.equals(jobOrderId, that.jobOrderId) &&
                jobAbfragedaten.equals(that.jobAbfragedaten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobOrderId, jobAbfragedaten);
    }

}

