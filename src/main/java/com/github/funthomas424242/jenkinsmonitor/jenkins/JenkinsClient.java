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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.stream.Collectors;


public class JenkinsClient {

    protected static final Logger LOG = LoggerFactory.getLogger(JenkinsClient.class);


    public static final String JSONKEY_FULL_DISPLAY_NAME = "fullDisplayName";
    public static final String JSONKEY_RESULT = "result";


    protected JobStatusBeschreibung getJobStatus(final URL jenkinsJobURL) throws IOException {
        final URL abfrageURL = new URL(jenkinsJobURL.toExternalForm() + JenkinsAPI.STATUS_PATH);
        final JSONObject resultJSON = sendGetRequest(abfrageURL);
        try {
            final String jobName = resultJSON.getString(JSONKEY_FULL_DISPLAY_NAME);
            final String jobStatus = resultJSON.getString(JSONKEY_RESULT);
            return new JobStatusBeschreibung(jobName, JobStatus.valueOf(jobStatus), jenkinsJobURL);
        } catch (JSONException ex) {
            return new JobStatusBeschreibung(jenkinsJobURL.getPath(), JobStatus.OTHER, jenkinsJobURL);
        }
    }

    protected JSONObject sendGetRequest(final URL statusAbfrageUrl) throws IOException {

        // TODO auslagern in Configuration
        final String user = "huluvu"; // username
        final String pass = "huluvu"; // password or API token
        final String authStr = user + ":" + pass;
        final String encodedAuth = Base64.getEncoder().encodeToString(authStr.getBytes("utf-8"));

        JSONObject resultJSON = null;
        try (final CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            final HttpHost target = new HttpHost(statusAbfrageUrl.getHost(), statusAbfrageUrl.getPort(), statusAbfrageUrl.getProtocol());
            final HttpGet httpGetRequest = new HttpGet(statusAbfrageUrl.getPath());
            httpGetRequest.setHeader("Authorization","Basic " + encodedAuth);
            final HttpResponse httpResponse = httpClient.execute(target, httpGetRequest);
            final HttpEntity entity = httpResponse.getEntity();
            final InputStream inputStream = entity.getContent();

            final String requestResult = readStreamIntoString(inputStream);
            LOG.debug("Empfangen als JSON:\n {}", requestResult);
            resultJSON = new JSONObject(requestResult);
        }
        return resultJSON;
    }

    protected String readStreamIntoString(InputStream inputStream) throws IOException {
        String requestResult;
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
            requestResult = buffer.lines().collect(Collectors.joining("\n"));
        }
        return requestResult;
    }


    public JobStatusBeschreibung[] ladeJobsStatus(JobBeschreibung[] jobBeschreibungen) {
        return Arrays.stream(jobBeschreibungen).map(beschreibung -> {
            JobStatusBeschreibung returnValue = null;
            try {
                final JobStatusBeschreibung jobStatus = getJobStatus(beschreibung.getJobUrl());
                returnValue = new JobStatusBeschreibung(jobStatus.getJobName()
                    , jobStatus.getJobStatus()
                    , beschreibung.getJobUrl());
                LOG.debug(String.format("JobStatus geladen: %s : %s  at %s ", jobStatus.getJobName(), jobStatus.getJobStatus().toString(), jobStatus.getJobUrl().toExternalForm()));
            } catch (IOException e) {
                LOG.error(e.getLocalizedMessage(), e);
                returnValue = new JobStatusBeschreibung(beschreibung.getJobId(),
                    JobStatus.OTHER,
                    beschreibung.getJobUrl());
                LOG.debug(String.format("JobStatus ERR geladen: %s : %s at %s ", beschreibung.getJobId(), JobStatus.OTHER.toString(), beschreibung.getJobUrl().toExternalForm()));
            }
            return returnValue;
        }).toArray(JobStatusBeschreibung[]::new);

    }

}
