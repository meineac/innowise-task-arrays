package edu.example.arrays.factory;

import edu.example.arrays.builder.IntArrayBuilder;
import edu.example.arrays.entity.IntArrayEntity;
import edu.example.arrays.exception.InvalidArrayException;

public final class IntArrayFactory implements ArrayFactory {
    @Override
    public IntArrayEntity create(int[] values) throws InvalidArrayException {
        if (values == null || values.length == 0) {
            throw new InvalidArrayException("Source array must not be null or empty");
        }
        IntArrayBuilder builder = new IntArrayBuilder();
        builder.addAll(values);
        return builder.build();
    }
}
