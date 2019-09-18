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

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JenkinsMonitorTray {

    protected SystemTray tray;

    public JenkinsMonitorTray() {
        //Obtain only one instance of the SystemTray object
        this.tray = SystemTray.getSystemTray();
    }


    public TrayIcon getTrayIcon() {
        return this.tray.getTrayIcons()[0];
    }

    public void show() {
        try {

            Image grayImage = createGrayImage();
            Image greenImage = createGreenImage();

            TrayIcon trayIcon = new TrayIcon(grayImage, "No jobs watching");
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            trayIcon.setToolTip("No jobs watching");
            tray.add(trayIcon);

            // Display info notification:
            trayIcon.displayMessage("Hello, World", "Java Notification Demo", TrayIcon.MessageType.INFO);
            // Error:
            // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.ERROR);
            // Warning:
            // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.WARNING);
        } catch (Exception ex) {
            System.err.print(ex);
        }
    }

    public void reloadConfiguration(Configuration configuration) {

    }

    public BufferedImage createGrayImage() {
            int w = 50;
            int h = 100;
            int subImageOffset = 10;

            BufferedImage src = new BufferedImage(w, h,
                BufferedImage.TYPE_BYTE_INDEXED);
            Graphics g = src.createGraphics();
            g.setColor(Color.gray);
            g.fillRect(0, 0, w, h);
//            g.setColor(Color.green);
//            g.fillRect(subImageOffset, subImageOffset,
//                w - 2 * subImageOffset, h - 2* subImageOffset);
//            g.setColor(Color.blue);
//            g.fillRect(2 * subImageOffset, 2 * subImageOffset,
//                w - 4 * subImageOffset, h - 4 * subImageOffset);
            g.dispose();

            return src;
    }

    public BufferedImage createGreenImage() {
        int w = 100;
        int h = 100;
        int subImageOffset = 10;

        BufferedImage src = new BufferedImage(w, h,
            BufferedImage.TYPE_BYTE_INDEXED);
        Graphics g = src.createGraphics();
        g.setColor(Color.red);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.green);
        g.fillRect(subImageOffset, subImageOffset,
            w - 2 * subImageOffset, h - 2* subImageOffset);
        g.setColor(Color.blue);
        g.fillRect(2 * subImageOffset, 2 * subImageOffset,
            w - 4 * subImageOffset, h - 4 * subImageOffset);
        g.dispose();

        return src;
    }
}
