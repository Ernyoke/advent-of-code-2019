package dev.esz.aoc.day02;

import java.util.Arrays;
import java.util.List;

public interface Day02 {
    static int part1(List<Integer> numbers) {
        int[] ints = numbers.stream().mapToInt(value -> value).toArray();
        ints[1] = 12;
        ints[2] = 2;

        return getExecutionResult(ints);
    }

    static int part2(List<Integer> numbers) {
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                int[] ints = numbers.stream().mapToInt(value -> value).toArray();
                ints[1] = i;
                ints[2] = j;
                if (getExecutionResult(ints) == 19690720) {
                    return 100 * i + j;
                }
            }
        }
        throw new IllegalStateException();
    }

    static int getExecutionResult(int[] ints) {
        int i = 0;
        OpCode currentOpCode = OpCode.fromValue(ints[i]);
        while (currentOpCode != OpCode.HALT) {
            int a = ints[ints[i + 1]];
            int b = ints[ints[i + 2]];
            int destination = ints[i + 3];
            ints[destination] = currentOpCode.doOperation(a, b);
            currentOpCode = OpCode.fromValue(ints[i += 4]);
        }
        return ints[0];
    }
}

enum OpCode {
    ADD(1) {
        public int doOperation(int a, int b) {
            return a + b;
        }
    }, MULTIPLY(2) {
        public int doOperation(int a, int b) {
            return a * b;
        }
    }, HALT(99) {
        public int doOperation(int a, int b) {
            return 0;
        }
    };

    private final int value;

    OpCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public abstract int doOperation(int a, int b);

    public static OpCode fromValue(int value) {
        return Arrays.stream(values())
                .filter(opCode -> opCode.getValue() == value)
                .findFirst()
                .orElseThrow();
    }
}
