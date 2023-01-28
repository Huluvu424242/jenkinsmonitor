package com.github.funthomas424242.jenkinsmonitor.logstash;

import java.util.Arrays;

public enum LogAppender {

    CONSOLE("CONSOLE"),

    FILE("FILE");

    public final String appenderName;

    LogAppender(final String appenderName) {
        this.appenderName = appenderName;
    }

    public static LogAppender fromValue(final String value) {
        return Arrays.stream(LogAppender.values())
                .filter(logAppender -> logAppender.appenderName.equals(value))
                .findAny()
                .orElse(null);
    }

    @Override
    public String toString() {
        return this.appenderName;
    }

    public static String getValueOrDefault(final LogAppender logAppender) {
        return logAppender != null ? logAppender.appenderName : CONSOLE.appenderName;
    }

}
