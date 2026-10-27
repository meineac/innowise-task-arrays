package edu.example.arrays.service;

import edu.example.arrays.entity.IntArrayEntity;
import java.util.OptionalInt;

public interface ExtremumService {
    OptionalInt findMin(IntArrayEntity array);

    OptionalInt findMax(IntArrayEntity array);
}
