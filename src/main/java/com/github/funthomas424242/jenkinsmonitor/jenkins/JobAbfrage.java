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
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class JobAbfrage implements Callable<JobStatusBeschreibung> {

    protected static final Logger LOG = LoggerFactory.getLogger(JobAbfrage.class);

    public static final String JSONKEY_FULL_DISPLAY_NAME = "fullDisplayName";
    public static final String JSONKEY_RESULT = "result";

    protected final JobAbfragedaten jobAbfragedaten;
    protected final String jobOrderId;
    protected final AbstractJobBeschreibungen<JobStatusBeschreibung> jobStatusBeschreibungen;

    public JobAbfrage(final AbstractJobBeschreibungen<JobStatusBeschreibung> jobStatusBeschreibungen, final JobAbfragedaten jobAbfragedaten, final String jobOrderId) {
        this.jobStatusBeschreibungen = jobStatusBeschreibungen;
        this.jobAbfragedaten = jobAbfragedaten;
        this.jobOrderId = jobOrderId;
    }

    @Override
    public JobStatusBeschreibung call() throws Exception {
        final JobStatusBeschreibung jobStatus = getJobStatus();
        this.jobStatusBeschreibungen.put(jobStatus.getPrimaryKey(), jobStatus);
        return jobStatus;
    }


    protected JobStatusBeschreibung getJobStatus() {

        try {
            final JSONObject resultJSON = sendGetRequest();
            final String jobName = resultJSON.getString(JSONKEY_FULL_DISPLAY_NAME);
            final String jobStatus = resultJSON.getString(JSONKEY_RESULT);
            return new JobStatusBeschreibung(jobName, JobStatus.valueOf(jobStatus), jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);

        } catch (HttpResponseException e) {
            if (e.getStatusCode() == 404) {
                return new JobStatusBeschreibung("Job Not Found ERROR: " + jobAbfragedaten.getJenkinsJobUrl(), JobStatus.OTHER, jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);
            } else {
                return new JobStatusBeschreibung("HTTP Status:" + e.getStatusCode(), JobStatus.OTHER, jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);
            }
        } catch (ConnectionFailedException e) {
            return new JobStatusBeschreibung("Connection ERROR: " + jobAbfragedaten.getJenkinsJobUrl(), JobStatus.OTHER, jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);
        } catch (NullPointerException | JSONException ex) {
            return new JobStatusBeschreibung(jobAbfragedaten.getJenkinsJobUrl().toExternalForm(), JobStatus.OTHER, jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);
        }
    }

    protected JSONObject sendGetRequest() throws HttpResponseException, ConnectionFailedException {
        final URL statusAbfrageUrl = jobAbfragedaten.getStatusAbfrageUrl();
        int statusCode = -1;
        try (final CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            final HttpHost target = new HttpHost(statusAbfrageUrl.getHost(), statusAbfrageUrl.getPort(), statusAbfrageUrl.getProtocol());
            final HttpGet httpGetRequest = new HttpGet(statusAbfrageUrl.getPath());
            final String basicAuthToken = jobAbfragedaten.getBasicAuthToken();
            if (basicAuthToken != null && basicAuthToken.length() > 1) {
                httpGetRequest.setHeader("Authorization", "Basic " + basicAuthToken);
            }
            final HttpResponse httpResponse;
            try {
                httpResponse = httpClient.execute(target, httpGetRequest);
            } catch (IOException ex) {
                throw new ConnectionFailedException(ex);
            }
            statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new HttpResponseException(statusCode, httpResponse.getStatusLine().getReasonPhrase());
            }
            return getJsonObjectFromResponse(httpResponse);
        } catch (JSONException | IOException ex) {
            LOG.warn("Could not retrieve data from jenkins: " + ex);
            if (ex instanceof HttpResponseException) {
                throw (HttpResponseException) ex;
            } else {
                LOG.warn("An Error occured" + ex);
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


}
