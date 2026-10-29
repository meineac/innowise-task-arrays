package com.innowise.array;

import com.innowise.array.builder.IntArrayBuilder;
import com.innowise.array.entity.IntArrayEntity;
import com.innowise.array.exception.InvalidArrayException;
import com.innowise.array.parser.IntArrayParser;
import com.innowise.array.service.impl.ArrayAverageService;
import com.innowise.array.service.impl.ArrayExtremumService;
import com.innowise.array.service.impl.ArraySortingService;
import com.innowise.array.service.impl.ArraySumService;
import com.innowise.array.validation.IntArrayValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArrayServicesTest {
    private IntArrayParser parser;
    private IntArrayValidator validator;
    private ArrayExtremumService extremumService;
    private ArraySumService sumService;
    private ArrayAverageService averageService;
    private ArraySortingService sortingService;

    @BeforeEach
    void setUp() {
        parser = new IntArrayParser();
        validator = new IntArrayValidator(parser);
        extremumService = new ArrayExtremumService();
        sumService = new ArraySumService();
        averageService = new ArrayAverageService();
        sortingService = new ArraySortingService();
    }

    @Test
    void shouldParseValidInput() {
        assertArrayEquals(new int[] {1, 2, 3}, parser.parse("1; 2; 3"));
        assertArrayEquals(new int[] {1, 2, 3}, parser.parse("1 - 2 – 3"));
        assertArrayEquals(new int[] {3, 4, 7}, parser.parse("3 4 7"));
    }

    @Test
    void shouldRejectInvalidInput() {
        assertFalse(validator.isValid("1, 2, x3"));
        assertFalse(validator.isValid(""));
        assertFalse(validator.isValid("1, 6..5, 77"));
        assertFalse(validator.isValid("2147483648"));
        assertFalse(validator.isValid("11- 2 – 42-"));
    }

    @Test
    void shouldSupportNegativeNumbers() {
        assertArrayEquals(new int[] {-1, -2, 3}, parser.parse("-1, -2, 3"));
    }

    @Test
    void shouldFindMinimumAndMaximum() throws InvalidArrayException {
        IntArrayEntity array = new IntArrayBuilder()
                .addAll(new int[] {8, -4, 12, 2})
                .build();

        assertEquals(-4, extremumService.findMin(array).orElseThrow());
        assertEquals(12, extremumService.findMax(array).orElseThrow());
    }

    @Test
    void shouldCalculateSumAndAverage() throws InvalidArrayException {
        IntArrayEntity array = new IntArrayBuilder()
                .addAll(new int[] {2, 4, 6})
                .build();

        assertEquals(12, sumService.calculateSum(array).orElseThrow());
        assertEquals(4.0, averageService.calculateAverage(array).orElseThrow(), 0.0001);
    }

    @Test
    void shouldSortUsingBothAlgorithms() throws InvalidArrayException {
        IntArrayEntity array = new IntArrayBuilder()
                .addAll(new int[] {5, -1, 8, 2, 2})
                .build();

        int[] expected = {-1, 2, 2, 5, 8};
        assertArrayEquals(expected, sortingService.sortByInsertion(array));
        assertArrayEquals(expected, sortingService.sortByQuickSort(array));
        assertArrayEquals(new int[] {5, -1, 8, 2, 2}, array.toArray());
    }

    @Test
    void shouldRejectEmptyArrayDuringBuilding() {
        assertThrows(InvalidArrayException.class, () -> new IntArrayBuilder().build());
    }

    @Test
    void shouldDetectIntegerOverflowDuringSum() throws InvalidArrayException {
        IntArrayEntity array = new IntArrayBuilder()
                .addAll(new int[] {Integer.MAX_VALUE, 1})
                .build();

        org.junit.jupiter.api.Assertions.assertThrows(
                ArithmeticException.class, () -> sumService.calculateSum(array));
    }
}
