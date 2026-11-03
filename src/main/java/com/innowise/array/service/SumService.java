package com.innowise.array.service;

import com.innowise.array.entity.IntArray;
import java.util.OptionalLong;

public interface SumService {
    OptionalLong calculateSum(IntArray array);
}
