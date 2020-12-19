package dev.esz.aoc.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface Utils {
    static List<Integer> readInts(String path) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                numbers.add(Integer.valueOf(line));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return numbers;
    }

    static List<Long> readLongs(String path) {
        List<Long> numbers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                numbers.add(Long.valueOf(line));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return numbers;
    }

    static List<Integer> readSingleLineToInts(String path) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine();
            return Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toUnmodifiableList());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return numbers;
    }

    static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return lines;
    }

    static List<List<String>> processLines(String path, String delimiter) {
        return readLines(path).stream().map(line -> Arrays.asList(line.split(delimiter))).collect(Collectors.toUnmodifiableList());
    }
}
