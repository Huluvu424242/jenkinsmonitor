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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class AbstractJobBeschreibungen<T> {

    final Map<String, T> jobBeschreibungen;

    public void remove(String key) {
        jobBeschreibungen.remove(key);
    }

    public AbstractJobBeschreibungen(final Map<String, T> jobBeschreibungen) {
        this.jobBeschreibungen = jobBeschreibungen;
    }

    public int size() {
        return jobBeschreibungen.size();
    }

    public boolean containsKey(String key) {
        return jobBeschreibungen.containsKey(key);
    }

    public T get(String key) {
        return jobBeschreibungen.get(key);
    }

    public T put(String key, T value) {
        return jobBeschreibungen.put(key, value);
    }

    public Set<String> keySet() {
        return jobBeschreibungen.keySet();
    }

    public Map<String, T> getCloneOfDataModel() {
        final Map<String, T> newMap = new HashMap<>();
        jobBeschreibungen.keySet().forEach(primaryKey -> newMap.put(primaryKey, jobBeschreibungen.get(primaryKey)));
        return newMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractJobBeschreibungen)) return false;
        AbstractJobBeschreibungen<?> that = (AbstractJobBeschreibungen<?>) o;
        return jobBeschreibungen.equals(that.jobBeschreibungen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobBeschreibungen);
    }
}
