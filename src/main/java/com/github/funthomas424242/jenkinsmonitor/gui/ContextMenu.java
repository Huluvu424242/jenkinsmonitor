package com.github.funthomas424242.jenkinsmonitor.gui;

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

import com.github.funthomas424242.jenkinsmonitor.etc.Timer;
import com.github.funthomas424242.jenkinsmonitor.jenkins.AbstractJobBeschreibung;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.github.funthomas424242.jenkinsmonitor.gui.JenkinsMonitorTray.*;

public class ContextMenu {

    public static final Logger LOGGER = LoggerFactory.getLogger(JenkinsMonitorTray.class);

    protected final JobStatusBeschreibungen jobStatusBeschreibungen;

    protected final SystemTrayWrapper tray;

    protected final Statusfenster statusArea;

    protected final Timer timer;

    public ContextMenu(final JobStatusBeschreibungen jobStatusBeschreibungen, final SystemTrayWrapper tray, final Statusfenster statusArea, final Timer timer) {
        this.jobStatusBeschreibungen = jobStatusBeschreibungen;
        this.tray = tray;
        this.statusArea = statusArea;
        this.timer = timer;
    }

    /**
     * https://stackoverflow.com/questions/13989265/task-tray-notification-balloon-events
     *
     * @return PopuMenu Contextmenü des TrayIcons
     */
    protected PopupMenu createContextMenu() {
        final PopupMenu popup = new PopupMenu();

        // Create a popup menu components
        AbstractJobBeschreibung.sortedStreamOf(this.jobStatusBeschreibungen)
            .forEach(statusBeschreibung -> {
                final String itemText = String.format("[%s] <%s> %s", statusBeschreibung.getJobOrderId(), statusBeschreibung.getJobStatus(), statusBeschreibung.getJobName());
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
                LOGGER.warn(String.format(ERR_COULD_NOT_OPEN_URL, WEBSITE_JENKINSMONITOR_ISSUES), ex);
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
//        MenuScroller.setScrollerFor(popup);
        return popup;
    }
}
