package edu.example.arrays.service.impl;

import edu.example.arrays.entity.IntArrayEntity;
import edu.example.arrays.service.AverageService;
import java.util.OptionalDouble;

public final class ArrayAverageService implements AverageService {
    @Override
    public OptionalDouble calculateAverage(IntArrayEntity array) {
        if (array == null || array.size() == 0) {
            return OptionalDouble.empty();
        }
        double sum = 0.0;
        for (int index = 0; index < array.size(); index++) {
            sum += array.get(index);
        }
        return OptionalDouble.of(sum / array.size());
    }
}
