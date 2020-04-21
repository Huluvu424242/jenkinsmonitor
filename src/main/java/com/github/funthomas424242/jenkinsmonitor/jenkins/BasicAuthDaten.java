package com.github.funthomas424242.jenkinsmonitor.jenkins;

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

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public class BasicAuthDaten {

    protected String userName;

    protected String password;

    public BasicAuthDaten(final String userName, final String password) {
        if (userName == null || password == null) {
            throw new IllegalArgumentException("Nutzername und Passwort dürfen nicht leer sein");
        }
        this.userName = userName;
        this.password = password;
    }

    public String getBasicAuthToken(String password) {
        String encodedToken = null;
        if (password != null && password.equals(this.password)) {
            encodedToken = Base64.getEncoder().encodeToString(String.format("%s:%s", this.userName, password).getBytes(StandardCharsets.UTF_8));
        }
        return encodedToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicAuthDaten that = (BasicAuthDaten) o;
        return userName.equals(that.userName) &&
            password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}
