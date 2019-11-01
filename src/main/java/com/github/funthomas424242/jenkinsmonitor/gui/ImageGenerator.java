package com.github.funthomas424242.jenkinsmonitor.gui;

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

import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatus;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatusBeschreibung;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class ImageGenerator {

    public static final Logger LOGGER = LoggerFactory.getLogger(ImageGenerator.class);

    protected final JobStatusBeschreibung[] jobsStatusBeschreibungen;

    protected ImageGenerator(final JobStatusBeschreibung[] jobsStatusBeschreibungen) {
        this.jobsStatusBeschreibungen = jobsStatusBeschreibungen;
    }

    protected BufferedImage createImage(final int width, final int height) {
        return drawImage(new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED), width, height);
    }

    protected BufferedImage drawImage(final BufferedImage image, final int width, final int height) {
        drawPartImage(image, 0, width, height, JobStatus.OTHER);

        if (jobsStatusBeschreibungen == null || jobsStatusBeschreibungen.length < 1) {
            return image;
        }

        final int jobCount = jobsStatusBeschreibungen.length;
        final int partImageWidth = width / jobCount;
        int startX = 0;
        for (JobStatusBeschreibung jenkinsJobBeschreibung : jobsStatusBeschreibungen) {
            drawPartImage(image, startX, partImageWidth, height, jenkinsJobBeschreibung.getJobStatus());
            startX += partImageWidth;
        }
        return image;
    }

    protected BufferedImage drawPartImage(BufferedImage image,
                                          final int x,
                                          final int width,
                                          final int height,
                                          final JobStatus jobStatus) {

        final Graphics g = image.createGraphics();
        g.setColor(jobStatus.getColor());
        g.fillRect(x, 0, width, height);
        g.dispose();

        return image;
    }

    public void updateStatusArea(final JWindow statusArea) {

        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(this.jobsStatusBeschreibungen.length, 1));

        Arrays.stream(this.jobsStatusBeschreibungen).forEach((jobStatus) -> {
            final String htmlTemplate = "<html><h1>" + jobStatus.getJobName() + "</h1><p>Status: " + jobStatus.getJobStatus().toString() + " <a href=\"" + jobStatus.getJobUrl() + "\">" + jobStatus.getJobUrl() + "</a></p></html>";
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
                        statusArea.setVisible(false);
                    } catch (IOException | URISyntaxException ex) {
                        LOGGER.error("URL " + uri.toString() + " konnte nicht geöffnet werden", ex);
                    }
                }
            });
            panel.add(label);
        });
        statusArea.setContentPane(panel);
        statusArea.pack();
        statusArea.repaint();
    }
}
