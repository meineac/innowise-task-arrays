package com.innowise.array.reader;

import java.nio.file.Path;
import java.util.List;

public interface ArrayFileReader {
    List<String> read(Path path);
}
