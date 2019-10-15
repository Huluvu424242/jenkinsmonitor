package com.github.funthomas424242.jenkinsmonitor.config;

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
        final String sukeyAndId = propertyKey.substring(Configuration.KEY_JENKINSAUTH.length() - 1);
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
        zugang.userName = password;
    }

    public Jenkinszugangskonfiguration[] getJenkinsZugangsdaten() {
        final List<Jenkinszugangskonfiguration> jobabfragedaten = new ArrayList<>();
        zugaenge
            .values()
            .stream()
            .forEach((zugang) -> {
                try {
                    final Jenkinszugangskonfiguration jenkinszugangskonfiguration
                        = new Jenkinszugangskonfiguration(
                        new URL(zugang.host),
                        new BasicAuthDaten(zugang.userName, zugang.password));
                    jobabfragedaten.add(jenkinszugangskonfiguration);
                } catch (MalformedURLException e) {
                    LOGGER.error("URL ist ungültig: {}", zugang.host);
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
