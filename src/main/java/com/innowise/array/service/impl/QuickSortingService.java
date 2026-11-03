package com.innowise.array.service.impl;

import com.innowise.array.entity.IntArray;
import com.innowise.array.exception.InvalidArrayException;
import com.innowise.array.service.SortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuickSortingService implements SortingService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public IntArray sort(IntArray array) {
        if (array == null) {
            LOGGER.error("Cannot sort by quick sort: array is null");
            throw new InvalidArrayException("Array must not be null");
        }
        int[] result = array.getValues();
        quickSort(result, 0, result.length - 1);
        IntArray sortedArray = new IntArray(result);
        LOGGER.info("Sorted array by insertion sort: {}", sortedArray);
        return sortedArray;
    }

    private void quickSort(int[] values, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = values[low + (high - low) / 2];
        int left = low;
        int right = high;

        while (left <= right) {
            while (values[left] < pivot) {
                left++;
            }
            while (values[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temporary = values[left];
                values[left] = values[right];
                values[right] = temporary;
                left++;
                right--;
            }
        }

        if (low < right) {
            quickSort(values, low, right);
        }
        if (left < high) {
            quickSort(values, left, high);
        }
    }
}
