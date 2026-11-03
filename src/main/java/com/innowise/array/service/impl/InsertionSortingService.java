package com.innowise.array.service.impl;

import com.innowise.array.entity.IntArray;
import com.innowise.array.exception.InvalidArrayException;
import com.innowise.array.service.SortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class InsertionSortingService implements SortingService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public IntArray sort(IntArray array) {
        if (array == null) {
            LOGGER.error("Cannot sort by insertion: array is null");
            throw new InvalidArrayException("Array must not be null");
        }

        int[] result = array.getValues();
        for (int index = 1; index < result.length; index++) {
            int current = result[index];
            int position = index - 1;
            while (position >= 0 && result[position] > current) {
                result[position + 1] = result[position];
                position--;
            }
            result[position + 1] = current;
        }
        IntArray sortedArray = new IntArray(result);
        LOGGER.info("Sorted array by insertion sort: {}", sortedArray);
        return sortedArray;
    }
}
