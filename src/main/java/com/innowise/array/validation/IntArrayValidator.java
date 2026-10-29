package com.innowise.array.validation;

import com.innowise.array.parser.impl.ArrayLineParserImpl;

public final class IntArrayValidator implements ArrayValidator {
    private final ArrayLineParserImpl parser;

    public IntArrayValidator(ArrayLineParserImpl parser) {
        this.parser = parser;
    }

    @Override
    public boolean isValid(String line) {
        if (line == null || line.isBlank()) {
            return false;
        }
        try {
            parser.parse(line);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
