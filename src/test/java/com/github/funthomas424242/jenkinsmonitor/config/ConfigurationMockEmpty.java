package com.github.funthomas424242.jenkinsmonitor.config;

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

import com.github.funthomas424242.jenkinsmonitor.config.ConfigurationFluentGrammar.Created;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigurationMockEmpty {
    public static final String PATH_EMPTY_CONFIGURATION_FILE = "src/test/resources/empty_configuration.properties";


    private ConfigurationMockEmpty() {
    }

    public static Created getOrCreateInstance() {
        return Configuration.getOrCreateInstance(getConfigFile());
    }

    protected static File getConfigFile() {
        final Path emptyConfigfilePath = Paths.get(".", PATH_EMPTY_CONFIGURATION_FILE);
        return emptyConfigfilePath.toAbsolutePath().toFile();
    }
}
