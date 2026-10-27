package edu.example.arrays.service.impl;

import edu.example.arrays.entity.IntArrayEntity;
import edu.example.arrays.service.ExtremumService;
import java.util.OptionalInt;

public final class ArrayExtremumService implements ExtremumService {
    @Override
    public OptionalInt findMin(IntArrayEntity array) {
        if (array == null || array.size() == 0) {
            return OptionalInt.empty();
        }
        int min = array.get(0);
        for (int index = 1; index < array.size(); index++) {
            if (array.get(index) < min) {
                min = array.get(index);
            }
        }
        return OptionalInt.of(min);
    }

    @Override
    public OptionalInt findMax(IntArrayEntity array) {
        if (array == null || array.size() == 0) {
            return OptionalInt.empty();
        }
        int max = array.get(0);
        for (int index = 1; index < array.size(); index++) {
            if (array.get(index) > max) {
                max = array.get(index);
            }
        }
        return OptionalInt.of(max);
    }
}
