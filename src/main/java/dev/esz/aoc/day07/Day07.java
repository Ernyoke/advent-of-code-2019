package dev.esz.aoc.day07;

import dev.esz.aoc.day05.Day05;

import java.util.ArrayList;
import java.util.List;

public class Day07 {
    private static final List<List<Integer>> combinations = new ArrayList<>();

    private static void generateCombinations(int start, int end, List<Integer> current) {
        int n = end - start;
        if (current.size() >= n + 1) {
            combinations.add(new ArrayList<>(current));
        } else {
            for (int i = start; i <= end; i++) {
                if (!current.contains(i)) {
                    current.add(i);
                    generateCombinations(start, end, current);
                    current.remove(current.size() - 1);
                }
            }
        }
    }

    public static int part1(List<Integer> ints, List<Integer> input) {
        int output = 0;
        for (Integer in : input) {
            output = Day05.part2(ints, List.of(in, output));
        }
        return output;
    }

    public static int part1(List<Integer> ints) {
        combinations.clear();
        generateCombinations(0, 4, new ArrayList<>());
        return combinations.stream().map(combination -> {
            int output = 0;
            for (Integer in : combination) {
                output = Day05.part2(ints, List.of(in, output));
            }
            return output;
        }).mapToInt(i -> i).max().orElseThrow();
    }

    public static int part2(List<Integer> ints, List<Integer> combination) {
        Processor[] processors = new Processor[5];
        List<List<Integer>> inputs = new ArrayList<>();
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new Processor(ints);
        }

        int output = 0;
        for (int j = 0; j < processors.length; j++) {
            inputs.add(new ArrayList<>(List.of(combination.get(j))));
        }
        do {
            inputs.get(0).add(output);
            for (int j = 0; j < processors.length; j++) {
                processors[j].execute(inputs.get(j));
                output = processors[j].getOutput();
                int nextProc = j + 1;
                if (nextProc < processors.length) {
                    inputs.get(nextProc).add(output);
                }
            }
        } while (processors[4].getStatus() != Processor.Status.FINISHED);

        return output;
    }

    public static int part2(List<Integer> ints) {
        combinations.clear();
        generateCombinations(5, 9, new ArrayList<>());
        return combinations.stream().map(combination -> {
            Processor[] processors = new Processor[5];
            List<List<Integer>> inputs = new ArrayList<>();
            for (int i = 0; i < processors.length; i++) {
                processors[i] = new Processor(ints);
            }

            int output = 0;
            for (int j = 0; j < processors.length; j++) {
                inputs.add(new ArrayList<>(List.of(combination.get(j))));
            }
            do {
                inputs.get(0).add(output);
                for (int j = 0; j < processors.length; j++) {
                    processors[j].execute(inputs.get(j));
                    output = processors[j].getOutput();
                    int nextProc = j + 1;
                    if (nextProc < processors.length) {
                        inputs.get(nextProc).add(output);
                    }
                }
            } while (processors[4].getStatus() != Processor.Status.FINISHED);

            return output;
        }).mapToInt(i -> i).max().orElseThrow();
    }
}
