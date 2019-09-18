package com.github.funthomas424242.jenkinsmonitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JenkinsMonitorTrayTest {

    JenkinsMonitorTray jenkinsMonitorTray;

    @BeforeEach
    public void setUp() {
        jenkinsMonitorTray = new JenkinsMonitorTray();
        jenkinsMonitorTray.show();
    }

    @Test
    @Disabled
    @DisplayName("Erzeuge ein graues Image mit Breite 5 und Höhe 10")
    public void createGrauesImage() {
        final BufferedImage grayImage = jenkinsMonitorTray.createGrayImage();
        assertEquals(5,grayImage.getWidth());
        assertEquals(10,grayImage.getHeight());
//        assertEquals("No jobs watching", grayImage.getToolTip());
    }




    @Test
    @DisplayName("Initial zeigt der Tooltipp <<No jobs watching>>")
    public void shouldShowNoJobsWatching() throws AWTException {
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertEquals("No jobs watching", trayIcon.getToolTip());
    }


    @Test
    @Disabled
    @DisplayName("Erzeuge ein graues TrayIcon mit Tooltipp: No jobs watching")
    public void createNoJobsWatching() {
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertEquals("No jobs watching", trayIcon.getToolTip());
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
