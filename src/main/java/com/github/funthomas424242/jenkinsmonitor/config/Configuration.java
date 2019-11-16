package com.github.funthomas424242.jenkinsmonitor.config;

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

import com.github.funthomas424242.jenkinsmonitor.etc.NetworkHelper;
import com.github.funthomas424242.jenkinsmonitor.jenkins.BasicAuthDaten;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobAbfragedaten;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobBeschreibung;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

public class Configuration {

    protected static final Logger LOG = LoggerFactory.getLogger(Configuration.class);

    public static final String JENKINSMONITOR_CONFIGURATIONFILENAME = "jenkinsmonitor.properties";
    public static final String PROPERTY_USER_HOME = "user.home";
    public static final String JENKINSMONITOR_POLLPERIOD = "jenkinsmonitor.pollperiod";
    public static final String DEFAULT_POLLPERIOD = "5";
    public static final String JOBKEY_PREFIX = "joburl-";
    public static final String KEY_JENKINSAUTH = "jenkinsauth.";

    protected File configurationFile;

    protected Properties configurationProperties;

    protected boolean isInitialisiert;

    public static File getDefaultConfigurationsfile() {
        return new File(System.getProperty(PROPERTY_USER_HOME) + File.separator + JENKINSMONITOR_CONFIGURATIONFILENAME);
    }

    public Configuration(final File configurationFile) {
        this.configurationFile = configurationFile;
        isInitialisiert = false;
    }

    protected void loadPropertiesFromFile(final File configFile) {
        if (this.isInitialisiert) return;
        final Properties properties = new Properties();
        try (FileInputStream propStream = new FileInputStream(configFile)) {
            properties.load(propStream);
            this.isInitialisiert = true;
        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage(), e);
        }
        this.configurationProperties = properties;
    }

    protected File getConfigurationfile() {
        return this.configurationFile;
    }

    protected Jenkinszugangskonfiguration[] getAllJenkinszugangskonfigurationen() {
        loadPropertiesFromFile(configurationFile);
        final Zugangsdatensammler zugangsdatensammler = new Zugangsdatensammler();
        configurationProperties
            .stringPropertyNames()
            .stream()
            .filter((key) -> key.startsWith(KEY_JENKINSAUTH))
            .forEach(key -> {
                zugangsdatensammler.addZugangsdatum(key, configurationProperties.getProperty(key));
            });
        return zugangsdatensammler.getJenkinsZugangsdaten();
    }

    protected JobAbfragedaten getAbfragedatenOf(final URL jobUrl) {
        loadPropertiesFromFile(configurationFile);
        final Jenkinszugangskonfiguration[] alleJenkinsZugaenge = getAllJenkinszugangskonfigurationen();
        final Optional<BasicAuthDaten> jenkinsZugangsdaten = Arrays.stream(alleJenkinsZugaenge)
            .filter((zugang) -> {
                return jobUrl.toExternalForm().startsWith(zugang.getJenkinsUrl().toExternalForm());
            })
            .map((zugang) -> zugang.getAuthDaten())
            .findFirst();
        if (jenkinsZugangsdaten.isPresent()) {
            return new JobAbfragedaten(jobUrl, jenkinsZugangsdaten.get());
        } else {
            return new JobAbfragedaten(jobUrl, null);
        }
    }


    public void reload() {
        reloadFromFile(this.configurationFile);
    }

    public void reloadFromFile(final File configFile) {
        this.isInitialisiert = false;
        this.configurationFile = configFile;
        loadPropertiesFromFile(configFile);
    }

    public long getPollPeriodInSecond() {
        loadPropertiesFromFile(configurationFile);
        final String propValue = this.configurationProperties.getProperty(JENKINSMONITOR_POLLPERIOD, DEFAULT_POLLPERIOD);
        return Long.parseLong(propValue);
    }

    public JobBeschreibung[] getJobBeschreibungen() {
        loadPropertiesFromFile(configurationFile);
        return configurationProperties
            .stringPropertyNames()
            .stream()
            .sorted()
            .filter((key) -> key.startsWith(JOBKEY_PREFIX))
            .map(key -> {
                final String value = configurationProperties.getProperty(key);
                final URL jobURL = NetworkHelper.urlOf(value);
                final JobAbfragedaten jobAbfragedaten = getAbfragedatenOf(jobURL);
                return new JobBeschreibung(null, jobAbfragedaten);
            }).toArray(JobBeschreibung[]::new);
    }

}
