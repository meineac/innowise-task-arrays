package com.innowise.array.builder;

import com.innowise.array.entity.IntArrayEntity;
import com.innowise.array.exception.InvalidArrayException;
import java.util.ArrayList;
import java.util.List;

public final class IntArrayBuilder {
    private final List<Integer> values = new ArrayList<>();

    public IntArrayBuilder add(int value) {
        values.add(value);
        return this;
    }

    public IntArrayBuilder addAll(int[] source) throws InvalidArrayException {
        if (source == null) {
            throw new InvalidArrayException("Source array must not be null");
        }
        for (int value : source) {
            values.add(value);
        }
        return this;
    }

    public IntArrayEntity build() throws InvalidArrayException {
        if (values.isEmpty()) {
            throw new InvalidArrayException("An array must contain at least one element");
        }

        int[] result = new int[values.size()];
        for (int index = 0; index < values.size(); index++) {
            result[index] = values.get(index);
        }
        return new IntArrayEntity(result);
    }
}
