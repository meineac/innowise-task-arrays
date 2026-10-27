package edu.example.arrays.service.impl;

import edu.example.arrays.entity.IntArrayEntity;
import edu.example.arrays.service.SumService;
import java.util.OptionalInt;

public final class ArraySumService implements SumService {
    @Override
    public OptionalInt calculateSum(IntArrayEntity array) {
        if (array == null || array.size() == 0) {
            return OptionalInt.empty();
        }
        int sum = 0;
        for (int index = 0; index < array.size(); index++) {
            sum = Math.addExact(sum, array.get(index));
        }
        return OptionalInt.of(sum);
    }
}
