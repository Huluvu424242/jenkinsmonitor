package com.github.funthomas424242.jenkinsmonitor.etc;

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

import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkHelper {

    protected static final Logger LOG = LoggerFactory.getLogger(NetworkHelper.class);

    private NetworkHelper() {
    }

    public static URL urlOf(final String urlPath) {
        try {
            return new URL(urlPath);
        } catch (MalformedURLException e) {
            LOG.error(e.getLocalizedMessage(), e);
        }
        return null;
    }
}
