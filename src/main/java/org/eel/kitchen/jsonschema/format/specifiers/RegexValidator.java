/*
 * Copyright (c) 2012, Francis Galiegue <fgaliegue@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.eel.kitchen.jsonschema.format.specifiers;

import com.fasterxml.jackson.databind.JsonNode;
import org.eel.kitchen.jsonschema.main.ValidationContext;
import org.eel.kitchen.jsonschema.main.ValidationReport;
import org.eel.kitchen.util.RhinoHelper;

/**
 * Validator for the {@code regex} format specification
 *
 * <p>Again, here, we do <b>not</b> use {@link java.util.regex} because it
 * does not fit the bill.</p>
 *
 * @see RhinoHelper
 */
public final class RegexValidator
    extends FormatValidator
{
    @Override
    public ValidationReport validate(final ValidationContext context,
        final JsonNode instance)
    {
        final ValidationReport report = context.createReport();

        if (!RhinoHelper.regexIsValid(instance.textValue()))
            report.message("string is not a valid regular expression");

        return report;
    }
}