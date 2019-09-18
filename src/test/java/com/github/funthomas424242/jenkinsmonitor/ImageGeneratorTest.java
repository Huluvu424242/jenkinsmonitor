package com.github.funthomas424242.jenkinsmonitor;

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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ImageGeneratorTest {


    @Test
    @DisplayName("Initialisierung mit null generiert ein graues Icon 100x100")
    void initNullCreateGrayIcon100x100() {
        final ImageGenerator generator = new ImageGenerator(null);
        final BufferedImage image = generator.getImage(100, 100);
        assertEquals(100, image.getHeight());
        assertEquals(100, image.getWidth());
        assertEquals(JobStatusBeschreibung.JobStatus.OTHER.getColor().getRGB(), image.getRGB(10, 10));
        assertEquals(Color.lightGray.getRGB(), image.getRGB(10, 10));
    }

    @Test
    @DisplayName("Initialisierung ohne Jobs generiert ein graues Icon 100x100")
    void initEmptyCreateGrayIcon100x100() {
        final JobStatusBeschreibung[] jobsStatus = new JobStatusBeschreibung[0];
        final ImageGenerator generator = new ImageGenerator(jobsStatus);
        final BufferedImage image = generator.getImage(100, 100);
        assertEquals(100, image.getHeight());
        assertEquals(100, image.getWidth());
        assertEquals(JobStatusBeschreibung.JobStatus.OTHER.getColor().getRGB(), image.getRGB(10, 10));
        assertEquals(Color.lightGray.getRGB(), image.getRGB(10, 10));
    }

    @Test
    @DisplayName("Initialisierung mit einem erfolgreichen Job generiert ein grünes Icon 100x100")
    void initOneJobGreenIcon100x100() {
        final JobStatusBeschreibung[] jobsStatusBeschreibungen = new JobStatusBeschreibung[1];
        jobsStatusBeschreibungen[0] = new JobStatusBeschreibung("Job1/master"
            , JobStatusBeschreibung.JobStatus.SUCCESS
            , null);
        final ImageGenerator generator = new ImageGenerator(jobsStatusBeschreibungen);
        final BufferedImage image = generator.getImage(100, 100);
        assertEquals(100, image.getHeight());
        assertEquals(100, image.getWidth());
        assertEquals(JobStatusBeschreibung.JobStatus.SUCCESS.getColor().getRGB(), image.getRGB(10, 10));
        assertEquals(Color.green.getRGB(), image.getRGB(10, 10));
    }

    @Test
    @DisplayName("Initialisierung mit einem instabilen Job generiert ein gelbes Icon 100x100")
    void initOneJobYellowIcon100x100() {
        final JobStatusBeschreibung[] jobsStatusBeschreibungen = new JobStatusBeschreibung[1];
        jobsStatusBeschreibungen[0] = new JobStatusBeschreibung("Job1/master"
            , JobStatusBeschreibung.JobStatus.INSTABIL
            , null);
        final ImageGenerator generator = new ImageGenerator(jobsStatusBeschreibungen);
        final BufferedImage image = generator.getImage(100, 100);
        assertEquals(100, image.getHeight());
        assertEquals(100, image.getWidth());
        assertEquals(JobStatusBeschreibung.JobStatus.INSTABIL.getColor().getRGB(), image.getRGB(10, 10));
        assertEquals(Color.yellow.getRGB(), image.getRGB(10, 10));
    }

    @Test
    @DisplayName("Initialisierung mit einem fehlerhaften Job generiert ein rotes Icon 100x100")
    void initOneJobRedIcon100x100() {
        final JobStatusBeschreibung[] jobsStatusBeschreibungen = new JobStatusBeschreibung[1];
        jobsStatusBeschreibungen[0] = new JobStatusBeschreibung("Job1/master"
            , JobStatusBeschreibung.JobStatus.FAILED
            , null);
        final ImageGenerator generator = new ImageGenerator(jobsStatusBeschreibungen);
        final BufferedImage image = generator.getImage(100, 100);
        assertEquals(100, image.getHeight());
        assertEquals(100, image.getWidth());
        assertEquals(JobStatusBeschreibung.JobStatus.FAILED.getColor().getRGB(), image.getRGB(10, 10));
        assertEquals(Color.red.getRGB(), image.getRGB(10, 10));
    }

}
