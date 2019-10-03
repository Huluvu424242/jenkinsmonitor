package com.github.funthomas424242.jenkinsmonitor.gui;

 public interface Timer {
    void register(Listener listener);
    void start();
    void stop();

    interface Listener {
        void timeElapsed();
    }
}
