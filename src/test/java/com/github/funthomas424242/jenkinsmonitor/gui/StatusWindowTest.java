package com.github.funthomas424242.jenkinsmonitor.gui;

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

import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatus;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatusBeschreibung;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

class StatusWindowTest {


    @Test
    void aktualisiereContentPane() throws MalformedURLException {
        final JobStatusBeschreibung[] jobstatusBeschreibungen = new JobStatusBeschreibung[3];
        jobstatusBeschreibungen[0] = new JobStatusBeschreibung("job0", JobStatus.FAILURE, new URL("http://localhost/job0"), "0");
        jobstatusBeschreibungen[1] = new JobStatusBeschreibung("job1", JobStatus.FAILURE, new URL("http://localhost/job1"), "1");
        jobstatusBeschreibungen[2] = new JobStatusBeschreibung("job2", JobStatus.FAILURE, new URL("http://localhost/job2"), "2");

        StatusWindow window = new StatusWindow();
        window.aktualisiereContentPane(jobstatusBeschreibungen);
        window.pack();
        window.setAlwaysOnTop(true);
        window.setLocationByPlatform(false);
        window.setVisible(true);
        window.dispose();
    }

}
