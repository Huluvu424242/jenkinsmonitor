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
import com.github.funthomas424242.jenkinsmonitor.jenkins.AbstractJobBeschreibung;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatus;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatusBeschreibung;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;


public class Statusfenster extends JDialog {
    public static final Logger LOGGER = LoggerFactory.getLogger(Statusfenster.class);

    protected final transient JobStatusBeschreibungen jobStatusBeschreibungen;

    public Statusfenster(final JobStatusBeschreibungen jobStatusBeschreibungen) {
        this.jobStatusBeschreibungen = jobStatusBeschreibungen;
        this.setAlwaysOnTop(true);
        this.setLocationByPlatform(false);
        // HINT: setPreferredSize: Nicht nutzen, ist dann immer zu klein
        this.pack();
        this.setTitle("Jenkins Jobstatusübersicht");
        final ImageIcon goldsteinLogo = ImageGenerator.getGoldSteinImageIcon();
        this.setIconImage(goldsteinLogo.getImage());
    }


    private static StatusItem createStatusItem(final JobStatusZeileOben zeileOben, final JobStatusZeileUnten zeileUnten, final JobStatus jobStatus, final URL jobUrl) {
        final String colorValueHEX = jobStatus.getColorValueHEX() != null ? jobStatus.getColorValueHEX() : JobStatus.OTHER.getColorValueHEX();

        final String htmlTemplate =
                "<html><body style=\"display:inline-block;font-family:monospace;background-color:" + colorValueHEX + ";\">"
                        + zeileOben.toHTMLString()
                        + zeileUnten.toHTMLString()
                        + "</body></html>";
        return new StatusItem(htmlTemplate, jobUrl);
    }


    private Container createContent() {
        final JobStatusBeschreibungen tmpJobStatusBeschreibungen = new JobStatusBeschreibungen(this.jobStatusBeschreibungen.getCloneOfDataModel());

        // Model füllen
        final List<StatusItem> statusItems = new ArrayList<>();
        final Counter counter = new Counter();
        AbstractJobBeschreibung.sortedStreamOf(tmpJobStatusBeschreibungen)
                .forEach(jobStatus -> {
                    statusItems.add(
                            createStatusItem(
                                    tmpJobStatusBeschreibungen.getJobStatusZeileOben(jobStatus),
                                    tmpJobStatusBeschreibungen.getJobStatusZeileUnten(jobStatus, counter.value + 1),
                                    jobStatus.getJobStatus(),
                                    jobStatus.getJobUrl())
                    );
                    counter.value++;
                });


        // Datenmodell erzeugen
        final DefaultListModel<StatusItem> listModel = new DefaultListModel<>();
        listModel.addAll(statusItems);
        // Liste erzeugen mit Datenmodell
        final JList<StatusItem> list = new JList<>(listModel);
        // Layoutvorgaben
        list.setLayoutOrientation(JList.VERTICAL);

        // Selektion Modus
        list.setSelectionMode(SINGLE_SELECTION);
        final ListSelectionModel listSelectionModel = list.getSelectionModel();
        listSelectionModel.addListSelectionListener(
                new SharedListSelectionHandler(statusItems, this));

        // Scrollpane erzeugen
        final JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setOpaque(true);
        return scrollPane;
    }

    public void aktualisiereContentPane() {
        if (jobStatusBeschreibungen != null && jobStatusBeschreibungen.size() > 0) {
            setContentPane(createContent());
            pack();
            repaint();
        }
    }

    public static void main(String[] args) {
        final JobStatusBeschreibungen jobstatusBeschreibungen = new JobStatusBeschreibungen();
        try {
            JobStatusBeschreibung jobstatusBeschreibungen0 = new JobStatusBeschreibung("job0", JobStatus.FAILURE, new URL("http://localhost/job0"), "0");
            jobstatusBeschreibungen.put(jobstatusBeschreibungen0.getPrimaryKey(), jobstatusBeschreibungen0);
            JobStatusBeschreibung jobstatusBeschreibungen1 = new JobStatusBeschreibung("job1", JobStatus.SUCCESS, new URL("http://localhost/sdfdfdfdfdffdfdff/hdjdjddjdjddhddhdhd/job1"), "1");
            jobstatusBeschreibungen.put(jobstatusBeschreibungen1.getPrimaryKey(), jobstatusBeschreibungen1);
            JobStatusBeschreibung jobstatusBeschreibungen2 = new JobStatusBeschreibung("job2", JobStatus.UNSTABLE, new URL("http://localhost/job2"), "2");
            jobstatusBeschreibungen.put(jobstatusBeschreibungen2.getPrimaryKey(), jobstatusBeschreibungen2);
        } catch (MalformedURLException e) {
            LOGGER.error(e.toString());
        }

        Statusfenster window = new Statusfenster(jobstatusBeschreibungen);
        window.aktualisiereContentPane();
        window.setVisible(true);

        try {
            Thread.sleep(4000);
            JobStatusBeschreibung jobstatusBeschreibungen2a = new JobStatusBeschreibung("job2", JobStatus.OTHER, new URL("http://localhost/job2"), "2");
            jobstatusBeschreibungen.put(jobstatusBeschreibungen2a.getPrimaryKey(), jobstatusBeschreibungen2a);
        } catch (MalformedURLException | InterruptedException e) {
            LOGGER.error(e.toString());
        }
        window.aktualisiereContentPane();

    }
}


class SharedListSelectionHandler implements ListSelectionListener {
    public static final Logger LOGGER = LoggerFactory.getLogger(SharedListSelectionHandler.class);

    protected final Statusfenster statusArea;
    protected final List<StatusItem> statusItems;

    public SharedListSelectionHandler(List<StatusItem> statusItems, final Statusfenster statusArea) {
        this.statusItems = statusItems;
        this.statusArea = statusArea;
    }

    public void valueChanged(ListSelectionEvent event) {
        final ListSelectionModel selectionModel = (ListSelectionModel) event.getSource();
        boolean isAdjusting = event.getValueIsAdjusting();

        if (!selectionModel.isSelectionEmpty() && !isAdjusting) {
            // Find out which indexes are selected.
            int minIndex = selectionModel.getMinSelectionIndex();

            final URI navigationURI;
            try {
                navigationURI = this.statusItems.get(minIndex).getNavigationURL().toURI();
                Desktop.getDesktop().browse(navigationURI);
                statusArea.setVisible(false);
            } catch (URISyntaxException | IOException e) {
                LOGGER.warn(e.toString());
            }
        }
    }
}
