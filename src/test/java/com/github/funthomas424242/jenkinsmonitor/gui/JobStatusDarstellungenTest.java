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

import com.github.funthomas424242.jenkinsmonitor.etc.NetworkHelper;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JenkinsClient;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatus;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobStatusBeschreibung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobStatusDarstellungenTest {

    protected TestDrivenTimer clock;


    @BeforeEach
    protected void beforeTest() {
        this.clock = new TestDrivenTimer();
    }

    @Test
    @Tag("headfull")
    @DisplayName("Zeige Buildstatusfläche für einen grünen Job an einer bestimmten Position")
    public void showBuildStatusAreaOneSuccessJob() {
        final JobStatusBeschreibungen jobsStatusBeschreibungen = new JobStatusBeschreibungen();
        final JobStatusBeschreibung jobsStatusBeschreibung0 = new JobStatusBeschreibung("Job1/master"
            , JobStatus.SUCCESS
            , NetworkHelper.urlOf("http://localhost/"), "#1");
        jobsStatusBeschreibungen.put(jobsStatusBeschreibung0.getPrimaryKey(), jobsStatusBeschreibung0);

        final JobStatusDarstellungen jobStatusDarstellungen
            =new JobStatusDarstellungen(jobsStatusBeschreibungen,clock);
        final Statusfenster statusArea = new Statusfenster(jobsStatusBeschreibungen);

        jobStatusDarstellungen.aktualisiereStatusfenster();

        assertNotNull(statusArea);
        // TODO einzelne enthaltene Inhalte abprüfen -> weiteres Testframework
    }

}
