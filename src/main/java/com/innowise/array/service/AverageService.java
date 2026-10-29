package com.innowise.array.service;

import com.innowise.array.entity.IntArrayEntity;
import java.util.OptionalDouble;

public interface AverageService {
    OptionalDouble calculateAverage(IntArrayEntity array);
}
