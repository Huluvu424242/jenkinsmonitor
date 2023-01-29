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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.job.BuildInfo;
import com.cdancy.jenkins.rest.features.JobsApi;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobAbfrage implements Callable<JobStatusBeschreibung> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(JobAbfrage.class);

    public static final String JSONKEY_FULL_DISPLAY_NAME = "fullDisplayName";
    public static final String JSONKEY_RESULT = "result";

    protected final JobAbfragedaten jobAbfragedaten;
    protected final String jobOrderId;

    public JobAbfrage(final JobAbfragedaten jobAbfragedaten, final String jobOrderId) {
        this.jobAbfragedaten = jobAbfragedaten;
        this.jobOrderId = jobOrderId;
    }

    public String getPrimaryKey() {
        final String url = getAbfrageUrl() != null ? getAbfrageUrl().toExternalForm() : "";
        return getJobOrderId() + "#" + url;
    }

    public URL getAbfrageUrl() {
        return this.jobAbfragedaten.getJenkinsJobUrl();
    }

    public String getJobOrderId() {
        return this.jobOrderId;
    }


    @Override
    public JobStatusBeschreibung call() throws Exception {
        return getJobStatus();
    }


    protected JobStatusBeschreibung getJobStatus() {

        try {
            final BuildInfo resultJSON = sendGetRequest();
            final String jobName = resultJSON.fullDisplayName();//  getString(JSONKEY_FULL_DISPLAY_NAME);
            final String jobStatus = resultJSON.result(); //getString(JSONKEY_RESULT);
            return new JobStatusBeschreibung(jobName, JobStatus.valueOf(jobStatus), jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);
        } catch (JobNotFoundException e) {
            return new JobStatusBeschreibung("Job Not Found ERROR: " + jobAbfragedaten.getJenkinsJobUrl(), JobStatus.OTHER, jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);
        } catch (ConnectionErrorException e) {
            return new JobStatusBeschreibung("HTTP Status:" + e.getStatusCode(), JobStatus.OTHER, jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);
        } catch (ConnectionFailedException e) {
            return new JobStatusBeschreibung("Connection ERROR: " + jobAbfragedaten.getJenkinsJobUrl(), JobStatus.OTHER, jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);
        } catch (NullPointerException | JSONException ex) {
            return new JobStatusBeschreibung(jobAbfragedaten.getJenkinsJobUrl().toExternalForm(), JobStatus.OTHER, jobAbfragedaten.getJenkinsJobUrl(), jobOrderId);
        }
    }

    protected BuildInfo sendGetRequest() throws JobNotFoundException, ConnectionFailedException, ConnectionErrorException {
        final URL statusAbfrageUrl = jobAbfragedaten.getStatusAbfrageUrl();
        int statusCode = -1;
        int timeout = 1;
//        final RequestConfig config = RequestConfig.custom()
//                .setConnectTimeout(timeout * 1000)
//                .setConnectionRequestTimeout(timeout * 1000)
//                .setSocketTimeout(timeout * 1000).build();
//        try (final CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {

        final JenkinsClient.Builder clientBuilder = JenkinsClient.builder()
                .endPoint(String.format("%s://%s:%d/", statusAbfrageUrl.getProtocol(), statusAbfrageUrl.getHost(), statusAbfrageUrl.getPort()));
        // Basic Auth
        final String basicAuthToken = jobAbfragedaten.getBasicAuthToken();
        if (basicAuthToken != null && basicAuthToken.length() > 1) {
            clientBuilder.credentials(jobAbfragedaten.getBasicAuthToken());
        }
        final JenkinsClient client = clientBuilder.build();

        final String abfrageURL = statusAbfrageUrl.getPath();
        Pattern pattern = Pattern.compile(".*/job/(.*)/job/(.*)");
        Matcher matcher = pattern.matcher(statusAbfrageUrl.getPath());
        final String folderName;
        final String jobName;
        final boolean matched= matcher.matches();
        if(matched){
            folderName = matcher.group(1);
            jobName = matcher.group(2);
        }else{
            folderName=null;
            jobName=null;
        }


        final JobsApi api = client.api().jobsApi();

//        final int lastBuildNumber = api.lastBuildNumber(null, statusAbfrageUrl.getPath());
        final int lastBuildNumber = api.lastBuildNumber(folderName, jobName);
        LOGGER.debug("Letzte Buildnummer von {}  ist {}", statusAbfrageUrl.getPath(), lastBuildNumber);

        final BuildInfo buildInfo = client.api().jobsApi().buildInfo(folderName,jobName, lastBuildNumber);
        LOGGER.debug("RESPONSE: URL: {}\n STATUS: {} \n", buildInfo.url(), buildInfo.result());


//        statusCode = httpResponse.getStatusLine().getStatusCode();
//        if (statusCode == 404) {
//            throw new JobNotFoundException(new HttpResponseException(statusCode, httpResponse.getStatusLine().getReasonPhrase()));
//        } else if (statusCode != 200) {
//            throw new ConnectionErrorException(new HttpResponseException(statusCode, httpResponse.getStatusLine().getReasonPhrase()));
//        }
//        return getJsonObjectFromResponse(httpResponse);
//        } catch (JSONException | IOException ex) {
//            LOGGER.warn(String.format("Could not retrieve data from jenkins: %s", ex));
//        }
        return buildInfo;
    }

    protected HttpResponse getHttpResponse(CloseableHttpClient httpClient, HttpHost target, HttpGet httpGetRequest) throws ConnectionFailedException {
        HttpResponse httpResponse;
        try {
            httpResponse = httpClient.execute(target, httpGetRequest);
        } catch (IOException ex) {
            throw new ConnectionFailedException(ex);
        }
        return httpResponse;
    }


    protected static JSONObject getJsonObjectFromResponse(HttpResponse httpResponse) {
        final HttpEntity entity = httpResponse.getEntity();
        final String requestResult;
        try {
            final InputStream inputStream = entity.getContent();
            requestResult = readStreamIntoString(inputStream);
        } catch (IOException e) {
            LOGGER.warn(String.format("Jenkins Response could not be read: %s", e));
            return null;
        }
        LOGGER.debug("Empfangen als JSON:\n {}", requestResult);
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
