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
import com.github.funthomas424242.jenkinsmonitor.jenkins.JenkinsClient;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobBeschreibung;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatusBeschreibung;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class JenkinsMonitorTray implements Timer.Listener {

    public static final Logger LOGGER = LoggerFactory.getLogger(JenkinsMonitorTray.class);
    public static final String WEBSITE_JENKINSMONITOR_ISSUES = "https://github.com/FunThomas424242/jenkinsmonitor/issues";
    public static final String WEBSITE_JENKINSMONITOR = "https://github.com/FunThomas424242/jenkinsmonitor";
    public static final String ERR_COULD_NOT_OPEN_URL = "URL %s konnte nicht geöffnet werden";

    protected final Configuration configuration;
    protected final SystemTrayWrapper tray;
    protected final JenkinsClient requester;
    protected final JWindow statusArea;
    protected final Timer timer;

    protected JobStatusBeschreibung[] jobStatusBeschreibungen;

    public JenkinsMonitorTray(final Configuration configuration) {
        this(new JenkinsClient(), configuration);
    }

    public JenkinsMonitorTray(final JenkinsClient jenkinsClient, final Configuration configuration) {
        this(new SystemTrayWrapper(), new RealTimer(configuration.getPollPeriodInSecond(), TimeUnit.SECONDS), jenkinsClient, configuration);
    }

    public JenkinsMonitorTray(final Timer timer, final JenkinsClient jenkinsClient, final Configuration configuration) {
        this(new SystemTrayWrapper(), timer, jenkinsClient, configuration);
    }

    protected JenkinsMonitorTray(final SystemTrayWrapper systemTray, final Timer timer, final JenkinsClient requester, final Configuration configuration) {
        //Obtain only one instance of the SystemTray object
        this.tray = systemTray;
        this.timer = timer;
        timer.register(this);
        timer.start();
        this.configuration = configuration;
        this.requester = requester;
        this.statusArea = new JWindow();
        try {
            this.statusArea.setAlwaysOnTop(true);
            this.statusArea.setLocationByPlatform(false);
        } catch (Exception ex) {
            LOGGER.error("Konnte natives Desktopverhalten nicht setzen", ex);
        }
    }

    public TrayIcon getTrayIcon() {
        return this.tray.getTrayIcon();
    }

    protected void erzeugeDarstellung() {
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
            if (this.jobStatusBeschreibungen.length > 0) {
                trayIcon.setToolTip("Links: Statusfenster ein-/aus, Rechts: Status & Settings");
            } else {
                trayIcon.setToolTip("Keine Jobs überwachend");
            }
            trayIcon.setPopupMenu(createSettingsMenu());

        } catch (Exception ex) {
            LOGGER.error("Unerwartet wie immer ", ex);
        }

    }

    protected ImageGenerator getImageGenerator() {
        return new ImageGenerator(this.jobStatusBeschreibungen);
    }

    /**
     * https://stackoverflow.com/questions/13989265/task-tray-notification-balloon-events
     *
     * @return PopuMenu Contextmenü des TrayIcons
     */
    protected PopupMenu createSettingsMenu() {
        final PopupMenu popup = new PopupMenu();


        // Create a popup menu components
        Arrays.stream(this.jobStatusBeschreibungen).forEach(statusBeschreibung -> {
            final String itemText = String.format("[%s] %s", statusBeschreibung.getJobStatus(), statusBeschreibung.getJobName());
            final MenuItem item = new MenuItem(itemText);
            item.addActionListener(actionEvent -> {
                URI webSite = null;
                try {
                    webSite = statusBeschreibung.getJobUrl().toURI();
                    Desktop.getDesktop().browse(webSite);
                    statusArea.setVisible(false);
                } catch (IOException | URISyntaxException ex) {
                    LOGGER.error(String.format(ERR_COULD_NOT_OPEN_URL, webSite), ex);
                }
            });
            popup.add(item);
        });


        final MenuItem aboutItem = new MenuItem("Über");
        aboutItem.addActionListener(actionEvent -> {
            try {
                Desktop.getDesktop().browse(new URI(WEBSITE_JENKINSMONITOR));
                statusArea.setVisible(false);
            } catch (IOException | URISyntaxException ex) {
                LOGGER.error(String.format(ERR_COULD_NOT_OPEN_URL, WEBSITE_JENKINSMONITOR), ex);
            }
        });
        MenuItem bugtracker = new MenuItem("Bugtracker");
        bugtracker.addActionListener(actionEvent -> {
            try {
                Desktop.getDesktop().browse(new URI(WEBSITE_JENKINSMONITOR_ISSUES));
                statusArea.setVisible(false);
            } catch (IOException | URISyntaxException ex) {
                LOGGER.error(String.format(ERR_COULD_NOT_OPEN_URL, WEBSITE_JENKINSMONITOR_ISSUES), ex);
            }
        });
        MenuItem exitItem = new MenuItem("Beenden");
        exitItem.addActionListener(actionEvent -> {
            timer.stop();
            statusArea.setVisible(false);
            statusArea.dispose();
            tray.removeTrayIcon();
        });

        //Add components to popup menu
        popup.add(aboutItem);
        popup.add(bugtracker);
        popup.addSeparator();
        popup.add(exitItem);
        return popup;
    }

    public void updateJobStatus() {
        final JobBeschreibung[] jobBeschreibungen = this.configuration.getJobBeschreibungen();
        updateJobStatus(jobBeschreibungen);
    }

    protected void updateJobStatus(JobBeschreibung[] jobBeschreibungen) {
        this.jobStatusBeschreibungen = requester.ladeJobsStatus(jobBeschreibungen);
        updateDarstellung();
    }

    protected void updateDarstellung() {
        erzeugeDarstellung();
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
