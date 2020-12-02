package io.karon.adventofcode.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputReaderHelper {
    public static List<String> getInputAllLines(final int day) throws IOException {
        final String filePath = String.format("src/main/resources/twenty/Day%d.txt", day);
        return Files.readAllLines(Paths.get(filePath));
    }

    public static int[] formatInputAsIntegerArray(final List<String> inputAllLines) {
        int[] formattedInput = new int[inputAllLines.size()];

        for (int i = 0; i < inputAllLines.size(); ++i) {
            formattedInput[i] = Integer.parseInt(inputAllLines.get(i));
        }

        return formattedInput;
    }
}
