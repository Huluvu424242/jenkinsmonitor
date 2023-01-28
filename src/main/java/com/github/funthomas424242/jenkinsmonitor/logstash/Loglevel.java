package com.github.funthomas424242.jenkinsmonitor.logstash;

import java.util.Arrays;

public enum Loglevel {
    DEBUG("debug"),
    INFO("info"),
    ERROR("error"),
    LOG("log");

    public final String levelName;

    Loglevel(final String levelName) {
        this.levelName = levelName;
    }


    public static Loglevel fromValue(final String value) {
        return Arrays.stream(Loglevel.values())
                .filter(level -> level.levelName.equals(value))
                .findAny()
                .orElse(null);
    }

    @Override
    public String toString() {
        return levelName;
    }

    public static String getValueOrDefault(final Loglevel logLevel) {
        return logLevel != null ? logLevel.levelName : INFO.levelName;
    }


}
