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

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;


public class JenkinsClient {

    protected static final Logger LOG = LoggerFactory.getLogger(JenkinsClient.class);


    public static final String JSONKEY_FULL_DISPLAY_NAME = "fullDisplayName";
    public static final String JSONKEY_RESULT = "result";

    protected static JobStatusBeschreibung getJobStatus(final JobAbfragedaten jobAbfragedaten, final String jobOrderId) {

        try {
            final JSONObject resultJSON = sendGetRequest(jobAbfragedaten);
            final String jobName = resultJSON.getString(JSONKEY_FULL_DISPLAY_NAME);
            final String jobStatus = resultJSON.getString(JSONKEY_RESULT);
            return new JobStatusBeschreibung(jobName, JobStatus.valueOf(jobStatus), jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);

        } catch (HttpResponseException e) {
            if (e.getStatusCode() == 404) {
                return new JobStatusBeschreibung("Job Not Found:" + jobAbfragedaten.getJenkinsJobUrl(), JobStatus.OTHER, jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);
            } else {
                return new JobStatusBeschreibung("HTTP Status:" + e.getStatusCode(), JobStatus.OTHER, jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);
            }
        } catch (NullPointerException | JSONException ex) {
            return new JobStatusBeschreibung(jobAbfragedaten.getJenkinsJobUrl().getPath(), JobStatus.OTHER, jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);
        }
    }

    protected static JSONObject sendGetRequest(final JobAbfragedaten statusabfrageDaten) throws HttpResponseException {
        final URL statusAbfrageUrl = statusabfrageDaten.getStatusAbfrageUrl();
        int statusCode = -1;
        try (final CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            final HttpHost target = new HttpHost(statusAbfrageUrl.getHost(), statusAbfrageUrl.getPort(), statusAbfrageUrl.getProtocol());
            final HttpGet httpGetRequest = new HttpGet(statusAbfrageUrl.getPath());
            final String basicAuthToken = statusabfrageDaten.getBasicAuthToken();
            if (basicAuthToken != null && basicAuthToken.length() > 1) {
                httpGetRequest.setHeader("Authorization", "Basic " + basicAuthToken);
            }
            final HttpResponse httpResponse = httpClient.execute(target, httpGetRequest);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new HttpResponseException(statusCode, httpResponse.getStatusLine().getReasonPhrase());
            }
            return getJsonObjectFromResponse(httpResponse);
        } catch (JSONException | IOException ex) {
            LOG.error("Could not retrieve data from jenkins: " + ex);
            if (ex instanceof HttpResponseException) {
                throw (HttpResponseException) ex;
            } else {
                LOG.error("An Error occured" + ex);
            }
        }
        return null;
    }

    @NotNull
    protected static JSONObject getJsonObjectFromResponse(HttpResponse httpResponse) {
        final HttpEntity entity = httpResponse.getEntity();
        final String requestResult;
        try {
            final InputStream inputStream = entity.getContent();
            requestResult = readStreamIntoString(inputStream);
        } catch (IOException e) {
            LOG.warn("Jenkins Response could not be read: " + e);
            return null;
        }
        LOG.debug("Empfangen als JSON:\n {}", requestResult);
        return new JSONObject(requestResult);
    }

    protected static String readStreamIntoString(InputStream inputStream) throws IOException {
        String requestResult;
        // wegen json gehen wir von utf-8 aus
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            requestResult = buffer.lines().collect(Collectors.joining("\n"));
        }
        return requestResult;
    }


    public void ladeJobsStatus(final JobStatusBeschreibungen jobStatusBeschreibungen, final JobBeschreibungen jobBeschreibungen) {
        LOG.debug("Frage Jobstatus ab");
        final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        final List<Future<JobStatusBeschreibung>> results = new ArrayList<>();
        AbstractJobBeschreibung.sortedStreamOf(jobBeschreibungen)
            .parallel()
            .map(beschreibung -> {
                final JobAbfrage jobAbfrage
                    = new JobAbfrage(jobStatusBeschreibungen, beschreibung.getJobAbfragedaten(), beschreibung.getJobOrderId());
                final Future<JobStatusBeschreibung> jobStatus = executor.submit(jobAbfrage);
                return jobStatus;
            })
            .collect(Collectors.toList())
            .forEach(jobStatusFuture -> {
                try {
                    final JobStatusBeschreibung jobStatus = jobStatusFuture.get();
                    jobStatusBeschreibungen.put(jobStatus.getPrimaryKey(), jobStatus);
                    LOG.debug(String.format("JobStatus geladen: %s : %s  at %s ", jobStatus.getJobName(), jobStatus.getJobStatus().toString(), jobStatus.getJobUrl().toExternalForm()));
                } catch (Exception e) {
                    LOG.error("Read Future Result goes wrong.");
                }
            });
        executor.shutdown();
    }

}
