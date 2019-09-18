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

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageGenerator {

    protected final JobStatusBeschreibung[] jobsStatusBeschreibungen;

    public ImageGenerator(final JobStatusBeschreibung[] jobsStatusBeschreibungen) {
        this.jobsStatusBeschreibungen = jobsStatusBeschreibungen;
    }

    public BufferedImage getImage(final int width, final int height) {
        return createGrayImage(width, height);
    }

    protected BufferedImage createGrayImage(int width, int height) {
        final BufferedImage image = new BufferedImage(width, height,
            BufferedImage.TYPE_BYTE_INDEXED);
        final Graphics g = image.createGraphics();
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, width, height);
        g.dispose();

        return image;
    }
}
