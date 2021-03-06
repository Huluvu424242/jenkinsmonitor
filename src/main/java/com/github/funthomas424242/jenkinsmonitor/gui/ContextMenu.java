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
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextMenu {

    public static final String WEBSITE_JENKINSMONITOR_ISSUES = "https://github.com/Huluvu424242/jenkinsmonitor/issues?q=is%3Aopen+is%3Aissue+label%3Abug";
    public static final String WEBSITE_JENKINSMONITOR = "https://github.com/Huluvu424242/jenkinsmonitor";
    public static final String ERR_COULD_NOT_OPEN_URL = "URL %s konnte nicht geöffnet werden";

    public static final Logger LOGGER = LoggerFactory.getLogger(ContextMenu.class);

    protected final Versionsinfofenster versionsinfofenster;

    protected final JobStatusBeschreibungen jobStatusBeschreibungen;

    protected final SystemTrayWrapper tray;

    protected final Statusfenster statusfenster;

    protected final Timer timer;

    public ContextMenu(final JobStatusBeschreibungen jobStatusBeschreibungen, final SystemTrayWrapper tray, final Timer timer) {
        // Statusfenster
        this.statusfenster = new Statusfenster(jobStatusBeschreibungen);
        this.versionsinfofenster = new Versionsinfofenster();
        this.jobStatusBeschreibungen = jobStatusBeschreibungen;
        this.tray = tray;
        this.timer = timer;
    }

    protected void aktualisiereStatusfenster() {
        statusfenster.aktualisiereContentPane();
    }

    protected void showStatusfenster(final boolean isShown){
        statusfenster.setVisible(isShown);
    }

    protected boolean isStatusfensterSichtbar(){
        return statusfenster.isVisible();
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
                            statusfenster.setVisible(false);
                        } catch (IOException | URISyntaxException ex) {
                            LOGGER.error(String.format(ERR_COULD_NOT_OPEN_URL, webSite), ex);
                        }
                    });
                    popup.add(item);
                });


        /* Projekt Homepage Menüeintrag */
        final MenuItem homepage = new MenuItem("Projekt-Webseite");
        homepage.addActionListener(actionEvent -> {
            try {
                Desktop.getDesktop().browse(new URI(WEBSITE_JENKINSMONITOR));
                statusfenster.setVisible(false);
            } catch (IOException | URISyntaxException ex) {
                LOGGER.error(String.format(ERR_COULD_NOT_OPEN_URL, WEBSITE_JENKINSMONITOR), ex);
            }
        });
        /* Bugtracker Menüeintrag */
        final MenuItem bugtracker = new MenuItem("Bugtracker");
        bugtracker.addActionListener(actionEvent -> {
            try {
                Desktop.getDesktop().browse(new URI(WEBSITE_JENKINSMONITOR_ISSUES));
                statusfenster.setVisible(false);
            } catch (IOException | URISyntaxException ex) {
                LOGGER.warn(String.format(ERR_COULD_NOT_OPEN_URL, WEBSITE_JENKINSMONITOR_ISSUES), ex);
            }
        });
        /* Versionsinfo Menüeintrag */
        final CheckboxMenuItem versionsinfo = new CheckboxMenuItem("Versionsinfo", versionsinfofenster.isVisible());
        versionsinfo.addItemListener(itemEvent -> versionsinfofenster.setVisible(!versionsinfofenster.isVisible()));

        final MenuItem exitItem = new MenuItem("Beenden");
        exitItem.addActionListener(actionEvent -> {
            timer.stop();
            versionsinfofenster.setVisible(false);
            versionsinfofenster.dispose();
            statusfenster.setVisible(false);
            statusfenster.dispose();
            tray.removeTrayIcon();
        });

        //Add components to popup menu
        popup.addSeparator();
        popup.add(versionsinfo);
        popup.add(bugtracker);
        popup.add(homepage);
        popup.addSeparator();
        popup.add(exitItem);
        return popup;
    }
}
