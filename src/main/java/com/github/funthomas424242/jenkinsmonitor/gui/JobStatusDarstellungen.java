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
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobBeschreibungen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.stream.Collectors;

public class JobStatusDarstellungen {

    public static final Logger LOGGER = LoggerFactory.getLogger(JobStatusDarstellungen.class);

    protected final JobStatusBeschreibungen jobStatusBeschreibungen;

    protected final SystemTrayWrapper trayWrapper;
    protected final Statusfenster statusfenster;
    protected final ContextMenu contextMenu;

    public JobStatusDarstellungen(final JobStatusBeschreibungen jobStatusBeschreibungen,
                                  final Timer timer) {
        this.jobStatusBeschreibungen = jobStatusBeschreibungen;
        this.trayWrapper = new SystemTrayWrapper();

        // Statusfenster
        this.statusfenster = new Statusfenster(jobStatusBeschreibungen);
        try {
            this.statusfenster.setAlwaysOnTop(true);
            this.statusfenster.setLocationByPlatform(false);
        } catch (Exception ex) {
            LOGGER.warn("Konnte natives Desktopverhalten nicht setzen", ex);
        }
        // ContextMenu
        this.contextMenu = new ContextMenu(this.jobStatusBeschreibungen, trayWrapper, statusfenster, timer);
    }


    protected void bereinigeJobStatusBeschreibungen(final JobBeschreibungen jobBeschreibungen) {
        final java.util.List<String> entriesToDelete = AbstractJobBeschreibung.sortedKeyStreamOf(jobStatusBeschreibungen)
            .parallel()
            .filter(primaryKey -> !jobBeschreibungen.containsKey(primaryKey))
            .collect(Collectors.toList());
        entriesToDelete.stream().parallel().forEach(jobStatusBeschreibungen::remove);
    }

    protected void aktualisiereStatusfenster() {
        statusfenster.aktualisiereContentPane();
    }

    protected void aktualisiereTrayIconDarstellung() {
        LOGGER.debug("Erzeuge Darstellung TrayIcon");
        try {


            TrayIcon trayIcon = trayWrapper.getTrayIcon();
            final ImageGenerator imageGenerator = new ImageGenerator(this.jobStatusBeschreibungen);
            if (trayIcon == null) {
                final BufferedImage trayImage = imageGenerator.createImage(100, 100);
                trayIcon = new TrayIcon(trayImage);
                trayIcon.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        LOGGER.debug("Mouseklick links");
                        if (e.getClickCount() == 1) {
                            statusfenster.setVisible(!statusfenster.isVisible());
                        }
                    }
                });
//                trayIcon.addActionListener(e -> {
//                    LOGGER.debug("Mouseklick links doppelt");
//                    statusArea.setVisible(!statusArea.isVisible());
//                });
                trayWrapper.add(trayIcon);
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

}
