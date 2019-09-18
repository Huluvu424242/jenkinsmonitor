package com.github.funthomas424242.jenkinsmonitor;

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
