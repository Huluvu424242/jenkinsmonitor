package com.github.funthomas424242.jenkinsmonitor.jenkins;

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

import net.greypanther.natsort.SimpleNaturalComparator;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Comparator;
import java.util.stream.Stream;

public interface AbstractJobBeschreibung extends Comparable<AbstractJobBeschreibung> {

    Comparator<String> NATURAL_COMPARATOR = SimpleNaturalComparator.getInstance();

    URL getJobUrl();

    String getJobOrderId();

    default String getPrimaryKey() {
        final String url = getJobUrl() != null ? getJobUrl().toExternalForm() : "";
        return getJobOrderId() + "#" + url;
    }

    default int compareTo(@NotNull AbstractJobBeschreibung jobBeschreibung) {
        if (getJobOrderId() == null && jobBeschreibung.getJobOrderId() == null) return 0;
        if (getJobOrderId() != null && jobBeschreibung.getJobOrderId() == null) return 1;
        if (getJobOrderId() == null && jobBeschreibung.getJobOrderId() != null) return -1;

        return NATURAL_COMPARATOR.compare(getJobOrderId(), jobBeschreibung.getJobOrderId());
    }

    static <T> Stream<String> sortedKeyStreamOf(final AbstractJobBeschreibungen<T> jobBeschreibung) {
        return jobBeschreibung.keySet().stream().sorted(NATURAL_COMPARATOR);
    }

    static <T> Stream<T> sortedStreamOf(final AbstractJobBeschreibungen<T> jobBeschreibung) {
        return jobBeschreibung.keySet().stream().sorted(NATURAL_COMPARATOR).map(jobBeschreibung::get);
    }

}
