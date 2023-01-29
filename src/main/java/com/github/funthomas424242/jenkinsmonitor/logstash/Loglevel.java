package com.github.funthomas424242.jenkinsmonitor.logstash;

/*-
 * #%L
 * Jenkins Monitor
 * %%
 * Copyright (C) 2019 - 2023 PIUG
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

import java.util.Arrays;

public enum Loglevel {
    DEBUG("debug"),
    INFO("info"),
    ERROR("error"),
    LOG("log");

    public final String levelName;

    Loglevel(final String levelName) {
        this.levelName = levelName;
    }


    public static Loglevel fromValue(final String value) {
        return Arrays.stream(Loglevel.values())
                .filter(level -> level.levelName.equals(value))
                .findAny()
                .orElse(null);
    }

    @Override
    public String toString() {
        return levelName;
    }

    public static String getValueOrDefault(final Loglevel logLevel) {
        return logLevel != null ? logLevel.levelName : INFO.levelName;
    }


}
