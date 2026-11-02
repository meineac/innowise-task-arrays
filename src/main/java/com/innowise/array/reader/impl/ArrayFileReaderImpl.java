package com.innowise.array.reader.impl;

import com.innowise.array.exception.InvalidArrayException;
import com.innowise.array.reader.ArrayFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class ArrayFileReaderImpl implements ArrayFileReader {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<String> read(Path path) {
        if (path == null) {
            LOGGER.error("File path is null");
            throw new InvalidArrayException("File path must not be null");
        }

        try {
            List<String> lines = Files.readAllLines(path);
            LOGGER.info("Successfully read {} lines from file: {}", lines.size(), path);
            return lines;
        } catch (IOException exception) {
            LOGGER.error("Failed to read file: {}", path, exception);
            throw new InvalidArrayException("Cannot read file: " + path, exception);
        }
    }
}
