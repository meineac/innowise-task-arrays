package com.innowise.array.service.impl;

import com.innowise.array.entity.IntArray;
import com.innowise.array.service.AverageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.OptionalDouble;

public final class AverageServiceImpl implements AverageService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public OptionalDouble calculateAverage(IntArray array) {
        if (array == null || array.size() == 0) {
            LOGGER.warn("Cannot calculate average: array is null or empty");
            return OptionalDouble.empty();
        }

        double sum = 0.0;
        for (int index = 0; index < array.size(); index++) {
            sum += array.getValue(index);
        }

        double average = sum / array.size();
        LOGGER.info("Calculated average: {}", average);
        return OptionalDouble.of(average);
    }
}
