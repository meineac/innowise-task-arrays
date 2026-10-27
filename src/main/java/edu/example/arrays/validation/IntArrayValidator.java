package edu.example.arrays.validation;

import edu.example.arrays.parser.IntArrayParser;

public final class IntArrayValidator implements ArrayValidator {
    private final IntArrayParser parser;

    public IntArrayValidator(IntArrayParser parser) {
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
