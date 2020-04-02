package com.github.funthomas424242.jenkinsmonitor.gui;

import com.github.funthomas424242.jenkinsmonitor.etc.Counter;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatusBeschreibung;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class StatusWindow extends JWindow {
    public static final Logger LOGGER = LoggerFactory.getLogger(StatusWindow.class);

//    JScrollPane scrollpane;

    public StatusWindow() {
//        super("JScrollPane Demonstration");
//        setSize(300, 200);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        aktualisiereContentPane();
    }


    private JLabel createLabel(final Counter counter, JobStatusBeschreibung jobStatus) {
        final String htmlTemplate = "<html><body style=\"display:inline-block;\"><h1>[" + jobStatus.getOrderId() + "] " + jobStatus.getJobName() + "</h1>"
            + "<p>(" + counter.value + ") Status: " + jobStatus.getJobStatus().toString()
            + " <a href=\"" + jobStatus.getJobUrl() + "\">" + jobStatus.getJobUrl() + "</a></p></body></html>";
        final JLabel label = new JLabel(htmlTemplate);
        label.setOpaque(true);
        label.setBackground(jobStatus.getStatusColor());
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                URI uri = null;
                try {
                    uri = jobStatus.getJobUrl().toURI();
                    Desktop.getDesktop().browse(uri);
                    setVisible(false);
                } catch (IOException | URISyntaxException ex) {
                    LOGGER.error("URL " + uri.toString() + " konnte nicht geöffnet werden", ex);
                }
            }
        });
        return label;
    }


    private JScrollPane createContent() {
        String categories[] = {"Household", "Office", "Extended Family",
            "Company (US)", "Company (World)", "Team", "Will",
            "Birthday Card List", "High School", "Country", "Continent",
            "Planet"};
        JList list = new JList(categories);
        return new JScrollPane(list);
    }

    public void aktualisiereContentPane() {
        getContentPane().add(createContent(), BorderLayout.CENTER);
    }

    public static void main(String args[]) {
        StatusWindow window = new StatusWindow();
        window.pack();
        window.setAlwaysOnTop(true);
        window.setLocationByPlatform(false);
        window.setVisible(true);

    }
}
