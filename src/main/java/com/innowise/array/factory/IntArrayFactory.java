package com.innowise.array.factory;

import com.innowise.array.builder.IntArrayBuilder;
import com.innowise.array.entity.IntArray;
import com.innowise.array.exception.InvalidArrayException;

public final class IntArrayFactory implements ArrayFactory {
    @Override
    public IntArray create(int[] values) throws InvalidArrayException {
        if (values == null || values.length == 0) {
            throw new InvalidArrayException("Source array must not be null or empty");
        }
        IntArrayBuilder builder = new IntArrayBuilder();
        builder.addAll(values);
        return builder.build();
    }
}
