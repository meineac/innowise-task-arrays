package com.innowise.array.service;

import com.innowise.array.entity.IntArrayEntity;
import java.util.OptionalInt;

public interface ExtremumService {
    OptionalInt findMin(IntArrayEntity array);

    OptionalInt findMax(IntArrayEntity array);
}
