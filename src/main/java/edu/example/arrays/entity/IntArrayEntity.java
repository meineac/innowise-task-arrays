package edu.example.arrays.entity;

import java.util.Arrays;

public final class IntArrayEntity {
    private final int[] values;

    public IntArrayEntity(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Array must not be null");
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
}
