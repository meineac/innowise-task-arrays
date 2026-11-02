package com.innowise.array.factory.impl;

import com.innowise.array.entity.IntArray;
import com.innowise.array.exception.InvalidArrayException;
import com.innowise.array.factory.ArrayFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ArrayFactoryImpl implements ArrayFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public IntArray create(int[] values) {
        if (values == null) {
            LOGGER.error("Cannot create IntArray: source array is null");
            throw new InvalidArrayException("Source array must not be null");
        }
        IntArray array = new IntArray(values);
        LOGGER.info("Created IntArray: {}", values);
        return array;
    }
}
