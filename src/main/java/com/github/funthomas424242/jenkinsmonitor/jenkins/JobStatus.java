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

import java.awt.*;

public enum JobStatus {
    SUCCESS(Color.green, "#7FFFD4"),
    FAILURE(Color.red, "#FF7F50"),
    UNSTABLE(Color.yellow, "#FFFF00"),
    ABORTED(Color.lightGray, "#C0C0C0"),
    // lightGray statt gray wegen gray Anomalie!;
    OTHER(Color.lightGray, "#C0C0C0");

    protected final Color color;
    protected final String colorValueHEX;

    JobStatus(Color color, String colorValueHEX) {
        this.color = color;
        this.colorValueHEX = colorValueHEX;
    }

    public Color getColor() {
        return this.color;
    }

    public String getColorValueHEX() {
        return this.colorValueHEX;
    }
}
