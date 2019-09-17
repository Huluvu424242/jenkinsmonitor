package com.github.funthomas424242.example.bookshop;

/*-
 * #%L
 * rades-example Example
 * %%
 * Copyright (C) 2018 - 2019 PIUG
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

import java.time.LocalDate;
import java.time.Period;

public class Person {

    final String name;

    final String vorname;

    final LocalDate geburtstag;

    int alter;

    public Person(final String vorname, final String name, final int geburtsjahr, final int geburtsmonat, final int geburtstag) {
        this.vorname = vorname;
        this.name = name;
        this.geburtstag = LocalDate.of(geburtsjahr, geburtsmonat, geburtstag);
    }

    public void berechneAlter(){
        this.alter = Period.between(geburtstag, LocalDate.now()).getYears();
    }

}
