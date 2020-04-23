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
import com.github.funthomas424242.jenkinsmonitor.jenkins.AbstractJobBeschreibung;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JenkinsClient;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobBeschreibungen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class JenkinsMonitorTray implements Timer.Listener {

    public static final Logger LOGGER = LoggerFactory.getLogger(JenkinsMonitorTray.class);


    protected final Configuration configuration;
    protected final SystemTrayWrapper tray;
    protected final JenkinsClient jenkinsClient;
    protected final Statusfenster statusArea;
    protected final ContextMenu contextMenu;
    protected final Timer timer;

    protected final JobStatusBeschreibungen jobStatusBeschreibungen;

    public JenkinsMonitorTray(final Configuration configuration) {
        this(new JenkinsClient(), configuration);
    }

    public JenkinsMonitorTray(final JenkinsClient jenkinsClient, final Configuration configuration) {
        this(new SystemTrayWrapper(), new RealTimer(configuration.getPollPeriodInSecond(), TimeUnit.SECONDS), jenkinsClient, configuration);
    }

    public JenkinsMonitorTray(final Timer timer, final JenkinsClient jenkinsClient, final Configuration configuration) {
        this(new SystemTrayWrapper(), timer, jenkinsClient, configuration);
    }

    protected JenkinsMonitorTray(final SystemTrayWrapper systemTray, final Timer timer, final JenkinsClient jenkinsClient, final Configuration configuration) {
        //Obtain only one instance of the SystemTray object
        this.tray = systemTray;
        this.jobStatusBeschreibungen = new JobStatusBeschreibungen();
        this.timer = timer;
        timer.register(this);
        timer.start();
        this.configuration = configuration;
        this.jenkinsClient = jenkinsClient;
        // Statusfenster
        this.statusArea = new Statusfenster(jobStatusBeschreibungen);
        try {
            this.statusArea.setAlwaysOnTop(true);
            this.statusArea.setLocationByPlatform(false);
        } catch (Exception ex) {
            LOGGER.warn("Konnte natives Desktopverhalten nicht setzen", ex);
        }
        // ContextMenu
        this.contextMenu=new ContextMenu(this.jobStatusBeschreibungen, tray, statusArea,  timer);
    }

    public TrayIcon getTrayIcon() {
        return this.tray.getTrayIcon();
    }

    protected void erzeugeTrayIconDarstellung() {
        LOGGER.debug("Erzeuge Darstellung TrayIcon");
        try {
            final ImageGenerator imageGenerator = getImageGenerator();
            imageGenerator.updateStatusArea(statusArea);

            TrayIcon trayIcon = getTrayIcon();
            if (trayIcon == null) {
                final BufferedImage trayImage = imageGenerator.createImage(100, 100);
                trayIcon = new TrayIcon(trayImage);
                trayIcon.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        LOGGER.debug("Mouseklick links");
                        if (e.getClickCount() == 1) {
                            statusArea.setVisible(!statusArea.isVisible());
                        }
                    }
                });
//                trayIcon.addActionListener(e -> {
//                    LOGGER.debug("Mouseklick links doppelt");
//                    statusArea.setVisible(!statusArea.isVisible());
//                });
                tray.add(trayIcon);
            } else {
                imageGenerator.drawImage((BufferedImage) trayIcon.getImage(), 100, 100);
            }

            trayIcon.setImageAutoSize(true);
            if (this.jobStatusBeschreibungen.size() > 0) {
                trayIcon.setToolTip("Links: Statusfenster ein-/aus, Rechts: Status & Settings");
            } else {
                trayIcon.setToolTip("Keine Jobs überwachend");
            }
            trayIcon.setPopupMenu(this.contextMenu.createContextMenu());

        } catch (Exception ex) {
            LOGGER.error("Unerwarteter Fehler - wie immer :( ", ex);
        }

    }

    protected ImageGenerator getImageGenerator() {
        return new ImageGenerator(this.jobStatusBeschreibungen);
    }

    protected void bereinigeJobStatusBeschreibungen(final JobBeschreibungen jobBeschreibungen){
        final java.util.List<String> entriesToDelete = AbstractJobBeschreibung.sortedKeyStreamOf(jobStatusBeschreibungen)
            .parallel()
            .filter(primaryKey -> !jobBeschreibungen.containsKey(primaryKey))
            .collect(Collectors.toList());
        entriesToDelete.stream().parallel().forEach(jobStatusBeschreibungen::remove);
    }

    public void updateJobStatus() {
        final JobBeschreibungen jobBeschreibungen = this.configuration.getJobBeschreibungen();
        bereinigeJobStatusBeschreibungen(jobBeschreibungen);

        // aktualisiere den Status der Jobs durch Jenkinsabfragen
        aktualisiereTrayIconDarstellung();
        // Langlaufender Prozess durch Request die ins timeout laufen
        jenkinsClient.ladeJobsStatus(jobStatusBeschreibungen, jobBeschreibungen);
        aktualisiereTrayIconDarstellung();
    }

    protected void aktualisiereTrayIconDarstellung() {
        erzeugeTrayIconDarstellung();
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    @Override
    public void timeElapsed() {
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
