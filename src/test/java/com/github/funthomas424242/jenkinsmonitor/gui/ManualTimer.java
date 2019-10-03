package com.github.funthomas424242.jenkinsmonitor.gui;

import java.util.ArrayList;
import java.util.List;

class ManualTimer implements Timer {
    private final List<Listener> listeners = new ArrayList<>();

    @Override
    public void register(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void start() {
        // Ignored
    }

    @Override
    public void stop() {
        // Ignored
    }

    public void elapseTime(){
        listeners.forEach(Listener::timeElapsed);
    }
}
