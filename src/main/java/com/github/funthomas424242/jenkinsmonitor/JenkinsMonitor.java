package com.github.funthomas424242.jenkinsmonitor;

/*-
 * #%L
 * jenkinsmonitor Example
 * %%
 * Copyright (C) 2018 - 2019 PIUG
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
import java.util.Properties;

public class JenkinsMonitor {

    protected static final Logger LOG = LoggerFactory.getLogger(JenkinsMonitor.class);


    public static final String JENKINSMONITOR_CONFIGURATIONFILENAME = "jenkinsmonitor.properties";
    public static final String PROPERTY_USER_HOME = "user.home";
    public static final String JENKINSMONITOR_POLLPERIOD = "jenkinsmonitor.pollperiod";
    public static final String DEFAULT_POLLPERIOD = "5";

    protected final File configurationFile;

    protected Properties configurationProperties;

    public JenkinsMonitor() {
        this(new File(System.getProperty(PROPERTY_USER_HOME) + File.separator + JENKINSMONITOR_CONFIGURATIONFILENAME));
    }

    protected JenkinsMonitor(File configurationFile) {
        this.configurationFile = configurationFile;
        this.configurationProperties=initializeConfiguration();
    }

    protected Properties initializeConfiguration() {
       final Properties properties = new Properties();
        try (FileInputStream propStream = new FileInputStream(this.configurationFile)) {
            properties.load(propStream);
        } catch (IOException e) {
            LOG.error(e.getLocalizedMessage(),e);
        }
        return properties;
    }

    public File getConfigurationfile() {
        return this.configurationFile;
    }

    public int getPollPeriod() {
        final String propValue = this.configurationProperties.getProperty(JENKINSMONITOR_POLLPERIOD, DEFAULT_POLLPERIOD);
        return Integer.parseInt(propValue);
    }
}
