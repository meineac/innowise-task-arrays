package com.innowise.array.validation.impl;

import com.innowise.array.validation.ArrayLineValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ArrayLineValidatorImpl implements ArrayLineValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final String VALID_COMMA_ARRAY_REGEX = "^\\s*[+-]?\\d+(\\s*,\\s*[+-]?\\d+)*\\s*$";

    @Override
    public boolean isValid(String line) {
        if (line == null) {
            logger.warn("Validation failed: input line is null");
            return false;
        }
        boolean isMatch = line.matches(VALID_COMMA_ARRAY_REGEX);
        logger.debug("Line '{}' validation result: {}", line, isMatch);
        return isMatch;
    }
}
