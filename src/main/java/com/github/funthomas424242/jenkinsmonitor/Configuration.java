package com.github.funthomas424242.jenkinsmonitor;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class Configuration {

    protected static final Logger LOG = LoggerFactory.getLogger(Configuration.class);

    public static final String JENKINSMONITOR_CONFIGURATIONFILENAME = "jenkinsmonitor.properties";
    public static final String PROPERTY_USER_HOME = "user.home";
    public static final String JENKINSMONITOR_POLLPERIOD = "jenkinsmonitor.pollperiod";
    public static final String DEFAULT_POLLPERIOD = "5";
    public static final String JOBKEY_PREFIX = "joburl-";

    protected File configurationFile;

    protected Properties configurationProperties;

    protected boolean isInitialisiert;

    protected Configuration() {
        this(new File(System.getProperty(PROPERTY_USER_HOME) + File.separator + JENKINSMONITOR_CONFIGURATIONFILENAME));
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

    public File getConfigurationfile() {
        return this.configurationFile;
    }

    public int getPollPeriodInSecond() {
        loadPropertiesFromFile(configurationFile);
        final String propValue = this.configurationProperties.getProperty(JENKINSMONITOR_POLLPERIOD, DEFAULT_POLLPERIOD);
        return Integer.parseInt(propValue);
    }

    public JobBeschreibung[] getJobBeschreibungen() {
        loadPropertiesFromFile(configurationFile);
        final List<JobBeschreibung> jobBeschreibungen = new ArrayList<>();
        configurationProperties.forEach((k, v) -> {
            final String key = (String) k;
            final String value = (String) v;
            if (key.startsWith(JOBKEY_PREFIX)) {
                final URL jobURL = urlOf(value);
                final JobBeschreibung jobBeschreibung = new JobBeschreibung(null, null, jobURL);
                jobBeschreibungen.add(jobBeschreibung);
            }
        });
        return jobBeschreibungen.toArray(new JobBeschreibung[jobBeschreibungen.size()]);
    }

    // TODO andere Klasse
    protected URL urlOf(final String urlPath) {
        try {
            return new URL(urlPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void reload() {
        reloadFromFile(this.configurationFile);
    }

    public void reloadFromFile(final File configFile) {
        this.isInitialisiert = false;
        this.configurationFile = configFile;
        loadPropertiesFromFile(configFile);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return configurationFile.equals(that.configurationFile) &&
            configurationProperties.equals(that.configurationProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configurationFile, configurationProperties);
    }
}
