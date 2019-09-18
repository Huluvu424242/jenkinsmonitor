package com.github.funthomas424242.jenkinsmonitor;

import java.awt.*;
import java.net.URL;
import java.util.Enumeration;

public class JobStatus {

    public enum Status {
        SUCCESS,
        FAILED,
        INSTABIL
    };

    protected final Status status;

    protected final String jobName;

    protected final URL jobUrl;


    public JobStatus(final String jobName, final Status status, final URL jobUrl){
        this.status=status;
        this.jobName=jobName;
        this.jobUrl=jobUrl;
    }

    public Color getStatusColor() {

    }
}
