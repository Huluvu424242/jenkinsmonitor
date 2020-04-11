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
import java.util.Set;

public class JobStatusBeschreibungen extends AbstractJobBeschreibungen<JobStatusBeschreibung>{


    public JobStatusBeschreibungen() {
       super(new HashMap<>());
    }

//    public int size() {
//        return jobStatusBeschreibungen.size();
//    }
//
//    public boolean containsKey(String key) {
//        return jobStatusBeschreibungen.containsKey(key);
//    }
//
//    public JobStatusBeschreibung get(JobStatusBeschreibung key) {
//        return jobStatusBeschreibungen.get(key);
//    }
//
//    public JobStatusBeschreibung put(String key, JobStatusBeschreibung value) {
//        return jobStatusBeschreibungen.put(key, value);
//    }
//
//    public Set<String> keySet() {
//        return jobStatusBeschreibungen.keySet();
//    }
}
