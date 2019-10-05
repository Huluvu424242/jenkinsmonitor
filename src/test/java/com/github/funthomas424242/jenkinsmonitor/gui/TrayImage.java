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

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

class ValueHolder {
    public int jobNr = 0;
    public boolean isOfColor = true;
}

public interface TrayImage {

    static boolean isImageOfColor(BufferedImage image, Color... colors) {
        final ValueHolder valueHolder = new ValueHolder();

        final int width = image.getWidth();
        final int jobAnzahl = colors.length;
        final int jobWith = width / jobAnzahl;

        Arrays.stream(colors).forEachOrdered((color) -> {
                valueHolder.isOfColor = isOfColor4JobRectangle(image, valueHolder.isOfColor, valueHolder.jobNr, jobWith, color);
                valueHolder.jobNr++;
            }
        );
        return valueHolder.isOfColor;
    }

    static boolean isOfColor4JobRectangle(BufferedImage image, boolean isOfColor, int jobNr, int jobWidth, Color color) {
        final int maxHeight = image.getHeight();
        boolean isSameColor = isOfColor;
        for (int offsetWidth = 0; offsetWidth < jobWidth; offsetWidth++) {
            for (int height = 0; height < maxHeight; height++) {
                isSameColor = isSameColor && (image.getRGB((jobWidth * jobNr) + offsetWidth, height) == color.getRGB());
            }
        }
        return isSameColor;
    }
}
