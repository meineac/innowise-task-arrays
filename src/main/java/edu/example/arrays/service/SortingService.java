package edu.example.arrays.service;

import edu.example.arrays.entity.IntArrayEntity;

public interface SortingService {
    int[] sortByInsertion(IntArrayEntity array);

    int[] sortByQuickSort(IntArrayEntity array);
}
