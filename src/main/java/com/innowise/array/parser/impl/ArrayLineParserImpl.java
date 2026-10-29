package com.innowise.array.parser.impl;

import com.innowise.array.parser.ArrayLineParser;

import java.util.ArrayList;
import java.util.List;

public final class ArrayLineParserImpl implements ArrayLineParser {
    public int[] parse(String lineOfNumbers) {
        if (lineOfNumbers == null || lineOfNumbers.isBlank()) {
            throw new NumberFormatException("Input line is empty");
        }

        String normalized = lineOfNumbers.trim().replace('\u2013', '-').replace('\u2014', '-');
        normalized = normalized.replaceAll("(?<=\\d)\\s+-\\s+(?=\\d)", " ");
        normalized = normalized.replaceAll("(?<=\\d)-(?=\\d)", " ");

        String[] tokens = normalized.trim().split("[,;\\s]+");
        List<Integer> parsed = new ArrayList<>();

        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }
            parsed.add(Integer.parseInt(token));
        }

        if (parsed.isEmpty()) {
            throw new NumberFormatException("No values found");
        }

        int[] values = new int[parsed.size()];
        for (int index = 0; index < parsed.size(); index++) {
            values[index] = parsed.get(index);
        }
        return values;
    }
}
