package io.karon.adventofcode.twenty;

import io.karon.adventofcode.utils.InputReaderHelper;

import java.io.IOException;
import java.util.List;

public class Day1 {
    static class Star1 {
        static int getResult() throws IOException {
            final List<String> inputAllLines = InputReaderHelper.getInputAllLines(1);
            final int[] inputAsIntegers = InputReaderHelper.formatInputAsIntegerArray(inputAllLines);

            for (int i = 0; i < inputAsIntegers.length; ++i) {
                for (int j = i + 1; j < inputAsIntegers.length; ++j) {
                    if (inputAsIntegers[i] + inputAsIntegers[j] == 2020) {
                        return inputAsIntegers[i] * inputAsIntegers[j];
                    }
                }
            }

            return -1;
        }
    }

    static class Star2 {
        static int getResult() throws IOException {
            final List<String> inputAllLines = InputReaderHelper.getInputAllLines(1);
            final int[] inputAsIntegers = InputReaderHelper.formatInputAsIntegerArray(inputAllLines);

            for (int i = 0; i < inputAsIntegers.length; ++i) {
                for (int j = i + 1; j < inputAsIntegers.length; ++j) {
                    for (int k = j + 1; k < inputAsIntegers.length; ++k) {
                        if (inputAsIntegers[i] + inputAsIntegers[j] + inputAsIntegers[k] == 2020) {
                            return inputAsIntegers[i] * inputAsIntegers[j] * inputAsIntegers[k];
                        }
                    }
                }
            }

            return -1;
        }
    }
}
