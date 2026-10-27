package edu.example.arrays.entity;

import edu.example.arrays.exception.InvalidArrayException;

import java.util.Arrays;

public final class IntArrayEntity {
    private static final String NULL_ARRAY_MESSAGE = "Array must not be null";

    private final int[] values;

    public IntArrayEntity(int[] values) {
        if (values == null) {
            throw new InvalidArrayException(NULL_ARRAY_MESSAGE);
        }
        this.values = Arrays.copyOf(values, values.length);
    }

    public int size() {
        return values.length;
    }

    public int get(int index) {
        return values[index];
    }

    public int[] toArray() {
        return Arrays.copyOf(values, values.length);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof IntArrayEntity that) {
            return Arrays.equals(values, that.values);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }

    @Override
    public String toString() {
        String valuesText = Arrays.toString(values);
        return "IntArrayEntity{values=" + valuesText + "}";
    }
}
