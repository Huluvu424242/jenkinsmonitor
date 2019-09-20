package com.github.funthomas424242.jenkinsmonitor;

import java.awt.*;

public enum JobStatus {
    SUCCESS(Color.green),
    FAILED(Color.red),
    INSTABIL(Color.yellow),
    // lightGray statt gray wegen gray Anomalie!
    OTHER(Color.lightGray);

    protected Color color;

    JobStatus(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }
}
