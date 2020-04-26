package com.github.funthomas424242.jenkinsmonitor.gui;

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

import com.github.funthomas424242.jenkinsmonitor.config.Configuration;
import com.github.funthomas424242.jenkinsmonitor.etc.RealTimer;
import com.github.funthomas424242.jenkinsmonitor.etc.Timer;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JenkinsClient;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobBeschreibungen;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JenkinsMonitorTray implements Timer.Listener {

    public static final Logger LOGGER = LoggerFactory.getLogger(JenkinsMonitorTray.class);


    protected final Configuration configuration;
    protected final JobStatusBeschreibungen jobStatusBeschreibungen;
    protected final JobStatusDarstellungen jobStatusDarstellungen;
    protected final JenkinsClient jenkinsClient;
    protected final Timer timer;


    public JenkinsMonitorTray(final Configuration configuration) {
        this(new JenkinsClient(), configuration);
    }

    public JenkinsMonitorTray(final JenkinsClient jenkinsClient, final Configuration configuration) {
        this(new RealTimer(configuration.getPollPeriodInSecond(), TimeUnit.SECONDS), jenkinsClient, configuration);
    }

    protected JenkinsMonitorTray(final Timer timer, final JenkinsClient jenkinsClient, final Configuration configuration) {
        this.configuration = configuration;
        this.jobStatusBeschreibungen = new JobStatusBeschreibungen();
        this.jobStatusDarstellungen = new JobStatusDarstellungen(this.jobStatusBeschreibungen, timer);
        this.jenkinsClient = jenkinsClient;
        this.timer = timer;
        timer.register(this);
        timer.start(); // implicit call updateJobStatus()
    }

    protected JobStatusDarstellungen getJobStatusDarstellungen() {
        return this.jobStatusDarstellungen;
    }

    public void updateJobStatus() {
        final JobBeschreibungen jobBeschreibungen = configuration.getJobBeschreibungen();
        jobStatusDarstellungen.bereinigeJobStatusBeschreibungen(jobBeschreibungen);

        // aktualisiere den Status der Anzeigen ohne Jenkinsabfragen
        jobStatusDarstellungen.aktualisiereStatusfenster();
        jobStatusDarstellungen.aktualisiereTrayIconDarstellung();

        // Langlaufender, blockierender Prozess mit Jenkinsabfragen durch Request die ins timeout laufen
        jenkinsClient.ladeJobsStatus(jobStatusBeschreibungen, jobBeschreibungen);

        // aktualisiere den Status der Anzeigen ohne Jenkinsabfragen
        jobStatusDarstellungen.aktualisiereTrayIconDarstellung();
        jobStatusDarstellungen.aktualisiereStatusfenster();
    }

    @Override
    public void timeStarted() {
        updateJobStatus();
    }

    @Override
    public void timeElapsed() {
        timeBasedUpdate();
    }

    protected void timeBasedUpdate() {
        LOGGER.debug("Lade Konfiguration");
        this.configuration.reload();
        if (this.timer.getPeriod() != this.configuration.getPollPeriodInSecond()) {
            LOGGER.debug("Setze Timer Period auf {} Sekunden.", this.configuration.getPollPeriodInSecond());
            this.timer.resetPeriod(this.configuration.getPollPeriodInSecond(), TimeUnit.SECONDS);
        }
        LOGGER.debug("Aktualisiere Status");
        updateJobStatus();
        LOGGER.debug("Status jetzt aktuell");
    }
}
