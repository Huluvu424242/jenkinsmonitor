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
import java.util.Map;
import java.util.stream.Collectors;


public class JenkinsClient {

    protected static final Logger LOG = LoggerFactory.getLogger(JenkinsClient.class);


    public static final String JSONKEY_FULL_DISPLAY_NAME = "fullDisplayName";
    public static final String JSONKEY_RESULT = "result";


    protected JobStatusBeschreibung getJobStatus(final JobAbfragedaten jobAbfragedaten, final String jobId) throws IOException {

        final JSONObject resultJSON = sendGetRequest(jobAbfragedaten);
        try {
            final String jobName = resultJSON.getString(JSONKEY_FULL_DISPLAY_NAME);
            final String jobStatus = resultJSON.getString(JSONKEY_RESULT);
            return new JobStatusBeschreibung(jobName, JobStatus.valueOf(jobStatus), jobAbfragedaten.getJenkinsJobUrl(), jobId);
        } catch (NullPointerException | JSONException ex) {
            return new JobStatusBeschreibung(jobAbfragedaten.getJenkinsJobUrl().getPath(), JobStatus.OTHER, jobAbfragedaten.getJenkinsJobUrl(), jobId);
        }
    }

    protected JSONObject sendGetRequest(final JobAbfragedaten statusabfrageDaten) throws IOException {
        final URL statusAbfrageUrl = statusabfrageDaten.getStatusAbfrageUrl();

        try (final CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            final HttpHost target = new HttpHost(statusAbfrageUrl.getHost(), statusAbfrageUrl.getPort(), statusAbfrageUrl.getProtocol());
            final HttpGet httpGetRequest = new HttpGet(statusAbfrageUrl.getPath());
            final String basicAuthToken = statusabfrageDaten.getBasicAuthToken();
            if (basicAuthToken != null && basicAuthToken.length() > 1) {
                httpGetRequest.setHeader("Authorization", "Basic " + basicAuthToken);
            }
            final HttpResponse httpResponse = httpClient.execute(target, httpGetRequest);
            final int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new HttpResponseException(statusCode, httpResponse.getStatusLine().getReasonPhrase());
            }
            final HttpEntity entity = httpResponse.getEntity();
            final InputStream inputStream = entity.getContent();
            final String requestResult = readStreamIntoString(inputStream);
            LOG.debug("Empfangen als JSON:\n {}", requestResult);
            return new JSONObject(requestResult);
        } catch (Exception ex) {
            LOG.warn("Could not retrieve data from jenkins: " + ex);
        }
        return null;
    }

    protected String readStreamIntoString(InputStream inputStream) throws IOException {
        String requestResult;
        // wegen json gehen wir von utf-8 aus
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, "utf-8"))) {
            requestResult = buffer.lines().collect(Collectors.joining("\n"));
        }
        return requestResult;
    }


    public void ladeJobsStatus(final Map<String, JobStatusBeschreibung> jobStatusBeschreibungen, final JobBeschreibung[] jobBeschreibungen) {
        LOG.debug("Frage Jobstatus ab");
        // TODO alte Jobsstatusbeschreibungen löschen wenn sie nicht mehr in den jobBeschreibungen vorkommen

        // Jobstatus neu abfragen und Ergebnis in Map eintragen
        Arrays.stream(jobBeschreibungen)
            .sorted()
            .forEach(beschreibung -> {
                JobStatusBeschreibung returnValue = null;
                try {
                    // TODO prüfen ob schon vorhanden sind bei zunächst leerer Konfig
                    //final JobAbfragedaten jobAbfragedaten = new JobAbfragedaten(beschreibung.getJobUrl(), beschreibung.g);
                    final JobAbfragedaten jobAbfragedaten = beschreibung.getJobAbfragedaten();
                    // primär schlüssel nutzen um in Map einzutragen.
                    final JobStatusBeschreibung jobStatusEmpfangen = getJobStatus(jobAbfragedaten, beschreibung.getJobId());
                    // Nutzung des empfangen JobStatus ohne Ummappen
                    final JobStatusBeschreibung jobStatus = new JobStatusBeschreibung(jobStatusEmpfangen.getJobName()
                        , jobStatusEmpfangen.getJobStatus()
                        , beschreibung.getJobUrl()
                        , beschreibung.getJobId());
                    jobStatusBeschreibungen.put(jobStatus.getPrimaryKey(),jobStatus);
                    LOG.debug(String.format("JobStatus geladen: %s : %s  at %s ", jobStatus.getJobName(), jobStatus.getJobStatus().toString(), jobStatus.getJobUrl().toExternalForm()));
                } catch (IOException e) {
                    LOG.error(e.getLocalizedMessage(), e);
                    final JobStatusBeschreibung jobStatus  = new JobStatusBeschreibung(beschreibung.getJobId(),
                        JobStatus.OTHER,
                        beschreibung.getJobUrl()
                        , beschreibung.getJobId());
                    jobStatusBeschreibungen.put(jobStatus.getPrimaryKey(),jobStatus);
                    LOG.debug(String.format("JobStatus ERR geladen: %s : %s at %s ", beschreibung.getJobId(), JobStatus.OTHER.toString(), beschreibung.getJobUrl().toExternalForm()));
                }
            });
    }

}
