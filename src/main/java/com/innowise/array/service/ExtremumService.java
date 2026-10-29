package com.innowise.array.service;

import com.innowise.array.entity.IntArray;
import java.util.OptionalInt;

public interface ExtremumService {
    OptionalInt findMin(IntArray array);

    OptionalInt findMax(IntArray array);
}
