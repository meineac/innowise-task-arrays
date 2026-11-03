package com.innowise.array.service.impl;

import com.innowise.array.entity.IntArray;
import com.innowise.array.service.SumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.OptionalLong;

public final class SumServiceImpl implements SumService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public OptionalLong calculateSum(IntArray array) {
        if (array == null || array.size() == 0) {
            LOGGER.warn("Cannot calculate sum: array is null or empty");
            return OptionalLong.empty();
        }

        long sum = 0;
        for (int index = 0; index < array.size(); index++) {
            sum += array.getValue(index);
        }

        LOGGER.info("Calculated sum: {}", sum);
        return OptionalLong.of(sum);
    }
}
