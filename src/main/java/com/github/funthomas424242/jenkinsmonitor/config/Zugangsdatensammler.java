package com.github.funthomas424242.jenkinsmonitor.config;

import com.github.funthomas424242.jenkinsmonitor.jenkins.JenkinsZugangsdaten;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;


public class Zugangsdatensammler {

    protected class Zugang {
        public URL jenkinsJobURL;
        public String userName;
        public String password;
    }


    protected final HashMap<String, Zugang> zugaenge;

    public Zugangsdatensammler() {
        zugaenge = new HashMap<>();
    }

    public void addZugangsdatumJenkinsJobUrl(String jenkinsId, URL jenkinsJobUrl) {
        checkAllParameterUntilFirstNotNull(jenkinsId, jenkinsJobUrl);
        final Zugang zugang = getOrCreateZugang(jenkinsId);
        zugang.jenkinsJobURL = jenkinsJobUrl;
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

    public JenkinsZugangsdaten[] getJenkinsZugangsdaten() {
        return zugaenge
            .values()
            .stream()
            .map((zugang) -> {
                return new JenkinsZugangsdaten(zugang.jenkinsJobURL, zugang.userName, zugang.password);
            })
            .toArray(JenkinsZugangsdaten[]::new);
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
