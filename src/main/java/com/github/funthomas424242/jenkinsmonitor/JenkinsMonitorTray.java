package com.github.funthomas424242.jenkinsmonitor;

import javax.swing.*;
import java.awt.*;

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

            // If you want to create an icon in the system tray to preview
            Image image = Toolkit.getDefaultToolkit().createImage("some-icon.png");
            //Alternative (if the icon is on the classpath):
            //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

            TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
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
}
