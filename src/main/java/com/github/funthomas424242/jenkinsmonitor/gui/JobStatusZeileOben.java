package com.github.funthomas424242.jenkinsmonitor.gui;

/*-
 * #%L
 * Jenkins Monitor
 * %%
 * Copyright (C) 2019 - 2020 PIUG
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

public class JobStatusZeileOben {

    // eigentlich unique aber durch Sonderzeichenverarbeitung evtl. merhdeutig
    protected final String jobName;

    // nicht eindeutig, da in der Config leer sein kann
    protected final String jobOrderId;

    // maximale Länge der Anzeige aller Zeilen
    protected final int maxLen;


    public JobStatusZeileOben(final String jobName, final String jobOrderId, final int maxLen) {
        this.jobName = jobName;
        this.jobOrderId = jobOrderId;
        this.maxLen = maxLen;
    }

    public String toHTMLString() {
        final String tmpOrderId = this.jobOrderId != null ? this.jobOrderId : "###";
        final String tmpJobName = this.jobName != null ? this.jobName : "unbenannt";
        final String obereZeile = tmpOrderId + tmpJobName;
        final int deltaOben = maxLen - obereZeile.length();
        return "<div style=\"font-size:22\">[" + tmpOrderId + "] " + tmpJobName + "&nbsp;".repeat(Math.max(0, deltaOben)) + "</div>";
    }

}
