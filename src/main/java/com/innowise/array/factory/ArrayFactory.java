package com.innowise.array.factory;

import com.innowise.array.entity.IntArray;

public interface ArrayFactory {
    IntArray create(int[] values);
}
