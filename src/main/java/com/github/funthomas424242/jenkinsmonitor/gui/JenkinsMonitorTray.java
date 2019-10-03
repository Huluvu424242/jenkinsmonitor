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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import static com.github.funthomas424242.jenkinsmonitor.gui.ImageGenerator.LOGGER;

public class JenkinsMonitorTray {

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
        this.configuration = configuration;
        this.requester = requester;
        this.statusArea = new JWindow();
        try {
            this.statusArea.setAlwaysOnTop(true);
            this.statusArea.setLocationByPlatform(false);
        }catch(Exception ex){
            LOGGER.error("Konnte natives Desktopverhalten nicht setzen", ex);
        }
    }

    public TrayIcon getTrayIcon() {
        return this.tray.getTrayIcon();
    }

    protected void erzeugeDarstellung() {
        try {
            final ImageGenerator imageGenerator = new ImageGenerator(this.jobStatusBeschreibungen);
            final Image trayImage = imageGenerator.getImage(100, 100);
            final TrayIcon trayIcon = new TrayIcon(trayImage);
            trayIcon.setImageAutoSize(true);
            if (this.jobStatusBeschreibungen.length > 0) {
                trayIcon.setToolTip("Linksklick: Status ein-/ausblenden, Rechtsklick: Settings");
            } else {
                trayIcon.setToolTip("Keine Jobs überwachend");
            }
            trayIcon.setPopupMenu(createSettingsMenu());
            tray.add(trayIcon);

            trayIcon.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    final Point showPoint = e.getPoint();
                    System.out.println("### loc(" + showPoint.getX() + ", " + showPoint.getY() + ") ##");
                    imageGenerator.updateStatusArea(statusArea, showPoint);
                    if (e.getClickCount() == 1) {
                        statusArea.setVisible(!statusArea.isVisible());
                    }
                }
            });

        } catch (
            Exception ex) {
            System.err.print(ex);
        }

    }

    /**
     * https://stackoverflow.com/questions/13989265/task-tray-notification-balloon-events
     *
     * @return
     */
    protected PopupMenu createSettingsMenu() {
        final PopupMenu popup = new PopupMenu();


        // Create a popup menu components
        final MenuItem aboutItem = new MenuItem("Über");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/FunThomas424242/jenkinsmonitor"));
                    statusArea.setVisible(false);
                } catch (IOException | URISyntaxException ex) {
                    LOGGER.error("URL https://github.com/FunThomas424242/jenkinsmonitor konnte nicht geöffnet werden", ex);
                }
            }
        });
        MenuItem bugtracker = new MenuItem("Bugtracker");
        bugtracker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/FunThomas424242/jenkinsmonitor/issues"));
                    statusArea.setVisible(false);
                } catch (IOException | URISyntaxException ex) {
                    LOGGER.error("URL https://github.com/FunThomas424242/jenkinsmonitor/issues konnte nicht geöffnet werden", ex);
                }
            }
        });
        MenuItem exitItem = new MenuItem("Beenden");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                statusArea.setVisible(false);
                statusArea.dispose();
                tray.removeTrayIcon();
            }
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
        tray.remove(getTrayIcon());
        erzeugeDarstellung();
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

}
