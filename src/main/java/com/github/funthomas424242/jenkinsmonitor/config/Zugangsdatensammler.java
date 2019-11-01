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

import com.github.funthomas424242.jenkinsmonitor.jenkins.BasicAuthDaten;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Zugangsdatensammler {

    transient protected final Logger LOGGER = LoggerFactory.getLogger(Zugangsdatensammler.class);

    protected class Zugang {
        public String host;
        public String userName;
        public String password;
    }


    protected final HashMap<String, Zugang> zugaenge;

    public Zugangsdatensammler() {
        zugaenge = new HashMap<>();
    }

    public void addZugangsdatum(String propertyKey, String propertyWert) {
        final String sukeyAndId = propertyKey.substring(Configuration.KEY_JENKINSAUTH.length());
        final String[] values = sukeyAndId.split("-");
        if ("host".equals(values[0])) {
            addZugangsdatumJenkinsHost(values[1], propertyWert);
        }
        if ("username".equals(values[0])) {
            addZugangsdatumJenkinsUserName(values[1], propertyWert);
        }
        if ("password".equals(values[0])) {
            addZugangsdatumJenkinsPassword(values[1], propertyWert);
        }
    }

    public void addZugangsdatumJenkinsHost(String jenkinsId, String jesnkinsHost) {
        checkAllParameterUntilFirstNotNull(jenkinsId, jesnkinsHost);
        final Zugang zugang = getOrCreateZugang(jenkinsId);
        zugang.host = jesnkinsHost;
    }

    public void addZugangsdatumJenkinsUserName(String jenkinsId, String userName) {
        checkAllParameterUntilFirstNotNull(jenkinsId, userName);
        final Zugang zugang = getOrCreateZugang(jenkinsId);
        zugang.userName = userName;
    }


    public void addZugangsdatumJenkinsPassword(String jenkinsId, String password) {
        checkAllParameterUntilFirstNotNull(jenkinsId, password);
        final Zugang zugang = getOrCreateZugang(jenkinsId);
        zugang.password = password;
    }

    public Jenkinszugangskonfiguration[] getJenkinsZugangsdaten() {
        final List<Jenkinszugangskonfiguration> jobabfragedaten = new ArrayList<>();
        zugaenge
            .values()
            .stream()
            .forEach((zugang) -> {
                if( zugang.userName != null && zugang.password != null) {
                    try {
                        final Jenkinszugangskonfiguration jenkinszugangskonfiguration
                            = new Jenkinszugangskonfiguration(
                            new URL(zugang.host),
                            new BasicAuthDaten(zugang.userName, zugang.password));
                        jobabfragedaten.add(jenkinszugangskonfiguration);
                    } catch (MalformedURLException e) {
                        LOGGER.error("URL ist ungültig: {}", zugang.host);
                    }
                }else{
                    LOGGER.info("Für {} existieren keine Zugangsdaten", zugang.host);
                }
            });
        return jobabfragedaten.toArray(Jenkinszugangskonfiguration[]::new);
    }

    protected void checkAllParameterUntilFirstNotNull(Object... parameter) {
        Arrays.stream(parameter)
            .filter((part) -> {
                if (part == null) {
                    throw new IllegalArgumentException("jenkinsID und zugangstyp sind erforderlich");
                } else {
                    return false;
                }
            });
    }

    protected Zugang getOrCreateZugang(String jenkinsId) {
        final Zugang zugang;
        if (zugaenge.containsKey(jenkinsId)) {
            zugang = zugaenge.get(jenkinsId);
        } else {
            zugang = new Zugang();
            zugaenge.put(jenkinsId, zugang);
        }
        return zugang;
    }


}
