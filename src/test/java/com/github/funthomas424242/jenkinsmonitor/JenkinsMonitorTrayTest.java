package com.github.funthomas424242.jenkinsmonitor;

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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JenkinsMonitorTrayTest {

    JenkinsMonitorTray jenkinsMonitorTray;

    @BeforeEach
    public void setUp() {
        jenkinsMonitorTray = new JenkinsMonitorTray();
        jenkinsMonitorTray.show();
    }

    boolean isImageOfColor(BufferedImage image, Color color) {
        boolean isOfColor = true;
        final int width = image.getWidth();
        final int height = image.getHeight();
        for (int breite = 0; breite < width; breite++) {
            for (int hoehe = 0; hoehe < height; hoehe++) {
                isOfColor = isOfColor && image.getRGB(breite, hoehe) == color.getRGB();
            }
        }
        return isOfColor;
    }

    @Test
    @DisplayName("Initial wird ein graues Icon angezeigt mit Tooltipp <<No jobs watching>>")
    public void shouldShowNoJobsWatching() throws AWTException {
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertEquals("No jobs watching", trayIcon.getToolTip());
        final Image image = trayIcon.getImage();
        assertTrue(isImageOfColor((BufferedImage) image, JobStatusBeschreibung.JobStatus.OTHER.getColor()));
    }


    @Test
    @Disabled
    @DisplayName("Erzeuge ein grünes TrayIcon")
    public void createOneJobGreen() {
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertEquals("No jobs watching", trayIcon.getToolTip());
    }


    @Test
    @Disabled
    @DisplayName("Der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master success>>")
    public void shouldShowOneJobWatching() {
        final Configuration configuration = new OneJobConfiguration();
        jenkinsMonitorTray.reloadConfiguration(configuration);
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
//        assertEquals("MulitbranchJob/master s/uccess", trayIcon.getToolTip());
    }

    @Test
    @Disabled
    public void show() {
        jenkinsMonitorTray.getTrayIcon();
    }

}
