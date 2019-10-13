package com.github.funthomas424242.jenkinsmonitor.jenkins;

import java.net.URL;

final public class StatusAbfrageInformationen {
    public final URL statusAbfrageUrl;
    public String basicAuthToken;

    public StatusAbfrageInformationen(final URL statusAbfrageUrl, final String userName, String password){
        this.statusAbfrageUrl=statusAbfrageUrl;
    }

    public URL getStatusAbfrageUrl() {
        return null;
    }

    public String getUserName() {
        return null;
    }

    public String getPassword() {
        return null;
    }

    public String getBasicAuthToken(String password) {
        return null;
    }
}
