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

import static com.github.funthomas424242.jenkinsmonitor.config.ConfigurationFluentGrammar.Loaded;

public class JenkinsMonitor {

    // Die LOGGER Konstante darf hier nicht her, da die Konfiguration neu geladen wird
    // So erfolgt schon der erste Log mit neuer Konfiguration.

    protected JenkinsMonitorTray monitorTray;

    public JenkinsMonitor(Loaded configuration) {
        final Logger logger = LoggerFactory.getLogger(JenkinsMonitor.class);
        this.monitorTray = new JenkinsMonitorTray(configuration);
        logger.info("Jenkinsmonitor gestartet");
    }

    public JenkinsMonitorTray getMonitorTray() {
        return this.monitorTray;
    }

    public static void main(final String[] commandlineArgs) {
        final Loaded defaultConfiguration = Configuration.getOrCreateInstance(Configuration.getDefaultConfigurationsfile()).reload();
        defaultConfiguration.resetLoggerConfiguration();
        new JenkinsMonitor(defaultConfiguration);
    }
}
