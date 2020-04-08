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

import com.github.funthomas424242.jenkinsmonitor.jenkins.AbstractJobBeschreibung;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatus;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatusBeschreibung;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

class StartXHolder {
    public int startX = 0;
}

public class ImageGenerator {

    public static final Logger LOGGER = LoggerFactory.getLogger(ImageGenerator.class);

    protected final Map<String, JobStatusBeschreibung> jobsStatusBeschreibungen;

    protected ImageGenerator(final Map<String, JobStatusBeschreibung> jobsStatusBeschreibungen) {
        this.jobsStatusBeschreibungen = jobsStatusBeschreibungen;
    }

    protected BufferedImage createImage(final int width, final int height) {
        return drawImage(new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED), width, height);
    }

    protected BufferedImage drawImage(final BufferedImage image, final int width, final int height) {
        // rename in fillAndDrawPartImage
        drawPartImage(image, 0, width, height, JobStatus.OTHER);

        if (jobsStatusBeschreibungen == null || jobsStatusBeschreibungen.size() < 1) {
            return image;
        }

        final int jobCount = jobsStatusBeschreibungen.size();
        final int partImageWidth = width / jobCount;
        final StartXHolder startXHolder = new StartXHolder();
        AbstractJobBeschreibung.sortedKeyStreamOf(this.jobsStatusBeschreibungen)
//            .keySet().stream().sorted()
            .forEach(primaryKey -> {
                drawPartImage(image, startXHolder.startX, partImageWidth, height, this.jobsStatusBeschreibungen.get(primaryKey).getJobStatus());
                startXHolder.startX += partImageWidth;
            });
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

    public void updateStatusArea(final Statusfenster statusArea) {
        if (this.jobsStatusBeschreibungen != null) {
            statusArea.aktualisiereContentPane(this.jobsStatusBeschreibungen);
        }
    }

}
