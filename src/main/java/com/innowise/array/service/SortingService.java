package com.innowise.array.service;

import com.innowise.array.entity.IntArrayEntity;

public interface SortingService {
    int[] sortByInsertion(IntArrayEntity array);

    int[] sortByQuickSort(IntArrayEntity array);
}
