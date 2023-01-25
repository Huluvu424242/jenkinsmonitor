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

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JenkinsHttpClient {

    protected static final Logger LOG = LoggerFactory.getLogger(JenkinsHttpClient.class);

    public void ladeJobsStatus(final AbstractJobBeschreibungen<JobStatusBeschreibung> jobStatusBeschreibungen, final JobBeschreibungen jobBeschreibungen) {
        LOG.debug("Frage Jobstatus ab");
        final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        AbstractJobBeschreibung.sortedStreamOf(jobBeschreibungen)
                .parallel()
                .map(beschreibung -> {
                    final JobAbfrage jobAbfrage
                            = new JobAbfrage(beschreibung.getJobAbfragedaten(), beschreibung.getJobOrderId());
                    final Future<JobStatusBeschreibung> jobAbfrageFuture = executor.submit(jobAbfrage);
                    return new JobAbfrageFutureWrapper(jobAbfrage, jobAbfrageFuture);
                })
                // CHECK REFACTORED  optimieren nach https://www.concretepage.com/java/jdk-8/java-8-stream-collect-example#collect
                //.collect(Collectors.toList())
                .collect(ArrayList<JobAbfrageFutureWrapper>::new,ArrayList::add,ArrayList::addAll)
                .forEach(jobAbfrageFutureWrapper -> {
                    final Future<JobStatusBeschreibung> future = jobAbfrageFutureWrapper.getJobAbfrageFuture();
                    try {
                        final JobStatusBeschreibung jobStatus = future.get(3, TimeUnit.SECONDS);
                        jobStatusBeschreibungen.put(jobStatus.getPrimaryKey(), jobStatus);
                        LOG.debug("JobStatus geladen: {} : {}  at {} ", jobStatus.getJobName(), jobStatus.getJobStatus(), jobStatus.getJobUrl().toExternalForm());
                    } catch (InterruptedException | TimeoutException | ExecutionException ex) {

                        LOG.warn("Read Future Result goes wrong with exception: \n {}", ex.toString());
                        addStatusOTHER(jobStatusBeschreibungen, jobAbfrageFutureWrapper);
                        future.cancel(true);
                    }
                });
        executor.shutdown();
    }

    private static void addStatusOTHER(final AbstractJobBeschreibungen<JobStatusBeschreibung> jobStatusBeschreibungen, final JobAbfrageFutureWrapper jobAbfrageFutureWrapper) {
        final JobAbfrage jobAbfrage = jobAbfrageFutureWrapper.getJobAbfrage();
        final JobStatusBeschreibung jobStatusBeschreibung
                = new JobStatusBeschreibung("Connection Timeout" + jobAbfrage.getAbfrageUrl().toExternalForm(), JobStatus.OTHER, jobAbfrage.getAbfrageUrl(), jobAbfrage.getJobOrderId());
        jobStatusBeschreibungen.put(jobAbfrage.getPrimaryKey(), jobStatusBeschreibung);

    }
}



