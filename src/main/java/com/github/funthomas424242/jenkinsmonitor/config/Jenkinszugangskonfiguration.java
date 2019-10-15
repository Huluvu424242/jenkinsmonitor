package com.github.funthomas424242.jenkinsmonitor.config;

import com.github.funthomas424242.jenkinsmonitor.jenkins.BasicAuthDaten;

import java.net.URL;

public class Jenkinszugangskonfiguration {

    protected URL jenkinsURL;

    protected BasicAuthDaten authDaten;

    public Jenkinszugangskonfiguration( final URL jenkinsURL, final BasicAuthDaten basicAuthDaten){
        this.jenkinsURL=jenkinsURL;
        this.authDaten=basicAuthDaten;
    }

    public URL getJenkinsUrl() {
        return jenkinsURL;
    }

    public BasicAuthDaten getAuthDaten() {
        return authDaten;
    }
}
