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

import java.awt.*;
import java.net.URL;
import java.util.Objects;

public final class JobStatusBeschreibung {


    protected final JobStatus jobStatus;

    protected final String jobName;

    protected final URL jobUrl;


    public JobStatusBeschreibung(final String jobName, final JobStatus jobStatus, final URL jobUrl) {
        this.jobStatus = jobStatus;
        this.jobName = jobName;
        this.jobUrl = jobUrl;
    }

    public Color getStatusColor() {
        if (jobStatus == null) return JobStatus.OTHER.getColor();
        return jobStatus.getColor();
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public URL getJobUrl() {
        return this.jobUrl;
    }

    public String getJobName() {
        return this.jobName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobStatusBeschreibung that = (JobStatusBeschreibung) o;
        return jobStatus == that.jobStatus &&
            Objects.equals(jobName, that.jobName) &&
            Objects.equals(jobUrl, that.jobUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobStatus, jobName, jobUrl);
    }
}
