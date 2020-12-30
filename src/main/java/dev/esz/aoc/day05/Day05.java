package dev.esz.aoc.day05;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Arrays;
import java.util.List;

public interface Day05 {
    static int part1(List<Integer> numbers, int input) {
        return getExecutionResultPart1(numbers.stream().mapToInt(value -> value).toArray(), input);
    }

    static int part2(List<Integer> numbers, List<Integer> input) {
        return getExecutionResultPart2(numbers.stream().mapToInt(value -> value).toArray(), input);
    }

    static int getExecutionResultPart1(int[] ints, int input) {
        int ip = 0;
        int output = 0;
        Instruction instruction = Instruction.fromValue(ints[ip]);
        while (instruction.getOpCode() != OpCode.HALT) {
            int a, b, destination;
            switch (instruction.getOpCode()) {
                case ADD:
                    a = instruction.getFirstParamMode() == Mode.POSITION ? ints[ints[ip + 1]] : ints[ip + 1];
                    b = instruction.getSecondParamMode() == Mode.POSITION ? ints[ints[ip + 2]] : ints[ip + 2];
                    destination = ints[ip + 3];
                    ints[destination] = a + b;
                    break;
                case MULTIPLY:
                    a = instruction.getFirstParamMode() == Mode.POSITION ? ints[ints[ip + 1]] : ints[ip + 1];
                    b = instruction.getSecondParamMode() == Mode.POSITION ? ints[ints[ip + 2]] : ints[ip + 2];
                    destination = ints[ip + 3];
                    ints[destination] = a * b;
                    break;
                case IN:
                    destination = instruction.getThirdParamMode() == Mode.POSITION ? ints[ip + 1] : ints[ip + 1];
                    ints[destination] = input;
                    break;
                case OUT:
                    destination = ints[ip + 1];
                    output = ints[destination];
                    break;
                case HALT:
                    break;
            }
            ip += instruction.getOpCode().getPcIncrementValue();
            instruction = Instruction.fromValue(ints[ip]);
        }
        return output;
    }

    static int getExecutionResultPart2(int[] ints, List<Integer> input) {
        int ip = 0;
        int output = 0;
        int inputIndex = 0;
        Instruction instruction = Instruction.fromValue(ints[ip]);
        while (instruction.getOpCode() != OpCode.HALT) {
            int a, b, c, destination;
            switch (instruction.getOpCode()) {
                case ADD:
                    a = instruction.getFirstParamMode() == Mode.POSITION ? ints[ints[ip + 1]] : ints[ip + 1];
                    b = instruction.getSecondParamMode() == Mode.POSITION ? ints[ints[ip + 2]] : ints[ip + 2];
                    destination = ints[ip + 3];
                    ints[destination] = a + b;
                    ip += instruction.getOpCode().getPcIncrementValue();
                    break;
                case MULTIPLY:
                    a = instruction.getFirstParamMode() == Mode.POSITION ? ints[ints[ip + 1]] : ints[ip + 1];
                    b = instruction.getSecondParamMode() == Mode.POSITION ? ints[ints[ip + 2]] : ints[ip + 2];
                    destination = ints[ip + 3];
                    ints[destination] = a * b;
                    ip += instruction.getOpCode().getPcIncrementValue();
                    break;
                case IN:
                    destination = instruction.getThirdParamMode() == Mode.POSITION ? ints[ip + 1] : ints[ip + 1];
                    ints[destination] = input.get(inputIndex++);
                    ip += instruction.getOpCode().getPcIncrementValue();
                    break;
                case OUT:
                    output = instruction.getFirstParamMode() == Mode.POSITION ? ints[ints[ip + 1]] : ints[ip + 1];
                    ip += instruction.getOpCode().getPcIncrementValue();
                    break;
                case JUMP_IF_TRUE:
                    a = instruction.getFirstParamMode() == Mode.POSITION ? ints[ints[ip + 1]] : ints[ip + 1];
                    b = instruction.getSecondParamMode() == Mode.POSITION ? ints[ints[ip + 2]] : ints[ip + 2];
                    if (a != 0) {
                        ip = b;
                    } else {
                        ip += instruction.getOpCode().getPcIncrementValue();
                    }
                    break;
                case JUMP_IF_FALSE:
                    a = instruction.getFirstParamMode() == Mode.POSITION ? ints[ints[ip + 1]] : ints[ip + 1];
                    b = instruction.getSecondParamMode() == Mode.POSITION ? ints[ints[ip + 2]] : ints[ip + 2];
                    if (a == 0) {
                        ip = b;
                    } else {
                        ip += instruction.getOpCode().getPcIncrementValue();
                    }
                    break;
                case LESS_THEN:
                    a = instruction.getFirstParamMode() == Mode.POSITION ? ints[ints[ip + 1]] : ints[ip + 1];
                    b = instruction.getSecondParamMode() == Mode.POSITION ? ints[ints[ip + 2]] : ints[ip + 2];
                    c = ints[ip + 3];
                    if (a < b) {
                        ints[c] = 1;
                    } else {
                        ints[c] = 0;
                    }
                    ip += instruction.getOpCode().getPcIncrementValue();
                    break;
                case EQUALS:
                    a = instruction.getFirstParamMode() == Mode.POSITION ? ints[ints[ip + 1]] : ints[ip + 1];
                    b = instruction.getSecondParamMode() == Mode.POSITION ? ints[ints[ip + 2]] : ints[ip + 2];
                    c = ints[ip + 3];
                    if (a == b) {
                        ints[c] = 1;
                    } else {
                        ints[c] = 0;
                    }
                    ip += instruction.getOpCode().getPcIncrementValue();
                    break;
                case HALT:
                    break;
            }
            instruction = Instruction.fromValue(ints[ip]);
        }
        return output;
    }
}

enum OpCode {
    ADD(1, 4),
    MULTIPLY(2, 4),
    HALT(99, 0),
    IN(3, 2),
    OUT(4, 2),
    JUMP_IF_TRUE(5, 3),
    JUMP_IF_FALSE(6, 3),
    LESS_THEN(7, 4),
    EQUALS(8, 4);
    private final int value;
    private final int pcIncrementValue;

    OpCode(int value, int pcIncrementValue) {
        this.value = value;
        this.pcIncrementValue = pcIncrementValue;
    }

    public int getValue() {
        return value;
    }

    public int getPcIncrementValue() {
        return pcIncrementValue;
    }

    public static OpCode fromValue(int value) {
        return Arrays.stream(values())
                .filter(opCode -> opCode.getValue() == value)
                .findFirst()
                .orElseThrow();
    }
}

enum Mode {
    POSITION(0), IMMEDIATE(1);

    private final int value;

    Mode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Mode fromValue(int value) {
        return Arrays.stream(values())
                .filter(mode -> mode.getValue() == value)
                .findFirst()
                .orElseThrow();
    }
}

@Value
class Instruction {
    OpCode opCode;
    Mode firstParamMode;
    Mode secondParamMode;
    Mode thirdParamMode;

    public static Instruction fromValue(int value) {
        OpCode opCode = OpCode.fromValue(value % 100);

        value /= 100;
        Mode firstParamMode = Mode.fromValue(value % 10);

        value /= 10;
        Mode secondParamMode = Mode.fromValue(value % 10);

        value /= 10;
        Mode thirdParamMode = Mode.fromValue(value % 10);

        return new Instruction(opCode, firstParamMode, secondParamMode, thirdParamMode);
    }
}
