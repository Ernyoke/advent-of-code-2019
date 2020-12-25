package dev.esz.aoc.day07;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

public class Processor {
    public enum Status {
        WAITING_INPUT, FINISHED, RUNNING, PAUSED
    }

    private final int[] ints;

    @Getter
    private int output;

    @Getter
    private Status status;

    private int ip = 0;

    public Processor(List<Integer> ints) {
        this.ints = ints.stream().mapToInt(value -> value).toArray();
    }

    public void execute(List<Integer> input) {
        status = Status.RUNNING;
        Instruction instruction = Instruction.fromValue(ints[ip]);
        while (instruction.getOpCode() != OpCode.HALT && status == Status.RUNNING) {
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
                    if (input.size() > 0) {
                        destination = instruction.getThirdParamMode() == Mode.POSITION ? ints[ip + 1] : ints[ip + 1];
                        ints[destination] = input.get(0);
                        input.remove(0);
                        ip += instruction.getOpCode().getPcIncrementValue();
                    } else {
                        status = Status.WAITING_INPUT;
                    }
                    break;
                case OUT:
                    output = instruction.getFirstParamMode() == Mode.POSITION ? ints[ints[ip + 1]] : ints[ip + 1];
                    ip += instruction.getOpCode().getPcIncrementValue();
                    status = Status.PAUSED;
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
            if (instruction.getOpCode() == OpCode.HALT) {
                status = Status.FINISHED;
            }
        }
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

@RequiredArgsConstructor
@Getter
class Instruction {
    private final OpCode opCode;
    private final Mode firstParamMode;
    private final Mode secondParamMode;
    private final Mode thirdParamMode;

    public static Instruction fromValue(long lvalue) {
        int value = (int) lvalue;
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
