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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class Period {
    public long duration;
    public TimeUnit durationTimeUnit;
}

public class RealTimer implements Timer {

    public static final Logger LOGGER = LoggerFactory.getLogger(RealTimer.class);

    protected final Period period;
    protected final List<Listener> listeners = Collections.synchronizedList(new ArrayList<>());
    protected final ScheduledExecutorService timerService = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> cancelableFuture;

    public RealTimer(long period, TimeUnit periodTimeUnit) {
        this.period = new Period();
        this.period.duration = period;
        this.period.durationTimeUnit = periodTimeUnit;
    }

    @Override
    public void register(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void start() {
        this.cancelableFuture = timerService.scheduleAtFixedRate(this::reportTimeElapse, period.duration, period.duration, period.durationTimeUnit);
    }

    private void reportTimeElapse() {
        listeners.forEach(Listener::timeElapsed);
    }

    public void cancel() {
        cancelableFuture.cancel(false);
    }

    @Override
    public void stop() {
        timerService.shutdown();
    }

    @Override
    public void resetPeriod(long period, TimeUnit periodTimeUnit) {
        LOGGER.debug("Stoppe Timer");
        cancel();
        LOGGER.debug("Timer gestoppt");
        this.period.duration = period;
        this.period.durationTimeUnit = periodTimeUnit;
        LOGGER.debug("Starte Timer");
        start();
        LOGGER.debug("Timer gestartet");
    }

    @Override
    public long getPeriod() {
        return period.duration;
    }


}
