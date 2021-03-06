/*
 * Copyright (c) 2013, Francis Galiegue <fgaliegue@gmail.com>
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

package com.github.fge.jsonschema.keyword.validator.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.exceptions.ProcessingException;
import com.github.fge.jsonschema.keyword.validator.AbstractKeywordValidator;
import com.github.fge.jsonschema.processing.Processor;
import com.github.fge.jsonschema.processors.data.FullData;
import com.github.fge.jsonschema.report.ProcessingReport;

import static com.github.fge.jsonschema.messages.KeywordValidationMessages.*;

/**
 * Keyword validator for {@code additionalItems}
 */
public final class AdditionalItemsValidator
    extends AbstractKeywordValidator
{
    private final boolean additionalOK;
    private final int itemsSize;

    public AdditionalItemsValidator(final JsonNode digest)
    {
        super("additionalItems");
        additionalOK = digest.get(keyword).booleanValue();
        itemsSize = digest.get("itemsSize").intValue();
    }

    @Override
    public void validate(final Processor<FullData, FullData> processor,
        final ProcessingReport report, final FullData data)
        throws ProcessingException
    {
        if (additionalOK)
            return;

        final int size = data.getInstance().getNode().size();
        if (size > itemsSize)
            report.error(newMsg(data).message(ADDITIONAL_ITEMS_NOT_ALLOWED)
                .put("allowed", itemsSize).put("found", size));
    }

    @Override
    public String toString()
    {
        return keyword + ": "
            + (additionalOK ? "allowed" : itemsSize + " max");
    }
}
