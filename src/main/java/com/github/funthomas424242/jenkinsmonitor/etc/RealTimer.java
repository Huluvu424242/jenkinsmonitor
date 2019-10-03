package com.github.funthomas424242.jenkinsmonitor.etc;

/*-
 * #%L
 * Jenkins Monitor
 * %%
 * Copyright (C) 2019 PIUG
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


import com.github.funthomas424242.jenkinsmonitor.gui.Timer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RealTimer implements Timer {
    private final long period;
    private final TimeUnit periodTimeUnit;
    private final List<Listener> listeners = Collections.synchronizedList(new ArrayList<>());
    private final ScheduledExecutorService timerService = Executors.newSingleThreadScheduledExecutor();

    public RealTimer(long period, TimeUnit periodTimeUnit) {
        this.period = period;
        this.periodTimeUnit = periodTimeUnit;
    }

    @Override
    public void register(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void start() {
        timerService.scheduleAtFixedRate(this::reportTimeElapse, period, period, periodTimeUnit);
    }

    private void reportTimeElapse() {
        listeners.forEach(Listener::timeElapsed);
    }

    @Override
    public void stop() {
        timerService.shutdown();
    }
}
