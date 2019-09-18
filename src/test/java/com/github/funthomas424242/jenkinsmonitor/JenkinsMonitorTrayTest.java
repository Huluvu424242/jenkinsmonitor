package com.github.funthomas424242.jenkinsmonitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JenkinsMonitorTrayTest {

    JenkinsMonitorTray jenkinsMonitorTray;

    @BeforeEach
    public void setUp() {
        jenkinsMonitorTray = new JenkinsMonitorTray();
        jenkinsMonitorTray.show();
    }

    @Test
    @DisplayName("Initial zeigt der Tooltipp <<No jobs watching>>")
    public void shouldShowNoJobsWatching() throws AWTException {
        final TrayIcon trayIcon = jenkinsMonitorTray.getTrayIcon();
        assertEquals( "No jobs watching",trayIcon.getToolTip());
    }

    @Test
    @DisplayName("Der Tooltipp soll einen Eintrag enthalten: <<MultibranchJob/master success>>")
    public void shouldShowOneJobWatching(){
        final Configuration configuration = new OneJobConfiguration();
        jenkinsMonitorTray.reloadConfiguration(configuration);
        final TrayIcon trayIcon=jenkinsMonitorTray.getTrayIcon();
//        assertEquals("MulitbranchJob/master s/uccess", trayIcon.getToolTip());
    }

}
