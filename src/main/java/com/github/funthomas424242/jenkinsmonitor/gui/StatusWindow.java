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


import com.github.funthomas424242.jenkinsmonitor.etc.Counter;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatus;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatusBeschreibung;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StatusWindow extends JWindow {
    public static final Logger LOGGER = LoggerFactory.getLogger(StatusWindow.class);

//    JScrollPane scrollpane;

    public StatusWindow() {
//        super("JScrollPane Demonstration");
//        setSize(300, 200);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        aktualisiereContentPane();
    }

    public StatusWindow(final JobStatusBeschreibung[] jobStatusBeschreibungen) {
        aktualisiereContentPane(jobStatusBeschreibungen);
    }


    private JLabel createLabel(final int counter, JobStatusBeschreibung jobStatus) {
        final String htmlTemplate = "<html><body style=\"display:inline-block;\"><h1>[" + jobStatus.getOrderId() + "] " + jobStatus.getJobName() + "</h1>"
            + "<p>(" + counter + ") Status: " + jobStatus.getJobStatus().toString()
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

    private StatusItem createStatusItem(final int counter, JobStatusBeschreibung jobStatus) {
        final String htmlTemplate = "<html><body style=\"display:inline-block;\"><h1>[" + jobStatus.getOrderId() + "] " + jobStatus.getJobName() + "</h1>"
            + "<p>(" + counter + ") Status: " + jobStatus.getJobStatus().toString()
            + " <a href=\"" + jobStatus.getJobUrl() + "\">" + jobStatus.getJobUrl() + "</a></p></body></html>";
        return new StatusItem(htmlTemplate, jobStatus.getJobUrl());
    }


    private JScrollPane createContent(final JobStatusBeschreibung[] jobsStatusBeschreibungen) {

//        list.setLayout(new GridLayout(jobsStatusBeschreibungen.length, 1));
        final JLabel[] eintraege = new JLabel[jobsStatusBeschreibungen.length];
        final Counter counter = new Counter();
        Arrays.stream(jobsStatusBeschreibungen).sorted().forEach((jobStatus) -> {
            eintraege[counter.value] = createLabel(counter.value + 1, jobStatus);
            counter.value++;
        });
        final JList<JLabel> list = new JList(eintraege);
        final JScrollPane pane = new JScrollPane(list);
        return pane;
    }

    private JScrollPane createContentTmp(final JobStatusBeschreibung[] jobsStatusBeschreibungen) {
        final List<StatusItem> statusItems = new ArrayList<StatusItem>();
        final Counter counter = new Counter();
        Arrays.stream(jobsStatusBeschreibungen).sorted().forEach((jobStatus) -> {
            statusItems.add(createStatusItem(counter.value + 1, jobStatus));
            counter.value++;
        });
        final StatusItem[] listModel = statusItems.toArray(new StatusItem[statusItems.size()]);
        final JList<String> list = new JList(listModel);
        list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        final ListSelectionModel listSelectionModel = list.getSelectionModel();
        listSelectionModel.addListSelectionListener(
            new SharedListSelectionHandler(listModel,this));
        final JScrollPane pane = new JScrollPane(list);
        return pane;
    }

    public void aktualisiereContentPane(final JobStatusBeschreibung[] jobsStatusBeschreibungen) {
//        this.add(createContentTmp(jobsStatusBeschreibungen),BorderLayout.CENTER);
//        getContentPane().add(createContentTmp(), BorderLayout.CENTER);
//        setContentPane(createContent(jobsStatusBeschreibungen));
        setContentPane(createContentTmp(jobsStatusBeschreibungen));
        pack();
        repaint();
    }

    public static void main(String args[]) {
        final JobStatusBeschreibung[] jobstatusBeschreibungen = new JobStatusBeschreibung[3];
        try {
            jobstatusBeschreibungen[0] = new JobStatusBeschreibung("job0", JobStatus.FAILURE, new URL("http://localhost/job0"), "0");
            jobstatusBeschreibungen[1] = new JobStatusBeschreibung("job1", JobStatus.FAILURE, new URL("http://localhost/job1"), "1");
            jobstatusBeschreibungen[2] = new JobStatusBeschreibung("job2", JobStatus.FAILURE, new URL("http://localhost/job2"), "2");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        StatusWindow window = new StatusWindow();
        window.aktualisiereContentPane(jobstatusBeschreibungen);
        window.pack();
        window.setAlwaysOnTop(true);
        window.setLocationByPlatform(false);
        window.setVisible(true);

        try {
            Thread.sleep(4000);
            jobstatusBeschreibungen[2] = new JobStatusBeschreibung("job3", JobStatus.FAILURE, new URL("http://localhost/job3"), "2");
        } catch (MalformedURLException | InterruptedException e) {
            e.printStackTrace();
        }
        window.aktualisiereContentPane(jobstatusBeschreibungen);

    }
}


class SharedListSelectionHandler implements ListSelectionListener {
    public static final Logger LOGGER = LoggerFactory.getLogger(SharedListSelectionHandler.class);

    protected final StatusWindow statusArea;
    protected final StatusItem[] listModel;

    public SharedListSelectionHandler(final StatusItem[] listModel, final StatusWindow statusArea) {
        this.listModel = listModel;
        this.statusArea=statusArea;
    }

    public void valueChanged(ListSelectionEvent event) {
        final ListSelectionModel selectionModel = (ListSelectionModel) event.getSource();
        int firstIndex = event.getFirstIndex();
        int lastIndex = event.getLastIndex();
        boolean isAdjusting = event.getValueIsAdjusting();

        if (!selectionModel.isSelectionEmpty() && !isAdjusting) {
            // Find out which indexes are selected.
            int minIndex = selectionModel.getMinSelectionIndex();
            int maxIndex = selectionModel.getMaxSelectionIndex();

            final URI navigationURI;
            try {
                navigationURI = this.listModel[minIndex].getNavigationURL().toURI();
                Desktop.getDesktop().browse(navigationURI);
                statusArea.setVisible(false);
            } catch (URISyntaxException | IOException e) {
                LOGGER.error(e.toString());
            }


        }
    }
}
