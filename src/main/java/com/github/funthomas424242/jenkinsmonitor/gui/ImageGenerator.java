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

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageGenerator {

    protected final JobStatusBeschreibung[] jobsStatusBeschreibungen;

    protected ImageGenerator(final JobStatusBeschreibung[] jobsStatusBeschreibungen) {
        this.jobsStatusBeschreibungen = jobsStatusBeschreibungen;
    }

    protected BufferedImage getImage(final int width, final int height) {
        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED);
        createPartImage(image, 0, width, height, JobStatus.OTHER);

        if (jobsStatusBeschreibungen == null || jobsStatusBeschreibungen.length < 1) {
            return image;
        }

        final int jobCount = jobsStatusBeschreibungen.length;
        final int partImageWidth = width / jobCount;
        int startX = 0;
        for (JobStatusBeschreibung jenkinsJobBeschreibung : jobsStatusBeschreibungen) {
            createPartImage(image, startX, partImageWidth, height, jenkinsJobBeschreibung.getJobStatus());
            startX += partImageWidth;
        }
        return image;
    }

    protected BufferedImage createPartImage(BufferedImage image,
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

    public String getTooltip() {
        return "\u001b[31mNo \u001b[32mjobs \u001b[33mwatching \u001b[0m";
    }

    public JDialog getStatusArea(final Point point) {

        // Erzeugung eines neuen Dialoges
        JDialog meinJDialog = new JDialog();
        meinJDialog.setTitle("JPanel Beispiel");
//        meinJDialog.setSize(450, 300);
        meinJDialog.setSize(450, 300);
        meinJDialog.setUndecorated(true);
        meinJDialog.setLocation(point);

        JPanel panel = new JPanel();
        // Hier setzen wir die Hintergrundfarbe unseres JPanels auf rot
        panel.setBackground(Color.red);
        // Hier fügen wir unserem Dialog unser JPanel hinzu
        meinJDialog.add(panel);
        // Wir lassen unseren Dialog anzeigen
        meinJDialog.setVisible(true);
        return meinJDialog;
    }
}
