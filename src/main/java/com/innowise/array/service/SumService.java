package com.innowise.array.service;

import com.innowise.array.entity.IntArrayEntity;
import java.util.OptionalInt;

public interface SumService {
    OptionalInt calculateSum(IntArrayEntity array);
}
