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
import com.github.funthomas424242.jenkinsmonitor.etc.NetworkHelper;
import com.github.funthomas424242.jenkinsmonitor.jenkins.AbstractJobBeschreibung;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatus;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatusBeschreibung;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatusBeschreibungen;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class StatusWindowJFX extends Application {

    public static final Logger LOGGER = LoggerFactory.getLogger(StatusWindowJFX.class);

    protected static JobStatusBeschreibungen jobStatusBeschreibungen;


    private StatusItem createStatusItem(final int maxLen, final int counter, JobStatusBeschreibung jobStatus) {
        final String colorValueHEX = jobStatus.getJobStatus().getColorValueHEX() != null ? jobStatus.getJobStatus().getColorValueHEX() : JobStatus.OTHER.getColorValueHEX();
        final String orderId = jobStatus.getJobOrderId() != null ? jobStatus.getJobOrderId() : "###";
        final String jobName = jobStatus.getJobName() != null ? jobStatus.getJobName() : "unbenannt";
        final String counterValue = counter + "";
        final String status = jobStatus.getJobStatus().toString() != null ? jobStatus.getJobStatus().toString() : "unbekannt";
        final String url = jobStatus.getJobUrl() != null ? jobStatus.getJobUrl().toString() : "<no url>";

        String rawLine = "[" + orderId + "] " + jobName;
        final int deltaLen = maxLen - jobName.length();
        LOGGER.info("RawLine len "+rawLine.length());
        LOGGER.info("Max len "+maxLen);
        LOGGER.info("Delta len "+deltaLen);

        for (int i = 0; i < deltaLen; i++) {
            rawLine += "=";
//            rawLine += "\u037A";
        }
        rawLine += ">";
        final String htmlTemplate = rawLine + "\n(" + counterValue + ") Status: " + status
            + " " + url + " ";
        final StatusItem statusItem = new StatusItem(htmlTemplate, jobStatus.getJobUrl());
        statusItem.setBackground(new Background(new BackgroundFill(jobStatus.getStatusColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        statusItem.autosize();
        return statusItem;
    }

    /**
     * Erzeugt den Fensterinhalt
     *
     * @param tmpJobsStatusBeschreibungen liegen bereits als Kopie vor
     * @return
     */
    private ScrollPane createContent(final JobStatusBeschreibungen tmpJobsStatusBeschreibungen) {

        // Datenmodell erzeugen
        final List<StatusItem> statusItems = new ArrayList<>();
        final Counter counter = new Counter();
        AbstractJobBeschreibung.sortedStreamOf(tmpJobsStatusBeschreibungen)
            .forEach((jobStatus) -> {
                statusItems.add(createStatusItem(tmpJobsStatusBeschreibungen.getDisplayCharLength(), counter.value + 1, jobStatus));
                counter.value++;
            });

//
//        // Datenmodell erzeugen
//        final DefaultListModel<StatusItem> listModel = new DefaultListModel<>();
//        listModel.addAll(statusItems);
//        // Liste erzeugen mit Datenmodell
//        final JList<StatusItem> list = new JList<>(listModel);
//        // Layoutvorgaben
//        list.setLayoutOrientation(JList.VERTICAL);
//
//        // Selektion Modus
//        list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
//        final ListSelectionModel listSelectionModel = list.getSelectionModel();
//        listSelectionModel.addListSelectionListener(
//            new SharedListSelectionHandler(statusItems, this));
//
//        // Scrollpane erzeugen
//        final JScrollPane scrollPane = new JScrollPane(list);
//        final GoldsteinPanel logoPanel = new GoldsteinPanel();
//        final JPanel contentPanel = new JPanel();
//        contentPanel.setLayout(new GridLayout(2, 1));
//        contentPanel.setOpaque(true);
//        contentPanel.add(scrollPane);
//        contentPanel.add(logoPanel);
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");

        StatusItem l0 = new StatusItem("<html><div style=\"background-color:red\">Hello, JavaFX</div> " + javafxVersion + ", running on Java " + javaVersion + ".</html>", NetworkHelper.urlOf("http://locahost:8080/"));
        l0.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        StatusItem l1 = new StatusItem("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".", NetworkHelper.urlOf("http://locahost:8080/"));
        StatusItem l2 = new StatusItem("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".", NetworkHelper.urlOf("http://locahost:8080/"));


        final ObservableList<StatusItem> names = FXCollections.observableArrayList(statusItems);
        final ListView<StatusItem> listView = new ListView<>(names);

        final ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listView);
        return scrollPane;
    }


    @Override
    public void start(Stage stage) {
        // Erzeuge Kopie vom Modell für JFX Instanz
        final JobStatusBeschreibungen tmpJobStatusBeschreibungen = new JobStatusBeschreibungen(jobStatusBeschreibungen.getCloneOfDataModel());
        tmpJobStatusBeschreibungen.computeDisplayCharLength();
        final ScrollPane pane = createContent(tmpJobStatusBeschreibungen);
        Scene scene = new Scene(pane, 100, 100);
        stage.setScene(scene);
        stage.show();
    }

    public static void openModal(final JobStatusBeschreibungen jobStatusBeschreibungen) {
        StatusWindowJFX.jobStatusBeschreibungen = jobStatusBeschreibungen;
        launch();
    }

}
