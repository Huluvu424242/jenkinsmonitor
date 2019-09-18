package com.github.funthomas424242.jenkinsmonitor;

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
        if(status == null ) return Color.gray;
        switch (status){
            case FAILED: return Color.red;
            case SUCCESS: return Color.green;
            case INSTABIL: return Color.yellow;
            default: return Color.gray;
        }
    }

    public Status getStatus() {
        return status;
    }

    public URL getJobUrl(){
        return this.jobUrl;
    }
}
