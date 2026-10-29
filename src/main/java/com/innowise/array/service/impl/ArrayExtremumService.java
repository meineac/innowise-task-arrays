package com.innowise.array.service.impl;

import com.innowise.array.entity.IntArray;
import com.innowise.array.service.ExtremumService;
import java.util.OptionalInt;

public final class ArrayExtremumService implements ExtremumService {
    @Override
    public OptionalInt findMin(IntArray array) {
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
    public OptionalInt findMax(IntArray array) {
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
