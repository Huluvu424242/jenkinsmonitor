package com.github.funthomas424242.jenkinsmonitor;

/*-
 * #%L
 * jenkinsmonitor Example
 * %%
 * Copyright (C) 2018 - 2019 PIUG
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;


class JenkinsMonitorTest {

    JenkinsMonitor jenkinsMonitor;

    @BeforeEach
    void setUpTestfall() {
        jenkinsMonitor = new JenkinsMonitor();
    }


    @Test
    void nutztValidJenkinsPropertyfile() {
        final JenkinsMonitor jenkinsMonitor = new JenkinsMonitor();

        final File file = jenkinsMonitor.getConfigurationfile();

        final String propertyFilePath = file.getAbsolutePath().toString();
        final String expectedPath = System.getProperty("user.home")+File.separator+"jenkinsmonitor.properties";
        assertEquals(expectedPath, propertyFilePath);
    }




}
