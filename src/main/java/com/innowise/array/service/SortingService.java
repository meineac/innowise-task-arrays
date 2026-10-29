package com.innowise.array.service;

import com.innowise.array.entity.IntArray;

public interface SortingService {
    int[] sortByInsertion(IntArray array);

    int[] sortByQuickSort(IntArray array);
}
