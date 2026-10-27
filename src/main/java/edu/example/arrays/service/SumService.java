package edu.example.arrays.service;

import edu.example.arrays.entity.IntArrayEntity;
import java.util.OptionalInt;

public interface SumService {
    OptionalInt calculateSum(IntArrayEntity array);
}
