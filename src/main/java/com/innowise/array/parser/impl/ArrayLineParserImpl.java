package com.innowise.array.parser.impl;

import com.innowise.array.exception.InvalidArrayException;
import com.innowise.array.parser.ArrayLineParser;
import com.innowise.array.validation.ArrayLineValidator;
import com.innowise.array.validation.impl.ArrayLineValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ArrayLineParserImpl implements ArrayLineParser {
    private static final Logger logger = LogManager.getLogger();
    private static final String COMMA_DELIMITER_REGEX = "\\s*,\\s*";

    @Override
    public int[] parse(String line) {
        ArrayLineValidator validator = new ArrayLineValidatorImpl();
        boolean isLineValid = validator.isValid(line);
        if (!isLineValid) {
            logger.error("Cannot parse invalid line: '{}'", line);
            throw new InvalidArrayException("Invalid line format: " + line);
        }

        String trimmedLine = line.trim();
        String[] numberTokens = trimmedLine.split(COMMA_DELIMITER_REGEX);
        int[] numbers = new int[numberTokens.length];

        try {
            for (int i = 0; i < numberTokens.length; i++) {
                numbers[i] = Integer.parseInt(numberTokens[i]);
            }
        } catch (NumberFormatException exception) {
            logger.error("Number out of int range in line: '{}'", line, exception);
            throw new InvalidArrayException("Number out of int range in line: " + line, exception);
        }

        logger.info("Successfully parsed {} elements from line", numbers.length);
        return numbers;
    }
}
