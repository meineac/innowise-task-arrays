package edu.example.arrays.service;

import edu.example.arrays.entity.IntArrayEntity;
import java.util.OptionalDouble;

public interface AverageService {
    OptionalDouble calculateAverage(IntArrayEntity array);
}
