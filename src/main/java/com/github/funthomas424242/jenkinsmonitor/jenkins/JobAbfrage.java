package com.github.funthomas424242.jenkinsmonitor.jenkins;

/*-
 * #%L
 * Jenkins Monitor
 * %%
 * Copyright (C) 2019 - 2020 PIUG
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

import java.util.Map;
import java.util.concurrent.Callable;

public class JobAbfrage implements Callable<JobStatusBeschreibung> {
    final Map<String, JobStatusBeschreibung> jobStatusBeschreibungen;
    final JobAbfragedaten jobAbfragedaten;
    final String jobOrderId;

    public JobAbfrage(final Map<String, JobStatusBeschreibung> jobStatusBeschreibungen, final JobAbfragedaten jobAbfragedaten, final String jobOrderId) {
        this.jobStatusBeschreibungen = jobStatusBeschreibungen;
        this.jobAbfragedaten = jobAbfragedaten;
        this.jobOrderId = jobOrderId;
    }

    @Override
    public JobStatusBeschreibung call() throws Exception {
        final JobStatusBeschreibung jobStatus =  JenkinsClient.getJobStatus(jobAbfragedaten, jobOrderId);
        this.jobStatusBeschreibungen.put(jobStatus.getPrimaryKey(), jobStatus);
        return jobStatus;
    }
}
