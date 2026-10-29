package com.innowise.array.factory;

import com.innowise.array.entity.IntArray;
import com.innowise.array.exception.InvalidArrayException;

public interface ArrayFactory {
    IntArray create(int[] values) throws InvalidArrayException;
}
