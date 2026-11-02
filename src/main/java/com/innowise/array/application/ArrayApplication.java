package com.innowise.array.application;

import com.innowise.array.entity.IntArray;
import com.innowise.array.exception.InvalidArrayException;
import com.innowise.array.factory.ArrayFactory;
import com.innowise.array.parser.impl.ArrayLineParserImpl;
import com.innowise.array.reader.ArrayFileReader;
import com.innowise.array.service.AverageService;
import com.innowise.array.service.ExtremumService;
import com.innowise.array.service.SortingService;
import com.innowise.array.service.SumService;
import com.innowise.array.validation.ArrayLineValidator;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ArrayApplication {
    private static final Logger LOGGER = LogManager.getLogger(ArrayApplication.class);

    private final ArrayFileReader fileReader;
    private final ArrayLineValidator validator;
    private final ArrayLineParserImpl parser;
    private final ArrayFactory factory;
    private final ExtremumService extremumService;
    private final SumService sumService;
    private final AverageService averageService;
    private final SortingService sortingService;

    public ArrayApplication(
            ArrayFileReader fileReader,
            ArrayLineValidator validator,
            ArrayLineParserImpl parser,
            ArrayFactory factory,
            ExtremumService extremumService,
            SumService sumService,
            AverageService averageService,
            SortingService sortingService) {
        this.fileReader = fileReader;
        this.validator = validator;
        this.parser = parser;
        this.factory = factory;
        this.extremumService = extremumService;
        this.sumService = sumService;
        this.averageService = averageService;
        this.sortingService = sortingService;
    }

    public void run(Path inputPath) throws IOException {
        for (String line : fileReader.read(inputPath)) {
            if (!validator.isValid(line)) {
                LOGGER.warn("Invalid input line: '{}'", line);
                continue;
            }
            try {
                int[] values = parser.parse(line);
                IntArray array = factory.create(values);
                LOGGER.info("Input array: {}", Arrays.toString(array.getValues()));
                LOGGER.info("Minimum: {}", extremumService.findMin(array));
                LOGGER.info("Maximum: {}", extremumService.findMax(array));
                LOGGER.info("Sum: {}", sumService.calculateSum(array));
                LOGGER.info("Average: {}", averageService.calculateAverage(array));
                LOGGER.info("Insertion sort: {}",
                        Arrays.toString(sortingService.sortByInsertion(array)));
                LOGGER.info("Quick sort: {}",
                        Arrays.toString(sortingService.sortByQuickSort(array)));
            } catch (NumberFormatException | InvalidArrayException exception) {
                LOGGER.error("Could not process input line: '{}'", line, exception);
            } catch (ArithmeticException exception) {
                LOGGER.error("Arithmetic error for input line: '{}'", line, exception);
            }
        }
    }
}
