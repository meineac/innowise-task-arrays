package edu.example.arrays.application;

import edu.example.arrays.factory.IntArrayFactory;
import edu.example.arrays.parser.IntArrayParser;
import edu.example.arrays.reader.TextArrayFileReader;
import edu.example.arrays.service.impl.ArrayAverageService;
import edu.example.arrays.service.impl.ArrayExtremumService;
import edu.example.arrays.service.impl.ArraySortingService;
import edu.example.arrays.service.impl.ArraySumService;
import edu.example.arrays.validation.IntArrayValidator;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ApplicationLauncher {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationLauncher.class);

    public void launch() {
        IntArrayParser parser = new IntArrayParser();
        ArrayApplication application = new ArrayApplication(
                new TextArrayFileReader(),
                new IntArrayValidator(parser),
                parser,
                new IntArrayFactory(),
                new ArrayExtremumService(),
                new ArraySumService(),
                new ArrayAverageService(),
                new ArraySortingService());

        try {
            application.run(Path.of("data", "arrays.txt"));
        } catch (IOException exception) {
            LOGGER.error("Failed to read input file", exception);
        }
    }

    public static void main(String[] args) {
        new ApplicationLauncher().launch();
    }
}
