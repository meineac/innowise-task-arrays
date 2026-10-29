package com.innowise.array.factory;

import com.innowise.array.entity.IntArrayEntity;
import com.innowise.array.exception.InvalidArrayException;

public interface ArrayFactory {
    IntArrayEntity create(int[] values) throws InvalidArrayException;
}
