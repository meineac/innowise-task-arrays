package edu.example.arrays.application;

import edu.example.arrays.entity.IntArrayEntity;
import edu.example.arrays.exception.InvalidArrayException;
import edu.example.arrays.factory.ArrayFactory;
import edu.example.arrays.parser.IntArrayParser;
import edu.example.arrays.reader.ArrayFileReader;
import edu.example.arrays.service.AverageService;
import edu.example.arrays.service.ExtremumService;
import edu.example.arrays.service.SortingService;
import edu.example.arrays.service.SumService;
import edu.example.arrays.validation.ArrayValidator;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ArrayApplication {
    private static final Logger LOGGER = LogManager.getLogger(ArrayApplication.class);

    private final ArrayFileReader fileReader;
    private final ArrayValidator validator;
    private final IntArrayParser parser;
    private final ArrayFactory factory;
    private final ExtremumService extremumService;
    private final SumService sumService;
    private final AverageService averageService;
    private final SortingService sortingService;

    public ArrayApplication(
            ArrayFileReader fileReader,
            ArrayValidator validator,
            IntArrayParser parser,
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
                IntArrayEntity array = factory.create(values);
                LOGGER.info("Input array: {}", Arrays.toString(array.toArray()));
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
