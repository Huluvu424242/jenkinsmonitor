package com.github.funthomas424242.jenkinsmonitor.etc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class NetworkHelper {

    protected static final Logger LOG = LoggerFactory.getLogger(NetworkHelper.class);

    public static URL urlOf(final String urlPath) {
        try {
            return new URL(urlPath);
        } catch (MalformedURLException e) {
            LOG.error(e.getLocalizedMessage(), e);
        }
        return null;
    }
}
