package com.innowise.array.reader.impl;

import com.innowise.array.exception.InvalidArrayException;
import com.innowise.array.reader.ArrayFileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class ArrayFileReaderImpl implements ArrayFileReader {
    @Override
    public List<String> read(Path path) {
        if (path == null || path.isAbsolute()) {
            throw new InvalidArrayException("Path must not be null or absolute");
        }

        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new InvalidArrayException("Cannot read file: " + path, e);
        }
    }
}
