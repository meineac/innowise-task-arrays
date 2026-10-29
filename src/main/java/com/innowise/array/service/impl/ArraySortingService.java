package com.innowise.array.service.impl;

import com.innowise.array.entity.IntArrayEntity;
import com.innowise.array.service.SortingService;

public final class ArraySortingService implements SortingService {
    @Override
    public int[] sortByInsertion(IntArrayEntity array) {
        int[] result = array.toArray();
        for (int index = 1; index < result.length; index++) {
            int current = result[index];
            int position = index - 1;
            while (position >= 0 && result[position] > current) {
                result[position + 1] = result[position];
                position--;
            }
            result[position + 1] = current;
        }
        return result;
    }

    @Override
    public int[] sortByQuickSort(IntArrayEntity array) {
        int[] result = array.toArray();
        quickSort(result, 0, result.length - 1);
        return result;
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
