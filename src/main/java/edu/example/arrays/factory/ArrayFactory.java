package edu.example.arrays.factory;

import edu.example.arrays.entity.IntArrayEntity;
import edu.example.arrays.exception.InvalidArrayException;

public interface ArrayFactory {
    IntArrayEntity create(int[] values) throws InvalidArrayException;
}
