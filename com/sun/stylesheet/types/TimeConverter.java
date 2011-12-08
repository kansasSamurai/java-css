/*
 * Copyright 2008 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */
 
package com.sun.stylesheet.types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.stylesheet.StylesheetException;

/**
 * Converts strings representing times (e.g. "500ms" or "0.5s") to 
 * {@link Time Times}.
 */
public class TimeConverter implements TypeConverter<Time> {
    Pattern pattern = Pattern.compile("(\\d*(?:\\.\\d*)?)(ms|s|m)");
    
    public Time convertFromString(String string) {
        Matcher m = pattern.matcher(string);
        if (m.matches()) {
            float value = Float.parseFloat(m.group(1));
            String unit = m.group(2);
            return new Time(value, Time.Unit.valueOf(unit.toUpperCase()));
        }
        else
            throw new StylesheetException("Could not convert string '" + string + 
                    "' into Time");
    }
}