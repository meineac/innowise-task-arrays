package com.innowise.array.service;

import com.innowise.array.entity.IntArray;
import java.util.OptionalDouble;

public interface AverageService {
    OptionalDouble calculateAverage(IntArray array);
}
