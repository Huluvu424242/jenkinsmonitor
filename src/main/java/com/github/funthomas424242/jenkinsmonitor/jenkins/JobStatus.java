package com.github.funthomas424242.jenkinsmonitor.jenkins;

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

import javafx.scene.paint.Color;

public enum JobStatus {
    SUCCESS(java.awt.Color.green, Color.GREEN, "#7FFFD4"),
    FAILURE(java.awt.Color.red, Color.RED, "#FF7F50"),
    UNSTABLE(java.awt.Color.yellow, Color.YELLOW, "#FFFF00"),
    ABORTED(java.awt.Color.lightGray, Color.LIGHTGRAY, "#C0C0C0"),
    // lightGray statt gray wegen gray Anomalie!;
    OTHER(java.awt.Color.lightGray, Color.LIGHTGRAY, "#C0C0C0");

    protected final Color color;
    protected final java.awt.Color awtColor;
    protected final String colorValueHEX;

    JobStatus(java.awt.Color awtColor, Color color, String colorValueHEX) {
        this.awtColor = awtColor;
        this.color = color;
        this.colorValueHEX = colorValueHEX;
    }

    public Color getColor() {
        return this.color;
    }

    public java.awt.Color getAWTColor() {
        return this.awtColor;
    }

    public String getColorValueHEX() {
        return this.colorValueHEX;
    }
}
