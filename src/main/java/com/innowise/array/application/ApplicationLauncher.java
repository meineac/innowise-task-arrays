package com.innowise.array.application;

import com.innowise.array.factory.IntArrayFactory;
import com.innowise.array.parser.impl.ArrayLineParserImpl;
import com.innowise.array.reader.impl.ArrayFileReaderImpl;
import com.innowise.array.service.impl.ArrayAverageService;
import com.innowise.array.service.impl.ArrayExtremumService;
import com.innowise.array.service.impl.ArraySortingService;
import com.innowise.array.service.impl.ArraySumService;
import com.innowise.array.validation.impl.ArrayLineValidatorImpl;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ApplicationLauncher {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationLauncher.class);

    public void launch() {
        ArrayLineParserImpl parser = new ArrayLineParserImpl();
        ArrayApplication application = new ArrayApplication(
                new ArrayFileReaderImpl(),
                new ArrayLineValidatorImpl(),
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
