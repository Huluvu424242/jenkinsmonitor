package com.github.funthomas424242.jenkinsmonitor.config;

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

import com.github.funthomas424242.jenkinsmonitor.jenkins.JobAbfragedaten;
import com.github.funthomas424242.jenkinsmonitor.jenkins.JobBeschreibungen;
import java.io.File;
import java.net.URL;

public interface ConfigurationFluentGrammar {

    static interface States extends Created, Loaded {
    }

    static interface Created {

        boolean isInitialisiert();

        Loaded reload();

        Loaded reloadFromFile(final File configFile);

        File getConfigurationfile();


    }

    static interface Loaded extends Created {

        Loaded resetLoggerConfiguration();

        long getPollPeriodInSecond();

        Jenkinszugangskonfiguration[] getAllJenkinszugangskonfigurationen();
        JobAbfragedaten getAbfragedatenOf(final URL jobUrl);
        JobBeschreibungen getJobBeschreibungen();

    }


}


