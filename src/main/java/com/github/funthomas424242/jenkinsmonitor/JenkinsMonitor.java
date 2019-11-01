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

import com.github.funthomas424242.jenkinsmonitor.config.Configuration;
import com.github.funthomas424242.jenkinsmonitor.gui.JenkinsMonitorTray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JenkinsMonitor {

    protected static final Logger LOGGER = LoggerFactory.getLogger(JenkinsMonitor.class);

    protected JenkinsMonitorTray monitorTray;

    public JenkinsMonitor(Configuration configuration) {
        this.monitorTray = new JenkinsMonitorTray(configuration);
        this.monitorTray.updateJobStatus();
        LOGGER.info("Jenkinsmonitor gestartet");
    }

    public static void main(final String[] commandlineArgs) {
        final Configuration defaultConfiguration = new Configuration(Configuration.getDefaultConfigurationsfile());
        new JenkinsMonitor(defaultConfiguration);
    }
}
