package com.innowise.array.service.impl;

import com.innowise.array.entity.IntArray;
import com.innowise.array.service.ExtremumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.OptionalInt;

public final class ExtremumServiceImpl implements ExtremumService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public OptionalInt findMin(IntArray array) {
        if (array == null || array.size() == 0) {
            LOGGER.warn("Cannot find minimum: array is null or empty");
            return OptionalInt.empty();
        }

        int min = array.getValue(0);
        for (int index = 1; index < array.size(); index++) {
            int currentValue = array.getValue(index);
            if (currentValue < min) {
                min = currentValue;
            }
        }

        LOGGER.info("Found minimum value: {}", min);
        return OptionalInt.of(min);
    }

    @Override
    public OptionalInt findMax(IntArray array) {
        if (array == null || array.size() == 0) {
            LOGGER.warn("Cannot find maximum: array is null or empty");
            return OptionalInt.empty();
        }

        int max = array.getValue(0);
        for (int index = 1; index < array.size(); index++) {
            int currentValue = array.getValue(index);
            if (currentValue > max) {
                max = currentValue;
            }
        }

        LOGGER.info("Found maximum value: {}", max);
        return OptionalInt.of(max);
    }
}
