package com.innowise.array.entity;

import com.innowise.array.exception.InvalidArrayException;

import java.util.Arrays;

public final class IntArray {
    private final int[] values;

    public IntArray(int[] values) {
        if (values == null) {
            throw new InvalidArrayException("Array must not be null");
        }
        this.values = Arrays.copyOf(values, values.length);
    }

    public int size() {
        return values.length;
    }

    public int getValue(int index) {
        if (index < 0 || index >= values.length) {
            throw new InvalidArrayException("Array index out of bounds: " + index);
        }
        return values[index];
    }

    public int[] getValues() {
        return Arrays.copyOf(values, values.length);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof IntArray that) {
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
        return "IntArray{values=" + valuesText + "}";
    }
}
