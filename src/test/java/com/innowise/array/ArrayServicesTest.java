package com.innowise.array;

import com.innowise.array.entity.IntArray;
import com.innowise.array.exception.InvalidArrayException;
import com.innowise.array.factory.ArrayFactory;
import com.innowise.array.factory.impl.ArrayFactoryImpl;
import com.innowise.array.parser.ArrayLineParser;
import com.innowise.array.parser.impl.ArrayLineParserImpl;
import com.innowise.array.reader.ArrayFileReader;
import com.innowise.array.reader.impl.ArrayFileReaderImpl;
import com.innowise.array.service.AverageService;
import com.innowise.array.service.ExtremumService;
import com.innowise.array.service.SortingService;
import com.innowise.array.service.SumService;
import com.innowise.array.service.impl.AverageServiceImpl;
import com.innowise.array.service.impl.ExtremumServiceImpl;
import com.innowise.array.service.impl.InsertionSortingService;
import com.innowise.array.service.impl.QuickSortingService;
import com.innowise.array.service.impl.SumServiceImpl;
import com.innowise.array.validation.ArrayLineValidator;
import com.innowise.array.validation.impl.ArrayLineValidatorImpl;
import java.nio.file.Path;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayServicesTest {
    private static final IntArray SAMPLE_ARRAY = new IntArray(new int[] {5, -2, 10, 0, 4});
    private static final IntArray SORTED_SAMPLE_ARRAY = new IntArray(new int[] {-2, 0, 4, 5, 10});
    private static final IntArray AVERAGE_ARRAY = new IntArray(new int[] {2, 4, 6});
    private static final IntArray EMPTY_ARRAY = new IntArray(new int[0]);
    private static final IntArray PARSED_ENTITY_ARRAY = new IntArray(new int[] {-1, 2, 3});

    private static final int[] RAW_VALUES = {-1, 2, 3};
    private static final String VALID_LINE = " -1 , 2 , 3 ";
    private static final String EMPTY_LINE = "";
    private static final String BLANK_LINE = "   ";
    private static final String INVALID_TOKEN_LINE = "1x , 2 , 4";
    private static final String TRAILING_COMMA_LINE = "1, 1 ,";
    private static final String LEADING_COMMA_LINE = ",1 , 3";
    private static final String OVERFLOW_NUMBER_LINE = "2147483648, 1";

    private static final Path VALID_DATA_PATH = Path.of("data", "arrays.txt");
    private static final Path MISSING_DATA_PATH = Path.of("data", "missing.txt");
    private static final List<String> EXPECTED_FILE_LINES = List.of(
            "1,2,3,4,5",
            "",
            "1,2,3",
            "1x , 2 , 4",
            " 1 , 2 , 3",
            "1, 1 ,",
            ",1 , 3",
            ""
    );

    private ExtremumService extremumService;
    private SumService sumService;
    private AverageService averageService;
    private SortingService insertionSortingService;
    private SortingService quickSortingService;
    private ArrayLineValidator validator;
    private ArrayLineParser parser;
    private ArrayFileReader fileReader;
    private ArrayFactory factory;

    @BeforeEach
    void setUp() {
        extremumService = new ExtremumServiceImpl();
        sumService = new SumServiceImpl();
        averageService = new AverageServiceImpl();
        insertionSortingService = new InsertionSortingService();
        quickSortingService = new QuickSortingService();
        validator = new ArrayLineValidatorImpl();
        parser = new ArrayLineParserImpl();
        fileReader = new ArrayFileReaderImpl();
        factory = new ArrayFactoryImpl();
    }

    @Test
    void shouldFindMinimumValue() {
        // Given
        OptionalInt expected = OptionalInt.of(-2);

        // When
        OptionalInt actual = extremumService.findMin(SAMPLE_ARRAY);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyMinimumWhenArrayIsEmpty() {
        // Given
        OptionalInt expected = OptionalInt.empty();

        // When
        OptionalInt actual = extremumService.findMin(EMPTY_ARRAY);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyMinimumWhenArrayIsNull() {
        // Given
        OptionalInt expected = OptionalInt.empty();

        // When
        OptionalInt actual = extremumService.findMin(null);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindMaximumValue() {
        // Given
        OptionalInt expected = OptionalInt.of(10);

        // When
        OptionalInt actual = extremumService.findMax(SAMPLE_ARRAY);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyMaximumWhenArrayIsEmpty() {
        // Given
        OptionalInt expected = OptionalInt.empty();

        // When
        OptionalInt actual = extremumService.findMax(EMPTY_ARRAY);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyMaximumWhenArrayIsNull() {
        // Given
        OptionalInt expected = OptionalInt.empty();

        // When
        OptionalInt actual = extremumService.findMax(null);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateSum() {
        // Given
        OptionalLong expected = OptionalLong.of(17L);

        // When
        OptionalLong actual = sumService.calculateSum(SAMPLE_ARRAY);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptySumWhenArrayIsEmpty() {
        // Given
        OptionalLong expected = OptionalLong.empty();

        // When
        OptionalLong actual = sumService.calculateSum(EMPTY_ARRAY);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptySumWhenArrayIsNull() {
        // Given
        OptionalLong expected = OptionalLong.empty();

        // When
        OptionalLong actual = sumService.calculateSum(null);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateAverage() {
        // Given
        OptionalDouble expected = OptionalDouble.of(4.0);

        // When
        OptionalDouble actual = averageService.calculateAverage(AVERAGE_ARRAY);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyAverageWhenArrayIsEmpty() {
        // Given
        OptionalDouble expected = OptionalDouble.empty();

        // When
        OptionalDouble actual = averageService.calculateAverage(EMPTY_ARRAY);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyAverageWhenArrayIsNull() {
        // Given
        OptionalDouble expected = OptionalDouble.empty();

        // When
        OptionalDouble actual = averageService.calculateAverage(null);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortArrayUsingInsertionSort() {
        // Given
        IntArray expected = SORTED_SAMPLE_ARRAY;

        // When
        IntArray actual = insertionSortingService.sort(SAMPLE_ARRAY);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortEmptyArrayUsingInsertionSort() {
        // Given
        IntArray expected = EMPTY_ARRAY;

        // When
        IntArray actual = insertionSortingService.sort(EMPTY_ARRAY);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWhenInsertionSortInputIsNull() {
        // Given
        Class<InvalidArrayException> expectedType = InvalidArrayException.class;

        // When
        Executable action = () -> insertionSortingService.sort(null);

        // Then
        assertThrows(expectedType, action);
    }

    @Test
    void shouldSortArrayUsingQuickSort() {
        // Given
        IntArray expected = SORTED_SAMPLE_ARRAY;

        // When
        IntArray actual = quickSortingService.sort(SAMPLE_ARRAY);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldSortEmptyArrayUsingQuickSort() {
        // Given
        IntArray expected = EMPTY_ARRAY;

        // When
        IntArray actual = quickSortingService.sort(EMPTY_ARRAY);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWhenQuickSortInputIsNull() {
        // Given
        Class<InvalidArrayException> expectedType = InvalidArrayException.class;

        // When
        Executable action = () -> quickSortingService.sort(null);

        // Then
        assertThrows(expectedType, action);
    }

    @Test
    void shouldValidateCorrectLine() {
        // Given
        String input = VALID_LINE;

        // When
        boolean isValid = validator.isValid(input);

        // Then
        assertTrue(isValid);
    }

    @Test
    void shouldRejectEmptyLine() {
        // Given
        String input = EMPTY_LINE;

        // When
        boolean isValid = validator.isValid(input);

        // Then
        assertFalse(isValid);
    }

    @Test
    void shouldRejectBlankLine() {
        // Given
        String input = BLANK_LINE;

        // When
        boolean isValid = validator.isValid(input);

        // Then
        assertFalse(isValid);
    }

    @Test
    void shouldRejectNullLine() {
        // Given
        String input = null;

        // When
        boolean isValid = validator.isValid(input);

        // Then
        assertFalse(isValid);
    }

    @Test
    void shouldRejectLineWithInvalidToken() {
        // Given
        String input = INVALID_TOKEN_LINE;

        // When
        boolean isValid = validator.isValid(input);

        // Then
        assertFalse(isValid);
    }

    @Test
    void shouldRejectLineWithTrailingComma() {
        // Given
        String input = TRAILING_COMMA_LINE;

        // When
        boolean isValid = validator.isValid(input);

        // Then
        assertFalse(isValid);
    }

    @Test
    void shouldRejectLineWithLeadingComma() {
        // Given
        String input = LEADING_COMMA_LINE;

        // When
        boolean isValid = validator.isValid(input);

        // Then
        assertFalse(isValid);
    }

    @Test
    void shouldParseValidLine() {
        // Given
        int[] expected = RAW_VALUES;

        // When
        int[] actual = parser.parse(VALID_LINE);

        // Then
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWhenParsingEmptyLine() {
        // Given
        Class<InvalidArrayException> expectedType = InvalidArrayException.class;

        // When
        Executable action = () -> parser.parse(EMPTY_LINE);

        // Then
        assertThrows(expectedType, action);
    }

    @Test
    void shouldThrowExceptionWhenParsingInvalidLine() {
        // Given
        Class<InvalidArrayException> expectedType = InvalidArrayException.class;

        // When
        Executable action = () -> parser.parse(INVALID_TOKEN_LINE);

        // Then
        assertThrows(expectedType, action);
    }

    @Test
    void shouldThrowExceptionWhenParsingOutOfRangeNumber() {
        // Given
        Class<InvalidArrayException> expectedType = InvalidArrayException.class;

        // When
        Executable action = () -> parser.parse(OVERFLOW_NUMBER_LINE);

        // Then
        assertThrows(expectedType, action);
    }

    @Test
    void shouldReadLinesFromFile() {
        // Given
        List<String> expected = EXPECTED_FILE_LINES;

        // When
        List<String> actual = fileReader.read(VALID_DATA_PATH);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWhenFileDoesNotExist() {
        // Given
        Class<InvalidArrayException> expectedType = InvalidArrayException.class;

        // When
        Executable action = () -> fileReader.read(MISSING_DATA_PATH);

        // Then
        assertThrows(expectedType, action);
    }

    @Test
    void shouldThrowExceptionWhenFilePathIsNull() {
        // Given
        Class<InvalidArrayException> expectedType = InvalidArrayException.class;

        // When
        Executable action = () -> fileReader.read(null);

        // Then
        assertThrows(expectedType, action);
    }

    @Test
    void shouldCreateArrayUsingFactory() {
        // Given
        IntArray expected = PARSED_ENTITY_ARRAY;

        // When
        IntArray actual = factory.create(RAW_VALUES);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWhenFactoryInputIsNull() {
        // Given
        Class<InvalidArrayException> expectedType = InvalidArrayException.class;

        // When
        Executable action = () -> factory.create(null);

        // Then
        assertThrows(expectedType, action);
    }

    @Test
    void shouldReturnElementByValidIndex() {
        // Given
        int expected = 10;

        // When
        int actual = SAMPLE_ARRAY.getValue(2);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWhenIndexIsNegative() {
        // Given
        Class<InvalidArrayException> expectedType = InvalidArrayException.class;

        // When
        Executable action = () -> SAMPLE_ARRAY.getValue(-1);

        // Then
        assertThrows(expectedType, action);
    }

    @Test
    void shouldThrowExceptionWhenIndexIsOutOfBounds() {
        // Given
        Class<InvalidArrayException> expectedType = InvalidArrayException.class;

        // When
        Executable action = () -> SAMPLE_ARRAY.getValue(10);

        // Then
        assertThrows(expectedType, action);
    }
}