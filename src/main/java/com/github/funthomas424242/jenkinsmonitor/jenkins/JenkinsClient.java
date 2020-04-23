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

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;


public class JenkinsClient {

    protected static final Logger LOG = LoggerFactory.getLogger(JenkinsClient.class);

    public void ladeJobsStatus(final AbstractJobBeschreibungen<JobStatusBeschreibung> jobStatusBeschreibungen, final JobBeschreibungen jobBeschreibungen) {
        LOG.debug("Frage Jobstatus ab");
        final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        AbstractJobBeschreibung.sortedStreamOf(jobBeschreibungen)
            .parallel()
            .map(beschreibung -> {
                final JobAbfrage jobAbfrage
                    = new JobAbfrage(jobStatusBeschreibungen, beschreibung.getJobAbfragedaten(), beschreibung.getJobOrderId());
                return executor.submit(jobAbfrage);
            })
            .collect(Collectors.toList())
            .forEach(jobStatusFuture -> {
                try {
                    final JobStatusBeschreibung jobStatus = jobStatusFuture.get();
                    jobStatusBeschreibungen.put(jobStatus.getPrimaryKey(), jobStatus);
                    LOG.debug(String.format("JobStatus geladen: %s : %s  at %s ", jobStatus.getJobName(), jobStatus.getJobStatus().toString(), jobStatus.getJobUrl().toExternalForm()));
                } catch (Exception e) {
                    LOG.warn("Read Future Result goes wrong.");
                }
            });
        executor.shutdown();
    }

}
